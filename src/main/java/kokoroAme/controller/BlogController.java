package kokoroAme.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import kokoroAme.common.Result;
import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogUser;
import kokoroAme.mapper.BlogMapper;
import kokoroAme.service.BlogService;
import kokoroAme.util.ShiroUtil;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;
    @GetMapping("/blogUser/{currentPage}/{pageSize}")
    public Result blogUser(@PathVariable(name = "currentPage") Integer currentPage,
    		@PathVariable(name ="pageSize") Integer pageSize
    	) {
    	Page page = new Page(currentPage, pageSize);
    	IPage pageData = blogMapper.selectBlogsWithName(page);
        return Result.success(pageData);
    }
    
    @GetMapping("/blogUserTag/{field}/{currentPage}/{pageSize}")
    public Result blogUserTag(@PathVariable(name = "currentPage") Integer currentPage,
    		@PathVariable(name ="pageSize") Integer pageSize,
    		@PathVariable(name ="field") String field
    	) {
    	Page page = new Page(currentPage, pageSize);
    	IPage pageData = blogMapper.selectBlogsWithNameTag(page,field);
        return Result.success(pageData);
    }
    
    
    @GetMapping("/blogs/{currentPage}/{pageSize}")
    public Result blogs(@PathVariable(name = "currentPage") Integer currentPage,
    		@PathVariable(name ="pageSize") Integer pageSize
    	) {
        Page page = new Page(currentPage, pageSize);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return Result.success(blog);
    }
    
    @RequiresAuthentication
    @PostMapping("/blog/edit")
	public Result edit(@Validated @RequestBody Blog blog) {
	    Blog temp = null;
	    if(blog.getId() != null) {
	        temp = blogService.getById(blog.getId());
	        Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
	    } else {
	        temp = new Blog();
	        temp.setUserId(ShiroUtil.getProfile().getId());
	        temp.setCreated(LocalDateTime.now());
	        temp.setStatus(0);
	    }
	    BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
	    blogService.saveOrUpdate(temp);
	    return Result.success(null);
	}
    
    @RequiresAuthentication
    @GetMapping("/blogs/user/{userId}")
	public Result edit(@PathVariable(name="userId") Integer userId) {
	    Assert.notNull(userId,"没有权限");
	    Assert.isTrue(userId.longValue() == ShiroUtil.getProfile().getId(), "没有权限");
	    
	    List<Map<String, Object>> map =  blogMapper.selectMaps(new QueryWrapper<Blog>().eq("user_id",userId.longValue()));
	    return Result.success(map);
	}
    
    @RequiresAuthentication
    @PostMapping("/blog/delete/{id}")
	public Result delete(@PathVariable(name = "id") Long id) {
    	Blog blog = blogService.getById(id);
	    if(blog!= null) {
	        Assert.isTrue(blog.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
		    blogService.removeById(id);
		    return Result.success(null);
	    } else {
	    	return Result.fail("博客不存在");
	    }
	}
}
