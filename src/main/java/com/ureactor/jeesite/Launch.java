package com.ureactor.jeesite;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.ureactor.jeesite.modules.sys.service.SystemService;

/**
 * spring boot 启动类
 * @author fangpengli@uworks.cc
 *
 */
@EnableCaching
@SpringBootApplication
@ServletComponentScan("com.ureactor.jeesite")
@ComponentScan(value = "com.ureactor.jeesite",lazyInit = true)
public class Launch {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Launch.class).web(true).run(args);
        SystemService.printKeyLoadMessage();
    }
}
