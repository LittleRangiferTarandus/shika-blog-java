package kokoroAme.mapper;

import kokoroAme.entity.BlogUser;
import kokoroAme.entity.Comment;
import kokoroAme.entity.CommentUser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kokoroAme
 * @since 2021-12-14
 */
public interface CommentMapper extends BaseMapper<Comment> {
	public IPage<CommentUser> selectCommentUser(Page page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
