import com.github.javafaker.Faker;

public class EmailGenerator {

    public static String generateEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}
