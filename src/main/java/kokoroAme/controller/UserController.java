package kokoroAme.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.map.MapUtil;
import kokoroAme.common.Result;
import kokoroAme.common.UserInfo;
import kokoroAme.entity.User;
import kokoroAme.service.UserService;
import kokoroAme.util.ShiroUtil;

@RestController
@RequestMapping(value="/user")
public class UserController { 
    @Autowired
    UserService userService;
    @Value("${file-save-path}")
    private String fileSavePath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
    
    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
    	User user = userService.getById(id);
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("nickname", user.getNickname())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map());
    }
    
    @RequiresAuthentication
    @PostMapping("/save")
    public Object update(@Validated @RequestBody UserInfo user) {
    	boolean isSuccess = userService.updateNickNameEmailById(user.getNickname(),user.getEmail(),user.getId());
        return Result.success(isSuccess);
    }
    
    @RequiresAuthentication
    @PostMapping("/upload/avatar")
    public Object updateAvatar(@RequestParam(name = "avatar",required = true) MultipartFile uploadFile, HttpServletRequest req) {
    	String filePath = "";
        String format = sdf.format(new Date());
        File folder = new File(fileSavePath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() +
                    oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                uploadFile.transferTo(new File(folder, newName));
                filePath = req.getScheme() + "://" + req.getServerName() + ":" +
                        req.getServerPort() + "/uploadFile/" + format + newName;
            } catch (IOException e) {
                e.printStackTrace();
                return Result.fail("上传失败! ");
            }
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            uploadFile.transferTo(new File(folder, newName));
            filePath = req.getScheme() + "://" + req.getServerName() + ":" +
                    req.getServerPort() + "/uploadFile/" + format + newName;
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("上传失败! ");
        }
        User currentUser = new User();
        currentUser.setId(ShiroUtil.getProfile().getId());
        currentUser.setAvatar(filePath);
        boolean isUpdate = userService.updateById(currentUser);
        if(isUpdate) {
        	return Result.success(filePath);
        }
        return Result.fail("头像更新失败！");
    }
}

