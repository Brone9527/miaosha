package com.nicho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @SpringBootApplication表明主程序类
 *
 */
//C:\Users\Nicholas\IdeaProjects\springboot-01-helloworld\src\main\resources\mapper
@SpringBootApplication
//@MapperScan("resources.mapper")
public class MiaoShaMain {
    public static void main(String [] args) {
        //Spring应用启动
        SpringApplication.run(MiaoShaMain.class,args);

    }
}
