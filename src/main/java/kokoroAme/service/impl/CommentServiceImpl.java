package kokoroAme.service.impl;

import kokoroAme.entity.Comment;
import kokoroAme.mapper.CommentMapper;
import kokoroAme.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
