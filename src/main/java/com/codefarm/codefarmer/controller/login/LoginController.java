package com.codefarm.codefarmer.controller.login;

import com.codefarm.codefarmer.domain.oauth.GoogleOAuthRequest;
import com.codefarm.codefarmer.domain.oauth.GoogleOAuthResponse;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.service.join.JoinKakaoService;
import com.codefarm.codefarmer.service.join.JoinService;
import com.codefarm.codefarmer.service.login.GoogleService;
import com.codefarm.codefarmer.service.login.KakaoService;
import com.codefarm.codefarmer.service.login.NaverService;
//import com.codefarm.codefarmer.service.member.MemberService;
import com.codefarm.codefarmer.service.member.MemberService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/login/*")
@RequiredArgsConstructor
public class LoginController {

    private final KakaoService kakaoService;
    private final MemberService memberService;
    private final NaverService naverService;
    private final GoogleService googleService;
    private final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/kakao")
    public RedirectView kakaoLogin(String code, HttpSession session) throws Exception {
        log.info("코드 : "+code);
        String token = kakaoService.getKakaoAccessToken(code);
        String email = kakaoService.getKakaoEmailByToken(token);
        Long oauthId = kakaoService.getKakaoIdByToken(token);
        String memberOauthId = oauthId+"k";
        Long id = kakaoService.selectId(memberOauthId);
        String type = kakaoService.selectType(memberOauthId);

        session.setAttribute("oauthId", memberOauthId);
        session.setAttribute("memberId", id);
        session.setAttribute("memberType", type);
        session.setAttribute("token", token);
        if(kakaoService.checkOauth(memberOauthId) == 0){
            return new RedirectView("/register/form");
        }
        return new RedirectView("/main");
    }

    @GetMapping("/logoutkakao")
    public RedirectView kakaoLogout(HttpSession session){
        log.info("logout");
        kakaoService.logoutKakao((String)session.getAttribute("token"));
        session.invalidate();

        return new RedirectView("/main");
    }

    @GetMapping("/quitkakao")
    public RedirectView kakaoQuit(HttpSession session){
        log.info("quit");
        memberService.secession((Long)session.getAttribute("memberId"));
        kakaoService.quitKakao((String)session.getAttribute("token"));
        session.invalidate();

        return new RedirectView("/main");
    }

    @GetMapping("")
    public String loginPage(){
        return "/login/login";
        }

    @GetMapping("/agreement")
    public String agreementPage(){
        return "/join/agreement";
    }


    @GetMapping("/naver")
    public RedirectView naverLogin(@RequestParam String code, HttpSession session) throws Exception {
        log.info("코드 : "+code);

        String token = naverService.getNaverAccessToken(code);
        String email = naverService.getNaverEmailByToken(token);
        String oauthId = naverService.getNaverIdByToken(token);
        String memberOauthId = oauthId+"n";
        Long id = naverService.selectId(memberOauthId);
        String type = naverService.selectType(memberOauthId);

        session.setAttribute("oauthId", memberOauthId);
        session.setAttribute("memberId", id);
        session.setAttribute("memberType", type);
        session.setAttribute("token", token);

        if (naverService.checkOauth(memberOauthId) == 0){
            return new RedirectView("/register/form");
        }

        return new RedirectView("/main");

    }


    @GetMapping("/google")
    public RedirectView googleLogin(Model model, @RequestParam(value = "code") String authCode, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        log.info("Login 구글 들어옴 : "+authCode);

        RestTemplate restTemplate = new RestTemplate();

        //Google OAuth Access Token 요청을 위한 파라미터 세팅
        GoogleOAuthRequest googleOAuthRequestParam =  new GoogleOAuthRequest();
        googleOAuthRequestParam.setClientId("467034188557-a2dk8gl9s7rtj2nvh0f0t5ls35gnfsi3.apps.googleusercontent.com");
        googleOAuthRequestParam.setClientSecret("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");
        googleOAuthRequestParam.setCode(authCode);
        googleOAuthRequestParam.setRedirectUri("http://localhost:5555/login/google");
        googleOAuthRequestParam.setGrantType("authorization_code");

        //JSON 파싱을 위한 기본값 세팅
        //요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //AccessToken 발급 요청
        ResponseEntity<String> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token", googleOAuthRequestParam, String.class);

        //Token Request
        GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {
        });

        //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
        String jwtToken = result.getIdToken();
        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo").queryParam("id_token", jwtToken).toUriString();

        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});
        model.addAllAttributes(userInfo);
        model.addAttribute("token", result.getAccessToken());


        session.setAttribute("oauthId", result.getAccessToken() + "g");
        redirectAttributes.addFlashAttribute("memberOauthId", result.getAccessToken() + "g");
        redirectAttributes.addFlashAttribute("memberEmail", userInfo.get("email"));


        String email = userInfo.get("email");

        System.out.println("이메일 : " + email);

        String oauthId = result.getAccessToken();
        String memberOauthId = oauthId+"g";
        Long id = googleService.findByMember(email);

        System.out.println("아이디 : " + id);

        String type = naverService.selectType(memberOauthId);


        session.setAttribute("oauthId", memberOauthId);
        session.setAttribute("memberId", id);
        session.setAttribute("memberType", type);

        System.out.println("멤버타입 : " + type);

//        session.setAttribute("token", oauthId);

        System.out.println("토큰 : " + memberOauthId);

        if (memberRepository.findByEmail(email) == 0){
            return new RedirectView("/register/form");
        }

        return new RedirectView("/main");

    }

    @GetMapping("/test")
    public String loginTest(){
        return "/login/test";
    }

    @GetMapping("/logoutnaver")
    public RedirectView naverLogout(HttpSession session){
        log.info("logout");
        session.invalidate();

        return new RedirectView("/main");
    }

    @GetMapping("/logoutgoogle")
    public RedirectView googleLogout(HttpSession session){
        log.info("logout");
        session.invalidate();

        return new RedirectView("/main");
    }


    @GetMapping("/quitgoogle")
    public RedirectView googleQuit(HttpSession session){
        log.info("quit");
        memberService.secession((Long)session.getAttribute("memberId"));
        session.invalidate();

        return new RedirectView("/main");
    }


    @GetMapping("/quitnaver")
    public RedirectView naverQuit(HttpSession session){
        log.info("quit");
        memberService.secession((Long)session.getAttribute("memberId"));
        session.invalidate();

        return new RedirectView("/main");
    }


}
