package kokoroAme.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

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
 * @since 2021-11-21
 */
@Data
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;
    @NotBlank(message = "title不能为空")
    private String title;
    @NotBlank(message = "desc不能为空")
    private String description;

    private String content;

    private LocalDateTime created;

    private Integer status;
    private String field;
    private List<Long> tags;
}
