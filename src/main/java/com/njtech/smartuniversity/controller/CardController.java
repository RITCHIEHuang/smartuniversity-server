package com.njtech.smartuniversity.controller;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CardBean;
import com.njtech.smartuniversity.service.CardService;
import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jimmy on 6/28/18
 */

@RestController
@Api(value = "/api/cards", tags = {"一卡通"}, description = "一卡通相关")
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    CardService mCardService;

    /**
     * 查询单个卡号信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "单个一卡通查询接口", notes = "使用一卡通权限")
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
    public JsonResult GetCard(@PathVariable("id") int id) {
        CardBean cardBean = mCardService.selectByCardId(id);
        if (cardBean != null) {
            return new JsonUtil().successDataDefault(cardBean);
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在该卡号");
    }


    /**
     * 查询某用户的卡号信息
     *
     * @param sq_id
     * @return
     */
    @GetMapping("/application/{sq_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "一卡通进度查询接口", notes = "一卡通进度查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户ID", name = "sq_id", dataType = "String", paramType = "path", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult GetCardState(@PathVariable("sq_id") String sq_id) {
        CardBean cardBean = mCardService.selectBySQId(sq_id);
        if (cardBean != null) {
            return new JsonUtil().successDataDefault(cardBean);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在该申请");
    }

    /**
     * 查询所有一卡通信息
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "一卡通列表接口", notes = "获取所有一卡通信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListCards() {
        List<CardBean> cardBeanList = mCardService.getAllCards();
        if (cardBeanList != null) {
            return new JsonUtil().successDataDefault(cardBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有一卡通信息");

    }


    /**
     * 查询不可用状态的一卡通数量
     *
     * @return
     */
    @GetMapping("/unpassnum")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "不可用状态数量接口", notes = "获取所有不可用状态数量")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult getUnpassNum() {
        long unpasscnt = mCardService.getUnpassCount();
        return new JsonUtil().successDataDefault(unpasscnt);

    }


    /**
     * 查询一卡通数量
     *
     * @return
     */
    @GetMapping("/totalnum")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "不可用状态数量接口", notes = "获取所有不可用状态数量")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult getTotalNum() {
        long unpasscnt = mCardService.getTotalCount();
        return new JsonUtil().successDataDefault(unpasscnt);

    }

    /**
     * 查询所有未通过一卡通信息
     *
     * @return
     */
    @GetMapping("/unpasslist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "未通过一卡通列表接口", notes = "获取所有未通过一卡通信息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult ListUnPassCards() {
        List<CardBean> cardBeanList = mCardService.getAllUnpassCards();
        if (cardBeanList != null) {
            return new JsonUtil().successDataDefault(cardBeanList);
        }
        return new JsonUtil().wrongNoDataWithMessage("没有未通过一卡通信息");

    }


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "一卡通列表分页接口", notes = "获取分页一卡通信息")
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
    public JsonResult ListPageCards(@RequestParam(value = "page", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        PageInfo<CardBean> pageInfo = mCardService.getCardsByPage(pageNum, pageSize);
        if (pageInfo != null) {
            return new JsonUtil().successDataDefault(pageInfo);
        }
        return new JsonUtil().wrongNoDataWithMessage("不存在一卡通信息");

    }


    /**
     * 删除一卡通信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "一卡通删除接口", notes = "删除指定ID的一卡通信息")
    @ApiImplicitParam(value = "一卡通ID", name = "id", dataType = "int", paramType = "path", required = true)
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult DeleteCard(@PathVariable("id") int id) {
        boolean result = mCardService.deleteById(id);
        if (result) {
            return new JsonUtil().successEmptyDataDefault("删除该一卡通成功");
        }

        return new JsonUtil().wrongNoDataWithMessage("不存在一卡通信息");
    }

    /**
     * 申请一卡通信息
     *
     * @param sqId
     * @param authId
     * @return
     */
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER')")
    @ApiOperation(value = "一卡通申请接口", notes = "一卡通申请")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "申请id", name = "sq_id", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "一卡通权限", name = "auth_id", dataType = "int", paramType = "form", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult addCard(@RequestParam("sq_id") String sqId,
                              @RequestParam("auth_id") int authId) {
        CardBean cardBean = new CardBean();
        String card_vid = UUID.randomUUID().toString().replace("-", "");
        cardBean.setCardVid(card_vid);
        cardBean.setState("0");
        cardBean.setApplicationTime(new Date());
        if (sqId != null && !sqId.isEmpty()) {
            cardBean.setSqId(sqId);
        }

        if (authId != 0) {
            cardBean.setAuthId(authId);
        }

        CardBean cardBean1 = mCardService.addCard(cardBean);
        if (cardBean1 != null) {
            return new JsonUtil().successDataWithMessage(cardBean1, "一卡通申请成功");

        }
        return new JsonUtil().wrongNoDataWithMessage("一卡通申请失败");
    }

    /**
     * 通过一卡通信息
     *
     * @param id
     * @param state
     * @return
     */

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "一卡通信息修改接口", notes = "修改指定ID的一卡通信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态", name = "state", dataType = "String", paramType = "form", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public JsonResult updateCard(@PathVariable("id") int id,
                                 @RequestParam String state
    ) {
        CardBean cardBean = mCardService.selectByCardId(id);
        if (state != null) {
            cardBean.setState(state);
        }
        CardBean newCardBean = mCardService.update(cardBean);
        return new JsonUtil().successDataWithMessage(newCardBean, "修改一卡通信息成功");
    }

}

