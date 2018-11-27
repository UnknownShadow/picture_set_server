package com.server.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.server.controller.BaseController;
import com.server.dao.*;
import com.server.entity.UsersEntity;
import com.server.entity.api.*;
import com.server.service.WeChatPayService;
import com.server.service.qcloud.CosService;
import com.server.utils.PublicUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "美图吧小程序接口中心", description = "美图吧小程序接口中心")
@RestController
@RequestMapping("small/api/")
public class SmallController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    UsersDao usersDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    PicturesDao picturesDao;
    @Autowired
    ShareDao shareDao;
    @Autowired
    PreviewRecordDao previewRecordDao;

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



    @ApiOperation(value = "小程序登录", notes = "小程序登录",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public Ajax signIn(@RequestBody SignInReq signIn) throws Exception {

        logger.info("客户端请求数据（small/api/signIn）：{}", signIn.toString());

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

        logger.info("服务端返回数据（small/api/signIn）：{}", json);
        return new Ajax(json);
    }


    @ApiOperation(value = "首页数据", notes = "首页数据",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public Ajax index(@RequestBody Paging paging) throws Exception {

        logger.info("客户端请求数据（small/api/index）：{}", paging.toString());

        Integer page = paging.getPage();
        Integer limit = paging.getLimit();
        if (page <= 0) page = 0;
        if (limit <= 0) limit = 20;
        int offset = page * limit;
        logger.info("limit值：{}、page值：{}", limit, offset);

        List<CategoryResp> categories = categoryDao.getAllByPaging(offset, limit);


        logger.info("服务端返回数据（small/api/index）：{}", categories.toArray());
        return new Ajax(categories);
    }


    @ApiOperation(value = "详情页面", notes = "详情页面",
            httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "details", method = RequestMethod.GET)
    public Ajax details(@RequestParam(value = "id") int id) throws Exception {

        logger.info("客户端请求数据（small/api/details）：{}", id);

        //增加访问量
        categoryDao.updatePvByID(id);

        List<JSONObject> details = picturesDao.queryByCategoryID(id);

        logger.info("服务端返回数据（small/api/details）：{}", details.toArray());
        return new Ajax(details);
    }


    @ApiOperation(value = "记录用户分享", notes = "记录用户分享",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "setShare", method = RequestMethod.POST)
    public Ajax setShare(@RequestBody SetShareReq setShareReq) throws Exception {

        logger.info("客户端请求数据（small/api/setShare）：{}", setShareReq.toString());

        shareDao.insert(setShareReq.getId(), setShareReq.getCategoryId(), setShareReq.getStatus());

        logger.info("服务端（small/api/setShare）返回");
        return new Ajax("记录成功");
    }


    @ApiOperation(value = "记录用户进入小程序的次数", notes = "记录用户进入小程序的次数",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "setTimes", method = RequestMethod.POST)
    public Ajax setTimes(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（small/api/setTimes）：{}", apiRequest.toString());

        //增加小程序进入的次数
        usersDao.updateTimesByID(apiRequest.getId());

        logger.info("服务端（small/api/setTimes）返回");
        return new Ajax("记录成功");
    }


    @ApiOperation(value = "记录用户的图片", notes = "记录用户的图片",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "setPreview", method = RequestMethod.POST)
    public Ajax setPreview(@RequestBody SetPreviewReq setPreviewReq) throws Exception {

        logger.info("客户端请求数据（small/api/setPreview）：{}", setPreviewReq.toString());

        //增加小程序进入的次数
        previewRecordDao.insert(setPreviewReq.getId(),setPreviewReq.getPicturesId());

        logger.info("服务端（small/api/setPreview）返回");
        return new Ajax("记录成功");
    }

}

