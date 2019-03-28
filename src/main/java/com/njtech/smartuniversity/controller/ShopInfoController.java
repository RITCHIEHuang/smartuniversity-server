package com.njtech.smartuniversity.controller;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.ShopInfoBean;
import com.njtech.smartuniversity.service.ShopInfoService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Jimmy on 11:01 2018/6/30
 */

@RestController
@Api(value = "/api/shopinfos", tags = {"商家"}, description = "商家相关")
@RequestMapping("/api/shopinfos")
public class ShopInfoController {
    @Autowired
    ShopInfoService mShopInfoService;

    /**
     * 查询单个商家信息
     *
     * @param uuid
     * @return
     */
    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "单个商家查询接口", notes = "使用商家权限")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商家ID", name = "uuid", dataType = "int", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult GetCard(@PathVariable("uuid") String uuid) {
        ShopInfoBean shopInfoBean = mShopInfoService.selectByShopInfoUUID(uuid);
        if (shopInfoBean != null) {
            return new JsonUtil().successDataDefault(shopInfoBean);
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该商家");
    }

    /**
     * 查询所有商家信息
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家列表接口", notes = "获取所有商家信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListShopInfo() {
        List<ShopInfoBean> shopInfoBeanList = mShopInfoService.getAllShopInfos();
        if (shopInfoBeanList != null) {
            return new JsonUtil().successDataDefault(shopInfoBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有任何商家信息");

    }


    /**
     * 查询商户的商家信息
     *
     * @return
     */
    @GetMapping("/shop/{user_uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商户商店查询接口", notes = "获取商户的商店信息")
    @ApiImplicitParam(value = "商户ID", name = "user_uuid", dataType = "String", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult getShopInfo(@PathVariable("user_uuid") String userUUId) {
        List<ShopInfoBean> shopInfoBeanList = mShopInfoService.selectbyUserUUid(userUUId);
        if (shopInfoBeanList != null) {
            return new JsonUtil().successDataDefault(shopInfoBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有该商户的商家信息");

    }


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家列表分页接口", notes = "获取分页商家信息")
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
    public JsonResult ListPageShopInfos(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        PageInfo<ShopInfoBean> pageInfo = mShopInfoService.getShopInfosByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在商家信息");

    }


    /**
     * 删除商家信息
     *
     * @param uuid
     * @return
     */
    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家删除接口", notes = "删除指定ID的商家信息")
    @ApiImplicitParam(value = "商家ID", name = "uuid", dataType = "String", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult DeleteShopInfo(@PathVariable("uuid") String uuid) {
        boolean result = mShopInfoService.deleteByUUId(uuid);
        if (result) {
            return new JsonUtil().successEmptyDataDefault("删除该商家成功");
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该商家信息");
    }


    /**
     * 添加商家信息
     *
     * @param shopName
     * @param address
     * @param shopState
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家注册接口", notes = "商家注册")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "老板ID", name = "user_uuid", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商家名", name = "shop_name", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商家地址", name = "show_addr", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商家状态", name = "state", dataType = "String", paramType = "form", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult addShopInfo(@RequestParam("user_uuid") String userUUid,
                                  @RequestParam("shop_name") String shopName,
                                  @RequestParam("show_addr") String address,
                                  @RequestParam("state") String shopState) {
        ShopInfoBean shopInfoBean = new ShopInfoBean();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        shopInfoBean.setUuid(uuid);
        if (userUUid != null && !userUUid.isEmpty()) {
            shopInfoBean.setUserUuid(userUUid);
        }
        if (shopName != null && !shopName.isEmpty()) {
            shopInfoBean.setShopName(shopName);
        }

        if (address != null && !address.isEmpty()) {
            shopInfoBean.setShopAddr(address);
        }

        if (shopState != null && !shopState.isEmpty()) {
            shopInfoBean.setState(shopState);
        }

        ShopInfoBean shopInfoBean1 = mShopInfoService.addShopInfoBean(shopInfoBean);
        if (shopInfoBean1 != null) {
            return new JsonUtil().successDataWithMessage(shopInfoBean1, "商家注册成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("商家注册失败");
    }

    /**
     * 修改商家信息
     *
     * @param uuid
     * @param shopName
     * @param address
     * @param state
     * @return
     */

    @PatchMapping(value = "/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家信息修改接口", notes = "修改指定ID的商家信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商家uuid", name = "uuid", dataType = "String", paramType = "path"),
            @ApiImplicitParam(value = "商家名", name = "shop_name", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商家地址", name = "show_addr", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "状态", name = "state", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult updateShopInfo(@PathVariable("uuid") String uuid,
                                     @RequestParam String shopName,
                                     @RequestParam String address,
                                     @RequestParam String state
    ) {
        ShopInfoBean shopInfoBean = mShopInfoService.selectByShopInfoUUID(uuid);
        if (shopName != null) {
            shopInfoBean.setShopName(shopName);
        }
        if (address != null) {
            shopInfoBean.setShopAddr(address);
        }
        if (state != null) {
            shopInfoBean.setState(state);
        }
        ShopInfoBean newShopInfoBean = mShopInfoService.update(shopInfoBean);
        return new JsonUtil().successDataWithMessage(newShopInfoBean, "修改商家信息成功");
    }


}
