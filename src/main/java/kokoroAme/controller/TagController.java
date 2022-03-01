package kokoroAme.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.hutool.core.lang.Assert;
import kokoroAme.common.Result;
import kokoroAme.entity.Tag;
import kokoroAme.entity.TagWithChildren;
import kokoroAme.service.TagService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	TagService tagService;
	
    @GetMapping("/tags/{type}")
    public Result tags(@PathVariable(name = "type") String type) {
    	QueryWrapper queryWrapper= new QueryWrapper();
    	queryWrapper.eq("type", type);
        List<Tag> tags = tagService.getTags(queryWrapper);
        return Result.success(tags);
    }
    
    @GetMapping("/tagsChildren/{type}")
    public Result tagsWithChildren(@PathVariable(name = "type") String type) {
    	QueryWrapper queryWrapper= new QueryWrapper();
    	queryWrapper.eq("type", type);
        List<TagWithChildren> tags = tagService.getTagsWithChildren(queryWrapper);
        return Result.success(tags);
    }
}
