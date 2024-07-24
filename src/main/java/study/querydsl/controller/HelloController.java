package study.querydsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello"; // return "hello.html" file in templates folder.
    }
}
