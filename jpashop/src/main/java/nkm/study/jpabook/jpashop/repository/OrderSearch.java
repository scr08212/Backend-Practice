package nkm.study.jpabook.jpashop.repository;

import lombok.Getter;
import lombok.Setter;
import nkm.study.jpabook.jpashop.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
