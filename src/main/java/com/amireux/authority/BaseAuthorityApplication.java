package com.amireux.authority;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.amireux.authority.mapper")//扫描包
public class BaseAuthorityApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseAuthorityApplication.class, args);
    }

}
