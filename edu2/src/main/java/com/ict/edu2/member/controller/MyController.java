package com.ict.edu2.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu2.member.dao.MemberDAO;
import com.ict.edu2.member.vo.VO;

import lombok.extern.slf4j.Slf4j;


  @CrossOrigin(originPatterns = "http://localhost:3000")
  @RestController
@RequestMapping("/member")
@Slf4j
public class MyController{

  @Autowired
  private MemberDAO memberDAO;

  @GetMapping("/list")
  public Map<String,Object> getList(){
    List<VO> list = memberDAO.getList();
    Map<String,Object> resMap = new HashMap<>();
    resMap.put("list", list);
    return resMap ;
  }


  @GetMapping("/")
  public String Hello(){
    return "Hello World";
  }

  
  @PostMapping("/login")
  public Map<String,Object> logIn(VO vo){

    System.out.println("\nlogin 서버 !!!!\n");
    System.out.println("\n"+vo.getM_id()+"\n");
    System.out.println("\n"+vo.getM_pw()+"\n");

    log.info("login 서버!!");
    log.info(vo.getM_id());
    log.info(vo.getM_pw());

    Map<String, Object> resMap = new HashMap<>();
    resMap.put("chk", 0);

    return resMap;
  }

}
