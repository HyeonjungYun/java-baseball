package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class NumberGenerator {

    private final List<Integer> correctNumbers;

    public NumberGenerator() {
        this.correctNumbers = generateNumbers();
    }

    public List<Integer> getCorrectNumbers () {
        return this.correctNumbers;
    }

    private List<Integer> generateNumbers () {
        List<Integer> generatedNumber = new ArrayList<>();

        while (generatedNumber.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);

            if (!generatedNumber.contains(randomNumber)) {
                generatedNumber.add(randomNumber);
            }
        }
        return generatedNumber;
    }
}
