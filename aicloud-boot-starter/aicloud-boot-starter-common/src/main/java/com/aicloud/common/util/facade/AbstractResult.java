/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved.
 * @PackageName:com.aicloud 
 * @Date:2018年2月8日上午9:39:02  
 * 
*/

package com.aicloud.common.util.facade;

import com.aicloud.common.util.tostring.ToString;

import java.io.Serializable;

/**
 * 抽象result（所有result的父类）
 */
public abstract class AbstractResult implements Serializable {
    // 状态
    private Status status;
    // 结果码
    private String code;
    // 描述信息
    private String message;

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ToString.toString(this);
    }
}
