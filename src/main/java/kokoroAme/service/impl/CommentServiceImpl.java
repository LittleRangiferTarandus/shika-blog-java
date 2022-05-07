package kokoroAme.service.impl;

import kokoroAme.entity.Comment;
import kokoroAme.entity.CommentUser;
import kokoroAme.mapper.CommentMapper;
import kokoroAme.service.CommentService;

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
 * @since 2021-12-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public IPage<CommentUser> getComments(Long blogId,Page page) {

	    
		IPage<CommentUser> comments = commentMapper.selectCommentUser(page,new QueryWrapper<Comment>().eq("blog_id", blogId).orderByDesc("m_comment.created"));
		return comments;
	}
	
}
