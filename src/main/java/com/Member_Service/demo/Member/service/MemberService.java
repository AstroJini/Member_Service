package com.Member_Service.demo.Member.service;

import com.Member_Service.demo.Member.domain.Member;
import com.Member_Service.demo.Member.dto.MemberCreateDto;
import com.Member_Service.demo.Member.dto.MemberDetailDto;
import com.Member_Service.demo.Member.dto.MemberUpdatePwDto;
import com.Member_Service.demo.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberCreateDto memberCreateDto){
        if (memberRepository.findByEmail(memberCreateDto.getEmail().isPresent)){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        Member member = memberCreateDto.memberToEntity();
        this.memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        memberRepository.findAll().stream()
                .map(a->a.listFromEntity()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberDetailDto findById(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new NoSuchElementException("존재하지 않는 아이디입니다."));
        MemberDetailDto dto = MemberDetailDto.fromEntity(member);
        return dto;
    }

    public void updatePassword(MemberUpdatePwDto memberUpdatePwDto){
        Member member = memberRepository.findByEmail(memberUpdatePwDto.getEmail()).orElseThrow(()->new NoSuchElementException("no email found"));
        member.updatePw(memberUpdatePwDto.getPassword());
    }
    public void delete(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new NoSuchElementException("없는 사용자 입니다"));
        memberRepository.delete(member);
    }
}
