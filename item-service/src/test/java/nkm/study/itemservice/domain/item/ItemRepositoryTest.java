package nkm.study.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);
        // when
        Item savedItem = itemRepository.save(item);

        // get
        Item foundItem = itemRepository.findById(item.getId());

        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 10000, 10);
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        assertThat(items).hasSize(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        Item item1 = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long id = savedItem.getId();

        Item updateParam = new Item("item2", 100, 1);
        itemRepository.update(id, updateParam);

        Item foundItem = itemRepository.findById(id);
        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void clearStore() {
    }
}