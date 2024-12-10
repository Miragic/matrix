package com.mcloud.matrix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.mcloud.matrix.**.mapper"})
public class MatrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatrixApplication.class, args);
    }

}
