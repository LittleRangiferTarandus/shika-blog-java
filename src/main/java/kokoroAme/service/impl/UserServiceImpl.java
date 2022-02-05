package kokoroAme.service.impl;

import kokoroAme.entity.User;
import kokoroAme.mapper.UserMapper;
import kokoroAme.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	UserMapper userMapper;
	public boolean updateNickNameEmailById(  String nickname, String email, Long id) {
		return userMapper.updateNickNameEmailById(nickname,email,id);
	}
}
