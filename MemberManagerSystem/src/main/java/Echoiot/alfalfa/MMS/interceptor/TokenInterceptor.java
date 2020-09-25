package Echoiot.alfalfa.MMS.interceptor;


import Echoiot.alfalfa.MMS.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 18:18
 * @Version 1.0
 * 全局校验Token
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public TokenInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * 通过拦截器对请求头进行校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                //获得token
                String token = header.substring(7);
                //验证token
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null) {
                        request.setAttribute("uid",claims.getId());
                        request.setAttribute("roles",roles);
                        return true;
                    } else {
                        throw new BadCredentialsException("令牌已失效");
                    }
                } catch (Exception e) {
                    throw new BadCredentialsException("令牌已失效");
                }
            }
        }
        throw new AuthenticationCredentialsNotFoundException("请先登录");
    }
}
