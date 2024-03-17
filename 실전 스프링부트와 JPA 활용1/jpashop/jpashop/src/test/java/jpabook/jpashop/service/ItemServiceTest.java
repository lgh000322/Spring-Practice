package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;
    @Test
    public void 아이템_저장()throws Exception {
        //given
        Item item = new Book();
        item.setName("테스트");

        //when
        Long itemId = itemService.saveItem(item);

        //then
        assertEquals(item.getId(),itemId);
    }

    @Test
    public void 아이템_전체_조회()throws Exception {
        //given
        Item item = new Book();
        item.setName("테스트");

        Item item2 = new Book();
        item.setName("테스트2");
        int inputItemAmount=2;
        Long itemId = itemService.saveItem(item);
        Long itemId2 = itemService.saveItem(item2);

        //when
        int itemSize = itemService.findItems().size();
        //then
        assertEquals(inputItemAmount, itemSize);

    }

    @Test
    public void 단일_아이템_조회() {
        //given
        Item item = new Book();
        item.setName("테스트");
        Long itemId = itemService.saveItem(item);

        //when
        Item findItem = itemService.findOne(itemId);

        //then
        assertEquals(item,findItem);

    }
}