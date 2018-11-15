package com.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${cos.appId}")
    protected long cosAppId;
    @Value("${cos.secretId}")
    protected String cosSecretId;
    @Value("${cos.secretKey}")
    protected String cosSecretKey;
    @Value("${cos.region}")
    protected String cosRegion;


    @Value("${server.url}")
    protected String server;
}
