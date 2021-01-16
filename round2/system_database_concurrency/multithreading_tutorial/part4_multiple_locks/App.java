package round2.system_database_concurrency.multithreading_tutorial.part4_multiple_locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Xi Zhang
 * @date 1/12/21 10:22 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part4_multiple_locks
 * @link
 * @description
 */
public class App {



    public static void main(String[] args) {
        new Worker().main();

    }
}
