package com.example.springbootsession;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringbootsessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootsessionApplication.class, args);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        return "test";
    }

    @RequestMapping("/set")
    @ResponseBody
    public String set(HttpSession session, @RequestParam String key, @RequestParam String value) {
        session.setAttribute(key, value);
        return "test";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(HttpSession session, @RequestParam String key) {
        return (String) session.getAttribute(key);
    }
}
