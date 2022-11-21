package com.sal.fm;

import com.sal.fm.controller.Controller;
import com.sal.fm.dao.FlooringMasteryException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) throws FlooringMasteryException {
//        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//            appContext.scan("com.sal.fm");
//            appContext.refresh();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Controller controller = context.getBean("controller", Controller.class);
        controller.run();
    }
}
