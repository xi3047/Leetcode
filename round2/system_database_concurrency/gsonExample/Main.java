package round2.system_database_concurrency.gsonExample;

import com.google.gson.Gson;
import round1_misc.OOD.designPattern.builder.User;

/**
 * @author Xi Zhang
 * @date 2/2/21 10:54 下午
 * @topic round2.system_database_concurrency.gsonExample
 * @link
 * @description
 */
public class Main {
    public static void main(String[] args) {
//        serializeUserSimple();
        deserializeUserSimple();
    }

    private static void serializeUserSimple() {
        UserSimple user = new UserSimple(
                "Peter",
                "peter@gmail.com",
                18,
                false
        );
        Gson gson = new Gson();
        String json = gson.toJson(user);

    }
    private static void deserializeUserSimple() {
        String userJson = "{'name':'Peter','email':'peter@gmail.com','age':28,'isDeveloper':false}";
        Gson gson = new Gson();
        UserSimple userSimple = gson.fromJson(userJson, UserSimple.class);
    }
}
