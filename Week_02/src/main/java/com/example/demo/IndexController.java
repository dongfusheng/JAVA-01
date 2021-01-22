package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("index")
public class IndexController {

    @ResponseBody
    @RequestMapping("/ok.json")
    public String checkOk(){
        return "Serivice ok .....................";
    }
}
