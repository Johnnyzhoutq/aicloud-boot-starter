/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.validation.validator;

import java.util.regex.Pattern;

/**
 * 银行卡号校验器（16到22位的纯数字即校验通过）
 */
public class BankCarNoValidator {
    // 银行卡正则表达式
    private static Pattern BANK_CARD_NO_PATTERN = Pattern.compile("^[0-9]{16,22}$");

    /**
     * 校验
     *
     * @param bankCardNo 待校验银行卡号
     * @return true 校验通过；false 校验未通过
     */
    public static boolean validate(String bankCardNo) {
        if (bankCardNo == null) {
            return false;
        }
        return BANK_CARD_NO_PATTERN.matcher(bankCardNo).matches();
    }
}
