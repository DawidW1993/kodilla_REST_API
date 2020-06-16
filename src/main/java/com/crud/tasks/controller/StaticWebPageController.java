package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String,Object> model){
        model.put("variable","My Thymeleaf variable");
        model.put("two",2 );
        model.put("c1","2 * 2 = ");
        model.put("c2","2 * 2 + 2 = ");
        model.put("c3","2 - 2 * 2 = ");
        return "index";
    }
}
