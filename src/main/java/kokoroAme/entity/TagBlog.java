package kokoroAme.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author kokoroAme
 * @since 2022-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_tag_blog")
public class TagBlog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "tag_id")
    private Long tagId;
    @TableId(value = "blog_id")
    private Long blogId;


}
