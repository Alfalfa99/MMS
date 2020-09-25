package Echoiot.alfalfa.MMS.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author Alfalfa99
 * @Date 2020/9/20 10:48
 * @Version 1.0
 */

public class VerifyException extends AuthenticationException {
    public VerifyException(String msg) {
        super(msg);
    }

    public VerifyException(String msg, Throwable t) {
        super(msg, t);
    }
}
