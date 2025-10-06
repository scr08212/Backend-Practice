package nkm.study.jpabook.jpashop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nkm.study.jpabook.jpashop.domain.Address;
import nkm.study.jpabook.jpashop.domain.Member;
import nkm.study.jpabook.jpashop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {

        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if(result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getStreet(), memberForm.getCity(), memberForm.getZipcode());

        Member member = new Member();
        member.setAddress(address);
        member.setName(memberForm.getName());

        memberService.join(member);
        return "redirect:/";
    }
}
