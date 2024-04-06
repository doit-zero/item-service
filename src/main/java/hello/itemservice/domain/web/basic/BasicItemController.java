package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> itmes = itemRepository.findAll();
        model.addAttribute("items",itmes);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    // @PostMapping("/add")
//    public String addItemV1(@RequestParam("itemName") String itemName,
//                       @RequestParam("price") int price,
//                       @RequestParam("quantity") Integer quantity,
//                       Model model){
//        Item item = new Item(itemName,price,quantity);
//        log.info("item 상세정보 : {}",item);
//        itemRepository.save(item);
//
//        model.addAttribute("item",item);
//
//        return "basic/item";
//    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item,Model model){
        log.info("item 상세정보 : {}",item);
        itemRepository.save(item);
        return "basic/item";
    }
//    @PostMapping("/add")
    public String addItemV3(Item item,Model model){
        log.info("item 상세정보 : {}",item);
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item){
        log.info("item 상세정보 : {}",item);
        itemRepository.save(item);
        return "basic/item";
    }
   // @PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        log.info("item 상세정보 : {}",item);
        return "redirect:/basic/items/" + item.getId();
    }
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item sacedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",sacedItem.getId());
        redirectAttributes.addAttribute("status",true);
        log.info("item 상세정보 : {}",item);
        return "redirect:/basic/items/{itemId}";
    }

    // 테스트 용
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("test",222,222));
        itemRepository.save(new Item("test2",3333,222));
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId,@ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }



}
