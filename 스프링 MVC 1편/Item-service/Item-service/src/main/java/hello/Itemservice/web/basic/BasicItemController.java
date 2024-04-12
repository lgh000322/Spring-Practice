package hello.Itemservice.web.basic;

import hello.Itemservice.domain.item.Item;
import hello.Itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Slf4j
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

  /*  @PostMapping("/add")
    public String addItemV1(@RequestParam("itemName") String itemName,
                       @RequestParam("price") int price,
                       @RequestParam("quantity") Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
      //  model.addAttribute("item", item); //ModelAttribute 어노테이션을 쓰면 뷰에 띄워줄때 자동추가됨, 받을때도 자동으로 받음
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        //  model.addAttribute("item", item); //ModelAttribute에 이름을 생략할 경우 클래스의 첫글자만 소문자로 바뀌고 모델에 추가해준다.
        return "basic/item";
    }*/

   /* @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        //  model.addAttribute("item", item);
        return "basic/item";
    }*/

    /*@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        //  model.addAttribute("item", item);
        // V4의 경우 post요청 처리 후 뷰로 넘어가게 됨. 그때 새로고침을 누르면 이전의 post가 또 호출되서 저장이 반복적으로 일어나는 문제가 발생함
        // 그래서 PRG(Post Redirect Get)방식을 사용해서 post 요청을 한 후에는 redirect를 해서 문제를 해결함
        return "redirect:/basic/items/" + item.getId();
    }*/

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        //이전 버전에는 itemId가 인코딩되지 않고 들어가는 문제가 있었음
        //새로운 요구사항으로 아이템 추가시 "아이템이 추가되었습니다."와 같은 메시지를 출력해야함
        //리다이렉트에 포함하지 않은 속성값은 쿼리파라미터로 들어감 (?status=true)
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute("item") Item item) {
        itemRepository.update(itemId, item);
        log.info("업데이트 수행, 받은 아이템아이디={}", itemId);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("ItemA", 10000, 10));
        itemRepository.save(new Item("ItemB", 20000, 20));
    }
}
