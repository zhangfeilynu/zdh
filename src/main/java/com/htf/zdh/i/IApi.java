package com.htf.zdh.i;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.restassured.path.json.JsonPath;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IApi {
	private static final Logger logger = LoggerFactory.getLogger(IApi.class);

	public static void main(String[] args) {

		long i = (long) ((Math.random() * 9 + 1) * 1000000000);
		System.out.print(i);

	}

	public static String upComing(String acceptUserId, Integer messageType) {// 汇富小i推送消息接口
		long id = (long) ((Math.random() * 9 + 1) * 1000000000);
		logger.info("随机id是：" + id);
		String url = "http://10.50.115.9:8080/rest/external/api/upcoming";
		Map<String, Object> p = new HashMap<>();
		p.put("acceptUserId", acceptUserId);
		p.put("androidUri", "string");
		p.put("categoryType", "NCRM");
		p.put("classificationMatters", "string");
		p.put("content", "123456");
		p.put("id", id);
		p.put("iosUri", "string");
		p.put("messageType", messageType);
		p.put("params", "string");
		p.put("remark", "string");
		p.put("setType", 0);
		p.put("title", "string");
		p.put("upcomingStatus", "UNFINISHED");

		Gson gson = new Gson();
		String json = gson.toJson(p);
		json = "[" + json + "]";
		logger.info("入参：" + json);
		MediaType type = MediaType.parse("application/json;charset=utf-8");
		OkHttpClient httpClient = new OkHttpClient();
		@SuppressWarnings("deprecation")
		RequestBody requestBody = RequestBody.create(json, type);
		Request request = new Request.Builder().post(requestBody).url(url).build();
		Response response = null;
		try {
			response = httpClient.newCall(request).execute();
			String responseStr = response.body().string();
			logger.info("http://10.50.115.9:8080/rest/external/api/upcoming返回结果:" + responseStr);
			return responseStr;

		} catch (IOException e) {
			logger.error("http://10.50.115.9:8080/rest/external/api/upcoming请求异常，" + e.getMessage());
			Map<String, String> p2 = new HashMap<>();
			p2.put("code", "500");
			p2.put("message", "操作异常");
			Gson gson2 = new Gson();
			String json2 = gson2.toJson(p2);
			return json2;
		} finally {
			if (response != null) {
				response.close();
			}
		}

	}
}
