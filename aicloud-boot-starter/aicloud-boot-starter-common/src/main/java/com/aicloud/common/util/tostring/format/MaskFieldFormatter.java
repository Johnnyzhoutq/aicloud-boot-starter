/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.tostring.format;

import com.aicloud.common.util.tostring.FieldFormatter;
import com.aicloud.common.util.validation.validator.*;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 掩码格式器
 */
public class MaskFieldFormatter implements FieldFormatter {
    // 全部掩码的字符长度
    private static final int ALL_MASK_STR_SIZE = 6;

    // 需被掩码的属性
    private Field field;
    // 被格式化的属性前段（属性名=）
    private String formattedPre;
    // 是否全部掩码
    private boolean allMask;
    // 掩码字符
    private char maskChar;
    // 全部掩码的字符串
    private String allMaskStr;

    @Override
    public void initialize(Field field) {
        if (field.getType() != String.class) {
            throw new IllegalArgumentException("@Mask只能标注在String类型字段上，" + field + "不是String类型");
        }

        this.field = field;
        formattedPre = field.getName() + "=";
        Mask maskAnnotation = AnnotatedElementUtils.findMergedAnnotation(field, Mask.class);
        allMask = maskAnnotation.allMask();
        maskChar = maskAnnotation.maskChar();
        allMaskStr = buildMaskAllStr(maskChar);
    }

    @Override
    public String format(Object obj) {
        String maskedStr;
        String str = (String) ReflectionUtils.getField(field, obj);
        if (str == null) {
            maskedStr = null;
        } else {
            if (allMask) {
                maskedStr = allMaskStr;
            } else {
                maskedStr = autoMask(str);
            }
        }

        return formattedPre + maskedStr;
    }

    // 自动判断需掩码部分
    private String autoMask(String str) {
        if (CertNoValidator.validate(str)) {
            // 身份证号明文：前1、后1
            return MaskUtils.mask(str, 1, 1, maskChar);
        }
        if (MobileNoValidator.validate(str)) {
            // 手机号明文：前3、后4
            return MaskUtils.mask(str, 3, 4, maskChar);
        } else if (EmailValidator.validate(str)) {
            // 邮箱掩码，掩码前：zhongxunking@163.com，掩码后：zho******ing@163.com
            int localUnmaskSize = str.indexOf('@') / 2;
            int endSize = localUnmaskSize / 2;
            int startSize = localUnmaskSize - endSize;
            endSize += str.length() - str.indexOf('@');

            return MaskUtils.mask(str, startSize, endSize, maskChar);
        } else if (BankCarNoValidator.validate(str)) {
            // 银行卡号明文：前6、后4
            return MaskUtils.mask(str, 6, 4, maskChar);
        } else if (OrganizationCodeValidator.validate(str)) {
            // 组织机构代码明文：前1、后3
            return MaskUtils.mask(str, 1, 3, maskChar);
        } else {
            // 无法识别的信息，采用全部掩码
            return MaskUtils.mask(str, 0, 0, maskChar);
        }
    }

    // 构建固定长度的全部掩码字符串
    private static String buildMaskAllStr(char maskChar) {
        StringBuilder builder = new StringBuilder(ALL_MASK_STR_SIZE);
        for (int i = 0; i < ALL_MASK_STR_SIZE; i++) {
            builder.append(maskChar);
        }
        return builder.toString();
    }
}
