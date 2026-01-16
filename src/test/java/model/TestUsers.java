package model;

public class TestUsers {

    public static final User BOB = new User("bob@example.com", "10203040");
    public static final User ALICE_LOCKED = new User("alice@example.com", "10203040");
    public static final User INVALID_USER = new User("bob@example.com", "1020304050");
    public static final User FAKE_USER = new User("marian@fake.com", "1234456");
    public static final User EMPTY_USER = new User("", "1234456");
    public static final User EMPTY_PASSWORD = new User("bob@example.com", "");

    private TestUsers() {
    }
}
