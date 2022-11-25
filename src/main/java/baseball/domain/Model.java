package baseball.domain;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final int BALL_SIZE = 3;

    public List<Integer> getStrikeBall (List<Integer> generatedNumber, List<Integer> usersNumber) {
        List<Integer> strikeBall = new ArrayList<>(List.of(0,0));

        for (int temp = 0; temp < BALL_SIZE; temp++) {
            int check = checkStrikeBall(generatedNumber, usersNumber, temp);

            if(check == 1) strikeBall.set(0, strikeBall.get(0) +1);
            if(check == 2) strikeBall.set(1, strikeBall.get(1) +1);
        }

        return strikeBall;
    }

    private int checkStrikeBall (List<Integer> generatedNumber, List<Integer> usersNumber, int temp) {

        if(usersNumber.contains(generatedNumber.get(temp))) {
            if(generatedNumber.get(temp).equals(usersNumber.get(temp))) return 1;

            return 2;
        }
        return 0;
    }
}
