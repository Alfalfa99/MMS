package Echoiot.alfalfa.MMS.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Alfalfa99
 * @Date 2020/9/19 21:56
 * @Version 1.0
 * 用户登录DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @ApiModelProperty(example = "登录名")
    @NotNull(message = "账号不允许为空")
    private String username;
    @ApiModelProperty(example = "密码")
    @NotNull(message = "密码不允许为空")
    private String password;
    @ApiModelProperty(example = "验证码")
    @NotNull(message = "验证码不允许为空")
    private String verifyCode;
}
