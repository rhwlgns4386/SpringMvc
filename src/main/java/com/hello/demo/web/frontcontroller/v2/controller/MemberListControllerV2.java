package com.hello.demo.web.frontcontroller.v2.controller;

import com.hello.demo.domain.member.Member;
import com.hello.demo.domain.member.MemberRepository;
import com.hello.demo.web.frontcontroller.MyView;
import com.hello.demo.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members",members);

        String path="/WEB-INF/views/members.jsp";
        return new MyView(path);
    }
}
