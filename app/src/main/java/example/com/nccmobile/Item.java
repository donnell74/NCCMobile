package example.com.nccmobile;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "items")
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
    @Column(name = "isFav")
    public int isFav;

    public Item() {
        super();
    }

    public Item(String name, String image, Double price, int quantityInStock, String category, int isFav) {
        super();
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
        this.isFav = isFav;
    }

    public Item(String name, String image, Double price) {
        super();
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantityInStock = 0;
        this.category = "Unknown";
        this.isFav = 0;
    }

    public static List<Item> getAll() {
        return new Select()
                .from(Item.class)
                .execute();
    }

    public static List<Item> getAllFavorites() {
        return new Select()
                .from(Item.class)
                .where("isFav = ?", 1)
                .execute();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", category='" + category + '\'' +
                ", isFav=" + isFav +
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

    public int getIsFav() {
        return isFav;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
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