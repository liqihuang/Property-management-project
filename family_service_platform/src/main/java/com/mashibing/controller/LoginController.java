package com.mashibing.controller;

import com.mashibing.bean.TblUserRecord;
import com.mashibing.result.Permission;
import com.mashibing.result.Permissions;
import com.mashibing.result.R;
import com.mashibing.result.UserInfo;
import com.mashibing.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //解决前端报错（可处理可不处理）
    @RequestMapping("/auth/2step-code")
    public boolean step_code2(){
        System.out.println("此请求是前端框架默认的请求，可以不错处理，也可以通过这种方式处理掉");
        return true;
    }

    //登录接口
    @RequestMapping("/auth/login")
    public R login(@RequestParam(value = "username") String username,
                   @RequestParam(value = "password") String password,
                   HttpSession session){
        System.out.println("login");
        TblUserRecord tblUserRecord = loginService.login(username,password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        //将用户信息写入Session中
        session.setAttribute("userRecord",tblUserRecord);
        R r = new R(200,"ok",tblUserRecord);
        System.out.println(tblUserRecord);
        return r;
    }

    @RequestMapping("/user/info")
    public R getInfo(HttpSession session){
        //获取用户信息
        TblUserRecord tblUserRecord = (TblUserRecord) session.getAttribute("userRecord");
        //获取用户对应的功能模块
        String[] rolePrivileges = tblUserRecord.getTblRole().getRolePrivileges().split("-");
        // 拼接需要返回的数据对象格式
        Permissions permissions = new Permissions();
        List<Permission> permissionList = new ArrayList<>();
        for (String s : rolePrivileges) {
            permissionList.add(new Permission(s));
        }
        permissions.setPermissions(permissionList);
        UserInfo userInfo = new UserInfo(tblUserRecord.getUserName(),permissions);
        return new R(userInfo);
    }

    //处理登出业务
    @RequestMapping("/auth/logout")
    public void logOut(HttpSession session){
        System.out.println("Logout");
        session.invalidate();//将Session设置为失效
    }

}
