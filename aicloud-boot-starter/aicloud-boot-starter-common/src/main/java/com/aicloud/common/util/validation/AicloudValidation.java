/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.validation;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * JSR303校验
 */
public class AicloudValidation {
    // JSR303校验器
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 获取JSR303校验器
     */
    public static Validator getValidator() {
        return validator;
    }
}
