package baseball;

import baseball.domain.Model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class ModelTest {

    @Test
    void checkBallNumber () {
        Model model = new Model();

        List<Integer> generateNumbers = List.of(1,2,3);
        List<Integer> userNumbers = List.of(1,4,5);

        List<Integer> result = List.of(1,0);

        assertThat(model.getStrikeBall(generateNumbers, userNumbers))
                .isEqualTo(result);
    }
}
