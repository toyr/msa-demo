package com.toyr.msa;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-16 1:04 AM
 */
@SpringBootApplication
@EnableAdminServer
public class SpringbootadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootadminApplication.class, args);
    }

}
