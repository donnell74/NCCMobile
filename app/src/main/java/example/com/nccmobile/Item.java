package example.com.nccmobile;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by greg on 3/24/15.
 */
@Table(name = "items", id = "_id")
public class Item extends Model {
    @Column(name = "name")
    public String name;
    @Column(name = "image")
    public String image;
    @Column(name = "price")
    public Double price;
    @Column(name = "quantityInStock")
    public int quantityInStock;
    @Column(name = "category")
    public String category;

    public Item() {
        super();
    }

    public Item(String name, String image, Double price, int quantityInStock, String category) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
    }

    public Item(String name, String image, Double price) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantityInStock = 0;
        this.category = "Unknown";
    }

    public static List<Item> getAll() {
        return new Select()
                .from(Item.class)
                .execute();
    }


    @Override
    public String toString() {
        getId();
        return "Item{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", category='" + category + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getCategory() {
        return category;
    }
}