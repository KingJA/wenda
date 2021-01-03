package com.kingja.wenda.provider;

import com.alibaba.fastjson.JSON;
import com.kingja.wenda.dto.AccessTokenDTO;
import com.kingja.wenda.dto.GithubUser;

import org.springframework.stereotype.Component;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Description:TODO
 * Create Time:2020/12/22 0022 23:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Component
public class GithubProvider {


    public String getGithubToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            //返回格式：access_token=7bd2d0fcba088152b9f7c24dd587634c8809743f&scope=user&token_type=bearer
            return result.substring(result.indexOf("=") + 1, result.indexOf("&"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            return JSON.parseObject(result, GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
