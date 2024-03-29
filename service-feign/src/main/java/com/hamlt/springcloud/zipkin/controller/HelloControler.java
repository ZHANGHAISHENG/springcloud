package com.hamlt.springcloud.zipkin.controller;

import com.hamlt.springcloud.zipkin.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return helloService.hiService( name );
    }


}
