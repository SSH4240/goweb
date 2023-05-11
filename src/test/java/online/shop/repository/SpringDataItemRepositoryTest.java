package online.shop.repository;

import online.shop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SpringDataItemRepositoryTest {
    @Autowired
    SpringDataItemRepository springDataItemRepository;

    @Test
    void 특정가격_이상_상품검색(){
        List<Item> items = springDataItemRepository.findByPriceGreaterThanEqual(20000);
        items.stream().forEach(v-> System.out.println("v.getName() = " + v.getName()));
    }
    @Test
    void 이름포함_상품검색(){
        List<Item> items = springDataItemRepository.findByNameLike("spring%");
        items.stream().forEach(i-> System.out.println("i.getName() = " + i.getName()));
    }
    @Test
    void 페이징_테스트(){
        PageRequest firstPage = PageRequest.of(0, 2);
        PageRequest secondPage = PageRequest.of(1, 2);

        Page<Item> firstItems = springDataItemRepository.findAll(firstPage);
        firstItems.stream().forEach(v-> System.out.println("v.getName() = " + v.getName()));

        Page<Item> secondItems = springDataItemRepository.findAll(firstPage);
    }
    @Test void Page_Slice(){
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.ASC, "price"));
        Page<Item> pageBy = springDataItemRepository.findPageBy(pageRequest);
        System.out.println("==================================================");
        Slice<Item> sliceBy = springDataItemRepository.findSliceBy(pageRequest);
    }


}