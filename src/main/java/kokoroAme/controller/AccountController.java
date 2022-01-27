package kokoroAme.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import kokoroAme.common.LoginDto;
import kokoroAme.common.Result;
import kokoroAme.entity.User;
import kokoroAme.service.UserService;
import kokoroAme.util.JwtUtils;

@RestController
@RequestMapping
public class AccountController {
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserService userService;
	@PostMapping("/login")
	public Result login(@Validated @RequestBody LoginDto loginDto,HttpServletResponse response) {
		User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
		Assert.notNull(user, "用户不存在");
		//System.out.println(user.getPassword()+"  "+loginDto.getPassword()+"  "+SecureUtil.md5(loginDto.getPassword()));
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
        
	} 
	@RequestMapping("/logout")
	@RequiresAuthentication
	public Result logout() {
		SecurityUtils.getSubject().logout();;
		return Result.success(null);
	}
	

}
