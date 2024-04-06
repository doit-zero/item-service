package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long,Item> store = new HashMap<>();
    private static Long sequence = 0L;
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public Item findById(Long itemId){
        return store.get(itemId);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }
    public void clear(){
        store.clear();
    }

    public void update(Long itemId, Item paramItem){
        Item item = findById(itemId);
        item.setItemName(paramItem.getItemName());
        item.setPrice(paramItem.getPrice());
        item.setQuantity(paramItem.getQuantity());
    }


}
