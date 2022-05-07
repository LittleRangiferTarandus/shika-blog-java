package kokoroAme.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDateTime;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import kokoroAme.common.Result;
import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogTag;
import kokoroAme.entity.Comment;
import kokoroAme.entity.Tag;
import kokoroAme.service.BlogService;
import kokoroAme.service.CommentService;
import kokoroAme.util.ShiroUtil;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kokoroAme
 * @since 2021-12-14
 */
@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	BlogService blogService;
	
	
    @RequiresAuthentication
    @PostMapping("/comment/edit")
	public Result edit(@Validated @RequestBody Comment comment) {
 
	    var blogId=comment.getBlogId();
	    Assert.notNull(blogId, "不存在博客");
	    Blog tempBlog = blogService.getById(blogId);
	    Assert.notNull(tempBlog, "不存在博客");
	    Comment tempComment = null;
	    if(comment.getId() != null) {
	    	tempComment = commentService.getById(comment.getId());
	        Assert.isTrue(tempComment.getUserId() == ShiroUtil.getProfile().getId(), "没有权限编辑");
	    } else {
	    	tempComment = new Comment();
	    	tempComment.setUserId(ShiroUtil.getProfile().getId());
	    	tempComment.setCreated(LocalDateTime.now());
	    	tempComment.setStatus(0);
	    }
	    BeanUtil.copyProperties(comment, tempComment, "id", "userId", "created", "status");
 
	    commentService.saveOrUpdate(tempComment);

	    return Result.success(null);
	}
    
    @GetMapping("/comment/byBlog/{blogId}/{page}")
	public Result get(@PathVariable(name="blogId") Long blogId,@PathVariable(name="page") Integer pageIndex) {
    	Assert.notNull(blogId, "参数缺失");
    	Assert.notNull(pageIndex, "参数缺失");
    	Blog tempBlog = blogService.getById(blogId);
	    Assert.notNull(tempBlog, "不存在博客");
	    var page = new Page(pageIndex,15);
	    var comments = commentService.getComments(blogId,page);

	    return Result.success(comments);
	}
}
