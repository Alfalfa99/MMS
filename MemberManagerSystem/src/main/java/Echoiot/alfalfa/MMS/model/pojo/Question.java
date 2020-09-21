package Echoiot.alfalfa.MMS.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 16:25
 * @Version 1.0
 * 提问实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    /**
     * id:  问题id
     * uid：提问者id；
     * question_title:  问题标题
     * question_field:  问题内容
     * answer_count:    回答数
     * popcount:        点赞数
     * addtime:         问题添加时间
     * lastupdatetime:  最后回答时间
     */
    @ApiModelProperty(example = "问题id")
    private String id;
    @ApiModelProperty(example = "用户id")
    private String uid;
    @ApiModelProperty(example = "问题标题")
    private String question_title;
    @ApiModelProperty(example = "问题内容")
    private String question_field;
    @ApiModelProperty(example = "问题回答数")
    private Integer answer_count;
    @ApiModelProperty(example = "问题被点赞数")
    private Long popcount;
    @ApiModelProperty(example = "提问时间")
    private String addtime;
    @ApiModelProperty(example = "最后回复时间")
    private String lastupdatetime;
}
