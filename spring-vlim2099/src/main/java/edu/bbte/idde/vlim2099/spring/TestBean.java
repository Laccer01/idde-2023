package edu.bbte.idde.vlim2099.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestBean {
    @PostConstruct
    public void init(){
        System.out.println("Init");
    }
    public void sayHello(){
        System.out.println("Helloka");
    }
}
