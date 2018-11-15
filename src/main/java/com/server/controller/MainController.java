package com.server.controller;

import com.server.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MainController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 文件上传测试
     *
     * @return
     */
    @RequestMapping("upload")
    public ModelAndView te() {
        return new ModelAndView("upload");
    }


    @ResponseBody
    @RequestMapping("test")
    public String test() throws Exception {

        return "";
    }

}
