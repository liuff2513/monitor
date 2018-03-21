package com.baihui.monitor.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Description: //TODO
 * Created by feifei.liu on 2017/11/29 15:10
 **/
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "Hello Monitoring !";
    }
}
