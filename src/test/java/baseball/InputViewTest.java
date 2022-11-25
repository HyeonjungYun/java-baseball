package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import baseball.view.InputView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputViewTest {

    @DisplayName("사용자의 3자리 숫자 입력값에 예외 사항이 있는지 확인")
    @ValueSource(strings = {"012","a12","12","1234"})
    @ParameterizedTest
    void inputBallNumberTest (String ballNumber) {
        InputView input = new InputView();

        InputStream in = new ByteArrayInputStream(ballNumber.getBytes());
        System.setIn(in);

        assertThatThrownBy(input::inputBallNumber)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자의 재시작 커맨드 입력값에 예외 사항이 있는지 확인")
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
