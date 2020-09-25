package Echoiot.alfalfa.MMS.service;


import Echoiot.alfalfa.MMS.dao.UserDao;
import Echoiot.alfalfa.MMS.model.dto.UserLoginDTO;
import Echoiot.alfalfa.MMS.model.dto.UserRegisterDTO;
import Echoiot.alfalfa.MMS.model.dto.UserUpdateDTO;
import Echoiot.alfalfa.MMS.model.dto.UserUpdatePwdDTO;
import Echoiot.alfalfa.MMS.model.pojo.User;
import Echoiot.alfalfa.MMS.utils.DateTimeTransferUtil;
import Echoiot.alfalfa.MMS.utils.IdWorker;
import Echoiot.alfalfa.MMS.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Alfalfa99
 * @Date 2020/9/15 19:39
 * @Version 1.0
 */
@Service
@Transactional(rollbackForClassName = "Exception.class")
public class UserService {

    private final UserDao userDao;
    private final IdWorker idWorker;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao, IdWorker idWorker, JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.idWorker = idWorker;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 用户注册
     * @param urDTO 用户注册DTO
     */
    public void userRegister(UserRegisterDTO urDTO) {
        try {

            User user = new User();
            BeanUtils.copyProperties(urDTO,user);
            user.setId(idWorker.nextId() + "");
            user.setPassword(bCryptPasswordEncoder.encode(urDTO.getPassword()));
            user.setAddtime(DateTimeTransferUtil.getNowTimeStamp());
            user.setEnable(1);
            userDao.userRegister(user);
            userDao.allocateUserRole(user.getId());
        } catch (Exception e) {
            throw new DuplicateKeyException("用户名重复");
        }
    }

    /**
     * 通过用户id查询用户角色
     *
     * @param id 用户id
     * @return 用户角色
     */
    public String getUserRole(String id) {
        return userDao.getUserRole(id);
    }


    public String userLogin(UserLoginDTO ulDTO) {
        User user = userDao.userLogin(ulDTO.getUsername());
        if (user.getEnable() == 2){
            throw new DisabledException("用户已被封禁！");
        }
        if (bCryptPasswordEncoder.matches(ulDTO.getPassword(), user.getPassword())) {
            return jwtUtil.createJWT(user.getId(), this.getUserRole(user.getId()));
        }
        throw new InternalAuthenticationServiceException("用户名或密码有误");
    }

    /**
     * 通过用户id查询用户详情
     *
     * @param id 用户id
     * @return 用户实体
     */
    public User getUserDetailById(String id) {
        return userDao.getUserDetailById(id);
    }

    /**
     * 用户修改用户非敏感信息
     *
     */
    public User updateUserDetail(UserUpdateDTO uuDTO, String uid) {
        User targetUser = new User();
        BeanUtils.copyProperties(uuDTO,targetUser);
        targetUser.setId(uid);
        userDao.updateUserDetail(targetUser);
        return targetUser;
    }

    /**
     * 用户修改密码
     */
    public void updateUserPwd(UserUpdatePwdDTO uuPwdDTO, String uid) {
        if (uuPwdDTO.getNewPW()!=uuPwdDTO.getConfirmPW()){
            throw new IllegalArgumentException("密码不匹配");
        }
        User userById = userDao.getUserDetailById(uid);
        if (!bCryptPasswordEncoder.matches(uuPwdDTO.getNewPW(),userById.getPassword())){
            throw new InternalAuthenticationServiceException("密码有误");
        }
        userDao.updateUserPwd(bCryptPasswordEncoder.encode(uuPwdDTO.getNewPW()), uid);
    }

}
