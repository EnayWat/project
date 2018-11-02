import com.mongodb.*;

public class database {
    static MongoClientURI uri;
    static MongoClient mongo;
    static DB db;
    static DBCollection user;
    static DBCollection order;
    static DBCollection adress;

    public static void connect(){
        try{
            uri = new MongoClientURI("mongodb://admin:admin123456@ds249503.mlab.com:49503/bookshop");
            mongo = new MongoClient(uri);
            db = mongo.getDB(uri.getDatabase());
            user = db.getCollection("User");
            order = db.getCollection("Order");
            adress = db.getCollection("Adress");
        }catch (Exception e){

        }
    }static void  getUser(String id){
        BasicDBObject search = new BasicDBObject();
        search.put("_id",id);
        DBObject value  = user.findOne(search);
        User.name = (String) value.get("name");
        User.gender = (String) value.get("gender");
        User.adress.add((String) value.get("adress"));
    }static  void getAdress(String id){
        BasicDBObject search = new BasicDBObject();
        search.put("_id",id);
        DBObject value  = adress.findOne(search);
        Adress.id= (String) value.get("_id");
        Adress.houseNumber=(String)value.get("HouseNumber");
        Adress.villageNumber=(String)value.get("VillageNumber");
        Adress.subDistrict=(String)value.get("SubDistrict");
        Adress.district=(String)value.get("District");
        Adress.provine=(String)value.get("Provine");
        Adress.zipcode=(String)value.get("Zipcode");
    }
}
