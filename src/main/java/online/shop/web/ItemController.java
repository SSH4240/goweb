package online.shop.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.shop.domain.item.Book;
import online.shop.dto.BookForm;
import online.shop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(Model model){
        model.addAttribute("bookForm",new BookForm());
        return "item-form";
    }
    @PostMapping("items/new")
    public String create(@ModelAttribute BookForm bookForm, BindingResult bindingResult, Model model){
        if (!StringUtils.hasText(bookForm.getName())) {
            bindingResult.rejectValue("name","required");
        }
        if (bookForm.getPrice() == null || bookForm.getPrice() < 1000 || bookForm.getPrice() > 1000000) {
            bindingResult.rejectValue("price","range", new Object[]{1000,1000000},null);
        }
        if (bookForm.getStockQuantity() == null || bookForm.getStockQuantity() >= 9999) {
            bindingResult.rejectValue("stockQuantity","max", new Object[]{9999}, null);
        }
        if (bookForm.getPrice() != null && bookForm.getStockQuantity() != null) {
            int resultPrice = bookForm.getPrice() * bookForm.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "item-form2";
        }

        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.saveItem(book);
        return "redirect:/";
    }
}
