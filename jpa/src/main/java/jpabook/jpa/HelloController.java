package jpabook.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // Model에서 view로 넘길 수 있음
    @GetMapping("hello")
    public String hell(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

}