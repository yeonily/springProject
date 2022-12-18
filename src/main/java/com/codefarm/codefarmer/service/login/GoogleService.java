package com.codefarm.codefarmer.service.login;

import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleService {
    private final MemberRepository memberRepository;

    public Long findByMember(String email) {
        System.out.println("들어옴 : " + memberRepository.findByMemberEmail(email));
        return memberRepository.findByMemberEmail(email);
    }
}
