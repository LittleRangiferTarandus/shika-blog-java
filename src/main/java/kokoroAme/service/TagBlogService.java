package kokoroAme.service;

import kokoroAme.entity.Tag;
import kokoroAme.entity.TagBlog;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
public interface TagBlogService extends IService<TagBlog> {
	public List<Tag>  selectBlog2Tag(long id);
	public int createTag2BlogEntities(Long blog,List<Long> tags);
}
