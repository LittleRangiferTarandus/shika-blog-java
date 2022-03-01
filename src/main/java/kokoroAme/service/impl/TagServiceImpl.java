package kokoroAme.service.impl;

import kokoroAme.entity.Tag;
import kokoroAme.entity.TagWithChildren;
import kokoroAme.mapper.TagMapper;
import kokoroAme.service.TagService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2022-01-11
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
	@Autowired
	TagMapper tagMapper;
	
	@Override
	public List<Tag> getTags(QueryWrapper queryWrapper) {
		return tagMapper.selectList(queryWrapper);
	}

	@Override
	public List<TagWithChildren> getTagsWithChildren(QueryWrapper queryWrapper) {
		return tagMapper.selectListWithChildren(queryWrapper);
	}

}
