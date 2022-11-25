package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputViewTest {

    @DisplayName("")
    @ValueSource(strings = {"012","a12","12","1234"})
    @ParameterizedTest
    void inputBallNumberTest (String ballNumber) {
        InputView input = new InputView();

        InputStream in = new ByteArrayInputStream(ballNumber.getBytes());
        System.setIn(in);

        assertThatThrownBy(input::inputBallNumber)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("")
    @ValueSource(strings = {"0","a"," "})
    @ParameterizedTest
    void inputRestartNumberTest (String restart) {
        InputView input = new InputView();

        InputStream in = new ByteArrayInputStream(restart.getBytes());
        System.setIn(in);

        assertThatThrownBy(input::inputRestart)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
