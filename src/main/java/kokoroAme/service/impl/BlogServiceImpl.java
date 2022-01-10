package kokoroAme.service.impl;

import kokoroAme.entity.Blog;
import kokoroAme.mapper.BlogMapper;
import kokoroAme.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
