package kokoroAme.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import cn.hutool.http.HttpUtil;
import kokoroAme.common.City;
import kokoroAme.common.IPResult;
import kokoroAme.common.Result;

@RestController
@RequestMapping(value="/info")
public class InfomationController { 
    
    
    @GetMapping("/event/{date}")
    public Object test(@PathVariable("date") String date) {
    	String s =HttpUtil.get("https://zhufred.gitee.io/zreader/ht/ld/"+date+".json");
        return Result.success(s);
    }
    
}

