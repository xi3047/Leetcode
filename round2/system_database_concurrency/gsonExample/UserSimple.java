package round2.system_database_concurrency.gsonExample;

/**
 * @author Xi Zhang
 * @date 2/2/21 10:50 下午
 * @topic round2.system_database_concurrency.gsonExample
 * @link
 * @description
 */
public class UserSimple {
    private String name;
    private String email;
    private int age;
    private boolean isDeveloper;

    public UserSimple(String name, String email, int age, boolean isDeveloper) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
    }

}
