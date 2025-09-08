package util;

import com.github.javafaker.Faker;

public final class TestContext {
    private TestContext(){}

    public static String FIRSTNAME;
    public static String LASTNAME;
    public static String PAYEENAME;
    public static String ADDRESS;
    public static String CITY;
    public static String STATE;
    public static String ZIP;
    public static String NUMBER;
    public static String SSN;
    public static String ACCOUNTNUMBER;
    public static String AMOUNT;
    public static String FROMACCOUNT;
    public static String USERNAME;
    public static String PASSWORD;
    public static String LOANAMOUNT;
    public static String DOWNPAYMENT;


    public static void generateRandomUser() {
        Faker faker = new Faker();

        FIRSTNAME = faker.name().firstName();
        LASTNAME = faker.name().lastName();
        PAYEENAME = faker.name().name();
        ADDRESS = faker.address().streetAddress();
        CITY = faker.address().city();
        STATE = faker.address().state();
        ZIP = faker.address().zipCode().replaceAll("-", "");
        NUMBER = faker.number().digits(11);
        SSN = faker.idNumber().ssnValid().replaceAll("-", "");
        ACCOUNTNUMBER = faker.number().digits(5);
        AMOUNT = faker.number().digits(4);
        FROMACCOUNT = faker.number().digits(4);
        USERNAME = faker.name().username() + faker.number().digits(3);
        PASSWORD = faker.internet().password(8, 12);
        LOANAMOUNT = faker.number().digits(4);
        DOWNPAYMENT = faker.number().digits(3);

    }
}
