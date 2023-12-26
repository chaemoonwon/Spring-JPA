package jpabook.jpa.service;


import jpabook.jpa.domain.item.Book;
import jpabook.jpa.domain.item.Item;
import jpabook.jpa.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
// 상품 서비스는 단순히 ItemRepository에 위임만 하는 클래스
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional  // 여기서 설정한 Transactional이 실행됨
    public void saveItem(Item item) {
        itemRepository.save(item);
    }


    // 엔티티를 변경할 때는 항상 변경 감지 사용
    // 변경 감지하는 방법
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {

        Item findItem = itemRepository.findOne(itemId);

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        // 엔티티안에서 메소드를 만들어서 추적하는 방식으로 만드는 것이 좋음
//      findItem.change(name, price, stockQuantity)
    }



    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
