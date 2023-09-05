package com.ict.edu2.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu2.member.dao.MemberDAO;
import com.ict.edu2.member.vo.DataVO;
import com.ict.edu2.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/member")
@Slf4j
public class MyController {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private MemberDAO memberDAO;

  @GetMapping("/list")
  public Map<String, Object> getList() {
    List<MemberVO> list = memberDAO.getList();
    Map<String, Object> resMap = new HashMap<>();
    resMap.put("list", list);
    return resMap;
  }

  @GetMapping("/")
  public String Hello() {
    return "Hello World";
  }

  @PostMapping("/login")
  public Map<String, Object> logIn(MemberVO vo, HttpSession session) {

    Map<String, Object> resMap = new HashMap<>();
    DataVO dataVO = new DataVO();
    resMap.put("data", dataVO);

    // 입력받은 아이디가 존재하는지 검사
    int cnt = memberDAO.getIDCnt(vo.getM_id());
    if (cnt <= 0) {
      dataVO.setSuccess(false);
      dataVO.setMessage("아이디가 존재하지 않습니다.");
      resMap.put("data", dataVO);
      return resMap;
    } else {
      // 입력받은 아이디를 이용해서 DB 패스워드를 구하자
      MemberVO mvo = memberDAO.getMemberOne(vo.getM_id());
      // 가지고 온 패스워드와 입력된 패스워드가 같은 지 판별하자
      if (! passwordEncoder.matches(vo.getM_pw(), mvo.getM_pw())) {
        dataVO.setSuccess(false);
        dataVO.setMessage("비밀번호가 일치하지 않습니다.");
        resMap.put("data", dataVO);
        return resMap;
      } else {
        // 로그인 정보 저장(세션)
        session.setAttribute("mvo", mvo);
        dataVO.setSuccess(true);
        dataVO.setMessage("로그인 성공");
        dataVO.setData(mvo); // 프론트엔드에서 사용하기 위해서 저장
        resMap.put("data", dataVO);
        return resMap;
      }
      // 아이디의 패스워드가 DB에 패스워드와 같은지 검사
      // 그러나 패스워드가 암호화 되어 있으므로 암호화 처리 하자

    }
  }

  @PostMapping("/join")
  public Map<String, Object> join(MemberVO vo) {
    Map<String, Object> resMap = new HashMap<>();
    DataVO dataVO = new DataVO();
    resMap.put("data", dataVO);

    int result = memberDAO.getIDCnt(vo.getM_id()); // db에 있는 아이디인지 중복 체크
    if(result<1){
      // 중복된 아이디없음 회원가입 진행
      // 비밀번호 암호화 처리
      String pwd = passwordEncoder.encode(vo.getM_pw());
      vo.setM_pw(pwd);
      int result2 = memberDAO.Join(vo);
      MemberVO vo2 = memberDAO.getMemberOne(vo.getM_id());
      if(result2<1){  
        dataVO.setSuccess(false);
      dataVO.setMessage("회원가입 실패");
      resMap.put("data", dataVO);
      return resMap;
      }else{
      dataVO.setSuccess(true);
      dataVO.setMessage("회원가입 성공");
      dataVO.setData(vo2);
      resMap.put("data", dataVO);
      return resMap;
      }

    }else{ 
      // 아이디 중복됨 불가능
      dataVO.setSuccess(false);
      dataVO.setMessage("이미 있는 아이디입니다.");
      resMap.put("data", dataVO);
        return resMap;
    }
  }

}
