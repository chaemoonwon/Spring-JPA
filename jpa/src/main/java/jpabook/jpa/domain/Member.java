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

    @Embedded       //내장 타입
    private Address address;


    // 매핑된 상태
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
