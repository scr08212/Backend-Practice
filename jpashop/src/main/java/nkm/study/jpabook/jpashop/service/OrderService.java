package nkm.study.jpabook.jpashop.service;

import lombok.RequiredArgsConstructor;
import nkm.study.jpabook.jpashop.domain.Delivery;
import nkm.study.jpabook.jpashop.domain.Member;
import nkm.study.jpabook.jpashop.domain.Order;
import nkm.study.jpabook.jpashop.domain.OrderItem;
import nkm.study.jpabook.jpashop.domain.item.Item;
import nkm.study.jpabook.jpashop.repository.ItemRepository;
import nkm.study.jpabook.jpashop.repository.MemberRepository;
import nkm.study.jpabook.jpashop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.create(item, item.getPrice(), count);

        Order order = Order.create(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

//    public List<Order> findOrders(Long orderId){
//        return orderRepository.findAll(orderSearch);
//    }
}
