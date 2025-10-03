package nkm.study.jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import nkm.study.jpabook.jpashop.domain.Address;
import nkm.study.jpabook.jpashop.domain.Member;
import nkm.study.jpabook.jpashop.domain.Order;
import nkm.study.jpabook.jpashop.domain.OrderStatus;
import nkm.study.jpabook.jpashop.domain.item.Book;
import nkm.study.jpabook.jpashop.domain.item.Item;
import nkm.study.jpabook.jpashop.exception.NotEnoughStockException;
import nkm.study.jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 상품주문() {
        Member member = createMember();

        Book book = createBook();

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order one = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, one.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, one.getOrderItems().size(), "주문한 상품 종류 수가 정확 해야 함");
        assertEquals(10000 * orderCount, one.getTotalPrice(), "주문 가격은 가격 * 수량");
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야함");
    }

    private Book createBook() {
        Book book = new Book();
        book.setName("book1");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("서울", "강가", "12345"));
        em.persist(member);
        return member;
    }

    @Test
    void 주문취소() {
        Member member = createMember();
        Item item = createBook();
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order foundOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, foundOrder.getStatus(), "주문 취소시 상태는 CANCEL");
        assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다");
    }

    @Test
    void 수량초과() {
        Member member = createMember();
        Item item = createBook();

        int orderCount = 11;
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCount));

    }
}