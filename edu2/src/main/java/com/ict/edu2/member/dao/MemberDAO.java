package com.ict.edu2.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu2.member.vo.VO;

@Service
public class MemberDAO {
  @Autowired
  private SqlSessionTemplate ss; 

  public List<VO> getList(){
    List<VO> list = ss.selectList("members.list");
    return list;
 } 

}
