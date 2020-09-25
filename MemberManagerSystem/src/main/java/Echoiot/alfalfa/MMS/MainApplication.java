package Echoiot.alfalfa.MMS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @Author Alfalfa99
 * @Date 2020/9/20 20:06
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("Echoiot.alfalfa.MMS.dao")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
