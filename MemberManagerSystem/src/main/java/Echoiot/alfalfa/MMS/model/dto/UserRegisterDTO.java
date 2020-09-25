package Echoiot.alfalfa.MMS.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Alfalfa99
 * @Date 2020/9/19 21:43
 * @Version 1.0
 * 注册DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    @ApiModelProperty(example = "登录名")
    @NotNull(message = "用户名不允许为空")
    private String username;
    @ApiModelProperty(example = "密码")
    @NotNull(message = "密码不允许为空")
    private String password;
    @ApiModelProperty(example = "昵称")
    @NotNull(message = "昵称不允许为空")
    private String nickname;
    @ApiModelProperty(example = "验证码")
    @NotNull(message = "验证码不允许为空")
    private String verifyCode;
}
