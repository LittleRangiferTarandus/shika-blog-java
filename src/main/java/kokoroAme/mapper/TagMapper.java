package kokoroAme.mapper;

import kokoroAme.entity.Tag;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
public interface TagMapper extends BaseMapper<Tag> {
	public List selectListWithChildren(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
