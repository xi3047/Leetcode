package round4.bdfs;

import java.util.HashSet;
import java.util.Set;

public class L489_RobotRoomCleaner {
    interface Robot {
        public boolean move();

        public void turnLeft();
        public void turnRight();
        public void clean();
    }
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0 ,0);
    }

    private void dfs(Robot robot, int x, int y, int facingDirection) {
        String pos = x + "," + y;
        if (visited.contains(pos)) return;
        visited.add(pos);
        robot.clean();

        for (int i = 0; i < 4; i++) {
            int newFacingDirection = (facingDirection + i) % 4;
            int newX = x + dirs[newFacingDirection][0];
            int newY = y + dirs[newFacingDirection][1];

            if (robot.move()) {
                dfs(robot, newX, newY, newFacingDirection);
                goBack(robot);
            }

            robot.turnRight();
        }
    }

    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

}
