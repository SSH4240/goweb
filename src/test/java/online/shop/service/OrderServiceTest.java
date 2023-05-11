package online.shop.service;

import online.shop.domain.Address;
import online.shop.domain.Member;
import online.shop.domain.OrderStatus;
import online.shop.domain.Order;
import online.shop.domain.item.Book;
import online.shop.domain.item.Item;
import online.shop.exception.NotEnoughStockException;
import online.shop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        itemService.saveItem(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("kim");
        memberService.join(member);
        return member;
    }

    @Test
    public void 상품주문() throws  Exception{

        Member member = new Member();
        member.setName("kim");
        memberService.join(member);

        Book book = new Book();
        book.setName("JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        itemService.saveItem(book);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order findOrder = (Order) orderRepository.findOne(orderId);

        assertThat(findOrder.getStatus())
                .as("상품 주문시 상태는 ORDER")
                .isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getStatus());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);

        int orderCount = 11;

        //when
        NotEnoughStockException exception = assertThrows(NotEnoughStockException.class,
                ()->orderService.order(member.getId(), item.getId(), orderCount));

        //then
        String message = exception.getMessage();
        System.out.println("message = " + message);
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("JPA", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL 이다.");
        assertEquals(Float.parseFloat("주문이 취소된 상품은 그만큼 재고가 증가해야 한다."), 10, item.getStockQuantity());
    }
}