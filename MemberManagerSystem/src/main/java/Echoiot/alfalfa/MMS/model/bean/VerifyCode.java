package Echoiot.alfalfa.MMS.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Alfalfa99
 * @Date 2020/9/19 22:13
 * @Version 1.0
 * 验证码实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCode {
    private String code;
    private byte[] imgBytes;
    private long expireTime;
}
