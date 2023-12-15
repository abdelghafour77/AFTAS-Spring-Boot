package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.dtos.requests.MemberRequest;
import com.example.aftasspringboot.entities.Member;

import java.util.List;

public interface MemberService {

    public List<Member> getAllMembers();

    public Member getMemberById(Long id);

    public Member getMemberByCriteria(String criteria);

    public Member createMember(MemberRequest memberRequest);

    public Member updateMember(MemberRequest member , Long memberId);

    public void deleteMember(Long id);

    public List<Member> searchMembers(String value);
}
