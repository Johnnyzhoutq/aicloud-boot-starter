/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.jpascanned;

import com.aicloud.common.util.money.Money;
import com.aicloud.common.util.money.MoneyConverters.LongToMoneyConverter;
import com.aicloud.common.util.money.MoneyConverters.MoneyToLongConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Money在jpa场景下的转换器
 */
@Converter(autoApply = true)
public class MoneyJpaConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money attribute) {
        if (attribute == null) {
            return null;
        }
        return MoneyToLongConverter.INSTANCE.convert(attribute);
    }

    @Override
    public Money convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return null;
        }
        return LongToMoneyConverter.INSTANCE.convert(dbData);
    }
}
