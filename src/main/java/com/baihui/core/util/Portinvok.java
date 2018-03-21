package com.baihui.core.util;

import com.baihui.core.util.encrypt.EncryptUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理请求
 * @author ziyuzhang
 *
 */
public class Portinvok {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public Portinvok() {
		
	}
	
	
	/**
	 * 接口调用（解析）
	 * @param port  请求链接
	 * @param json  请求的json串
	 * @return
	 */
	public String getReq(String url){
		HttpClient httpclient = new DefaultHttpClient();
	    try {
	    	//IBE	
	    	HttpPost post4 = new HttpPost(url);
	    	post4.setHeader("Authorization", EncryptUtil.md5("123456"));
	    	HttpResponse response4 = httpclient.execute(post4);
	    	HttpEntity entity4 = response4.getEntity();
	    	if (entity4 != null) {
	    		String res = EntityUtils.toString(entity4,"utf-8");
		    	return res;
	    	}
	    	post4.abort();
	    } catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    } catch (IOException e) {
	    	System.out.println(e.getMessage());
	    } finally {
	    	httpclient.getConnectionManager().shutdown();
	    }
	    return null;

	}
	/**
	 * 接口调用（解析）
	 * @param port  请求链接
	 * @param json  请求的json串
	 * @return
	 */
	public String getRequestinfo(String str,String url,String sign){
		HttpClient httpclient = new DefaultHttpClient();
	    try {
	    	HttpPost post4 = new HttpPost(url);
	    	HttpEntity entity = new ByteArrayEntity(str.getBytes("UTF-8"));
	    	post4.setEntity(entity);
	    	post4.setHeader("Authorization", sign);
	    	post4.setHeader("Content-Length", "256");
	    	post4.setHeader("Accept", "application/xml");
	    	post4.setHeader("Content-Type", "application/xml;charset=utf-8");
	    	HttpResponse response4 = httpclient.execute(post4);
	    	HttpEntity entity4 = response4.getEntity();
	    	//System.out.println("---------------Strat");
	    	if (entity4 != null) {
	    		
	    		String res = EntityUtils.toString(entity4,"utf-8");
		    	//System.out.println(res);
		    	return res;
	    	}
	    	// Do not feel like reading the response body
	    	// Call abort on the request object
	    	post4.abort();
	    } catch (ParseException e) {
	    	// TODO Auto-generated catch block
	    	System.out.println(e.getMessage());
	    } catch (IOException e) {
	    	// TODO Auto-generated catch block
	    	System.out.println(e.getMessage());
	    } finally {
	    	// When PhttpClient instance is no longer needed,
	    	// shut down the connection manager to ensure
	    	// immediate deallocation of all system resources
	    	httpclient.getConnectionManager().shutdown();
	    }
	    return null;

	}
	/**
	 * 接口调用（解析）
	 * @param port  请求链接
	 * @param json  请求的json串
	 * @return
	 */
	public String AppTest(String url){
		HttpClient httpclient = new DefaultHttpClient();
	    try {
	    	//IBE	
	    	HttpPost post4 = new HttpPost(url);
	    	List<NameValuePair> parameters4  = new ArrayList<NameValuePair>();
	    	HttpEntity entparams4  = new  UrlEncodedFormEntity( parameters4, "UTF-8");
	    	post4.setEntity(entparams4);
	    	HttpResponse response4 = httpclient.execute(post4);
	    	HttpEntity entity4 = response4.getEntity();
	    	//System.out.println("---------------Strat");
	    	if (entity4 != null) {
	    		
	    		String res = EntityUtils.toString(entity4,"utf-8");
		    	//System.out.println(res);
		    	return res;
	    	}
	    	// Do not feel like reading the response body
	    	// Call abort on the request object
	    	post4.abort();
	    } catch (ParseException e) {
	    	// TODO Auto-generated catch block
	    	System.out.println(e.getMessage());
	    } catch (IOException e) {
	    	// TODO Auto-generated catch block
	    	System.out.println(e.getMessage());
	    } finally {
	    	// When PhttpClient instance is no longer needed,
	    	// shut down the connection manager to ensure
	    	// immediate deallocation of all system resources
	    	httpclient.getConnectionManager().shutdown();
	    }
	    return null;

	}
}
