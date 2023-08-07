package com.semtleapp.semtleapp.semtleuser.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.semtleapp.semtleapp.global.jwt.JwtProvider;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final SemtleUserRepository semtleUserRepository;



//https://kauth.kakao.com/oauth/authorize?
// response_type=code&client_id=03e358973f5106d2b7c655b68daf1862&redirect_uri=http://localhost:9000/oauth/kakao

    public String getKakaoAccessToken(String code) {
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

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
            sb.append("&client_id=03e358973f5106d2b7c655b68daf1862");
            sb.append("&redirect_uri=http://localhost:9000/oauth/kakao");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();


            System.out.println("======authService 요청 결과 시작======");
            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token for kakao: " + access_Token);
            System.out.println("refresh_token for kakao: " + refresh_Token);
            System.out.println("======authService 요청 결과 끝=======");

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    @Transactional(rollbackFor=SQLException.class)
    public Token createAndLoginKakaoUser(String accessTokenFromSocial)  {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //kakao access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + accessTokenFromSocial); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);


            //카카오에서 받은 정보 파싱
            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            Long id = element.getAsJsonObject().get("id").getAsLong(); //카카오의 인덱스

            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = ""; //카카오 계정의 이메일
            if (hasEmail) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            //카카오에 저장된 이름
            String name = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();


            //카카오 계정의 이메일과 현재 일반 회원가입한 이메일 중에서 같은 것이 없다면 카카오 계정의 이메일로 회원가입
            if(!semtleUserRepository.existsSemtleUserByEmailAndSocial(String.valueOf(email),"kakao")){
                SemtleUser semtleUser = SemtleUser.builder()
                        .email(email)
                        .password("")
                        .role("ROLE_USER")
                        .social("kakao")
                        .build();
                semtleUserRepository.save(semtleUser);

                //TODO 추가정보(학번, 학년 등) 언제 저장할지
                //TODO 이미 저장된 이메일에 social 연결
            }
            br.close();

            //로그인
            SemtleUser semtleUser = semtleUserRepository.findSemtleUserByEmailAndSocial(String.valueOf(email),"kakao").get();

            Token token = jwtProvider.createToken(semtleUser.getEmail());

            return token;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}
