package Echoiot.alfalfa.MMS.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author Alfalfa99
 * @Date 2020/9/20 11:13
 * @Version 1.0
 * 修改密码DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePwdDTO {
    @ApiModelProperty(example = "旧密码")
    @NotNull(message = "旧密码不允许为空")
    private String lastPW;
    @ApiModelProperty(example = "新密码")
    @NotNull(message = "新密码不允许为空")
    private String newPW;
    @ApiModelProperty(example = "确认密码")
    @NotNull(message = "确认密码不许为空")
    private String confirmPW;
}
