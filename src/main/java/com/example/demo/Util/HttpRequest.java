package com.example.demo.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;

//import com.mvc.org.util.GzipUtil;

/**
 * 
 * 2014-9-24 Administrator
 * 
 */
public class HttpRequest {
	private String defaultContentEncoding;
	private int connectTimeout;
	private int readTimeout;
	
	//日志操作类
	private static Logger logger = Logger.getLogger(HttpRequest.class);

	public HttpRequest() {
		this.defaultContentEncoding = "utf-8";
		connectTimeout = 120000;
		readTimeout =120000;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString) throws IOException {
		return makeCon(urlString, this.send(urlString, "GET", null, null), null, "GET");
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params) throws IOException {
		return makeCon(urlString, this.send(urlString, "GET", params, null), params, "GET");
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @param propertys
	 *            请求属性
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params, Map<String, String> propertys) throws IOException {
		return makeCon(urlString, this.send(urlString, "GET", params, propertys), params, "GET");
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString) throws IOException {
		return makeCon(urlString, this.send(urlString, "POST", null, null), null, "POST");
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params) throws IOException {
		return makeCon(urlString, this.send(urlString, "POST", params, null), params, "POST");
	}
	
	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @param propertys
	 *            请求属性
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys) throws IOException {
		return makeCon(urlString, this.send(urlString, "POST", params, propertys), params, "POST");
	}
	
	/**
	 * 发送POST请求
	 * @param urlString URL地址 
	 * @param body 报文休
	 * @param propertys  head
	 * @return
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, String body, Map<String, String> propertys) throws IOException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("param", body);
		return makeCon(urlString, this.send(urlString, body, propertys), param, "POST");
	}
	
	
	
	private URLSendResult send(String urlString, String body, Map<String, String> propertys) throws IOException {
		URLSendResult usr = new URLSendResult();
		HttpURLConnection urlConnection = null;
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
		urlConnection.setConnectTimeout(connectTimeout);
		urlConnection.setReadTimeout(readTimeout);

		if (propertys != null){
			for (String key : propertys.keySet()) {
				urlConnection.addRequestProperty(key, propertys.get(key));
			}
		}
		
		logger.info("http begin request url="+urlString + " POST参数：" + body);
		
		try {
			urlConnection.getOutputStream().write(body.getBytes());
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
			usr.setCode(urlConnection.getResponseCode());
			usr.setMsg(urlConnection.getResponseMessage());
		} catch (Exception e) {
			usr.setCode(999);
			usr.setMsg(e.getMessage());
		}
		usr.setUrlConnection(urlConnection);
		return usr;
	}

	/**
	 * 发送HTTP请求
	 * 
	 * @param urlString
	 * @return 响映对象
	 * @throws IOException
	 */
	private URLSendResult send(String urlString, String method, Map<String, String> parameters, Map<String, String> propertys) throws IOException {
		URLSendResult usr = new URLSendResult();
		
		HttpURLConnection urlConnection = null;
		StringBuffer param = null;
		if (method.equalsIgnoreCase("GET") && parameters != null) {
			param = GetParams(parameters);
			urlString += param;
			logger.info("http get begin request url="+urlString);
		}
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
		urlConnection.setConnectTimeout(connectTimeout);
		urlConnection.setReadTimeout(readTimeout);

		if (propertys != null)
			for (String key : propertys.keySet()) {
				urlConnection.addRequestProperty(key, propertys.get(key));
			}

		try {
			if (method.equalsIgnoreCase("POST") && parameters != null) {
				param = PostParams(parameters);
				logger.info("http post begin request url="+urlString + " POST参数：" + param.toString());
			
				urlConnection.getOutputStream().write(param.toString().getBytes());
				urlConnection.getOutputStream().flush();
				urlConnection.getOutputStream().close();
			}
			
			usr.setCode(urlConnection.getResponseCode());
			usr.setMsg(urlConnection.getResponseMessage());
			
		} catch (Exception e) {
			usr.setCode(999);
			usr.setMsg(e.getMessage());
		}
		
		usr.setUrlConnection(urlConnection);
		return usr;
	}
	
	private StringBuffer PostParams(Map<String, String> parameters){
		StringBuffer param = new StringBuffer();
		for (String key : parameters.keySet()) {
			param.append(key).append("=").append(parameters.get(key));
			param.append("&");
		}
		if(param.length()>0){
			param.delete(param.length()-1,param.length());
		}
		return param;
	}
	
	private StringBuffer GetParams(Map<String, String> parameters){
		if (parameters == null) return new StringBuffer();
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : parameters.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(parameters.get(key));
			i++;
		}
		return param;
	}
	
	private HttpRespons makeCon(String urlString, URLSendResult urlSendResult, Map<String, String> params, String method){
		HttpRespons httpResponser = null;
		try {
			HttpURLConnection urlConnection = urlSendResult.getUrlConnection();
			if(urlSendResult.getCode() == HttpStatus.SC_OK && urlConnection != null ){
				String cen = urlConnection.getContentEncoding();
				if("gzip".equalsIgnoreCase(cen)){
//					httpRespons = makeContentGzip(urlString, urlConnection);
				}else{
					httpResponser = makeContent(urlString, urlConnection);
				}
			}else{
				httpResponser = new HttpRespons();
				//失败
				httpResponser.urlString = urlString;
				httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
				httpResponser.file = urlConnection.getURL().getFile();
				httpResponser.host = urlConnection.getURL().getHost();
				httpResponser.path = urlConnection.getURL().getPath();
				httpResponser.port = urlConnection.getURL().getPort();
				httpResponser.protocol = urlConnection.getURL().getProtocol();
				httpResponser.query = urlConnection.getURL().getQuery();
				httpResponser.ref = urlConnection.getURL().getRef();
				httpResponser.userInfo = urlConnection.getURL().getUserInfo();
				httpResponser.code = urlSendResult.getCode();
				httpResponser.message = urlSendResult.getMsg();
				httpResponser.method = urlConnection.getRequestMethod();
				httpResponser.connectTimeout = urlConnection.getConnectTimeout();
				httpResponser.readTimeout = urlConnection.getReadTimeout();
			}
			
			if("POST".equalsIgnoreCase(method)){
				httpResponser.requestBody = urlString + " POST参数：" + PostParams(params);
			}else{
				httpResponser.requestBody = urlString + GetParams(params);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpResponser;
	}
	

	/**
	 * 得到响应对象
	 * 
	 * @param urlConnection
	 * @return 响应对象
	 * @throws IOException
	 */
	private HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws IOException {
		HttpRespons httpResponser = new HttpRespons();
		InputStream in = null;
		try {
			in = urlConnection.getInputStream();

			String ecod = urlConnection.getContentEncoding();
			if (ecod == null)
				ecod = this.defaultContentEncoding;
			/*StringBuffer temp = new StringBuffer();
			byte[] bytes = new byte[4096];
			int size = 0;
			while ((size = in.read(bytes)) > 0) {
				String str = new String(bytes, 0, size, ecod);
				temp.append(str);
			}*/

			//2015.4.9 ljy 将按字节读入改为逐行读入
			StringBuffer temp = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			//逐行读入
			while ((line = br.readLine()) != null) {
				temp.append(line);
				temp.append("\r");
			}

			httpResponser.urlString = urlString;

			httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
			httpResponser.file = urlConnection.getURL().getFile();
			httpResponser.host = urlConnection.getURL().getHost();
			httpResponser.path = urlConnection.getURL().getPath();
			httpResponser.port = urlConnection.getURL().getPort();
			httpResponser.protocol = urlConnection.getURL().getProtocol();
			httpResponser.query = urlConnection.getURL().getQuery();
			httpResponser.ref = urlConnection.getURL().getRef();
			httpResponser.userInfo = urlConnection.getURL().getUserInfo();

			httpResponser.content = new String(temp.toString().getBytes(ecod), ecod);
			httpResponser.contentEncoding = ecod;
			httpResponser.code = urlConnection.getResponseCode();
			httpResponser.message = urlConnection.getResponseMessage();
			httpResponser.contentType = urlConnection.getContentType();
			httpResponser.method = urlConnection.getRequestMethod();
			httpResponser.connectTimeout = urlConnection.getConnectTimeout();
			httpResponser.readTimeout = urlConnection.getReadTimeout();
		//	logger.info("http end response content="+httpResponser.content);
			
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
			if(in!=null){
				in.close();
			}
		}
		return httpResponser;
	}
	
//	private HttpRespons makeContentGzip(String urlString, HttpURLConnection urlConnection) throws IOException {
//		HttpRespons httpResponser = new HttpRespons();
//		InputStream in = null;
//		try {
//			if(urlConnection!=null){
//				in = urlConnection.getInputStream();
//
//				StringBuffer temp = new StringBuffer();
//				byte[] bytes = new byte[4096];
//				int size = 0;
//				while ((size = in.read(bytes)) > 0) {
//					String str = new String(bytes, 0, size, "ISO-8859-1");
//					temp.append(str);
//				}
//
//				String result =  GzipUtil.unCompress(temp.toString());
//				
//				String ecod = urlConnection.getContentEncoding();
//				if (ecod == null)
//					ecod = this.defaultContentEncoding;
//
//				httpResponser.urlString = urlString;
//				httpResponser.content = result;
//				httpResponser.contentEncoding = ecod;
//				httpResponser.code = urlConnection.getResponseCode();
//				logger.info("http end response content isGzip="+httpResponser.content);
//				
//			}
//		} catch (IOException e) {
//			throw e;
//		} finally {
//			if (urlConnection != null)
//				urlConnection.disconnect();
//			if(in!=null){
//				in.close();
//			}
//		}
//		return httpResponser;
//	}

	/**
	 * 默认的响应字符集
	 */
	public String getDefaultContentEncoding() {
		return this.defaultContentEncoding;
	}

	/**
	 * 设置默认的响应字符集
	 */
	public void setDefaultContentEncoding(String defaultContentEncoding) {
		this.defaultContentEncoding = defaultContentEncoding;
	}

	/**
	 * @return 读取连接时间
	 */
	public int getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @param 设置连接时间
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * @return 读取读取时间
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * @param 设置读取时间
	 */
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

}
