/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.money;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Money约束校验器
 */
public class MoneyConstraintValidator implements ConstraintValidator<MoneyConstraint, Money> {
    // 违反最小值的信息模板
    private static final String VIOLATE_MIN_MESSAGE_TEMPLATE = "{com.aicloud.common.util.money.MoneyConstraint.message.min}";
    // 违反最大值的信息模板
    private static final String VIOLATE_MAX_MESSAGE_TEMPLATE = "{com.aicloud.common.util.money.MoneyConstraint.message.max}";
    // 违反不能为null的信息模板
    private static final String VIOLATE_NOT_NULL_MESSAGE_TEMPLATE = "{com.aicloud.common.util.money.MoneyConstraint.message.not-null}";

    // 最小值
    private long min;
    // 最大值
    private long max;
    // 是否可以为null
    private boolean nullable;

    @Override
    public void initialize(MoneyConstraint constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        nullable = constraintAnnotation.nullable();
        if (min > max) {
            throw new IllegalArgumentException("金额约束最小值不能大于最大值：" + constraintAnnotation);
        }
    }

    @Override
    public boolean isValid(Money value, ConstraintValidatorContext context) {
        if (value == null) {
            if (!nullable) {
                buildConstraintViolation(context, VIOLATE_NOT_NULL_MESSAGE_TEMPLATE);
            }
            return nullable;
        }
        if (value.getCent() < min) {
            buildConstraintViolation(context, VIOLATE_MIN_MESSAGE_TEMPLATE);
            return false;
        }
        if (value.getCent() > max) {
            buildConstraintViolation(context, VIOLATE_MAX_MESSAGE_TEMPLATE);
            return false;
        }
        return true;
    }

    // 构造违反约束信息
    private void buildConstraintViolation(ConstraintValidatorContext context, String template) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(template).addConstraintViolation();
    }
}
