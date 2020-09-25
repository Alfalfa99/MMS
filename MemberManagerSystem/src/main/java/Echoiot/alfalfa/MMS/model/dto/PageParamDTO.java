package Echoiot.alfalfa.MMS.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author Alfalfa99
 * @Date 2020/9/18 13:33
 * @Version 1.0
 * 分页查询DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParamDTO {
    /**
     * cp: 当前页数
     * ps: 每一页的数据条数
     * order: map{待排序字段:排序规则(asc:升序,desc：降序)}
     */
    @ApiModelProperty(example = "当前页数")
    @NotNull(message = "当前页数不能为空")
    private Integer cp;
    @ApiModelProperty(example = "每页条数")
    @NotNull(message = "每页条数不能为空")
    private Integer ps;
    @ApiModelProperty(example = "map{待排序字段:排序规则}")
    private Map<String,String> order;
}
