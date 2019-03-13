package com.hamlt.springcloud.zipkin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "service-hi", fallback = HelloServiceImpl.class)
public interface  HelloService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hiService(@RequestParam(value = "name") String name);

}
