import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "shoppingCart")
@SessionScoped
public class ShoppingCart {
    
    private List<Item> cart = new ArrayList<Item>();
    private float total;

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public float getTotal() {
        total = 0;
        for (Item item : cart) {
            total = total + (item.getQuantity()*item.getP().getCardPrice());
        }
        return total;
        
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public String addToCart(Products p){
        
        //increment quantity if duplicate item
        for(Item item : cart) {
            if(item.getP().getCard_id() == p.getCard_id()) {
                item.setQuantity(item.getQuantity()+1);
                return "cart";
            }
        }
        
        //create a new item in a cart
        Item i = new Item();
        i.setQuantity(1);
        i.setP(p);
        cart.add(i);
        return "cart";
        
    }
    
    public void update() {
        //reload page
    }
    
    public void remove(Item i) {
        for (Item item : cart) {
            if(item.equals(i)) {
                cart.remove(item);
                break;
            }
        }
    }
    
    public String payment() {
        return "payment";
    }
}
