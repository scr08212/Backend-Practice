package nkm.study.jpabook.jpashop.controller;

import lombok.RequiredArgsConstructor;
import nkm.study.jpabook.jpashop.domain.Member;
import nkm.study.jpabook.jpashop.domain.item.Item;
import nkm.study.jpabook.jpashop.service.ItemService;
import nkm.study.jpabook.jpashop.service.MemberService;
import nkm.study.jpabook.jpashop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Item> items = itemService.findAll();
        List<Member> members = memberService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("members", members);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }
}
