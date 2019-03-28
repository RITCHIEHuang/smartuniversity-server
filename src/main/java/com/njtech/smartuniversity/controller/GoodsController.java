package com.njtech.smartuniversity.controller;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.service.GoodsService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import com.qiniu.util.Json;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jimmy on 20:52 2018/6/29
 */

@RestController
@Api(value = "/api/goods", tags = {"商品"}, description = "商品相关")
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    GoodsService mGoodsService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "单个商品查询接口", notes = "使用商品权限")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "一卡通ID", name = "id", dataType = "int", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })

    public JsonResult GetGoods(@PathVariable("id") int id) {
        GoodsBean goodsBean = mGoodsService.selectByGoodsID(id);
        if (goodsBean != null) {
            return new JsonUtil().successDataDefault(goodsBean);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在该商品");
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "商品列表接口", notes = "获取所有商品信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListGoods() {
        List<GoodsBean> goodsBeanList = mGoodsService.getAllGoods();
        if (goodsBeanList != null) {
            return new JsonUtil().successDataDefault(goodsBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有商品信息");
    }


    @GetMapping("/userlist")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "用户商品列表接口", notes = "获取所有上架商品信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult UserListGoods() {
        List<GoodsBean> goodsBeanList = mGoodsService.getAllAvaliableGoods();
        if (goodsBeanList != null) {
            return new JsonUtil().successDataDefault(goodsBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有商品信息");
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商品列表分页接口", notes = "获取分页商品信息")
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
    public JsonResult ListPageGoods(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        PageInfo<GoodsBean> pageInfo = mGoodsService.getGoodsByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在商品信息");

    }


    @GetMapping("/shop/{s_i_uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家商品列表接口", notes = "获取商家商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商家id", name = "s_i_uuid", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListShopGoods(@PathVariable(value = "s_i_uuid") String shopId) {
        List<GoodsBean> goodsBeanList = mGoodsService.getShopGoods(shopId);
        if (goodsBeanList != null) {
            return new JsonUtil().successDataDefault(goodsBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有该商家的商品信息");
    }


    @GetMapping("/merchant/{user_uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商家商品列表接口", notes = "获取商家商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商家id", name = "user_uuid", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListMerchantGoods(@PathVariable(value = "user_uuid") String userId) {
        List<GoodsBean> goodsBeanList = mGoodsService.getMerchantGoods(userId);
        if (goodsBeanList != null) {
            return new JsonUtil().successDataDefault(goodsBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有该商户的商品信息");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商品信息删除接口", notes = "删除指定ID的商品信息")
    @ApiImplicitParam(value = "商品ID", name = "id", dataType = "int", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult DeleteCard(@PathVariable("id") int id) {
        boolean result = mGoodsService.deleteById(id);
        if (result) {
            return new JsonUtil().successEmptyDataDefault("删除该商品信息成功");
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该商品信息");
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商品信息添加接口", notes = "商品信息添加")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品名", name = "g_name", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商品价格", name = "g_price", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "数量", name = "g_quantity", dataType = "int", paramType = "form", required = true),
            @ApiImplicitParam(value = "商品来源", name = "g_ly", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "条形码", name = "g_vid", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商家ID", name = "s_i_uuid", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "商品图片", name = "g_img", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品缩略图", name = "g_simg", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品描述", name = "g_label", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品详情", name = "g_details", dataType = "String", paramType = "form")

    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult addGoods(@RequestParam("g_name") String gName,
                               @RequestParam("g_price") String price,
                               @RequestParam("g_quantity") int quantity,
                               @RequestParam("g_ly") String gly,
                               @RequestParam("g_vid") String gVID,
                               @RequestParam("s_i_uuid") String UUID,
                               @RequestParam(value = "g_img", required = false) String image,
                               @RequestParam(value = "g_simg", required = false) String gsimg,
                               @RequestParam(value = "g_label", required = false) String label,
                               @RequestParam(value = "g_details", required = false) String detail
    ) {

        GoodsBean goodsBean = new GoodsBean();

        goodsBean.setsIUuid(UUID);
        if (gName != null && !gName.isEmpty()) {
            goodsBean.setgName(gName);
        }

        if (price != null && !price.isEmpty()) {
            goodsBean.setgPrice(price);
        }
        goodsBean.setgQuantity(quantity);
        if (gly != null && !gly.isEmpty()) {
            goodsBean.setgLy(gly);
        }


        goodsBean.setgState("1");
        goodsBean.setgVid(gVID);
        goodsBean.setgImg(image);
        goodsBean.setgSimg(gsimg);
        goodsBean.setgLabel(label);
        goodsBean.setgDetails(detail);

        GoodsBean goodsBean1 = mGoodsService.addGoods(goodsBean);
        if (goodsBean1 != null) {
            return new JsonUtil().successDataWithMessage(goodsBean1, "商品添加成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("商品添加失败");
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商品信息修改接口", notes = "修改指定ID的商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品ID", name = "id", dataType = "int", paramType = "path", required = true),
            @ApiImplicitParam(value = "商品名", name = "g_name", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品价格", name = "g_price", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "质量", name = "g_quantity", dataType = "int", paramType = "form"),
            @ApiImplicitParam(value = "商品来源", name = "g_ly", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "条形码", name = "g_vid", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品图片", name = "g_img", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品缩略图", name = "g_simg", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品描述", name = "g_label", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品详情", name = "g_details", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "商品状态", name = "g_state", dataType = "String", paramType = "form")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult updateGood(@PathVariable(value = "id") int id,
                                 @RequestParam(value = "g_name", required = false) String gName,
                                 @RequestParam(value = "g_price", required = false) String price,
                                 @RequestParam(value = "g_quantity", required = false, defaultValue = "0") int quantity,
                                 @RequestParam(value = "g_ly", required = false) String gly,
                                 @RequestParam(value = "g_vid", required = false) String gVID,
                                 @RequestParam(value = "g_img", required = false) String image,
                                 @RequestParam(value = "g_simg", required = false) String simage,
                                 @RequestParam(value = "g_label", required = false) String label,
                                 @RequestParam(value = "g_details", required = false) String detail,
                                 @RequestParam(value = "g_state", required = false) String state
    ) {
        GoodsBean goodsBean = mGoodsService.selectByGoodsID(id);

        if (gName != null && !gName.isEmpty()) {
            goodsBean.setgName(gName);
        }
        if (price != null && !price.isEmpty()) {
            goodsBean.setgPrice(price);
        }
        if (quantity != 0) {
            goodsBean.setgQuantity(quantity);
        }
        if (gly != null && !gly.isEmpty()) {
            goodsBean.setgLy(gly);
        }
        if (gVID != null && !gVID.isEmpty()) {
            goodsBean.setgVid(gVID);
        }
        if (image != null && !image.isEmpty()) {
            goodsBean.setgImg(image);
        }
        if (simage != null && !simage.isEmpty()) {
            goodsBean.setgSimg(simage);
        }
        if (label != null && !label.isEmpty()) {
            goodsBean.setgLabel(label);
        }
        if (detail != null && !detail.isEmpty()) {
            goodsBean.setgDetails(detail);
        }
        if (state != null && !state.isEmpty()) {
            System.out.println("=====================" + state);
            goodsBean.setgState(state);
        }

        GoodsBean newGoodesBean = mGoodsService.update(goodsBean);
        return new JsonUtil().successDataWithMessage(newGoodesBean, "修改商品信息成功");
    }


    @PostMapping(value = "/add/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "商品增加修改接口", notes = "指定商品补货")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品ID", name = "id", dataType = "int", paramType = "path", required = true),
            @ApiImplicitParam(value = "质量", name = "add_num", dataType = "int", paramType = "form", required = true)
    })
    public JsonResult addGood(@PathVariable("id") int id,
                              @RequestParam("add_num") int addNum) {

        GoodsBean goodsBean = mGoodsService.selectByGoodsID(id);
        if (addNum >= 0) {
            int quantity = goodsBean.getgQuantity();
            int new_quantity = quantity + addNum;
            goodsBean.setgQuantity(new_quantity);
            if (new_quantity > 0) {
                goodsBean.setgState("1");
            }
            GoodsBean goodsBean1 = mGoodsService.update(goodsBean);
            return new JsonUtil().successDataWithMessage(goodsBean1, "补货成功!");
        } else {
            return new JsonUtil().wrongNoDataWithMessage("补货数量错误!");
        }
    }
}