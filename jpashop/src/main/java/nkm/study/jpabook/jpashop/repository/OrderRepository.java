package nkm.study.jpabook.jpashop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import nkm.study.jpabook.jpashop.domain.Order;
import nkm.study.jpabook.jpashop.domain.OrderStatus;
import nkm.study.jpabook.jpashop.domain.QMember;
import nkm.study.jpabook.jpashop.domain.QOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static nkm.study.jpabook.jpashop.domain.QMember.member;
import static nkm.study.jpabook.jpashop.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEquals(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression statusEquals(OrderStatus status) {
        if(status == null)
            return null;

        return order.status.eq(status);
    }

    private BooleanExpression nameLike(String name) {
        if(!StringUtils.hasText(name))
            return null;

        return member.name.like(name);
    }


}
