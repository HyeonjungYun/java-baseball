package baseball.view;

import baseball.messages.PrintMessage;

import java.util.List;

public class OutputView {

    public void printRestart () {
        PrintMessage.RESTART.print();
    }

    public void printResult (List<Integer> strikeBall) {
        printNothing(strikeBall);
        printBall(strikeBall.get(1));
        printStrike(strikeBall.get(0));
        System.out.println();
    }

    public void printNothing (List<Integer> strikeBall) {
        if (strikeBall.get(0) < 1 && strikeBall.get(1) < 1) {
            System.out.print("낫싱");
        }
    }

    private void printBall (int ball) {
        if (ball > 0) System.out.printf("%d볼 ", ball);

    }

    private void printStrike (int strike) {
        if (strike > 0) System.out.printf("%d스트라이크 ", strike);
    }
}
