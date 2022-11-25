package baseball;

import baseball.domain.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class ModelTest {

    @DisplayName("스트라이크, 볼의 갯수가 정확하게 나오는 지 확인")
    @Test
    void checkBallNumber () {
        Model model = new Model();

        List<Integer> generateNumbers = List.of(1,2,3);
        List<Integer> userNumbers = List.of(1,4,5);
        model.setGenerateNumbers(generateNumbers);

        List<Integer> result = List.of(1,0);

        assertThat(model.getStrikeBall(userNumbers))
                .isEqualTo(result);
    }
}
