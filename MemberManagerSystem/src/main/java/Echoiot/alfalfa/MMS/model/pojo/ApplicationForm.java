package Echoiot.alfalfa.MMS.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 16:41
 * @Version 1.0
 * 申请表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationForm {
    /**
     * id:  表格id
     * uid: 申请者uid
     * name:    姓名
     * email:   邮箱地址
     * phone:   手机
     * gender:  性别
     * age:     年龄
     * college: 学院
     * grade:   年级
     * classes: 专业班级
     * selfintroduce:   自我介绍
     * preexp:  项目经历或比赛经历
     * addtime: 申请时间
     * status:  申请状态（0面试时间待定，1待面试，2拒绝，3面试结束,4通过）
     * otherLab:    是否加入了别的社团或实验室或学生会
     */
    @ApiModelProperty(example = "申请表的id")
    private String id;
    @ApiModelProperty(example = "用户id")
    private String uid;
    @ApiModelProperty(example = "真实姓名id")
    private String name;
    @ApiModelProperty(example = "邮箱")
    private String email;
    @ApiModelProperty(example = "手机")
    private String phone;
    @ApiModelProperty(example = "性别")
    private String gender;
    @ApiModelProperty(example = "年龄")
    private String age;
    @ApiModelProperty(example = "学院")
    private String college;
    @ApiModelProperty(example = "年级")
    private String grade;
    @ApiModelProperty(example = "专业班级")
    private String classes;
    @ApiModelProperty(example = "自我介绍")
    private String selfintroduce;
    @ApiModelProperty(example = "获奖经验或者项目经验")
    private String preexp;
    @ApiModelProperty(example = "申请时间")
    private String addtime;
    @ApiModelProperty(example = "申请表状态:    0-面试时间待定,1-待面试,2-未通过,3-面试结束,4-通过")
    private Integer status;
    @ApiModelProperty(example = "是否参加了别的社团或组织")
    private String otherLab;
}
