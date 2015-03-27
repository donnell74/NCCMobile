package example.com.nccmobile;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "carts")
public class Cart extends Model {
    @Column(name = "item_id")
    public Item item_id;
    @Column(name = "quantityInCart")
    public int quantityInCart;

    public Cart() {
        super();
    }

    public Cart(Item item_id, int quantityInCart) {
        super();
        this.item_id = item_id;
        this.quantityInCart = quantityInCart;
    }

    public Cart(Item item_id) {
        super();
        this.item_id = item_id;
        this.quantityInCart = 1;
    }

    public static List<Cart> getAll() {
        return new Select()
                .from(Cart.class)
                .execute();
    }

    public static Cart findItemId(Long id) {
        return new Select()
                .from(Cart.class)
                .where("item_id = ?", id)
                .executeSingle();
    }

    public Item getItem_id() {
        return item_id;
    }

    public void setItem_id(Item item_id) {
        this.item_id = item_id;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + getId() +
                ", item_id=" + item_id.getId() +
                ", quantityInCart=" + quantityInCart +
                '}';
    }
}