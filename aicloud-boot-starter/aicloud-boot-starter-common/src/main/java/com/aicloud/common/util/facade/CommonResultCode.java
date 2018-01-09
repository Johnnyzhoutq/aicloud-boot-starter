/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 17:11 创建
 */
package com.aicloud.common.util.facade;

/**
 * 常用结果码
 */
public enum CommonResultCode {

    SUCCESS("common-0000", "成功"),

    UNKNOWN_ERROR("common-0001", "未知错误"),

    INVALID_PARAMETER("common-0002", "请求参数非法"),;

    // 结果码
    private String code;

    // 描述
    private String message;

    CommonResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取结果码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取描述
     */
    public String getMessage() {
        return message;
    }
}
