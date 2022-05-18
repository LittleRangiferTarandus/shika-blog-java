package kokoroAme.service.impl;

import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogUser;
import kokoroAme.entity.BlogUserTag;
import kokoroAme.entity.CountBlog;
import kokoroAme.mapper.BlogMapper;
import kokoroAme.service.BlogService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
	@Autowired
	BlogMapper blogMapper;
	public List<Blog> blogsHome(int pageSize) {
		return blogMapper.selectRandomBlogs(pageSize,pageSize+10);
	}
	@Override
	public IPage<BlogUserTag> selectBlogsPro(Page page, QueryWrapper queryWrapper) {
		return blogMapper.selectBlogsPro(page, queryWrapper);
	}
	@Override
	public BlogUserTag selectOneBlogPro(QueryWrapper queryWrapper) {
		return baseMapper.selectBlogsPro(queryWrapper);
	}
	@Override
	public List<CountBlog> countBlog( QueryWrapper queryWrapper) {
		return blogMapper.countBlog(queryWrapper);
	}
}
