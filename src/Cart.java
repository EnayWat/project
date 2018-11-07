import java.util.ArrayList;

public class Cart {
    static ArrayList<Product> products = new ArrayList<Product>();
    static int totalAmount = 0;
    static double totalPrice = 0;

    public static void addToCart(String id, String name, int amount, double price) {
        products.add(new Product(id, name, amount, price));
        totalAmount += amount;
        totalPrice += (price * amount);
    }

    public static void resetCart() {
        products.clear();
        totalAmount = 0;
        totalPrice = 0;
    }
}
