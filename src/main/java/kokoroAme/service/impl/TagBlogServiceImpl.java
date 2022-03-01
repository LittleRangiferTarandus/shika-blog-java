package kokoroAme.service.impl;

import kokoroAme.entity.Tag;
import kokoroAme.entity.TagBlog;
import kokoroAme.mapper.BlogMapper;
import kokoroAme.mapper.TagBlogMapper;
import kokoroAme.mapper.TagMapper;
import kokoroAme.service.TagBlogService;

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
public class TagBlogServiceImpl extends ServiceImpl<TagBlogMapper, TagBlog> implements TagBlogService {
	@Autowired
	TagBlogMapper tagBlogMapper;
	@Autowired
	BlogMapper blogMapper;
	@Autowired
	TagMapper tagMapper;
	public List<Tag>  selectBlog2Tag(long id){
		return tagBlogMapper.selectBlog2Tag(id);
	}
	
	public int createTag2BlogEntities(Long blog,List<Long> tags) {
		if(blogMapper.selectById(blog)==null) {
			return 0;
		}
		if(tags.size()==0) {
			tagBlogMapper.delete(new QueryWrapper<TagBlog>().eq("blog_id", blog));
		}else {
			tagBlogMapper.delete(new QueryWrapper<TagBlog>().notIn("tag_id", tags).eq("blog_id", blog));
		}
		for(long tag : tags) {
			TagBlog tagBlog= new TagBlog();
			tagBlog.setBlogId(blog);
			tagBlog.setTagId(tag);
			saveOrUpdate(tagBlog);
		}
		return 1;
		
	}
}
