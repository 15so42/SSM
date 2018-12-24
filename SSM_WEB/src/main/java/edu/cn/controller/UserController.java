package edu.cn.controller;

import Impl.RoleService;
import Impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.Role;
import pojo.User;
import tools.Constants;
import tools.PageSupport;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger= Logger.getLogger(String.valueOf(UserController.class));
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
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
            session.setAttribute(Constants.USER_SESSION,user);
            return "redirect:/user/main.html";
        }
        else{
            request.setAttribute("error","用戶名或密碼不正确");
            return "login";
        }


    }

    @RequestMapping(value="/main.html")
    public String main(HttpSession session){
        if(session.getAttribute(Constants.USER_SESSION)==null){
            return "redirect:/user/login.html";
        }
        return "frame";
    }

    @RequestMapping(value="/exlogin.html",method=RequestMethod.GET)
    public String exLogin(@RequestParam String userCode,@RequestParam String userPassword){

        //调用service方法，进行用户匹配
        User user = userService.login(userCode,userPassword);
        if(null == user){//登录失败
            throw new RuntimeException("用户名或者密码不正确！");
        }
        return "redirect:/user/main.html";
    }

    @RequestMapping(value="/logout.html")
    public String logout(HttpSession session){
        //清除session
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }

    //查询用户
    @RequestMapping(value="/userlist.html")
    public String getUserList(Model model,
                              @RequestParam(value="queryUserName",required=false) String queryUserName,
                              @RequestParam(value="queryUserRole",required=false) String queryUserRole,
                              @RequestParam(value="pageIndex",required=false) String pageIndex){
        logger.info("getUserList ---- > queryUserName: " + queryUserName);
        logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
        logger.info("getUserList ---- > pageIndex: " + pageIndex);
        int _queryUserRole = 0;
        List<User> userList = null;
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;

        if(queryUserName == null){
            queryUserName = "";
        }
        if(queryUserRole != null && !queryUserRole.equals("")){
            _queryUserRole = Integer.parseInt(queryUserRole);
        }

        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "redirect:/user/syserror.html";
                //response.sendRedirect("syserror.jsp");
            }
        }
        //总数量（表）
        int totalCount	= userService.getUserCount(queryUserName,_queryUserRole);

        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);
        int totalPageCount = pages.getTotalPageCount();
        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        userList = userService.getUserList(queryUserName,_queryUserRole,currentPageNo,pageSize);
        model.addAttribute("userList", userList);
        List<Role> roleList = null;
        roleList = roleService.getRoleList(queryUserName,_queryUserRole,currentPageNo);

        model.addAttribute("roleList", roleList);
        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "userlist";
    }

    @RequestMapping(value="/syserror.html")
    public String sysError(){
        return "syserror";
    }

    //==========================================================================
    //找不到前端页面了,返回json数据
    //==========================================================================

    //使用GET方法查询所有用户
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUser(){
        List<User> users=userService.getAllUser();
        return  users;
    }

    //使用GET按照id查询用户
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("id") int id){
        User user=userService.getUserById(id);
        return user;
    }
    //使用post插入用户
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody

    public Map InsertUser(@RequestBody User user){
        int result=userService.InsertUser(user);
        Map<String,String> map=new HashMap<String,String>();
        if(result==1)
            map.put("result","插入成功");
        else
            map.put("result","插入失败");
        return map;

    }

    //使用post路径插入简单用户
    @RequestMapping(value="/{userCode}/{userName}/{userPassword}",method=RequestMethod.POST)
    @ResponseBody
    public Map InsertUserSimply(@PathVariable String userCode, @PathVariable String userName, @PathVariable String userPassword)
    {
        User user=new User(userCode,userName,userPassword);
        int result=userService.InsertUser(user);
        Map<String,String> map=new HashMap<String,String>();
        if(result==1)
            map.put("result","插入成功");
        else
            map.put("result","插入失败");
        return map;

    }

    //使用put修改用户
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    @ResponseBody
    public Map update(@PathVariable int id,@RequestBody User user)
    {

        int result=userService.updateUser(id,user);
        Map<String,String> map=new HashMap<String,String>();
        if(result==1)
            map.put("result","更改用户信息完成");
        else
            map.put("result","更改用户信息失败");
        return map;

    }

    //使用delte删除用户
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Map delete(@PathVariable int id)
    {

        int result=userService.deleteUserById(id);
        Map<String,String> map=new HashMap<String,String>();
        if(result==1)
            map.put("result","删除用户成功");
        else
            map.put("result","删除用户失败");
        return map;

    }








}
