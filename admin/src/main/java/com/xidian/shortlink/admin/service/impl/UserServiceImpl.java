package com.xidian.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xidian.shortlink.admin.common.constant.RedisCacheConstant;
import com.xidian.shortlink.admin.common.convention.exception.ClientException;
import com.xidian.shortlink.admin.common.enums.UserErrorCode;
import com.xidian.shortlink.admin.dao.entity.UserDO;
import com.xidian.shortlink.admin.dao.mapper.UserMapper;
import com.xidian.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.xidian.shortlink.admin.dto.resp.UserRespDTO;
import com.xidian.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;

    @Override
    public UserRespDTO getUserByUserName(String username) {
        LambdaQueryWrapper<UserDO> querryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(querryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCode.USER_NULL);
        }
        UserRespDTO userRespDTO = new UserRespDTO();
        BeanUtils.copyProperties(userDO, userRespDTO);
        return userRespDTO;
    }

    @Override
    public Boolean hasUsername(String username){
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO userReqParam) {
        if(hasUsername(userReqParam.getUsername())){
            throw new ClientException(UserErrorCode.USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY + userReqParam.getUsername());
        try {
            if(lock.tryLock()){
                int inserted = baseMapper.insert(BeanUtil.toBean(userReqParam, UserDO.class));
                if(inserted < 1){
                    throw new ClientException(UserErrorCode.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(userReqParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCode.USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }

    }

}
