package kokoroAme.service;

import kokoroAme.entity.Comment;
import kokoroAme.entity.CommentUser;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kokoroAme
 * @since 2021-12-14
 */
public interface CommentService extends IService<Comment> {
	public IPage<CommentUser> getComments(Long blogId,Page page);
}
