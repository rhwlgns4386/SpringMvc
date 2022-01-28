package com.hello.demo.web.frontcontroller.v3.controller;

import com.hello.demo.domain.member.Member;
import com.hello.demo.domain.member.MemberRepository;
import com.hello.demo.web.frontcontroller.ModelView;

import com.hello.demo.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository=MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> members = memberRepository.findAll();
        ModelView modelView=new ModelView("members");
        modelView.getModel().put("members",members);

        return modelView;
    }
}
