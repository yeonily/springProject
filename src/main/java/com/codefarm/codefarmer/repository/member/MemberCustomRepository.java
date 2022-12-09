package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.type.Status;

import java.util.List;

public interface MemberCustomRepository {
    //닉네임 중복검사
    public Integer checkNick(String nickname);
    //내가 등록한 프로그램 신청자 select
    public List<MemberProgram> findMyProgramApplyers(Long memberId);
    //내가 등록한 프로그램 신청자 select
    public List<MemberAlba> findMyAlbaApplyers(Long memberId);
    //oauthid 검사
    public Integer checkOauth(String oauthId);
    //내가 등록한 알바목록
    public List<Alba> findMyAlba(Long memberId);
    //내가 등록한 프로그램목록
    public List<Program> findMyProgram(Long memberId);
    //내가 등록한 게시글
    public List<Board> findMyBoard(Long memberId);
    //내가 문의한 목록
    public List<Inquire> findMyInquire(Long memberId);


}
