/* 
 * Copyright © 2017 aicloud
 */

/*
 * 修订记录:
 * @author aicloud 2017-08-04 13:42 创建
 */
package com.aicloud.common.util.money;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 钱
 */
public final class Money implements Comparable<Money>, Serializable {
    // 元到分的比例
    private static final BigDecimal SCALE = new BigDecimal(100);

    /**
     * 将以元为单位的字符串转换为Money
     *
     * @param amount 金额（以元为单位的字符串，比如："100"、"100.0"、"100.00"、"100.12"）
     * @return Money类型的金额
     * @throws IllegalArgumentException 如果amount的有效小数部分的位数超过2位（比如"100.123"会抛异常，而"100.120"不会抛异常）
     */
    public static Money amount(String amount) {
        BigDecimal bigDecimal = new BigDecimal(amount).stripTrailingZeros();
        if (bigDecimal.scale() > 2) {
            throw new IllegalArgumentException("金额[" + amount + "]的有效小数位数超过2位");
        }
        bigDecimal = bigDecimal.multiply(SCALE);
        return new Money(bigDecimal.longValue());
    }

    // 分
    private final long cent;

    public Money(long cent) {
        this.cent = cent;
    }

    /**
     * 获取分
     */
    public long getCent() {
        return cent;
    }

    /**
     * 加法（结果存到新对象，本对象不变）
     *
     * @param amount 待加金额
     * @return 相加后的金额
     */
    public Money add(Money amount) {
        return new Money(cent + amount.getCent());
    }

    /**
     * 减法（结果存到新对象，本对象不变）
     *
     * @param amount 待减金额
     * @return 相减后的金额
     */
    public Money subtract(Money amount) {
        return new Money(cent - amount.getCent());
    }

    /**
     * 大于比较
     *
     * @param amount 待比较金额
     * @return 如果本对象大于amount则返回true，否则返回false
     */
    public boolean greaterThan(Money amount) {
        return compareTo(amount) > 0;
    }

    /**
     * 小于比较
     *
     * @param amount 待比较金额
     * @return 如果本对象小于amount则返回true，否则返回false
     */
    public boolean lessThan(Money amount) {
        return compareTo(amount) < 0;
    }

    @Override
    public int compareTo(Money other) {
        if (cent < other.getCent()) {
            return -1;
        } else if (cent == other.getCent()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int hashCode() {
        return new Long(cent).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Money) {
            return compareTo((Money) obj) == 0;
        } else {
            return false;
        }
    }

    /**
     * 转换为以元为单位的字符串（包含两位小数，例如：0.00、100.10、100.12）
     */
    @Override
    public String toString() {
        return BigDecimal.valueOf(cent, 2).toString();
    }
}
