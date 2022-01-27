package kokoroAme.service;

import kokoroAme.entity.Blog;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
public interface BlogService extends IService<Blog> {
	public List<Blog> blogsHome(int pageSize);
}
