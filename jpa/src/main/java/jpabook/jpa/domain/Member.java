package jpabook.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded       //내장 타입은 @Embedded 나 @Embeddable 두 개 중 1개만 있으면 됨.
    private Address address;


    // 매핑된 상태
    @OneToMany(mappedBy = "member")    // 한 명의 회원이 여러 개 상품 주문
    private List<Order> orders = new ArrayList<>();
}
