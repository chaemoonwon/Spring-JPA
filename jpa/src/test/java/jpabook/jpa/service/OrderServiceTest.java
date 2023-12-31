package jpabook.jpa.service;

import jakarta.persistence.EntityManager;
import jpabook.jpa.domain.Address;
import jpabook.jpa.domain.Member;
import jpabook.jpa.domain.Order;
import jpabook.jpa.domain.OrderStatus;
import jpabook.jpa.domain.item.Book;
import jpabook.jpa.domain.item.Item;
import jpabook.jpa.exception.NotEnoughStockException;
import jpabook.jpa.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception {
        //given
        //회원
        Member member = createMember();

        //상품
        Book book = createBook("시골 JPA", 10000,10);

        //주문수량
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //then
        Order order = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다",
                1,
                order.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, order.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());


    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }


    @Test()
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000,10);

        int orderCount = 11;


        //when
//        orderService.order(member.getId(), item.getId(), orderCount);
        assertThrows(NotEnoughStockException.class, () ->orderService.order(member.getId(), item.getId(), orderCount));
//        Assertions.assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
//                .isInstanceOf(NotEnoughStockException.class);

        //then
        fail("재고 수량 부족 예외가 발생해야 한다");

    }


    @Test
    public void 주문취소() throws Exception {
        //given
        Book item = createBook("시골 JPA", 10000, 10);
        Member member = createMember();

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order order = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, order.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다", 10, item.getStockQuantity());

    }
//    @Test
//    public void () throws Exception {
    //given

    //when

    //then
//    }
//    @Test
//    public void () throws Exception {
    //given

    //when

    //then
//    }

}
