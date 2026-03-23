package com.xidian.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xidian.shortlink.admin.dao.entity.UserDO;
import com.xidian.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户服务接口
 */
public interface UserService extends IService<UserDO> {

    UserRespDTO getUserByUserName(String username);

    Boolean hasUsername(String username);
}
