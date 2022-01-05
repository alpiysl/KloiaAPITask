import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserGenerator {

    public static Map<String, Object> user;

    public static Map<String, Object> generateUser() {
        Faker faker = new Faker();
        user = new HashMap<>();
        user.put("id", faker.number().numberBetween(1,100));
        user.put("username", faker.name().username());
        user.put("firstName", faker.name().firstName());
        user.put("lastName", faker.name().lastName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("phone", faker.phoneNumber().cellPhone());
        user.put("userStatus", faker.number().randomDigitNotZero());
        return user;
    }

    public static Map<String, Object> generateUser(String name, String lastName) {
        Faker faker = new Faker();
        user = new HashMap<>();
        user.put("id", faker.number().numberBetween(1,100));
        user.put("username", faker.name().username());
        user.put("firstName", name);
        user.put("lastName", lastName);
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("phone", faker.phoneNumber().cellPhone());
        user.put("userStatus", faker.number().randomDigitNotZero());
        return user;
    }

    public static String idNumber(){
        return String.valueOf(user.get("id"));
    }
}
