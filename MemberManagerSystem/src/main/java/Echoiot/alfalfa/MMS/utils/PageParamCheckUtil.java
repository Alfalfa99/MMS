package Echoiot.alfalfa.MMS.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author Alfalfa99
 * @Date 2020/9/17 20:23
 * @Version 1.0
 * 检查分页参数
 */
@Component
public class PageParamCheckUtil {

    public String CheckOrder(Map<String, String> map) {
        StringBuilder finalOrder = new StringBuilder();
        //判断map是否为空
        if (map != null && !map.isEmpty()) {
            //读取map中的所有待排序字段及其排序方法
            for (String s : map.keySet()) {
                //把待排序字段加入finalOrder
                finalOrder.append(s);
                String order = map.getOrDefault(s, "asc").toLowerCase();
                if ("desc".equals(order)) {
                    finalOrder.append(" " + order + ",");
                } else {
                    finalOrder.append(" asc,");
                }
            }
            finalOrder.deleteCharAt(finalOrder.length() - 1);
        }
        return finalOrder.toString();
    }
}
