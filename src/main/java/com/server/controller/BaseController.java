package com.server.controller;

import com.server.dao.UsersDao;
import com.server.entity.UsersEntity;
import com.server.entity.api.Ajax;
import com.server.exceptions.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;

public abstract class BaseController implements Serializable {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UsersDao usersDao;

    @ExceptionHandler(Exception.class)
    public Ajax handleException(Exception ex) {
        logger.error("handleException", ex);
        if (ex instanceof AuthException) {
            return new Ajax(-9, ex.getMessage());
        }
        return new Ajax(-1, ex.getMessage());
    }

    protected UsersEntity auth(Integer user_id) throws Exception {
        if (user_id == null) {
            throw new AuthException("无效用户ID", -9);
        }
        UsersEntity userInfo = usersDao.queryByUserId(user_id);
        if (userInfo == null) {
            throw new AuthException("无效用户ID", -9);
        }
        return userInfo;
    }


}