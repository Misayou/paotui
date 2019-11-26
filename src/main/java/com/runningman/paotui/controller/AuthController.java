package com.runningman.paotui.controller;

import com.runningman.paotui.dto.Result;
import com.runningman.paotui.pojo.AuthInfo;
import com.runningman.paotui.pojo.User;
import com.runningman.paotui.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("auth")
    public Result auth(String real_name, String student_id, HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new Result().fail("未登录",1000);
        }

        //未输入姓名就提交
        if(real_name=="" || real_name==null || student_id=="" ||student_id==null){
            return new Result().fail("请确保认证信息已完整输入",1002);
        }

        //检查学号的有效性
        if(!authService.checkStudentId(student_id)){
            return new Result().fail("认证失败,一个学号只能认证一个账号",1001);
        }

        authService.makeAuth(user,real_name,student_id);
        return new Result().success("认证成功",0,null);

    }

    @RequestMapping("getStart")
    public Result getStart(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null) {
            return new Result().fail("nologin","未登录",0);
        }
        return new Result().success("用户的信誉积分",0,authService.getAuthStart(user.getUsername()));
    }

    @RequestMapping(value = "/getAuthInfo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<AuthInfo> getAuthInfo(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<AuthInfo> authInfo=authService.getAuthInfo(user.getUsername());
        return authInfo;
    }

    @RequestMapping("getUserInfo")
    public Result getUserInfo(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return new Result().success("未登录",1006);
        }
        //清除密码信息
        user.setPassword("");
        return new Result().success("已登录",0,user);
    }
}
