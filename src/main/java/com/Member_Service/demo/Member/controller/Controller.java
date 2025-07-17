package com.Member_Service.demo.Member.controller;

import com.Member_Service.demo.Member.domain.Member;
import com.Member_Service.demo.Member.dto.MemberCreateDto;
import com.Member_Service.demo.Member.dto.MemberLIstDto;
import com.Member_Service.demo.Member.dto.MemberUpdatePwDto;
import com.Member_Service.demo.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class Controller {
    private final MemberService memberService;
//    회원가입
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody MemberCreateDto memberCreateDto){
        this.memberService.save(memberCreateDto);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
//    회원목록조회
    @GetMapping("/list")
    public List<MemberLIstDto> list(){
        return memberService.findAll();
    }
//    회원 id로 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

//    회원 비밀번호 업데이트
    @PatchMapping("/updatepw")
    public void updatePw(@RequestBody MemberUpdatePwDto memberUpdatePwDto){
        memberService.updatePw(memberUpdatePwDto);
    }

//    회원 탈퇴하기
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        memberService.delete(id);
    }
}
