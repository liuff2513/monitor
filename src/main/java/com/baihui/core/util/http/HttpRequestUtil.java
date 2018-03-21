package com.baihui.core.util.http;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequestUtil {
	org.apache.logging.log4j.Logger logger = LogManager.getLogger(HttpRequestUtil.class);
	private static String DEFAULT_URI_ENCODING = "UTF-8";
 
    /**
     * @Description: 创建SSL请求客户端
     * @param sslFilePath
     * @param sslKey
     * @return   
     * @return CloseableHttpClient  
     * @throws
     * @author feifei.liu
     * @date 2016年10月26日 下午8:02:18
     */
    public CloseableHttpClient buildSSLHttpClient(String sslFilePath, String sslKey) {  
        CloseableHttpClient httpclient = null;  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            FileInputStream instream = new FileInputStream(new File(sslFilePath));  
            try {  
                // 加载keyStore d:\\tomcat.keystore    
                trustStore.load(instream, sslKey.toCharArray());  
            } catch (CertificateException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    instream.close();  
                } catch (Exception ignore) {  
                }  
            }  
            // 相信自己的CA和所有自签名的证书  
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();  
            // 只允许使用TLSv1协议  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,  
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyStoreException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {  
            if (httpclient != null) {  
                try {  
                    httpclient.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }
        return httpclient;
    }
    
    /**
     * @Description: HTTP请求
     * @param requestMethod
     * @param uri
     * @param params
     * @param uriEncoding   
     * @return void  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:29:07
     */
    public static String httpRequest(String requestMethod, String uri, Map<String, String> params, String uriEncoding) {
		if(requestMethod!=null&&"post".equals(requestMethod.trim().toLowerCase())) {
			return httpPost(uri, params, uriEncoding);
		}else{
			return httpGet(uri, params, uriEncoding);
		}
    }
    
    /**
     * @Description: HTTP GET请求
     * @param uri   
     * @return void  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:29:21
     */
    public static String httpGet(String uri) {
    	return httpGet(uri, null);
    }
    
    /**
     * @Description: HTTP GET请求
     * @param uri
     * @param params
     * @return   
     * @return String  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:44:24
     */
    public static String httpGet(String uri, Map<String, String> params) {
    	return httpGet(uri, params, null);
    }
    
    /**
     * @Description: HTTP GET请求
     * @param uri
     * @param uriEncoding   
     * @return void  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:29:38
     */
    public static String httpGet(String uri, Map<String, String> params, String uriEncoding) {
    	String result=null;
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	try {
    		if(params!=null&&params.size()>0){
				String paramsStr = "";
				for(Entry<String, String> entry:params.entrySet()) {
					paramsStr+="&"+entry.getKey()+"="+entry.getValue();
				}
				if(uri.indexOf("?")!=-1) {
					 uri+=paramsStr;
				}else{
					uri+="?"+paramsStr.substring(1);
				}
			}
			HttpGet httpGet = new HttpGet(uri = URLDecoder.decode(uri, uriEncoding==null||"".equals(uriEncoding)?DEFAULT_URI_ENCODING:uriEncoding));
			CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
				System.out.println("get::"+result);
			}
		} catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
    	return result;
    }
    
    /**
     * @Description: HTTP POST请求
     * @param uri   
     * @return void  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:29:45
     */
    public static String httpPost(String uri) {
    	return httpPost(uri, null);
    }
    
    /**
     * @Description: HTTP POST请求
     * @param uri
     * @param params   
     * @return void  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:29:58
     */
    public static String httpPost(String uri, Map<String, String> params) {
    	return httpPost(uri, params, null);
    }
    
    /**
     * @Description: HTTP POST请求
     * @param uri
     * @param params
     * @param uriEncoding   
     * @return void  
     * @throws
     * @author feifei.liu
     * @date 2016年10月27日 下午1:30:06
     */
    public static String httpPost(String uri, Map<String, String> params, String uriEncoding) {
    	String result=null;
    	// 创建默认的httpClient实例.
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	try {
    		params = params==null?new LinkedHashMap<String, String>():params;
    		//创建httpPost
            HttpPost httpPost = new HttpPost(uri = URLDecoder.decode(uri, uriEncoding==null||"".equals(uriEncoding)?DEFAULT_URI_ENCODING:uriEncoding));
            //创建参数队列
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for(Entry<String, String> entry:params.entrySet()) {
            	nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, uriEncoding==null||"".equals(uriEncoding)?DEFAULT_URI_ENCODING:uriEncoding);
            httpPost.setEntity(encodedFormEntity);
            CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            	HttpEntity httpEntity = httpResponse.getEntity();
            	result = EntityUtils.toString(httpEntity);
            	System.out.println("post::"+result);
            }
		} catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
    	return result;
    }
    
  
//    public void upload() {  
//        CloseableHttpClient httpclient = HttpClients.createDefault();  
//        try {  
//            HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");  
//  
//            FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));  
//            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
//  
//            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
//  
//            httppost.setEntity(reqEntity);  
//  
//            System.out.println("executing request " + httppost.getRequestLine());  
//            CloseableHttpResponse response = httpclient.execute(httppost);  
//            try {  
//                System.out.println("----------------------------------------");  
//                System.out.println(response.getStatusLine());  
//                HttpEntity resEntity = response.getEntity();  
//                if (resEntity != null) {  
//                    System.out.println("Response content length: " + resEntity.getContentLength());  
//                }  
//                EntityUtils.consume(resEntity);  
//            } finally {  
//                response.close();  
//            }  
//        } catch (ClientProtocolException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } finally {  
//            try {  
//                httpclient.close();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
//        }  
//    }
    public static void main(String[] args) {
    	Map<String, String> params = new LinkedHashMap<>();
    	params.put("address", "北京市海淀区上地十街10号");
    	params.put("output", "json");
    	params.put("ak", "ViKGsun5SbzprZU0jcg1QTxBpiXRFy0h");
    	httpGet("http://api.map.baidu.com/geocoder/v2/",params);
    	httpPost("http://api.map.baidu.com/geocoder/v2/",params);
    }
}
