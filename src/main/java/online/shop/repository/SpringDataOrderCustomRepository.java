package online.shop.repository;

import online.shop.domain.Order;

public interface SpringDataOrderCustomRepository  {
    Order findWithMemberItemDynamic(Long orderId);
}
