package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import model.BankAccount;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import service.MongoService;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class UserDao {

    private static final MongoDatabase database = MongoService.connect("aLevel");


    public void createUser(User user){
            Document document = MongoService.mapperFrom(user);
            MongoCollection<Document> users = database.getCollection("users");
            List<Document> documents = Collections.singletonList(document);
            users.insertMany(documents);
        }

    public static void showAll() {
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find();
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void findByFullName(String firstName, String lastName) {
        Document filter = new Document();
        filter.append("firstName", firstName);
        filter.append("lastName", lastName);
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void findById(String userId) {
        ObjectId id= new ObjectId(userId);
        Document filter = new Document();

        filter.append("_id", id);
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void findByAgeRange(int age) {
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(lte("age", age));
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void findByCity(String city) {
        Document filter = new Document();
        filter.append("city", city);
        MongoCollection<Document> users = database.getCollection("users");
        FindIterable<Document> documents = users.find(filter);
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void findByAccountsCount() {
        MongoCollection<Document> users = database.getCollection("users");

        FindIterable<Document> documents = users.find(where("this.accounts.length > 1"));
        for (Document document : documents) {
            System.out.println(document);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void updateUser(String userId, String firstName, String lastName, int age, String work, String city, List<BankAccount> userAccounts) {
        final Document filter = new Document();
        ObjectId id= new ObjectId(userId);
        filter.append("_id", id);
        final List<Document> accounts = new ArrayList<>();
        for (BankAccount a : userAccounts) {
            accounts.add(new Document("bankAccount",a.getNumber()).append("cardId",a.getBankService()));
        }
        final Document newData = new Document();
        newData.append("firstName", firstName);
        newData.append("lastName", lastName);
        newData.append("age", age);
        newData.append("work", work);
        newData.append("city", city);
        newData.append("accounts", userAccounts);

        final Document updateObject  = new Document();
        updateObject.append("$set", newData);

        MongoCollection<Document> users = database.getCollection("users");
        users.updateOne(filter, updateObject);
    }

    public static void deleteUser(String userId) {
        final Document filter = new Document();
        ObjectId id= new ObjectId(userId);
        filter.append("_id", id);
        MongoCollection<Document> users = database.getCollection("users");
        users.deleteOne(filter);
    }

    }


