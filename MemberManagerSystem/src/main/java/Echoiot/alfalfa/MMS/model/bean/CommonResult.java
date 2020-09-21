package Echoiot.alfalfa.MMS.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alfalfa99
 * @Date 2020/9/13 14:00
 * @Version 1.0
 * 统一请求返回体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
}
