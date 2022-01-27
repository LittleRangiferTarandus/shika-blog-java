package kokoroAme.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kokoroAme.common.Result;
import kokoroAme.entity.Tag;
import kokoroAme.mapper.TagBlogMapper;
import kokoroAme.service.TagBlogService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/tag-blog")
public class TagBlogController {
	@Autowired
	private TagBlogService tagBlogService;
	@RequestMapping("/tag/{id}")
	public Result getTagByBlogId(@PathVariable(name = "id") Long id) {
		List<Tag> list=tagBlogService.selectBlog2Tag(id);
		return Result.success(list);
	}
}
