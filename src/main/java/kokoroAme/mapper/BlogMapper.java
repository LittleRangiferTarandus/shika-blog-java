package kokoroAme.mapper;

import kokoroAme.entity.Blog;
import kokoroAme.entity.BlogUser;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
public interface BlogMapper extends BaseMapper<Blog> {
	public IPage<BlogUser> selectByIdWithName(IPage<BlogUser> page);
}
