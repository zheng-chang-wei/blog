package com.zcw.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcw
 * @date 2020/01/02
 * <p>
 * Description:
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }
}
