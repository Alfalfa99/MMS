package Echoiot.alfalfa.MMS.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * @Author Alfalfa99
 * @Date 2020/9/20 12:00
 * @Version 1.0
 * 用户更新DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    @ApiModelProperty(example = "邮箱")
    @Email(message = "邮箱格式错误")
    private String email;
    @ApiModelProperty(example = "昵称")
    private String nickname;
    @ApiModelProperty(example = "手机号")
    private String phone;
    @ApiModelProperty(example = "年龄")
    private String age;
    @ApiModelProperty(example = "性别")
    private String gender;
    @ApiModelProperty(example = "学院")
    private String college;
    @ApiModelProperty(example = "年级")
    private String grade;
    @ApiModelProperty(example = "专业班级")
    private String classes;

}
