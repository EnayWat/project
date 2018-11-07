import com.mongodb.*;

import java.util.List;

public class Database {
    static MongoClientURI uri;
    static MongoClient mongo;
    static DB db;
    static DBCollection user;
    static DBCollection order;
    static DBCollection adress;
    static DBCollection product;

    public static void connect() {
        try {
            uri = new MongoClientURI("mongodb://admin:admin123456@ds249503.mlab.com:49503/bookshop");
            mongo = new MongoClient(uri);
            db = mongo.getDB(uri.getDatabase());
            user = db.getCollection("User");
            order = db.getCollection("Order");
            adress = db.getCollection("Adress");
            product = db.getCollection("Product");
        } catch (Exception e) {

        }
    }

    static void getUser(String id) {
        BasicDBObject search = new BasicDBObject();
        search.put("_id", id);
        DBObject value = user.findOne(search);
        User.name = (String) value.get("name");
        User.gender = (String) value.get("gender");
        User.adress.add((String) value.get("adress"));
    }

    static void getAdress(String id) {
        BasicDBObject search = new BasicDBObject();
        search.put("_id", id);
        DBObject value = adress.findOne(search);
        Adress.id = (String) value.get("_id");
        Adress.houseNumber = (String) value.get("HouseNumber");
        Adress.villageNumber = (String) value.get("VillageNumber");
        Adress.subDistrict = (String) value.get("SubDistrict");
        Adress.district = (String) value.get("District");
        Adress.provine = (String) value.get("Provine");
        Adress.zipcode = (String) value.get("Zipcode");
    }

    static void getProduct(String id, int amount) {
        BasicDBObject search = new BasicDBObject();
        search.put("_id", id);
        DBObject value = product.findOne(search);
        String bookName = (String) value.get("bookname");
        int stringPrice = (int) value.get("price");
        Cart.addToCart(id, bookName, amount, stringPrice);
    }

    static void insertOrder() {
        BasicDBObject orderObj = new BasicDBObject();
        List<Object> productsDBList = new BasicDBList();

        for (Product product : Cart.products) {
            DBObject studentDBObject = new BasicDBObject();
            studentDBObject.put("IDProduct", product.id);
            studentDBObject.put("Amount", product.amount);
            productsDBList.add(studentDBObject);
        }
        orderObj.put("User", User.ID);
        orderObj.put("TotalPrice", Cart.totalPrice);
        orderObj.put("TotalAmount", Cart.totalAmount);
        orderObj.put("TypeOfDelivery", "Kerry");
        orderObj.put("TypeOfPay", "Kungthai");
        orderObj.put("Product", productsDBList);
        order.insert(orderObj);

    }
}