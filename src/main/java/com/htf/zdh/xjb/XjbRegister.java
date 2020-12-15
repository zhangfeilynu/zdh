package com.htf.zdh.xjb;

import com.google.gson.Gson;
import com.htf.zdh.utils.Utils;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.config.EncoderConfig.encoderConfig;

/**
 * @author zhangfei
 * @version 1.0.0
 * @className Login.java
 * @description 现金宝注册
 * @createTime 2020/11/27 09:29
 */
public class XjbRegister {

    private static final Logger logger = LoggerFactory.getLogger(XjbRegister.class);

    public static void main(String[] args) {}






    /*
     * @param mobileNo
     * @param baseUrl
     * @return java.lang.String
     * @author zhangfei
     * @description 注册接口
     * @updatetime 2020/11/27 17:03
     */
    private static String register(String mobileNo,String baseUrl){

        String url="/mobileEC/services/account/register";
        String registerUrl= new StringBuilder().append(baseUrl).append(url).toString();
        //String mobileNo= Utils.getMobile();
        Map<String, String> param = new HashMap<>();
        param.put("mobileNo", mobileNo);
        Gson gson = new Gson();
        String json = gson.toJson(param);
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(json,
                type);
        Request request = new Request.Builder().post(requestBody).url(registerUrl).build();
        String seriaNo = "";
        String responseStr;
        try (Response response = httpClient.newCall(request).execute()) {
            responseStr = response.body().string();
            logger.info(url+"返回结果:" + responseStr);
            JsonPath jsonPath = new JsonPath(responseStr);
            seriaNo = jsonPath.getString("body.seriaNo");
            logger.info("注册流水号：" + seriaNo);
        } catch (IOException e) {
            logger.error(url+"异常");
            e.printStackTrace();
        }
        logger.info("新注册手机号："+mobileNo);
        return seriaNo;
    }

    /*
     * @param seriaNo
     * @param baseUrl
     * @return java.lang.String
     * @author zhangfei
     * @description 短信验证码验证接口
     * @updatetime 2020/11/27 17:17
     */
    private static String confirm(String seriaNo,String baseUrl){

        String url="/mobileEC/services/account/register_confirm";
        String registerUrl= new StringBuilder().append(baseUrl).append(url).toString();
        Map<String, String> param = new HashMap<>();
        param.put("authCode", "123321");
        param.put("seriaNo",seriaNo);
        Gson gson = new Gson();
        String json = gson.toJson(param);
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(json,
                type);
        Request request = new Request.Builder().post(requestBody).url(registerUrl).build();
        String seriaNoC = "";
        String responseStr;
        try (Response response = httpClient.newCall(request).execute()) {
            responseStr = response.body().string();
            logger.info(url+"返回结果:" + responseStr);
            JsonPath jsonPath = new JsonPath(responseStr);
            seriaNoC = jsonPath.getString("body.seriaNo");
            logger.info(url+"返回流水号：" + seriaNoC);
        } catch (IOException e) {
            logger.error(url+"异常");
            e.printStackTrace();
        }
        return seriaNoC;
    }

    /*
     * @param seriaNo
     * @param baseUrl
     * @return void
     * @author zhangfei
     * @description 设置登录密码qweqwe123
     * @updatetime 2020/11/27 17:37
     */
    private static void loginPwd(String seriaNo,String baseUrl){

        String url="/mobileEC/services/account/register_loginpwd";
        String registerUrl= new StringBuilder().append(baseUrl).append(url).toString();
        Map<String, String> param = new HashMap<>();
        param.put("password", "/MNZG8cyPEHfNE9k8BpXNg==");//qweqwe123
        param.put("seriaNo",seriaNo);
        Gson gson = new Gson();
        String json = gson.toJson(param);
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(json,
                type);
        Request request = new Request.Builder().post(requestBody).url(registerUrl).build();
        String responseStr;
        try (Response response = httpClient.newCall(request).execute()) {
            responseStr = response.body().string();
            logger.info(url+"返回结果:" + responseStr);
        } catch (IOException e) {
            logger.error(url + "异常");
            e.printStackTrace();
        }
    }

    /*
     * @param mobileNo
     * @return io.restassured.http.Cookies
     * @author zhangfei
     * @description 获取登录cookie
     * @updatetime 2020/11/30 17:57
     */
    private static Cookies getCookies(String mobileNo){
        String url="/mobileEC/services/account/auth_login";
        Map<String, String> param = new HashMap<>();
        String certNum = param.put("certNum", mobileNo);
        io.restassured.response.Response response;
        response = given().contentType("application/json").body(param).post(url);
        if(null==response) return null;
        return response.getDetailedCookies();
    }


}
