package com.server.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.server.controller.BaseController;
import com.server.dao.CategoryDao;
import com.server.dao.PicturesDao;
import com.server.dao.UsersDao;
import com.server.entity.CategoryEntity;
import com.server.entity.api.Ajax;
import com.server.entity.api.WarehousingReq;
import com.server.service.qcloud.CosService;
import com.server.utils.CommonUtils;
import com.server.utils.PublicHandleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(value = "美图吧管理后台接口中心", description = "美图吧管理后台接口中心")
@RestController
@RequestMapping("platform/api/")
public class PlatformController extends BaseController {

    @Autowired
    CosService cosService;

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    PicturesDao picturesDao;
    @Autowired
    UsersDao usersDao;


    //腾讯云cos所需参数
    @Value("${cos.path}")
    private String cosPaths;


    /**
     * TODO 文件上传
     *
     * @param id     商品ID
     * @param file   上传的文件
     * @param status 用于区分文件上传到哪个cosPath
     */
    @ApiOperation(value = "文件上传", notes = "文件上传",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Ajax uploadFile(@RequestParam(value = "id") String id,
                           @RequestParam(value = "file") CommonsMultipartFile file,
                           @RequestParam(value = "status", defaultValue = "0") int status) throws Exception {

        logger.info("客户端请求数据（platform/api/uploadFile）：上传商品的对应ID：{}；" +
                "上传文件的状态码：{}；上传的资源文件：{}", id, status, file.getOriginalFilename());


        //从配置文件中获取cosPath参数
        String cosPath = PublicHandleUtils.getCosPath(cosPaths, status);

        String access_url = "";

        //文件上传的类型 默认 1: 图片
        int type = 1;

        //获取客户端需要上传的文件
        CommonsMultipartFile cmf = file;

        if (cmf != null) {

            //获取文件的后缀名
            String suffixName = file.getOriginalFilename();
            int indexOf = suffixName.lastIndexOf(".");
            if (indexOf < 0) indexOf = suffixName.length();
            suffixName = suffixName.substring(indexOf);         //需要测试
            logger.info("上传的文件后缀名为：{}", suffixName);

            //判断上传的文件是图片还是视频
//            type = PublicHandleUtils.getType(suffixName);

            //生成文件名
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //避免快速点击上传造成同名，提示文件已存在错误：生成4位随机数
            fileName = fileName + CommonUtils.getRandomString(4);

            //加载对象存储 COS
            cosService.load();
            InputStream is = null;
            try {
                is = cmf.getInputStream();

                //文件上传到腾讯云
                String isUpload = cosService.uploadByIo(cosPath + fileName + suffixName, is);

                JSONObject jsonObject = JSONObject.parseObject(isUpload);
                int code = jsonObject.getIntValue("code");

                if (code >= 0) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    access_url = data.getString("access_url");
                    logger.info("商品ID（{}）上传的资源文件地址为：{}", id, access_url);
                } else {
                    logger.warn("上传出错，错误信息：{code:{},message:{}}",
                            jsonObject.getIntValue("code"), jsonObject.getString("message"));

                    return new Ajax(jsonObject.getIntValue("code"), jsonObject.getString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cosService.getCosClient().shutdown();
            }
        } else {
            throw new Exception("无法获取上传的文件！");
        }

        //参数封装
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessUrl", access_url);
        logger.info("服务端返回数据（platform/api/uploadFile）：{}", jsonObject.toJSONString());
        return new Ajax(jsonObject);
    }


    /**
     * 图片新增
     */
    @ApiOperation(value = "图片集合入库", notes = "图片集合入库",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "warehousing", method = RequestMethod.POST)
    public Ajax warehousing(@RequestBody WarehousingReq warehousingReq) throws Exception {

        logger.info("客户端（warehousing）请求数据：{}", warehousingReq.toString());

        String title = warehousingReq.getTitle();
        String head_img = warehousingReq.getImagePath();
        List<String> pictures = warehousingReq.getPictures();

        if (pictures.size() <= 0) throw new Exception("请先上传图片组");

        //用于获取新增ID
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryDao.insert(head_img, title, categoryEntity);

        int category_id = categoryEntity.getId();

        for (String picture : pictures) {
            picturesDao.insert(picture, category_id);
        }

        logger.info("platform/api/warehousing 接口返回");

        return new Ajax("入库成功");
    }


    /**
     * TODO 文件上传
     *
     * @param file 上传的文件
     */
    @ApiOperation(value = "文件上传", notes = "文件上传",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "uploader", method = RequestMethod.POST)
    public Ajax uploader(@RequestParam(value = "file") CommonsMultipartFile file) throws Exception {

        logger.info("客户端请求数据（platform/api/uploadFile）：上传商品的对应ID：{}；" +
                "上传文件的状态码：{}；上传的资源文件：{}", file.getOriginalFilename());

        int status = 4;

        //从配置文件中获取cosPath参数
        String cosPath = PublicHandleUtils.getCosPath(cosPaths, status);

        String access_url = "";

        //文件上传的类型 默认 1: 图片
        int type = 1;

        //获取客户端需要上传的文件
        CommonsMultipartFile cmf = file;

        if (cmf != null) {

            //获取文件的后缀名
            String suffixName = file.getOriginalFilename();
            int indexOf = suffixName.lastIndexOf(".");
            if (indexOf < 0) indexOf = suffixName.length();
            suffixName = suffixName.substring(indexOf);         //需要测试
            logger.info("上传的文件后缀名为：{}", suffixName);

            //判断上传的文件是图片还是视频
//            type = PublicHandleUtils.getType(suffixName);

            //生成文件名
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //避免快速点击上传造成同名，提示文件已存在错误：生成4位随机数
            fileName = fileName + CommonUtils.getRandomString(4);

            //加载对象存储 COS
            cosService.load();
            InputStream is = null;
            try {
                is = cmf.getInputStream();

                //文件上传到腾讯云
                String isUpload = cosService.uploadByIo(cosPath + fileName + suffixName, is);

                JSONObject jsonObject = JSONObject.parseObject(isUpload);
                int code = jsonObject.getIntValue("code");

                if (code >= 0) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    access_url = data.getString("access_url");
                    logger.info("商品ID（{}）上传的资源文件地址为：{}", access_url);
                } else {
                    logger.warn("上传出错，错误信息：{code:{},message:{}}",
                            jsonObject.getIntValue("code"), jsonObject.getString("message"));

                    return new Ajax(jsonObject.getIntValue("code"), jsonObject.getString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cosService.getCosClient().shutdown();
            }
        } else {
            throw new Exception("无法获取上传的文件！");
        }

        //参数封装
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessUrl", access_url);
        logger.info("服务端返回数据（platform/api/uploadFile）：{}", jsonObject.toJSONString());
        return new Ajax(jsonObject);
    }


    @ApiOperation(value = "当日数据统计", notes = "当日数据统计",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "statistics", method = RequestMethod.POST)
    public Ajax uploader() throws Exception {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //当日新增人数
        int userCount = usersDao.countByToday(date + " 00:00:00", date + " 23:59:59");

        //总人数
        int allUserCount = usersDao.countAllUser();

        //参数封装
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userCount", userCount);
        jsonObject.put("allUserCount", allUserCount);

        logger.info("服务端返回数据（platform/api/statistics）：{}", jsonObject.toJSONString());
        return new Ajax(jsonObject);
    }


}
