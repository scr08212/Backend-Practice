package nkm.study.jpabook.jpashop.service;

import lombok.RequiredArgsConstructor;
import nkm.study.jpabook.jpashop.domain.*;
import nkm.study.jpabook.jpashop.domain.item.Item;
import nkm.study.jpabook.jpashop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }

}
