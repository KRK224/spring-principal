package spring.principal.core.order.interfaces;

import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

    MemberRepository getMemberRepository();
}
