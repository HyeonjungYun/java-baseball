package baseball.domain;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final int BALL_SIZE = 3;

    private List<Integer> checkStrike (List<Integer> generatedNumber, List<Integer> usersNumber) {
        List<Integer> strikeBall = new ArrayList<>(List.of(0,0));

        for (int temp = 0; temp < BALL_SIZE; temp++) {
            if (generatedNumber.get(temp).equals(usersNumber.get(temp))) {
                strikeBall.set(0, strikeBall.get(0) +1);
                checkBall(generatedNumber, usersNumber, strikeBall);
            }
        }
        strikeBall.set(1, strikeBall.get(1) - strikeBall.get(0));

        return strikeBall;
    }

    private void checkBall (List<Integer> generatedNumber, List<Integer> usersNumber, List<Integer> strikeBall) {

        if (generatedNumber.stream().anyMatch(usersNumber::contains)) {
            strikeBall.set(1, strikeBall.get(1) +1);
        }
    }
}
