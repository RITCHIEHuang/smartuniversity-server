package com.njtech.smartuniversity.controller;

import com.google.gson.Gson;
import com.njtech.smartuniversity.bean.AuthTokenBean;
import com.njtech.smartuniversity.bean.EmailInfoBean;
import com.njtech.smartuniversity.bean.LoginBean;
import com.njtech.smartuniversity.bean.UserBean;
import com.njtech.smartuniversity.config.security.JwtTokenUtil;
import com.njtech.smartuniversity.config.security.JwtUser;
import com.njtech.smartuniversity.service.EmailInfoService;
import com.njtech.smartuniversity.service.MailService;
import com.njtech.smartuniversity.service.UserService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import com.njtech.smartuniversity.util.MailUtil;
import com.njtech.smartuniversity.util.Validator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ritchie on 6/25/18
 */

@RestController
@Api(value = "/api/auth", tags = {"认证"}, description = "认证相关")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService mUserService;

    @Autowired
    MailService mMailService;

    @Autowired
    EmailInfoService mEmailInfoService;

    @Autowired
    JwtTokenUtil mJwtTokenUtil;

    @Qualifier("jwtUserDetailServiceImpl")
    @Autowired
    UserDetailsService mUserDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;


    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户名(邮箱/账号/id)", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "type", value = "用户登录类型 (0邮箱，1手机，2帐号)", dataType = "String", paramType = "form")
    })
    public JsonResult login(@RequestParam("id") String id,
                            @RequestParam("password") String password,
                            @RequestParam(value = "type", required = false) String type) throws AuthenticationException {
        LoginBean loginBean;
        if (type == null) {
            //默认的登录类型
            loginBean = mUserService.login(id, password);
            if (loginBean != null)
                return new JsonUtil().successDataWithMessage(loginBean,
                        "登录成功");
            else
                loginBean = mUserService.loginByAccountAndPassword(id, password);
            if (loginBean != null)
                return new JsonUtil().successDataWithMessage(loginBean,
                        "登录成功");
        } else if (type.equals("0")) {
            //邮箱登录
            loginBean = mUserService.loginByEmailAndPassword(id, password);
            if (loginBean != null)
                return new JsonUtil().successDataWithMessage(loginBean,
                        "登录成功");
            return new JsonUtil().wrongNoDataWithMessage("邮箱或密码错误");

        } else if (type.equals("1")) {
            return new JsonUtil().wrongNoDataWithMessage("不支持的登录方式");
            //手机登录暂不支持
        } else if (type.equals("2")) {
            loginBean = mUserService.loginByAccountAndPassword(id, password);
            if (loginBean != null)
                return new JsonUtil().successDataWithMessage(loginBean,
                        "登录成功");
            return new JsonUtil().wrongNoDataWithMessage("帐号或密码错误");
        }
        return new JsonUtil().wrongNoDataWithMessage("帐号或密码错误");
    }


    @PostMapping("/sendEmail")
    @ApiOperation(value = "发送邮箱验证码接口", notes = "发送用户邮箱验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "邮箱", name = "email", dataType = "String", paramType = "form", required = true)

    })
    public JsonResult verifyCode(@RequestParam("email") String email) {
        String code = MailUtil.generateCode();
        EmailInfoBean bean = mEmailInfoService.selectEmailInfoByEmail(email);

        mMailService.sendSimpleMail(email, MailUtil.SUBJECT_REGISTER,
                MailUtil.generateMessgae(code));

        if (bean != null && bean.getEmail() != null) {
            bean.setCode(code);
            bean.setCreatedTime(new Date());
            mEmailInfoService.updateEmailInfo(bean);
        } else {
            mEmailInfoService.addEmailInfo(email, code);
        }
        return new JsonUtil().successEmptyDataDefault("发送邮箱验证码成功!");

    }

    @PostMapping("/register")
    @ApiOperation(value = "商家/用户注册接口", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户注册类型(0邮箱，1手机，2帐号)", name = "type", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户类型(0普通用户 1商家)", name = "shop_type", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户名", name = "account", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "邮箱", name = "email", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "邮箱验证码", name = "verifyCode", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "用户密码", name = "password", dataType = "String", paramType = "form")
    })
    public JsonResult register(
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "verifyCode", required = false) String verifyCode,
            @RequestParam("type") String type,
            @RequestParam("shop_type") String shop_type) throws AuthenticationException {
        UserBean userBean = new UserBean();

        //生成id
        String newId = String.valueOf(System.currentTimeMillis()).substring(1);
        userBean.setId(newId);

        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userBean.setUuid(uuid);


        userBean.setState("1");
        userBean.setType(type);

        //邮箱
        if (type.equals("0") && !email.isEmpty() && Validator.isEmail(email) && !verifyCode.isEmpty()) {
            //验证邮箱验证码
            EmailInfoBean emailInfoBean = mEmailInfoService.selectEmailInfo(email, verifyCode);
            if (emailInfoBean != null && !emailInfoBean.getEmail().isEmpty()) {
                userBean.setEmail(email);
                userBean.setPassword(password);
            } else if (emailInfoBean == null) {
                return new JsonUtil().wrongNoDataWithMessage("邮箱验证码错误");
            } else {
                return new JsonUtil().wrongNoDataWithMessage("邮箱验证码出错");

            }
            //验证
        } else if (type.equals("2") && !account.isEmpty() && Validator.isPassword(password)) {
            userBean.setAccount(account);
            userBean.setPassword(password);

        }
        //设置用户注册类型
        if (shop_type.equals("0")) {
            //普通用户
            userBean.setShopType(shop_type);
            userBean.setAuthId(2);

        } else if (shop_type.equals("1")) {
            //商人
            userBean.setShopType(shop_type);
            userBean.setAuthId(1);

        } else {
            return new JsonUtil().wrongNoDataWithMessage("用户类型不存在");
        }


        UserBean userBean1 = mUserService.register(userBean);
        if (userBean1 != null) {
            return new JsonUtil().successDataWithMessage(userBean1,
                    "用户注册成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("用户注册失败");
    }


    @PostMapping("/refresh")
    @ApiOperation(value = "token刷新接口", notes = "刷新token")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "已授权token", name = "token", dataType = "String", paramType = "form", required = true)

    })
    public JsonResult refresh(@RequestParam("token") String token) throws AuthenticationException {
//        String authToken = request.getHeader(tokenHeader);
//        final String token = authToken.substring(tokenHeader.length());
        String username = mJwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) mUserDetailsService.loadUserByUsername(username);

        if (mJwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetTime())) {
            String refreshedToken = mJwtTokenUtil.refreshToken(token);
            AuthTokenBean tokenBean = new AuthTokenBean(refreshedToken);
            return new JsonUtil().successDataWithMessage(tokenBean, "token刷新成功");
        } else {
            return new JsonUtil().wrongNoDataWithMessage("token刷新失败");
        }

    }


}
