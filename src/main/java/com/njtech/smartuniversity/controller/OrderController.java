package com.njtech.smartuniversity.controller;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CustomGoodsBean;
import com.njtech.smartuniversity.bean.OrderBean;
import com.njtech.smartuniversity.service.OrderService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jimmy on 22:13 2018/6/29
 */

@RestController
@Api(value = "/api/orders", tags = {"订单"}, description = "订单相关")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService mOrderService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "单个订单查询接口", notes = "使用订单权限")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单ID", name = "id", dataType = "int", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })

    public JsonResult GetOrder(@PathVariable("id") int id) {
        OrderBean orderBean = mOrderService.selecteByOrderId(id);
        if (orderBean != null) {
            return new JsonUtil().successDataDefault(orderBean);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在该订单");
    }

    @GetMapping("/user/{user_uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "用户订单查询接口", notes = "用户订单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单ID", name = "user_uuid", dataType = "String", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })

    public JsonResult GetOrder(@PathVariable("user_uuid") String user_uuid) {
        List<OrderBean> orderBean = mOrderService.getUserOrders(user_uuid);
        if (orderBean != null) {
            return new JsonUtil().successDataDefault(orderBean);
        }
        return new JsonUtil().wrongNoDataWithMessage("该用户没有订单信息¬");
    }


    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "订单列表接口", notes = "获取所有订单信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListGoods() {
        List<OrderBean> orderBeanList = mOrderService.getAllOrders();
        if (orderBeanList != null) {
            return new JsonUtil().successDataDefault(orderBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有订单信息");
    }


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "订单列表分页接口", notes = "获取分页订单信息")
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
    public JsonResult ListPageOrders(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        PageInfo<OrderBean> pageInfo = mOrderService.getOrdersByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在订单信息");

    }

    @GetMapping("/hot")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "热门订单列表分页接口", notes = "获取分页热门订单信息")
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
    public JsonResult ListHotOrders(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        PageInfo<CustomGoodsBean> pageInfo = mOrderService.getHotOrdersByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在热门订单信息");

    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "订单信息删除接口", notes = "删除指定ID的订单信息")
    @ApiImplicitParam(value = "订单ID", name = "id", dataType = "int", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult DeleteCard(@PathVariable("id") int id) {
        boolean result = mOrderService.deleteById(id);
        if (result) {
            return new JsonUtil().successEmptyDataDefault("删除该订单信息成功");
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该订单信息");
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "订单信息添加接口", notes = "订单信息添加")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "自动生成UUID", name = "user_uuid", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商品ID", name = "g_id", dataType = "int", paramType = "form", required = true),
            @ApiImplicitParam(value = "购买数量", name = "num", dataType = "int", paramType = "form", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult addOrder(@RequestParam("user_uuid") String userUUID,
                               @RequestParam("g_id") int gid,
                               @RequestParam("num") int num) {
        OrderBean orderBean = new OrderBean();

        orderBean.setUserUuid(userUUID);
        orderBean.setgId(gid);
        orderBean.setNum(num);

        OrderBean orderBean1 = mOrderService.addOrderBean(orderBean);
        if (orderBean1 != null) {
            return new JsonUtil().successDataWithMessage(orderBean1, "购买成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("购买失败");
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "订单信息修改接口", notes = "订单信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "id", dataType = "int", paramType = "path", required = true),
            @ApiImplicitParam(value = "商品ID", name = "g_id", dataType = "int", paramType = "form", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult UpdateOrder(@RequestParam("id") int id,
                                  @RequestParam("g_id") int gid) {
//        OrderBean orderBean = mOrderService.selecteByOrderId(id);
//
//        orderBean.setgId(gid);
//
//        OrderBean orderBean1 = mOrderService.addOrderBean(orderBean);
//        if (orderBean1 != null) {
//            return new JsonUtil().successDataWithMessage(orderBean1, "订单修改成功");
//
//        }
        return new JsonUtil().wrongNoDataWithMessage("订单修改失败");
    }
}
