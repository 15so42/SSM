package edu.cn.controller;

import Impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger= Logger.getLogger(String.valueOf(UserController.class));
    @Resource
    private UserService userService;

    @RequestMapping(value="/login.html")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/dologin.html",method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session)
    {
        System.out.println("DoLogin");
        User user=userService.login(userCode,userPassword);
        if(user!=null){
            session.setAttribute("USER_SESSION",user);
            return "redirect:/user/main.html";
        }
        else{
            request.setAttribute("error","用戶名或密碼不正确");
            return "login";
        }


    }

    @RequestMapping(value="/main.html")
    public String main(HttpSession session){
        if(session.getAttribute("USER_SESSION")==null){
            return "redirect:/user/login.html";
        }
        return "frame";
    }



}
