/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved. 
 * @PackageName:com.aicloud.common.util.factory 
 * @Date:2018年4月23日上午10:37:31 
 * @Description 1位，不用。二进制中最高位为1的都是负数，但是我们生成的id一般都使用整数，所以这个最高位固定是0
 *  41位，用来记录时间戳（毫秒）。
	 41位可以表示2的41次方减1个数字，
	            如果只用来表示正整数（计算机中正数包含0），可以表示的数值范围是：0 至 241−1，减1是因为可表示的数值范围是从0开始算的，而不是1。
		也就是说41位可以表示241−1个毫秒的值，转化成单位年则是(241−1)/(1000∗60∗60∗24∗365)=69年
		
	10位，用来记录工作机器id。
	可以部署在210=1024个节点，包括5位datacenterId和5位workerId
	5位（bit）可以表示的最大正整数是25−1=31，即可以用0、1、2、3、....31这32个数字，来表示不同的datecenterId或workerId
	12位，序列号，用来记录同毫秒内产生的不同id。
	
	12位（bit）可以表示的最大正整数是212−1=4095，即可以用0、1、2、3、....4094这4095个数字，来表示同一机器同一时间截（毫秒)内产生的4095个ID序号
	由于在Java中64bit的整数是long类型，所以在Java中SnowFlake算法生成的id就是long来存储的。
	
	SnowFlake可以保证：
	所有生成的id按时间趋势递增
	整个分布式系统内不会产生重复id（因为有datacenterId和workerId来做区分）
 * 
*/  
  
package com.aicloud.common.util.factory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/** 
 * @ClassName:   IdGeneratorFactory
 * @Description: Twitter SnowFlake算法生成id,https://segmentfault.com/a/1190000011282426
 * @author       周天琪 johnny_ztq@163.com 
 * @Date:        2018年4月23日 上午10:37:31        
 */
public class IdGeneratorFactory {
	private long workerId;  
    private long datacenterId;  
    private long sequence = 0L;  //实际的自增序列号，从0开始自增
    private long twepoch = 1288834974657L; //Thu, 04 Nov 2010 01:42:54 GMT   起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量
    private long workerIdBits = 5L; //节点ID长度  
    private long datacenterIdBits = 5L; //数据中心ID长度  
    private long maxWorkerId = -1L ^ (-1L << workerIdBits); //最大支持机器节点数0~31，一共32个  
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); //最大支持数据中心节点数0~31，一共32个  
    private long sequenceBits = 12L; //序列号12位  
    private long workerIdShift = sequenceBits; //机器节点左移12位  
    private long datacenterIdShift = sequenceBits + workerIdBits; //数据中心节点左移17位  
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //时间毫秒数左移22位  
    private long sequenceMask = -1L ^ (-1L << sequenceBits); //4095  
     private long lastTimestamp = -1L;  
      
    private static class IdGenHolder {  
        private static final IdGeneratorFactory instance = new IdGeneratorFactory();  
    }  
      
    public static IdGeneratorFactory get(){  
        return IdGenHolder.instance;  
    }  
  
    public IdGeneratorFactory() {  
        this(0L, 0L);  
    }  
  
    public IdGeneratorFactory(long workerId, long datacenterId) {  
        if (workerId > maxWorkerId || workerId < 0) {  
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));  
        }  
        if (datacenterId > maxDatacenterId || datacenterId < 0) {  
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));  
        }  
        this.workerId = workerId;  
        this.datacenterId = datacenterId;  
    }  
      
    public synchronized long nextId() {  
        long timestamp = timeGen(); //获取当前毫秒数  
        //如果服务器时间有问题(时钟后退) 报错。  
        if (timestamp < lastTimestamp) {  
            throw new RuntimeException(String.format(  
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));  
        }  
        //如果上次生成时间和当前时间相同,在同一毫秒内  
        if (lastTimestamp == timestamp) {  
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位  
            sequence = (sequence + 1) & sequenceMask;  
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0  
            if (sequence == 0) {  
                timestamp = tilNextMillis(lastTimestamp); //自旋等待到下一毫秒  
            }  
        } else {
            sequence = 0L; //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加  
        }  
        lastTimestamp = timestamp;  
        // 最后按照规则拼出ID。  
        // 000000000000000000000000000000000000000000  00000            00000       000000000000  
        // time                                        datacenterId     workerId    sequence  
         return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)  
                | (workerId << workerIdShift) | sequence;  
    }  
  
    protected long tilNextMillis(long lastTimestamp) {  
        long timestamp = timeGen();  
        while (timestamp <= lastTimestamp) {  
            timestamp = timeGen();  
        }  
        return timestamp;  
    }  
  
    protected long timeGen() {  
        return System.currentTimeMillis();  
    }
    
    public static void main(String[] args) {
    	int n=1000000;
    	IdGeneratorFactory idWorker = new IdGeneratorFactory(1, 1);  
    	IdGeneratorFactory idWorker2 = new IdGeneratorFactory(2, 1);  
        Set<Long> setOne = new HashSet<Long>();  
        Set<Long> setTow = new HashSet<Long>();  
        long start = System.currentTimeMillis();  
        for (int i = 0; i < n; i++) {  
            setOne.add(idWorker.nextId());//加入set  
        }  
        long end1 = System.currentTimeMillis() - start;  
        System.out.println("第一批ID预计生成"+n+"个,实际生成"+setOne.size()+"个<<<<*>>>>共耗时:"+end1);  
  
        for (int i = 0; i < n; i++) {  
            setTow.add(idWorker2.nextId());//加入set  
        }  
        long end2 = System.currentTimeMillis() - start;  
        System.out.println("第二批ID预计生成"+n+"个,实际生成"+setOne.size()+"个<<<<*>>>>共耗时"+end2);  
  
        setOne.addAll(setTow);  
        Iterator<Long> it = setOne.iterator();
        while(it.hasNext()) {
        	Long l = it.next();
        	System.out.println("("+l+"),");
        }
        System.out.println("合并总计生成ID个数"+setOne.size()); 
	}
}
  