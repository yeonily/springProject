package com.codefarm.codefarmer.service.login;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    public String getAccessToken(String code) {
        String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=b6c8cc415d06296f3dad49e498e431cc");
            sb.append("&redirect_uri=http://localhost:5555/login/kakao");
            sb.append("&code="+code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = "+responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            while((line = br.readLine()) != null){
                result += line;
            }
            System.out.println("response body = "+result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return accessToken;
    }




    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer" + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = "+ responseCode );

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while((line = br.readLine()) != null){
                result += line;
            }
            System.out.println("response body = " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickName = properties.getAsJsonObject().get("nickname").getAsString();
            String email = properties.getAsJsonObject().get("email").getAsString();


//            int id = element.getAsJsonObject().get("id").getAsInt();
//            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
//            String email = "";
//            if(hasEmail){
//                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
//            }

            userInfo.put("nickname", nickName);
            userInfo.put("email", email);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

}
