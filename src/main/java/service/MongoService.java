package service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import model.BankAccount;
import model.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoService {

    private static MongoClient mongoClient;

    public static MongoDatabase connect(String databaseName) {
        return getMongoClient(null).getDatabase(databaseName);
    }

    private static MongoDatabase connect(String databaseName, MongoCredential credential) {
            return getMongoClient(credential).getDatabase(databaseName);
    }

    private static MongoClient getMongoClient(MongoCredential credential) {
        if (mongoClient == null) {
            final MongoClientOptions.Builder options = MongoClientOptions.builder();
            mongoClient = credential == null ? new MongoClient("localhost", 27017) :
                    new MongoClient(new ServerAddress("localhost", 27017), credential, options.build());
        }

        return mongoClient;
    }

    public static Document mapperFrom(User user) {
        final List<Document> userAccounts = new ArrayList<>();
        for (BankAccount a : user.getBankAccounts()) {
            userAccounts.add(new Document("bankAccount",a.getNumber()).append("bankService",a.getBankService()));
        }
        final Document document = new Document();
        document.append("firstName", user.getFirstName());
        document.append("lastName", user.getLastName());
        document.append("age", user.getAge());
        document.append("work", user.getWork());
        document.append("city", user.getCity());
        document.append("accounts", userAccounts);
        return document;
    }

    private static User mapperTo(Document document) {
        final User user = new User(
                document.get("firstName", String.class),
                document.get("lastName", String.class),
                document.get("age", int.class),
                document.get("work", String.class),
                document.get("city", String.class),
                document.getList("accouns", BankAccount.class)
        );
        user.setId(document.get("_id", String.class));
        return user;
    }
}
