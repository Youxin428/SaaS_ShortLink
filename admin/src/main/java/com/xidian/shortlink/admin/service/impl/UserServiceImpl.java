package com.xidian.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xidian.shortlink.admin.common.convention.exception.ClientException;
import com.xidian.shortlink.admin.common.enums.UserErrorCode;
import com.xidian.shortlink.admin.dao.entity.UserDO;
import com.xidian.shortlink.admin.dao.mapper.UserMapper;
import com.xidian.shortlink.admin.dto.resp.UserRespDTO;
import com.xidian.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

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
}
