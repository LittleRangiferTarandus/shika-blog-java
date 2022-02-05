package kokoroAme.service;

import kokoroAme.entity.User;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */
public interface UserService extends IService<User> {
	public boolean updateNickNameEmailById(  String nickname, String email, Long id);
}
