package kokoroAme.controller;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.map.MapUtil;
import kokoroAme.common.Result;
import kokoroAme.entity.User;
import kokoroAme.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController { 
    @Autowired
    UserService userService;
    
    
    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
    	User user = userService.getById(id);
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map());
    }
    
    @PostMapping("/save")
    public Object testUser(@Validated @RequestBody User user) {
        return user.toString();
    }
}

