package Echoiot.alfalfa.MMS.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/18 20:05
 * @Version 1.0
 * 任务传输对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    /**
     * task_member_id: 任务执行者id
     * task_name: 任务名称
     * task_field: 任务详情
     * task_start_time: 任务开始时间
     * task_final_time: 任务截至时间
     */
    @ApiModelProperty(example = "任务执行者id")
    @NotNull(message = "任务执行者不能为空")
    private List<String> task_member_id;
    @ApiModelProperty(example = "任务名")
    @NotNull(message = "任务名不能为空")
    private String task_name;
    @ApiModelProperty(example = "任务内容")
    @NotNull(message = "任务内容不能为空")
    private String task_field;
    @ApiModelProperty(example = "任务开始时间")
    @NotNull(message = "任务的开始时间")
    private String task_start_time;
    @ApiModelProperty(example = "任务结束时间")
    @NotNull(message = "任务的结束时间")
    private String task_final_time;
}
