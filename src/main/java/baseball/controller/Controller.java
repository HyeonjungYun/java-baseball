package baseball.controller;

import baseball.domain.Model;
import baseball.domain.NumberGenerator;
import baseball.messages.PrintMessage;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.List;

public class Controller {

    public void readyPlaying () {
        Model model = new Model();
        InputView input = new InputView();
        OutputView output = new OutputView();

        PrintMessage.START.print();
        playBaseball(model, input, output);
    }

    private void playBaseball (Model model, InputView input, OutputView output) {

        while (true) {
            NumberGenerator generatedNumber = new NumberGenerator();
            model.setGenerateNumbers(generatedNumber.getCorrectNumbers());

            while (true) {
                PrintMessage.INPUT.print();
                List<Integer> strikeBall = model.getStrikeBall(input.inputBallNumber());

                output.printResult(strikeBall);

                if (strikeBall.get(0) == 3) break;
            }

            if(restart(input, output)) break;
        }
    }

    private boolean restart (InputView input, OutputView output) {
        output.printRestart();
        int restartNumber = input.inputRestart();

        return restartNumber == 2;
    }
}