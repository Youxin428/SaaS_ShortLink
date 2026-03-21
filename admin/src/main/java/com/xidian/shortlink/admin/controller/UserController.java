package com.xidian.shortlink.admin.controller;

import com.xidian.shortlink.admin.dto.resp.UserRespDTO;
import com.xidian.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制层
 */
@RestController
@RequestMapping("/api/shortlink/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * 根据用户名查寻用户信息
     */
    @GetMapping("/{username}")
    public UserRespDTO getUser(@PathVariable("username") String username) {
        return userService.getUserByUserName(username);
    }
}
