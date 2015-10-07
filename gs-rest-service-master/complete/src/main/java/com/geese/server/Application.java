package com.geese.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /*ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("/appConfig.xml");*/
        //Integer someIntBean = (Integer) context.getBean("testBean");
        //System.out.println(someIntBean.intValue()); // Demonstrate appConfig loaded

        SpringApplication.run(Application.class, args);
    }
}
