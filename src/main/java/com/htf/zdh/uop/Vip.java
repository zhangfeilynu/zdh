package com.htf.zdh.uop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.htf.zdh.utils.DateUtils;

import io.restassured.path.json.JsonPath;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Vip {
	private static final Logger logger = LoggerFactory.getLogger(Vip.class);

	public static void main(String[] args) {
		// getreserveNo("H10208");

	}

	public static String getreserveNo(String fundId) {// 根据基金id获取第一条预约码
		String url = "http://10.50.16.242:8099/vip/v1/reserve-code/all";
		Map<String, String> param = new HashMap<>();
		param.put("fundId", fundId);
		param.put("pageNum", "1");
		param.put("pageSize", "1");
		Gson gson = new Gson();
		String json = gson.toJson(param);
		MediaType type = MediaType.parse("application/json;charset=utf-8");
		OkHttpClient httpClient = new OkHttpClient();
		@SuppressWarnings("deprecation")
		RequestBody requestBody = RequestBody.create(type, json);
		Request request = new Request.Builder().post(requestBody).url(url).build();
		Response response = null;
		String reserveNo = "";
		try {
			response = httpClient.newCall(request).execute();
			String responseStr = response.body().string();
			logger.info("返回结果:" + responseStr);
			JsonPath jsonPath = new JsonPath(responseStr);
			reserveNo = jsonPath.getString("body.results[0].reserveNo");
			logger.info("预约码：" + reserveNo);

		} catch (IOException e) {
			logger.error("/vip/v1/reserve-code/all请求异常，" + e.getMessage());
		} finally {
			if (response != null) {
				response.close();
			}
		}

		return reserveNo;
	}

	public static int update(String fundId, String auditStatus, String realAmt, String reserveNo) {// 配置额度，设置状态
		if (null == auditStatus || auditStatus.equals("")) {
			auditStatus = "Y";
		}
		String url = "http://10.50.16.242:8099/vip/v1/reserve-code/update";
		Map<String, String> param = new HashMap<>();
		param.put("fundId", fundId);
		param.put("auditStatus", auditStatus);
		param.put("realAmt", realAmt);
		param.put("reserveNo", reserveNo);
		Gson gson = new Gson();
		String json = gson.toJson(param);
		json = "[" + json + "]";
		MediaType type = MediaType.parse("application/json;charset=utf-8");
		OkHttpClient httpClient = new OkHttpClient();
		@SuppressWarnings("deprecation")
		RequestBody requestBody = RequestBody.create(type, json);
		Request request = new Request.Builder().put(requestBody).url(url).build();
		Response response = null;
		try {
			response = httpClient.newCall(request).execute();
			String responseStr = response.body().string();
			logger.info("/vip/v1/reserve-code/update返回结果:" + responseStr);
			JsonPath jsonPath = new JsonPath(responseStr);
			int i = -1;
			i = jsonPath.getInt("returnCode");
			return i;

		} catch (IOException e) {
			logger.error("/vip/v1/reserve-code/update请求异常，" + e.getMessage());
		} finally {
			if (response != null) {
				response.close();
			}
		}

		return -1;
	}

	public static int sendMessage(String deadlineDate, String reserveNo) {// 设置失效日期并发送
		if (null == deadlineDate || deadlineDate.equals("")) {
			deadlineDate = DateUtils.getDateNext();
		}

		String url = "http://10.50.16.242:8099/vip/v1/reserve-code/send-message";
		Map<String, String> param = new HashMap<>();
		param.put("deadlineDate", deadlineDate);
		param.put("reserveNo", reserveNo);
		Gson gson = new Gson();
		String json = gson.toJson(param);
		json = "[" + json + "]";
		MediaType type = MediaType.parse("application/json;charset=utf-8");
		OkHttpClient httpClient = new OkHttpClient();
		@SuppressWarnings("deprecation")
		RequestBody requestBody = RequestBody.create(type, json);
		Request request = new Request.Builder().put(requestBody).url(url).build();
		Response response = null;
		try {
			response = httpClient.newCall(request).execute();
			String responseStr = response.body().string();
			logger.info("/vip/v1/reserve-code/send-message返回结果:" + responseStr);
			JsonPath jsonPath = new JsonPath(responseStr);
			int i = -1;
			i = jsonPath.getInt("returnCode");
			return i;
		} catch (IOException e) {
			logger.error("/vip/v1/reserve-code/send-message请求异常，" + e.getMessage());
		} finally {
			if (response != null) {
				response.close();
			}
		}

		return -1;
	}

	public static int add(String custNm, String fundId, String mobileNo, String realAmt) {// 流水号管理-单笔管理-发送预约码给用户手机

		String deadlineDate = DateUtils.getDateNext();
		String nextOpenDate = DateUtils.getDateStr1();

		String url = "http://10.50.16.242:8099/vip/v1/reserve-code/add";
		Map<String, String> param = new HashMap<>();
		param.put("deadlineDate", deadlineDate);
		param.put("nextOpenDate", nextOpenDate);
		param.put("custNm", custNm);
		param.put("fundId", fundId);
		param.put("mobileNo", mobileNo);
		param.put("realAmt", realAmt);
		param.put("accptMd", "M");
		Gson gson = new Gson();
		String json = gson.toJson(param);
		json = "[" + json + "]";
		MediaType type = MediaType.parse("application/json;charset=utf-8");
		OkHttpClient httpClient = new OkHttpClient();
		@SuppressWarnings("deprecation")
		RequestBody requestBody = RequestBody.create(type, json);
		Request request = new Request.Builder().post(requestBody).url(url).build();
		Response response = null;
		try {
			response = httpClient.newCall(request).execute();
			String responseStr = response.body().string();
			logger.info("/vip/v1/reserve-code/add返回结果:" + responseStr);
			JsonPath jsonPath = new JsonPath(responseStr);
			int i = -1;
			i = jsonPath.getInt("returnCode");
			return i;
		} catch (IOException e) {
			logger.error("/vip/v1/reserve-code/add请求异常，" + e.getMessage());
		} finally {
			if (response != null) {
				response.close();
			}
		}

		return -1;
	}

}
