package com.maker.lp;

import org.maker.lp.util.MD5;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@MapperScan("com.maker.lp.mapper")
@SpringBootApplication
//@ComponentScan("org.maker.lp")
public class PersonManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManageSystemApplication.class, args);
        String pw= MD5.inputPassToFormPass("123");
        System.out.println("pw:"+pw);
        //d3b1294a61a07da9b49b6e22b2cbd7f9//123456
        //c38dc3dcb8f0b43ac8ea6a70b5ec7648//123
    }

}
