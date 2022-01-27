package kokoroAme.mapper;

import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogUser;
import kokoroAme.entity.BlogUserTag;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

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
	public IPage<BlogUserTag> selectBlogsWithNameTag(IPage<BlogUser> page,@Param("field") String field);
}
