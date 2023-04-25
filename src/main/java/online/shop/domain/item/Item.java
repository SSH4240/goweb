package online.shop.domain.item;

import lombok.Getter;
import lombok.Setter;
import online.shop.domain.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy="items")
    private List<Category> categories = new ArrayList<Category>();
}