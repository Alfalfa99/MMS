package Echoiot.alfalfa.MMS.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Alfalfa99
 * @Date 2020/9/17 11:02
 * @Version 1.0
 * 分页返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult <T> {
    private long pages;
    private long total;
    private List<T> rows;
}
