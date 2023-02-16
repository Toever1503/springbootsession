package com.example.springbootsession;

import jakarta.servlet.http.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@SpringBootApplication
@Controller
public class SpringbootsessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootsessionApplication.class, args);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
        System.out.println("Session ID: " + session.getId());
        Cookie[] cookies = request.getCookies();
        return "test";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
        System.out.println("Session ID: " + session.getId());
        session.invalidate();
        res.addCookie(new Cookie("SESSION", request.getSession(true).getId()));
        return "test";
    }








    @RequestMapping("/set")
    @ResponseBody
    public String set(HttpSession session, @RequestParam String key, @RequestParam String value, HttpServletRequest req) {
        session.setAttribute(key, value);
        return "test";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(HttpSession session, @RequestParam String key) {
        return (String) session.getAttribute(key);
    }

    @RequestMapping("/redirect")
    public void redirect(HttpServletResponse res, HttpSession session) throws IOException {
        res.addCookie(new Cookie("SESSION", session.getId()));
        res.addCookie(new Cookie("TESTT", session.getId()));
        res.sendRedirect("redirect:http://192.168.1.240:8080/test");
    }
}
