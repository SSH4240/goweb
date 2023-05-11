package online.shop.repository;

import online.shop.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPriceGreaterThanEqual(int price);
    List<Item> findByNameLike(String name);
    List<Item> findByNameLikeAndPriceLessThanEqualOrderByPriceAsc(String name, int price);
    Page<Item> findPageBy(Pageable pageable);
    Slice<Item> findSliceBy(Pageable pageable);
}
