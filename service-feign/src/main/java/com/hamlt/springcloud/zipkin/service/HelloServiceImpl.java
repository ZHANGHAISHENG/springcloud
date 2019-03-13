package com.hamlt.springcloud.zipkin.service;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String hiService(String name) {
        return "sorry "+name;
    }
}
