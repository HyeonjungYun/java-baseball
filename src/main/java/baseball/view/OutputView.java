package baseball.view;

import baseball.messages.PrintMessage;

import java.util.List;

public class OutputView {

    public void printRestart () {
        PrintMessage.RESTART.print();
    }

    public void printResult (List<Integer> strikeBall) {

        if (strikeBall.get(0) < 1 && strikeBall.get(1) < 1) {
            System.out.println("낫싱");
        }
        if (strikeBall.get(0) < 1 && strikeBall.get(1) > 0) {
            System.out.printf("%d볼\n", strikeBall.get(1));
        }
        if (strikeBall.get(0) > 0 && strikeBall.get(1) < 1) {
            System.out.printf("%d스트라이크\n", strikeBall.get(0));

            if(strikeBall.get(0) == 3) PrintMessage.CLEAR.print();
        }
        if (strikeBall.get(0) > 0 && strikeBall.get(1) > 0) {
            System.out.printf("%d볼 %d스트라이크\n", strikeBall.get(1), strikeBall.get(0));
        }
    }
}
