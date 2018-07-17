/** 
 * @Copyright (c) 2018, 周天琪 johnny_ztq@163.com.com All Rights Reserved. 
 * @PackageName:org.wowtools.springcloudext.ngineureka.util 
 * @Date:2018年7月17日下午2:30:48 
 * 
*/  
  
package com.aicloud.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.tomcat.util.codec.binary.Base64;
import org.wowtools.common.utils.SimpleHttpUtil;

/** 
 * @ClassName:   HttpUtil
 * @Description: TODO ADD FUNCTION.
 * @author       周天琪 johnny_ztq@163.com 
 * @Date:        2018年7月17日 下午2:30:48        
 */
public class HttpUtil extends SimpleHttpUtil{

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @authString 验证信息
	 * 			  例如security需要的验证信息，必须是base64加密的
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url,String authString) {
		String result;
		BufferedReader in = null;
		InputStream connIn = null;
		InputStreamReader ir = null;
		try {
			URL realUrl  = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			if(authString!=null && !authString.equals("")) {
				String authStringEnc = Base64.encodeBase64String(authString.getBytes());
				connection.setRequestProperty("Authorization", "Basic " +  authStringEnc);
			}
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			connIn =connection.getInputStream();
			ir = new InputStreamReader(connIn);
			in = new BufferedReader(ir);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (ir != null) {
					ir.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (connIn != null) {
					connIn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return result;
	}
}
  