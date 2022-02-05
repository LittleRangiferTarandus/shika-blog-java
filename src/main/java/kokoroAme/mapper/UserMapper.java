package kokoroAme.mapper;

import kokoroAme.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kokoroAme
 * @since 2021-11-21
 */

public interface UserMapper extends BaseMapper<User> {
	public boolean updateNickNameEmailById(@Param("nickname") String nickname,@Param("email")  String email,@Param("id") Long id);
}
