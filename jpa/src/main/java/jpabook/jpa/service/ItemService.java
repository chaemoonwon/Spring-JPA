package jpabook.jpa.service;


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

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
