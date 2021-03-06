package kokoroAme.common;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
@Data
public class LoginDto {
    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
