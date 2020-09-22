package Echoiot.alfalfa.MMS.service;

import Echoiot.alfalfa.MMS.dao.UserDao;
import Echoiot.alfalfa.MMS.model.pojo.User;
import Echoiot.alfalfa.MMS.util.DateTimeTransferUtil;
import Echoiot.alfalfa.MMS.util.IdWorker;
import Echoiot.alfalfa.MMS.util.JwtUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 19:39
 * @Version 1.0
 */
@Service
public class UserService {

    private final UserDao userDao;
    private final IdWorker idWorker;
    private final JwtUtil jwtUtil;
    public UserService(UserDao userDao, IdWorker idWorker, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.idWorker = idWorker;
        this.jwtUtil = jwtUtil;
    }

    public void userRegister(User user) {
        try {
            user.setId(idWorker.nextId() + "");
            user.setEnable(1);
            user.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
            userDao.userRegister(user);
        } catch (Exception e) {
            throw new DuplicateKeyException("用户名重复");
        }
    }

    public String userLogin(User user) {
        User trueUser = userDao.userLogin(user.getUsername());
        if (trueUser.getPassword().equals(user.getPassword())){
            return "登陆成功";
        }
        throw new InternalAuthenticationServiceException("用户名或密码有误");
    }
}
