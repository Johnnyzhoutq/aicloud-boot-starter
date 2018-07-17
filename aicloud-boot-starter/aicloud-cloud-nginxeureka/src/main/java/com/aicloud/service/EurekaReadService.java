package com.aicloud.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.aicloud.pojo.Record;
import com.aicloud.pojo.ServiceRecord;
import com.aicloud.util.HttpUtil;

/**
 * 与Eureka交互的服务
 *
 * @author liuyu
 * @date 2018/3/9
 */
@Service
@Order(value = 0)
public class EurekaReadService implements CommandLineRunner {
    @Value("${eureka.client.serviceUrl.httpUrl}")
    private String appUrl;
    @Value("${eureka.client.serviceUrl.securityAuth}")
    private String securityAuth;
    
    @Override
    public void run(String... args) throws Exception {
        if ('/' == appUrl.charAt(appUrl.length() - 1)) {
            appUrl += "apps";
        } else {
            appUrl += "/apps";
        }
    }

    private static String lastXml;//最后一次请求得到的注册中心服务xml

    /**
     * 查询注册中心中的服务
     *
     * @param record 操作记录，此服务会往record中写入交互时的相关信息
     * @return
     */
    private String getServiceXml(Record record) {
        record.setQueryStartTimestamp(System.currentTimeMillis());
        try {
        	//这里因为注册中心集成了sucurity，所以需要做验证
            String xml = HttpUtil.sendGet(appUrl,securityAuth);
            record.setQueryEndTimestamp(System.currentTimeMillis());
            return xml;
        } catch (Exception e) {
            record.setException(Record.State.ErrOnQueryEureka, e);
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询注册中心的服务并填充到record中
     *
     * @param record 操作记录，此服务会往record中写入交互时的相关信息
     * @param ignoreNotChange 即使服务没有更新，也继续操作
     * @return 服务是否发生过变化
     */
    public boolean queryService(Record record,boolean ignoreNotChange) {
        String xml = getServiceXml(record);
        if (!ignoreNotChange && xml.equals(lastXml)) {
            record.setState(Record.State.ServiceNotChange);
            return false;
        }
        lastXml = xml;
        try {
            StringReader read = new StringReader(xml);
            InputSource source = new InputSource(read);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(source);
            Element root = doc.getRootElement();
            List<Element> apps = root.getChildren("application");

            ArrayList<ServiceRecord> serviceList = new ArrayList<ServiceRecord>(apps.size());
            for (Element app : apps) {
                String appName = app.getChild("name").getText().toLowerCase();
//                if ("ngineureka".equals(appName)) {
//                    continue;
//                }
                List<Element> instances = app.getChildren("instance");
                ServiceRecord service = new ServiceRecord();
                serviceList.add(service);
                service.setName(appName);
                String[] urls = new String[instances.size()];
                service.setServiceUrls(urls);
                int j = 0;
                for (Element instance : instances) {
                    String ip = instance.getChild("ipAddr").getText();
                    String port = instance.getChild("port").getText();
                    urls[j] = ip + ":" + port;
                    j++;
                }
            }
            ServiceRecord[] services = new ServiceRecord[serviceList.size()];
            serviceList.toArray(services);
            record.setServices(services);
        } catch (Exception e) {
            record.setState(Record.State.ErrOnParseEurekaRes);
        }
        return true;
    }
}
