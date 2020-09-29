package com.example.demo.Util;

import java.net.HttpURLConnection;

public class URLSendResult {

	private int code;
	private String msg;
	private HttpURLConnection urlConnection;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HttpURLConnection getUrlConnection() {
		return urlConnection;
	}
	public void setUrlConnection(HttpURLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}
	
}
