package jpabook.jpa;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

//lombok 동작 확인
@Getter
@Setter
public class Hello {

    String data;
}
