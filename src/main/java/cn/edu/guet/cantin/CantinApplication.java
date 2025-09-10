package cn.edu.guet.cantin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.guet.cantin.mapper")
public class CantinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CantinApplication.class, args);
    }

}
