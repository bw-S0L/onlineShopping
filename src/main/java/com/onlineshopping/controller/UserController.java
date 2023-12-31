package com.onlineshopping.controller;

import com.onlineshopping.model.dto.UserInfoEditDTO;
import com.onlineshopping.model.dto.UserLoginDTO;
import com.onlineshopping.model.dto.UserPwdEditDTO;
import com.onlineshopping.model.dto.UserRegisterDTO;
import com.onlineshopping.model.vo.*;
import com.onlineshopping.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@RequestBody UserRegisterFVO userRegisterFVO) {
        CommonResult cm = new CommonResult(false);
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(userRegisterFVO);
        try {
            userService.register(userRegisterDTO);
        } catch (Exception e) {
            cm.setMessage(e.getMessage());
            return cm;
        }
        cm.setSuccess(true);
        return cm;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody UserLoginFVO userLoginFVO) {
        CommonResult cm = new CommonResult(false);
        UserLoginDTO userLoginDTO = new UserLoginDTO(userLoginFVO);
        String token;
        try {
            token = userService.login(request, response, userLoginDTO);
        } catch (Exception e) {
            cm.setMessage(e.getMessage());
            return cm;
        }
        cm.setSuccess(true);
        cm.setObject(new TokenVO(token));
        return cm;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response) {
        CommonResult cm = new CommonResult(false);
        try {
            userService.logout(request, response);
        } catch (Exception e) {
            cm.setMessage(e.getMessage());
            return cm;
        }
        cm.setSuccess(true);
        return cm;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult info(HttpServletRequest request, HttpServletResponse response) {
        CommonResult cm = new CommonResult(false);
        UserInfoVO userInfoVO;
        try {
            userInfoVO = userService.info(request, response);
        } catch (Exception e) {
            cm.setMessage(e.getMessage());
            return cm;
        }
        cm.setSuccess(true);
        cm.setObject(userInfoVO);
        return cm;
    }

    @RequestMapping(value = "/info/edit", method = RequestMethod.POST)
    public CommonResult infoEdit(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody UserInfoEditFVO userInfoEditFVO) {
        CommonResult cm = new CommonResult(false);
        UserInfoEditDTO userInfoEditDTO = new UserInfoEditDTO(userInfoEditFVO);
        String token;
        try {
            token = userService.infoEdit(request, response, userInfoEditDTO);
        } catch (Exception e) {
            cm.setMessage(e.getMessage());
            return cm;
        }
        cm.setSuccess(true);
        cm.setObject(new TokenVO(token));
        return cm;
    }

    @RequestMapping(value = "/pwd/edit", method = RequestMethod.POST)
    public CommonResult infoEdit(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody UserPwdEditFVO userPwdEditFVO) {
        CommonResult cm = new CommonResult(false);
        UserPwdEditDTO userPwdEditDTO = new UserPwdEditDTO(userPwdEditFVO);
        String token;
        try {
            token = userService.pwdEdit(request, response, userPwdEditDTO);
        } catch (Exception e) {
            cm.setMessage(e.getMessage());
            return cm;
        }
        cm.setSuccess(true);
        cm.setObject(new TokenVO(token));
        return cm;
    }
}
