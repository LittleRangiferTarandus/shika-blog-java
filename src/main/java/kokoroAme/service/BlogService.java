package kokoroAme.service;

import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogUser;
import kokoroAme.entity.BlogUserTag;

import java.util.List;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
public interface BlogService extends IService<Blog> {
	public List<Blog> blogsHome(int pageSize);
	public IPage<BlogUserTag> selectBlogsPro(IPage<BlogUser> page, QueryWrapper queryWrapper);
	public BlogUserTag selectOneBlogPro(QueryWrapper queryWrapper);
}
