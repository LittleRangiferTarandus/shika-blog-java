package kokoroAme.mapper;

import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogUser;
import kokoroAme.entity.BlogUserTag;
import kokoroAme.entity.CountBlog;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
public interface BlogMapper extends BaseMapper<Blog> {
	public IPage<BlogUser> selectBlogsWithName(IPage<BlogUser> page);
	public List<Blog> selectRandomBlogs(@Param("pageSize") int pageSize,@Param("allPageSize") int allPageSize);

	public IPage<BlogUserTag> selectBlogsPro(IPage<BlogUser> page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
	
	public BlogUserTag selectBlogsPro(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
	public List<CountBlog> countBlog(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
