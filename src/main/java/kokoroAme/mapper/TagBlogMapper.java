package kokoroAme.mapper;

import kokoroAme.entity.Tag;
import kokoroAme.entity.TagBlog;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
public interface TagBlogMapper extends BaseMapper<TagBlog> {
	public List<Tag> selectBlog2Tag(Long id);
}
