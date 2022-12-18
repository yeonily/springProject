package com.codefarm.codefarmer.service.join;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@Slf4j
public class JoinGoogleService {
    public String getGoogleAccessToken(String code){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://oauth2.googleapis.com/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=467034188557-a2dk8gl9s7rtj2nvh0f0t5ls35gnfsi3.apps.googleusercontent.com"); // TODO REST_API_KEY 입력
            sb.append("&client_secret=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"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();


            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }




    public void googleProfile(String token) {
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://accounts.google.com/o/oauth2/v2/auth";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);



        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //  id
    public String getGoogleIdByToken(String token) throws Exception{
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String googleId = null;
        try {
            String apiURL = "https://accounts.google.com/o/oauth2/v2/auth";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            googleId = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();

            return googleId;
        } catch (Exception e) {
            System.out.println(e);
        }

        return googleId;
    }

    //    name
    public String getGoogleNameByToken(String token) throws Exception{
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String googleName = null;
        try {
            String apiURL = "https://accounts.google.com/o/oauth2/v2/auth";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            googleName = element.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();

            return googleName;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }


    //   mobile
    public String getGoogleMobileByToken(String token) throws Exception{
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String mobile = null;
        try {
            String apiURL = "https://accounts.google.com/o/oauth2/v2/auth";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            mobile = element.getAsJsonObject().get("response").getAsJsonObject().get("mobile").getAsString();

            return formToPhoneNumber(mobile);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

//    핸드폰 형식 변환

    public String formToPhoneNumber(String phoneNumber){

        String [] arr = phoneNumber.split("-");
        String result = "";
        for (String text : arr){
            result+=text;
        }

        return result;
    }


    //    email
    public String getGoogleEmailByToken(String token) throws Exception{
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String email = null;
        try {
            String apiURL = "https://accounts.google.com/o/oauth2/v2/auth";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //Gson 라이브러리로 JSON파싱

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            email = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();

            return email;
        } catch (Exception e) {
            System.out.println(e);
        }

        return email;
    }







// 미구현

    public void logoutGoogle(String token){
        String reqURL ="https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=mCOGR2PgjWmMz5WHuVwE&client_secret=yDde_mmeQf&access_token="+token+"&service_provider=NAVER";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

//            conn.setRequestProperty("Authorization", "Bearer " + token);
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            if(responseCode ==400)
                throw new RuntimeException("네이버 로그아웃 도중 오류 발생");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";
            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            log.info("결과");
            log.info(result);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void googleLogoutTry() {
        BufferedReader in = null;

        try {
            URL obj = new URL("https://nid.naver.com/nidlogin.logout"); // 호출할 url
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();

            con.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null) { // response를 차례대로 출력
                System.out.println(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }

    }
//
//    public void logoutNaver(){
//        String reqURL ="https://nid.naver.com/nidlogin.logout";
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("post");
//
//            int responseCode = conn.getResponseCode();
//            log.info("responseCode : " + responseCode);
//
//            if(responseCode ==400)
//                throw new RuntimeException("네이버 로그아웃 도중 오류 발생");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            String br_line = "";
//            String result = "";
//            while ((br_line = br.readLine()) != null) {
//                result += br_line;
//            }
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void naverLogout(){
        try {
            Desktop.getDesktop().browse(new URI("http://mdago.tistory.com/"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}