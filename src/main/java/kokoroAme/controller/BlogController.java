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
import kokoroAme.entity.BlogTag;
import kokoroAme.entity.BlogUser;
import kokoroAme.entity.BlogUserTag;
import kokoroAme.entity.CountBlog;
import kokoroAme.entity.Tag;
import kokoroAme.mapper.BlogMapper;
import kokoroAme.service.BlogService;
import kokoroAme.service.TagBlogService;
import kokoroAme.service.TagService;
import kokoroAme.util.ShiroUtil;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TagBlogService tagBlogService;
    @Autowired
    TagService tagService;
    
    
    @GetMapping("/blogsPro/oneBlog/{blogId}")
	public Result getOneBlog(@PathVariable(name="blogId") Integer blogId ) {
	    QueryWrapper queryWrapper = new QueryWrapper<Blog>().eq("b.id", blogId).orderByDesc("b.created");
	    BlogUserTag pageData = blogService.selectOneBlogPro(queryWrapper );
	    return Result.success(pageData);
	}

    @GetMapping("/blogsPro/commonPage/{currentPage}/{pageSize}/{field}")
	public Result blogsByField(@PathVariable(name="pageSize") Integer pageSize ,@PathVariable(name="currentPage") Integer currentPage,
			@PathVariable(name="field") String field) {
	    Page page = new Page(currentPage, pageSize);
	    QueryWrapper queryWrapper = new QueryWrapper<Blog>().orderByDesc("b.created");
	    if(field!=null&&!"all".equals(field)) {
	    	queryWrapper.eq("field", field);
	    }
	    IPage pageData = blogService.selectBlogsPro(page,queryWrapper );
	    return Result.success(pageData);
	}

    @GetMapping("/blogsHome/{pageSize}")
    public Result blogsHome(@PathVariable(name ="pageSize") Integer pageSize) {
        List<Blog> blogs = blogService.blogsHome(pageSize);
        return Result.success(blogs);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return Result.success(blog);
    }
    
    @RequiresAuthentication
    @PostMapping("/blog/edit")
	public Result edit(@Validated @RequestBody BlogTag blog) {
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
	    int numTagCorrectField = tagService.count(new QueryWrapper<Tag>().eq("field", blog.getField()).in("id",blog.getTags()));
	    Assert.isTrue(numTagCorrectField==blog.getTags().size(), "提交了不属于对应板块的博客标签或者不存在的标签");
	    blogService.saveOrUpdate(temp);
	    if(blog.getTags()!=null) {
	    	tagBlogService.createTag2BlogEntities(blog.getId(), blog.getTags());
	    }
	    return Result.success(null);
	}
    
    @RequiresAuthentication
    @GetMapping("/blogsPro/userPage/{userId}/{currentPage}/{field}")
	public Result blogsProUser(@PathVariable(name="userId") Integer userId ,@PathVariable(name="currentPage") Integer currentPage,
			@PathVariable(name="field") String field) {
	    Assert.notNull(userId,"没有权限");
	    Assert.isTrue(userId.longValue() == ShiroUtil.getProfile().getId(), "没有权限");
	    Page page = new Page(currentPage, 10);
	    QueryWrapper queryWrapper = new QueryWrapper<Blog>().eq("user_id",userId.longValue()).orderByDesc("b.created");
	    if(field!=null&&!"all".equals(field)) {
	    	queryWrapper.eq("field", field);
	    }
	    IPage pageData = blogService.selectBlogsPro(page,queryWrapper );
	    return Result.success(pageData);
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
    
    
    @GetMapping("/blog/count/{id}")
    public Result countBlog(	@PathVariable(name="id") Long userId) {
    	Assert.notNull(userId,"参数缺失");
    	QueryWrapper queryWrapper = new QueryWrapper<Blog>().eq("user_id", userId);
    	List<CountBlog> countBlog =  blogService.countBlog( queryWrapper);
    	return Result.success(countBlog);
    }
}
