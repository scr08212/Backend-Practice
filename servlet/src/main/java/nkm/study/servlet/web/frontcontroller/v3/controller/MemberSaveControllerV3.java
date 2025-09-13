package nkm.study.servlet.web.frontcontroller.v3.controller;

import nkm.study.servlet.domain.member.Member;
import nkm.study.servlet.domain.member.MemberRepository;
import nkm.study.servlet.web.frontcontroller.ModelView;
import nkm.study.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username =  paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}
