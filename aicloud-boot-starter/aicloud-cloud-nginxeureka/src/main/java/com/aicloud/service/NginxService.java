package com.aicloud.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wowtools.common.utils.ResourcesReader;

import com.aicloud.pojo.Record;
import com.aicloud.pojo.ServiceRecord;
import com.aicloud.util.Constant;

/**
 * 操作nginx的服务
 *
 * @author liuyu
 * @date 2018/3/9
 */
@Service
public class NginxService {
	private static final Logger logger = LoggerFactory.getLogger(NginxService.class);
    /**
     * location块中扩展的参数
     */
    private final HashMap<String, String> locationParams;

    {
        String path = Constant.rootPath + "locationParam.txt";
        if (!new File(path).exists()) {
            locationParams = new HashMap<>(0);
        } else {
            try {
                String[] strs = ResourcesReader.readStr(Constant.rootPath + "locationParam.txt").split("\n");
                HashMap<String, StringBuilder> kv = new HashMap<>();
                StringBuilder param = null;
                for (String str : strs) {
                    if (str.length() > 0 && str.charAt(0) == '>') {
                        param = new StringBuilder();
                        String key = str.substring(1).trim().toLowerCase();
                        kv.put(key, param);
                    } else {
                        param.append('\t').append(str).append('\n');
                    }
                }
                locationParams = new HashMap<>(kv.size());
                kv.forEach((k, sb) -> locationParams.put(k, sb.toString()));
            } catch (Exception e) {
                throw new RuntimeException("解析配置文件locationParam.txt异常", e);
            }
        }


    }

    /**
     * 将服务信息写入nginx配置并reload
     *
     * @param record
     */
    public void reload(Record record) {
    	logger.info("将服务信息写入nginx配置并reload");
        writeCfg(record);
        record.setReloadStartTimestamp(System.currentTimeMillis());
        reloadCfg(record);
        record.setReloadEndTimestamp(System.currentTimeMillis());
    }

    /**
     * 将服务信息写入nginx配置
     *
     * @param record
     */
    private void writeCfg(Record record) {
        try {
            StringBuilder sbUpstream = new StringBuilder();
            StringBuilder sbServer = new StringBuilder();
            ServiceRecord[] services = record.getServices();
            for (ServiceRecord service : services) {
                String[] urls = service.getServiceUrls();
                String appName = service.getName();
                sbUpstream.append("upstream upstream_").append(appName).append("{\n");
                sbUpstream.append("\tleast_conn;\n");

                sbServer.append("location ^~ /").append(appName).append("/ {\n");
                sbServer.append("\tproxy_pass http://upstream_").append(appName).append(";\n");
                String param = locationParams.get(appName);
                if (null != param) {
                    sbServer.append(param);
                }
                sbServer.append("}\n\n");
                for (String url : urls) {
                    sbUpstream.append("\tserver ").append(url).append(";\n");
                }
                sbUpstream.append("}\n\n");
            }
            write(sbUpstream.toString(), "/ngineureka_upstream.conf");
            write(sbServer.toString(), "/ngineureka_location.conf");
        } catch (Exception e) {
            record.setException(Record.State.ErrOnWriteNginxCfg, e);
            throw new RuntimeException(e);
        }
    }

    private static void write(String cfg, String name) throws Exception {
        String path = Constant.confPath + name;
    	//String path = "D:\\" + name;
        File f = new File(path);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            out.write(cfg.getBytes());
        } finally {
            out.close();
        }
    }

    /**
     * 重新加载nginx配置
     */
    private static void reloadCfg(Record record) {
        String fileName = "/".equals(File.separator) ? "reload.sh" : "reload.bat";
        try {
            exeCmd(Constant.rootPath + fileName);
        } catch (Exception e) {
            record.setException(Record.State.ErrOnReloadNginx, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行控制台命令
     *
     * @param commandStr
     */
    private static String exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("chmod a+x " + commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            logger.info("reload nginx by " + commandStr);
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("执行控制台命令异常:" + commandStr, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
