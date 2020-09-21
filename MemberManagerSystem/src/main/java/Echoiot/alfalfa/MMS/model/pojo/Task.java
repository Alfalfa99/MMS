package Echoiot.alfalfa.MMS.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 16:10
 * @Version 1.0
 * 任务实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    /**
     * id: 任务id
     * task_boss_id：任务发布者id
     * task_member_id: 任务执行者id
     * task_name: 任务名称
     * task_field: 任务详情
     * task_start_time: 任务开始时间
     * task_final_time: 任务截至时间
     * task_addtime: 任务发布时间
     * task_status: 任务当前状态(0开启,1已完成,2关闭)
     */
    @ApiModelProperty(example = "任务id")
    private String id;
    @ApiModelProperty(example = "创建者id-不用传")
    private String task_boss_id;
    @ApiModelProperty(example = "执行者id列表")
    private String task_member_id;
    @ApiModelProperty(example = "任务名称")
    private String task_name;
    @ApiModelProperty(example = "任务详情")
    private String task_field;
    @ApiModelProperty(example = "任务开始时间")
    private String task_start_time;
    @ApiModelProperty(example = "任务结束时间")
    private String task_final_time;
    @ApiModelProperty(example = "任务发布时间")
    private String task_addtime;
    @ApiModelProperty(example = "任务状态 0开启,1已完成,2关闭")
    private Integer task_status;
}
