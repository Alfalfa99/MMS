package Echoiot.alfalfa.MMS.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 14:37
 * @Version 1.0
 * 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * id:  用户id
     * username:    用户名
     * password:    密码
     * email:   邮箱地址
     * nickname: 昵称
     * phone:   手机号
     * age: 年龄
     * gender: 性别
     * college: 学院
     * grade: 级
     * classes: 专业班级
     * popcount: 点赞数
     * question_count: 提问数
     * answer_count:    回答数
     * task_count：  待完成任务数
     * addtime：注册时间
     * enable:  0待激活，1正常，2封禁
     */
    @ApiModelProperty(example = "用户id")
    private String id;
    @ApiModelProperty(example = "登录名")
    private String username;
    @ApiModelProperty(example = "密码")
    private String password;
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
    @ApiModelProperty(example = "被点赞数")
    private Long popcount;
    @ApiModelProperty(example = "提问数")
    private Integer question_count;
    @ApiModelProperty(example = "回复数")
    private Integer answer_count;
    @ApiModelProperty(example = "待做任务数")
    private Integer task_count;
    @ApiModelProperty(example = "注册时间")
    private String addtime;
    @ApiModelProperty(example = "账号状态：0:待激活，1:正常，2:被封禁")
    private Integer enable;
}
