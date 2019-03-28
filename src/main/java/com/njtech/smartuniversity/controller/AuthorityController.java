package com.njtech.smartuniversity.controller;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.AuthorityBean;
import com.njtech.smartuniversity.bean.UserBean;
import com.njtech.smartuniversity.service.AuthorityService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ritchie on 6/25/18
 */

@RestController
@Api(value = "/api/authorities", tags = {"权限"}, description = "权限相关")
@RequestMapping("/api/authorities")
public class AuthorityController {

    @Autowired
    AuthorityService mAuthorityService;


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "单个权限查询接口", notes = "使用ID查询权限")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "权限ID", name = "id", dataType = "int", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult GetAuthority(@PathVariable("id") int id) {
        AuthorityBean authorityBean = mAuthorityService.selectByAuthorityId(id);
        if (authorityBean != null) {
            return new JsonUtil().successDataDefault(authorityBean);
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该权限");
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "权限列表接口", notes = "获取所有权限信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListAuthorities() {
        List<AuthorityBean> authorityBeanList = mAuthorityService.getAllAuthorities();
        if (authorityBeanList != null) {
            return new JsonUtil().successDataDefault(authorityBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在权限信息");

    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "权限列表分页接口", notes = "获取分页权限信息")
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
        PageInfo<AuthorityBean> pageInfo = mAuthorityService.getAuthorityByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在权限信息");
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "权限删除接口", notes = "删除指定ID的权限")
    @ApiImplicitParam(value = "权限ID", name = "id", dataType = "int", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult DeleteAuthority(@PathVariable("id") int id) {
        boolean result = mAuthorityService.deleteById(id);
        if (result) {
            return new JsonUtil().successEmptyDataDefault("删除该权限成功");
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该权限");
    }


    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "权限添加接口", notes = "权限添加")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "权限名称", name = "au_name", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "权限详情", name = "au_details", dataType = "String", paramType = "form", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult AddAuthority(@RequestParam("au_name") String name,
                                   @RequestParam("au_details") String details) {
        AuthorityBean authorityBean = new AuthorityBean();

        if (details != null && !details.isEmpty()) {
            authorityBean.setAuDetails(details);
        }
        authorityBean.setAuName(name);

        AuthorityBean authorityBean1 = mAuthorityService.addAuthority(authorityBean);
        if (authorityBean1 != null) {
            return new JsonUtil().successDataWithMessage(authorityBean1,
                    "权限添加成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("权限添加失败");
    }


    @PatchMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "权限信息接口", notes = "修改指定ID的权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "权限ID", name = "id", dataType = "int", paramType = "path", required = true),
            @ApiImplicitParam(value = "权限名称", name = "au_name", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "权限详情", name = "au_details", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult UpdateAuthority(@PathVariable("id") int id,
                                      @RequestParam(value = "au_name", required = false) String name,
                                      @RequestParam(value = "au_details", required = false) String details
    ) {

        AuthorityBean authorityBean = mAuthorityService.selectByAuthorityId(id);
        if (name != null && !name.isEmpty()) {
            authorityBean.setAuName(name);
        }
        if (details != null && !details.isEmpty()) {
            authorityBean.setAuDetails(details);
        }
        AuthorityBean authorityBean1 = mAuthorityService.update(authorityBean);
        return new JsonUtil().successDataWithMessage(authorityBean1, "修改用户信息成功");
    }

}
