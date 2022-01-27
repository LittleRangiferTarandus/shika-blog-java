package kokoroAme.service.impl;

import kokoroAme.entity.Tag;
import kokoroAme.entity.TagBlog;
import kokoroAme.mapper.TagBlogMapper;
import kokoroAme.service.TagBlogService;
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
public class TagBlogServiceImpl extends ServiceImpl<TagBlogMapper, TagBlog> implements TagBlogService {
	@Autowired
	TagBlogMapper tagBlogMapper;
	public List<Tag>  selectBlog2Tag(long id){
		return tagBlogMapper.selectBlog2Tag(id);
	}
}
