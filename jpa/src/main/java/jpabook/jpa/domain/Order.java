package jpabook.jpa.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    //연관관계 주인 정하기 => 두 필드 중 하나를 주인으로 잡아서 변경
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    private Delivery delivery;

    //주문 시간
    private LocalDateTime orderDate;

    //주문 상태(order, cancel)
    private OrderStatus status;

}
