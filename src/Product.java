public class Product {
    String id;
    String name;
    int amount;
    double price;

    Product(String id, String name, int amount, double price) {
        this.name = name;
        this.id = id;
        this.amount = amount;
        this.price = price;

    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
}
