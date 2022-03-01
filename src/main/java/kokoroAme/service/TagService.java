package kokoroAme.service;

import kokoroAme.entity.Tag;
import kokoroAme.entity.TagWithChildren;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
public interface TagService extends IService<Tag> {
	public List<Tag> getTags(QueryWrapper queryWrapper);
	public List<TagWithChildren> getTagsWithChildren(QueryWrapper queryWrapper);
}
