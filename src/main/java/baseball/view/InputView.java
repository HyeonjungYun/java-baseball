package baseball.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public List<Integer> inputBallNumber () {
        String ballNumber = Console.readLine();

        return convertBallNumber(ballNumber);
    }

    public int inputRestart () {
        String restartNumber = Console.readLine();
        validateRestart(restartNumber);

        return Integer.parseInt(restartNumber);

    }

    private void validateRestart (String number) {

        if (Integer.parseInt(number) != 1 && Integer.parseInt(number) != 2) {
            throw new IllegalArgumentException();
        }
    }

    private List<Integer> convertBallNumber (String inputNumber) {
        return inputNumber.chars()
                .peek((number) -> isRealNumber(number))
                .map((number) -> number - '0')
                .boxed().collect(Collectors.toList());
    }

    private void isRealNumber (int number) {
        if (number < 49 || number > 57) throw new IllegalArgumentException();
    }
}
