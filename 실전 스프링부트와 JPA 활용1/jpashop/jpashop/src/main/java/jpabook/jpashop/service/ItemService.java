package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    //변경 감지 업데이트
    @Transactional
    public void updateItem(Long itemId, String name,int price,int stockQuantity) {
        //findItem은 영속 상태이기 때문에 merge를 따로 호출하지 않아도됨
        //Transactional 어노테이션에 의해서 트랜잭션이 커밋됨 => JPA는 flush를 날리고 변경된것을 찾음
        //이 때 바뀐값이 있으면 디비에 업데이트 쿼리를 날림
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
