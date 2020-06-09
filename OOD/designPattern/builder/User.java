package OOD.designPattern.builder;

/*
    @author: Xi Zhang
    @date:   2019-03-13
    @time:   17:30
    Builder pattern 解决了 多field并且保证class是immutable
 */
public class User {
    private String username;    // required
    private String password;    // required
    private String firstName;
    private String lastName;
    private Enum gender;
    private String phone;
    private String address;     // better use a Address class here
    private String email;

    private User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;

    }
    @Override
    public String toString() {
        return username + ", " + password + ", " + firstName + ", " + lastName;
    }

    public static class UserBuilder{
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private Enum gender;
        private String phone;
        private String address;
        private String email;

        public UserBuilder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static void main(String[] args) {
        User user1 = new User.UserBuilder("jn2019", "12345").setFirstName("John").setLastName("Smith").build();
        System.out.println(user1);
    }

}
