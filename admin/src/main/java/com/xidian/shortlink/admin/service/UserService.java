package com.xidian.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xidian.shortlink.admin.dao.entity.UserDO;
import com.xidian.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.xidian.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户服务接口
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询童虎信息
     *
     * @param username
     * @return 用户返回实体
     */
    UserRespDTO getUserByUserName(String username);

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return 存在返回 true， 不存在返回 false
     */
    Boolean hasUsername(String username);

    /**
     * 用户注册
     *
     * @param userReqParam
     */
    void register(UserRegisterReqDTO userReqParam);
}
