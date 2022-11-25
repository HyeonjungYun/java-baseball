package baseball.domain;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final int BALL_SIZE = 3;
    private List<Integer> generatedNumbers;

    public void setGenerateNumbers (List<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    public List<Integer> getStrikeBall (List<Integer> usersNumber) {
        List<Integer> strikeBall = new ArrayList<>(List.of(0,0));

        for (int temp = 0; temp < BALL_SIZE; temp++) {
            int check = checkStrikeBall(usersNumber, temp);

            if(check == 1) strikeBall.set(0, strikeBall.get(0) +1);
            if(check == 2) strikeBall.set(1, strikeBall.get(1) +1);
        }

        return strikeBall;
    }

    private int checkStrikeBall (List<Integer> usersNumber, int temp) {

        if(usersNumber.contains(generatedNumbers.get(temp))) {
            if(generatedNumbers.get(temp).equals(usersNumber.get(temp))) return 1;
            return 2;
        }
        return 0;
    }
}
