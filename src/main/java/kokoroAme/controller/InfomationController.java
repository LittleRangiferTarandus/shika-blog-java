package kokoroAme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cn.hutool.http.HttpUtil;
import kokoroAme.common.City;
import kokoroAme.common.Result;

@RestController
@RequestMapping(value="/info")
public class InfomationController { 
    
    
    @GetMapping("/event/{date}")
    public Object test(@PathVariable("date") String date) {
    	String s =HttpUtil.get("https://zhufred.gitee.io/zreader/ht/ld/"+date+".json");
        return Result.success(s);
    }
    @GetMapping("/city")
    public Object test() {
    	String s =HttpUtil.get("https://restapi.amap.com/v3/ip?key=8a0d0fcad6ababbfc2ed02f21d9d4aa9");
    	Gson gson = new Gson();
    	City city= gson.fromJson(s,City.class);
    	String s2 =HttpUtil.get("https://restapi.amap.com/v3/weather/weatherInfo?key=8a0d0fcad6ababbfc2ed02f21d9d4aa9&city="+city.getAdcode());
        return Result.success(s2);
    }
    public static void main(String[] args) {
    	String s =HttpUtil.get("https://restapi.amap.com/v3/ip?key=8a0d0fcad6ababbfc2ed02f21d9d4aa9");
    	Gson gson = new Gson();
    	City city= gson.fromJson(s,City.class);
    	String s2 =HttpUtil.get("https://restapi.amap.com/v3/weather/weatherInfo?key=8a0d0fcad6ababbfc2ed02f21d9d4aa9&city="+city.getAdcode());
    	System.out.println(s2);
	}
    
}

