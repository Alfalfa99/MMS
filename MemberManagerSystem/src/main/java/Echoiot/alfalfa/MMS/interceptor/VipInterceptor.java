package Echoiot.alfalfa.MMS.interceptor;


import Echoiot.alfalfa.MMS.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author Alfalfa99
 * @Date 2020/9/13 18:18
 * @Version 1.0
 * 校验是否为Admin
 */
@Component
public class VipInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String roles = (String)request.getAttribute("roles");
        if ("VIP".equals(roles) ||"ADMIN".equals(roles)){
            return true;
        }
        throw new AccessDeniedException("权限不足");
    }
}
