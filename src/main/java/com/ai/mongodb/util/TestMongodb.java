/**
 * 
 */
package com.ai.mongodb.util;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneModel;

/**
 * Class Name		: TestMongodb<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public class TestMongodb {

    
    /**
     * api : http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
     * 
     * liuhb
     * @param args
     * @since
     *
     */
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("192.168.99.126", 27017)),new MongoClientOptions.Builder().build());
        // 连接到数据库
        MongoDatabase database = mongoClient.getDatabase("local");
        
        System.out.println("Connect to database successfully");
        //db.createCollection("hongboCollection");
        
        MongoCollection<Document> collection = database.getCollection("hongboCollection");
        /*
        Document document = new Document("title", "mongodb").append("description", "database").append("likes", 100).append("url", "http://www.w3cschool.cc/mongodb/");
        
        collection.insertOne(document);
        */
        
        /*
        List<Document> arrays = new ArrayList<Document>();
        Document document = null;
        for(int i = 0 ; i < 10 ; i ++) {
            document = new Document("title", "mongodb_" + i).append("description", "database").append("likes", 100).append("url", "http://www.w3cschool.cc/mongodb/");
            arrays.add(document);
        }
        collection.insertMany(arrays);
        */
        /*
        FindIterable<Document> result = collection.find(new Document("title_3","mongodb"));
        for (Document document : result) {
            String value = document.toJson();
            System.out.println(value);
        }
        */
        /*
        FindIterable<Document> allResult = collection.find();
        for (Document document : allResult) {
            String value = document.toJson();
            System.out.println(value);
        }
        */
        
        /*
        FindIterable<Document> find = collection.find(eq("title","mongodb"));
        for (Document document : find) {
            String value = document.toJson();
            System.out.println(value);
        }
        */
        
        Document newDocument = new Document("title_3", "mongodb_hongboliu").append("description", "database").append("likes", 100).append("url", "http://www.w3cschool.cc/mongodb/");
        //Document newDocument = new Document("title_3", "mongodb_hongboliu");
        
        //collection.updateOne(eq("title_3","mongodb"), new Document("$set",newDocument));
        
        //DeleteResult deleteOne = collection.deleteOne(eq("title_3", "mongodb_hongboliu"));
        //System.out.println(deleteOne.getDeletedCount());
        
        
        collection.bulkWrite(Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                new InsertOneModel<>(new Document("_id", 5)),
                new InsertOneModel<>(new Document("_id", 6))));
        
        
        
        System.out.println("Document inserted successfully");
        
        /*
        boolean auth = db.authenticate(myUserName, myPassword);
        System.out.println("Authentication: "+auth);
        */
    }
}
