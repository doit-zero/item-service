package hello.itemservice.item;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class itemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach(){
        itemRepository.clear();
    }
    @Test
    public void findById_test() throws Exception{
    //given
        Item item = new Item("test1",1000,200);
        //when
        Item savedItem = itemRepository.save(item);
    //then
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    public void findAll_test() throws Exception{
    //given
        Item item1 = new Item("test1",1000,200);
        Item item2 = new Item("test1",1000,200);
    //when
        itemRepository.save(item1);
        itemRepository.save(item2);
    //then
        List<Item> itemList = itemRepository.findAll();
        Assertions.assertThat(itemList.size()).isEqualTo(2);
    }

    @Test
    public void update_test() throws Exception{
    //given
        Item item = new Item("test1",1000,200);
        itemRepository.save(item);
    //when
        Item paramItem = new Item("test2",2000,200);
        itemRepository.update(item.getId(),paramItem);

    //then
        Item updatedItem = itemRepository.findById(item.getId());
        Assertions.assertThat(updatedItem.getItemName()).isEqualTo(paramItem.getItemName());
        Assertions.assertThat(updatedItem.getPrice()).isEqualTo(paramItem.getPrice());
        Assertions.assertThat(updatedItem.getQuantity()).isEqualTo(paramItem.getQuantity());
    }

}
