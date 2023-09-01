package com.ict.edu2.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter 
//@EqualsAndHashCode
//@RequiredArgsConstructor : final이나 @NotNull인 필드값만 파라미터로 받는 생성자
@Data// Data가 이 모든애들 처리를 역활을 해줌@Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 
// 인자가 없는 기본 생성자
@NoArgsConstructor
// 모든 인자가 들어있는 생성자 자동 생성
@AllArgsConstructor
public class VO {
    private String m_id , m_pw , m_name , m_age , m_reg;
}
