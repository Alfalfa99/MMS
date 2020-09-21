package Echoiot.alfalfa.MMS.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 16:32
 * @Version 1.0
 * 回答实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    /**
     * id:  回答id
     * uid： 回答者id
     * question_id: 问题的id
     * parentid：父回答的id（如不为空则为针对评论的评论）
     * answer_field：    回答的内容
     * popcount：    点赞数
     * addtime：     回复时间
     */
    @ApiModelProperty(example = "回答的id")
    private String id;
    @ApiModelProperty(example = "用户id")
    private String uid;
    @ApiModelProperty(example = "问题id")
    private String question_id;
    @ApiModelProperty(example = "父回答id")
    private String parentid;
    @ApiModelProperty(example = "回答内容")
    private String answer_field;
    @ApiModelProperty(example = "被点赞数")
    private Long popcount;
    @ApiModelProperty(example = "添加时间")
    private String addtime;
}
