/**
 * 
 */
package com.ai.mongodb.util;

import static com.mongodb.client.model.Filters.eq;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.mongodb.annotation.CollectionField;
import com.ai.mongodb.annotation.CollectionName;
import com.ai.mongodb.model.ProdDesc;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * api : http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/
 * Class Name		: MongoClientUtils<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public class MongoClientUtils {
    
    private static final Logger log = LoggerFactory.getLogger(MongoClientUtils.class);
    //eg:mongodb://localhost:27017,localhost:27018,localhost:27019
    private String connectionURI;
    
    private String databaseName;
    
    private MongoClient mongoClient;
    
    private MongoDatabase database;
    
    static {
        log.error("mongodb 初始化开始。。。。。");
    }
    
    public MongoClientUtils(String connectionURI,String databaseName) {
        mongoClient = new MongoClient(new MongoClientURI(connectionURI));
        database = mongoClient.getDatabase(databaseName);
    }

    public MongoClientUtils(String connectionURI,MongoClientOptions.Builder builder) {
        mongoClient = new MongoClient(new MongoClientURI(connectionURI,builder));
    }
    
    public void save(Object obj) {
        String collectionName = "";
        Class<? extends Object> objClass = obj.getClass();
        CollectionName annotation = objClass.getAnnotation(CollectionName.class);
        if (annotation != null) {
            String value = annotation.value();
            collectionName = StringUtils.isNotBlank(value) ? value : objClass.getSimpleName();
            MongoIterable<String> listCollectionNames = database.listCollectionNames();
            boolean existColl = false;
            for (String existName : listCollectionNames) {
                if (StringUtils.equalsIgnoreCase(existName, collectionName)) {
                    existColl = true;
                    break;
                }
            }
            if (!existColl) {
                database.createCollection(collectionName);
            }
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Field[] declaredFields = objClass.getDeclaredFields();
            Document document = new Document();
            for (Field field : declaredFields) {
                if (StringUtils.equalsIgnoreCase("serialVersionUID", field.getName())) {
                    continue;
                }
                Object fieldVal = null;
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);
                    fieldVal = field.get(obj);
                    CollectionField annotationFild = field.getAnnotation(CollectionField.class);
                    if (annotationFild != null) {
                        String definedName = annotationFild.value();
                        fieldName = StringUtils.isNotBlank(definedName) ? definedName : fieldName;
                    }
                    document.append(fieldName, fieldVal);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("mongodbclientutil save is error : ", e);
                }
            }
            collection.insertOne(document);
        }
    }
    
    public long update(Object obj){
        long result = 0;
        String collectionName = "";
        Class<? extends Object> objClass = obj.getClass();
        CollectionName annotation = objClass.getAnnotation(CollectionName.class);
        if (annotation != null) {
            String value = annotation.value();
            collectionName = StringUtils.isNotBlank(value) ? value : objClass.getSimpleName();
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Field[] declaredFields = objClass.getDeclaredFields();
            Document document = new Document();
            String primaryKey = StringUtils.EMPTY;
            Object primaryVal = null;
            for (Field field : declaredFields) {
                if (StringUtils.equalsIgnoreCase("serialVersionUID", field.getName())) {
                    continue;
                }
                Object fieldVal = null;
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);
                    fieldVal = field.get(obj);
                    CollectionField annotationFild = field.getAnnotation(CollectionField.class);
                    if (annotationFild != null) {
                        String definedName = annotationFild.value();
                        fieldName = StringUtils.isNotBlank(definedName) ? definedName : fieldName;
                        boolean isPrimaryKey = annotationFild.primaryKey();
                        if (isPrimaryKey) {
                            primaryKey = fieldName;
                            primaryVal = fieldVal;
                        }
                    }
                    document.append(fieldName, fieldVal);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("mongodbclientutil save is error : ", e);
                }
            }
            UpdateResult updateOne = collection.updateOne(eq(primaryKey,primaryVal), new Document("$set",document));
            result = updateOne.getModifiedCount();
        }
        return result;
    }
    
    public long delete(Object obj) {
        long result = 0;
        String collectionName = "";
        Class<? extends Object> objClass = obj.getClass();
        CollectionName annotation = objClass.getAnnotation(CollectionName.class);
        if (annotation != null) {
            String value = annotation.value();
            collectionName = StringUtils.isNotBlank(value) ? value : objClass.getSimpleName();
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Field[] declaredFields = objClass.getDeclaredFields();
            String primaryKey = StringUtils.EMPTY;
            Object primaryVal = null;
            for (Field field : declaredFields) {
                if (StringUtils.equalsIgnoreCase("serialVersionUID", field.getName())) {
                    continue;
                }
                Object fieldVal = null;
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);
                    fieldVal = field.get(obj);
                    CollectionField annotationFild = field.getAnnotation(CollectionField.class);
                    if (annotationFild != null) {
                        String definedName = annotationFild.value();
                        fieldName = StringUtils.isNotBlank(definedName) ? definedName : fieldName;
                        boolean isPrimaryKey = annotationFild.primaryKey();
                        if (isPrimaryKey) {
                            primaryKey = fieldName;
                            primaryVal = fieldVal;
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("mongodbclientutil save is error : ", e);
                }
            }
            DeleteResult deleteOne = collection.deleteOne(eq(primaryKey, primaryVal));
            result = deleteOne.getDeletedCount();
        }
        return result;
    }
    
    public Object findById(Object obj) {
        Object result = null;
        String collectionName = "";
        Class<? extends Object> objClass = obj.getClass();
        CollectionName annotation = objClass.getAnnotation(CollectionName.class);
        if (annotation != null) {
            String value = annotation.value();
            collectionName = StringUtils.isNotBlank(value) ? value : objClass.getSimpleName();
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Field[] declaredFields = objClass.getDeclaredFields();
            String primaryKey = StringUtils.EMPTY;
            Object primaryVal = null;
            for (Field field : declaredFields) {
                if (StringUtils.equalsIgnoreCase("serialVersionUID", field.getName())) {
                    continue;
                }
                Object fieldVal = null;
                String fieldName = field.getName();
                try {
                    field.setAccessible(true);
                    fieldVal = field.get(obj);
                    CollectionField annotationFild = field.getAnnotation(CollectionField.class);
                    if (annotationFild != null) {
                        String definedName = annotationFild.value();
                        fieldName = StringUtils.isNotBlank(definedName) ? definedName : fieldName;
                        boolean isPrimaryKey = annotationFild.primaryKey();
                        if (isPrimaryKey) {
                            primaryKey = fieldName;
                            primaryVal = fieldVal;
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("mongodbclientutil save is error : ", e);
                }
            }
            FindIterable<Document> find = collection.find(eq(primaryKey,primaryVal));
            ProdDesc prodDesc = new ProdDesc();
            for (Document document : find) {
                String string = document.getString("content");
                prodDesc.setContent(string);
            }
            result = prodDesc;
        }
        return result;
    }
    
    /**
     * 获取 ShardedJedis
     * 
     * @return
     */
    public MongoDatabase getDataBase() {
        return database;
    }
    
    public MongoClientUtils() {}    
    
    public void init() {
        mongoClient = new MongoClient(new MongoClientURI(connectionURI));
        database = mongoClient.getDatabase(databaseName);
    }
    
    public String getConnectionURI() {
        return connectionURI;
    }

    public void setConnectionURI(String connectionURI) {
        this.connectionURI = connectionURI;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    
    public static void main(String[] args) throws Exception {
        ProdDesc prodDesc = new ProdDesc();
        prodDesc.setId(1000L);
        Class<? extends Object> cla = prodDesc.getClass();
        CollectionName annotation = cla.getAnnotation(CollectionName.class);
        System.out.println(annotation);
        if (annotation != null) {
            String value = annotation.value();
            System.out.println(value);
            if (StringUtils.isEmpty(value)) {
                String claName = cla.getSimpleName();
                System.out.println(claName);
            }
        }
        
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field field : declaredFields) {
            if (StringUtils.equalsIgnoreCase("serialVersionUID", field.getName())) {
                continue;
            }
            System.out.println(field.getName());
            field.setAccessible(true);
            Object object = field.get(prodDesc);
            System.out.println(object);
            CollectionField annotationFild = field.getAnnotation(CollectionField.class);
            if (annotationFild != null) {
                String fieldName = annotationFild.value();
                if (StringUtils.isNotBlank(fieldName)) {
                    System.out.println(fieldName);
                }
            }
        }
    }
}
