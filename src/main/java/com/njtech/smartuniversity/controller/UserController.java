package com.njtech.smartuniversity.controller;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.EmailInfoBean;
import com.njtech.smartuniversity.bean.UserBean;
import com.njtech.smartuniversity.service.EmailInfoService;
import com.njtech.smartuniversity.service.UserService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import com.njtech.smartuniversity.util.Validator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ritchie on 6/25/18
 */

@RestController
@Api(value = "/api/users", tags = {"用户"}, description = "用户相关")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService mUserService;

    @Autowired
    EmailInfoService mEmailInfoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "单个用户查询接口", notes = "使用ID查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID(学号/工号)", name = "id", dataType = "String", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult GetUser(@PathVariable("id") String id) {
        UserBean user = mUserService.selectByUserId(id);
        if (user != null) {
            return new JsonUtil().successDataDefault(user);
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该用户");
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "用户列表接口", notes = "获取所有用户信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListUsers() {
        List<UserBean> userBeanList = mUserService.getAllUsers();
        if (userBeanList != null) {
            return new JsonUtil().successDataDefault(userBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在用户信息");

    }


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "用户列表分页接口", notes = "获取分页用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "page", dataType = "int", paramType = "form"),
            @ApiImplicitParam(value = "每页数量", name = "size", dataType = "int", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListPageUsers(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        PageInfo<UserBean> pageInfo = mUserService.getUsersByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在用户信息");

    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "用户删除接口", notes = "删除指定ID的用户")
    @ApiImplicitParam(value = "用户ID", name = "id", dataType = "String", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult DeleteUser(@PathVariable("id") String id) {
        boolean result = mUserService.deleteById(id);
        if (result) {

            return new JsonUtil().successEmptyDataDefault("删除该用户成功");

        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该用户");

    }


    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "用户信息修改接口", notes = "修改指定ID的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID", name = "id", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户状态(是否可用，0不可用，1可用)", name = "state", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "用户权限id", name = "auth_id", dataType = "int", paramType = "form"),
            @ApiImplicitParam(value = "用户类型(0普通用户 1商家)", name = "shop_type", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult UpdateUser(@PathVariable("id") String id,
                                 @RequestParam String state,
                                 @RequestParam int auth_id,
                                 @RequestParam String shop_type
    ) {

        UserBean userBean = mUserService.selectByUserId(id);
        if (state != null && !state.isEmpty()) {
            userBean.setState(state);
        }
        if (auth_id != 0) {
            userBean.setAuthId(auth_id);
        }
        if (shop_type != null && !shop_type.isEmpty()) {
            userBean.setShopType(shop_type);
        }
        UserBean newUserBean = mUserService.update(userBean);
        return new JsonUtil().successDataWithMessage(newUserBean, "修改用户信息成功");
    }


    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "商家/用户接口添加", notes = "用户添加")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户状态(是否可用，0不可用，1可用)", name = "state", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户注册类型(0邮箱，1手机，2帐号)", name = "type", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户权限id", name = "auth_id", dataType = "int", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户类型(0普通用户 1商家)", name = "shop_type", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "用户名", name = "account", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "邮箱", name = "email", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "邮箱验证码", name = "verifyCode", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "手机", name = "phone", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "用户密码", name = "password", dataType = "String", paramType = "form")
    })
    public JsonResult register(
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "verifyCode", required = false) String verifyCode,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam("state") String state,
            @RequestParam("type") String type,
            @RequestParam("auth_id") int auth_id,
            @RequestParam("shop_type") String shop_type) throws AuthenticationException {

        UserBean userBean = new UserBean();

        //生成id
        String newId = String.valueOf(System.currentTimeMillis()).substring(1);
        userBean.setId(newId);

        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userBean.setUuid(uuid);

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
        } else if (type.equals("1") && !phone.isEmpty() && Validator.isMobile(phone)) {
            userBean.setPhone(phone);
            if (!password.isEmpty() && Validator.isPassword(password)) {
                userBean.setPassword(password);
            }
            //验证用户账号
        } else if (type.equals("2") && !account.isEmpty() && Validator.isPassword(password)) {
            userBean.setAccount(account);
            userBean.setPassword(password);

        }
        //设置用户注册类型
        userBean.setType(type);
        userBean.setAuthId(auth_id);
        userBean.setShopType(shop_type);
        userBean.setState(state);
        UserBean userBean1 = mUserService.register(userBean);
        if (userBean1 != null) {
            return new JsonUtil().successDataWithMessage(userBean1,
                    "用户注册成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("用户注册失败");
    }


}
