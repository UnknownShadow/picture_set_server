package com.server.controller.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.server.controller.BaseController;
import com.server.dao.*;
import com.server.entity.*;
import com.server.entity.api.*;
import com.server.service.*;
import com.server.service.qcloud.CosService;
import com.server.service.qcloud.QcloudSmsService;
import com.server.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Api(value = "商城小程序接口中心", description = "商城小程序接口中心")
@RestController
@RequestMapping("store/api/")
public class StoreController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    UsersDao usersDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    PicturesDao picturesDao;

    @Autowired
    CosService cosService;
    @Autowired
    WeChatPayService weChatPayService;


    //腾讯云cos所需参数
    @Value("${cos.path}")
    private String cosPaths;

    //支付所需参数
    @Value("${small.appid}")
    private String appid;
    @Value("${small.mch_id}")
    private String mch_id;
    @Value("${small.apiKey}")
    private String apiKey;
    @Value("${small.notifyUrl}")
    private String notifyUrl;
    @Value("${small.unifiedOrder}")
    private String unifiedOrder;

    @Value("${server.token}")
    private String serverToken;


    @ApiOperation(value = "小程序登录", notes = "小程序登录",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public Ajax signIn(@RequestBody SignInReq signIn) throws Exception {

        logger.info("客户端请求数据（store/api/signIn）：{}", signIn.toString());

        int user_id = 0;    //用户ID

        //获取用户信息
        UserInfo userInfo = signIn.getUserInfo();
        if (PublicUtil.isEmpty(userInfo)) throw new Exception("无法获取用户信息");

        String code = signIn.getCode();
        if (PublicUtil.isEmpty(code)) throw new Exception("code不能为空！");

        //根据请求code获取 openid
        String openid = weChatPayService.getOpenId(code);
        if (PublicUtil.isEmpty(openid)) throw new Exception("登录失败，code错误！");

        //根据openid查询是否是新用户，
        //根据openid查询user_id
        UsersEntity usersInfo = usersDao.queryByOpenId(openid);
        if (PublicUtil.isEmpty(usersInfo)) {      //用户首次登录
            //用户数据信息入库
            usersDao.insertToUsers(openid, userInfo);
            user_id = userInfo.getId();
        } else {
            user_id = usersInfo.getId();
        }

        JSONObject json = new JSONObject();
        json.put("userId", user_id);

        logger.info("服务端返回数据（store/api/signIn）：{}", json);
        return new Ajax(json);
    }


    @ApiOperation(value = "首页数据", notes = "首页数据",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public Ajax index(@RequestBody Paging paging) throws Exception {

        logger.info("客户端请求数据（store/api/index）：{}", paging.toString());

        Integer page = paging.getPage();
        Integer limit = paging.getLimit();
        if (page <= 0) page = 0;
        if (limit <= 0) limit = 20;
        int offset = page * limit;
        logger.info("limit值：{}、page值：{}", limit, offset);

        List<CategoryResp> categories = categoryDao.getAllByPaging(offset, limit);


        logger.info("服务端返回数据（store/api/index）：{}", categories.toArray());
        return new Ajax(categories);
    }


    @ApiOperation(value = "详情页面", notes = "详情页面",
            httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "details", method = RequestMethod.GET)
    public Ajax details(@RequestParam(value = "id") int id) throws Exception {

        logger.info("客户端请求数据（store/api/details）：{}",id);

        List<JSONObject> details = picturesDao.queryByCategoryID(id);

        logger.info("服务端返回数据（store/api/details）：{}", details.toArray());
        return new Ajax(details);
    }


}

