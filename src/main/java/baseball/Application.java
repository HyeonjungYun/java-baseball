package baseball;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static int THE_NUMBER_OF_BALLS = 3;

    // main()�޼��忡�� ���� �޾� ó���ϴ� Ŭ����
    public static class Model {

        private List<Integer> computerNumberList = new ArrayList<>();
        private List<Integer> inputNumberList = new ArrayList<>();
        private int strikeCounting;
        private int ballCounting;

        public void proceedGame (String inputString) {
            inputNumberList.clear();
            splitNumber(inputNumberList, inputString);
            distinguishStrike();
            distinguishBall();

        }

        // ��Ʈ����ũ�� �����ϴ� �޼���
        public void distinguishStrike() {
            int methodInStrikeCounting = 0;

            for (int i = 0; i < THE_NUMBER_OF_BALLS; i++) {
                if (inputNumberList.get(i).equals(computerNumberList.get(i))) {
                    methodInStrikeCounting += 1;
                }
            }

            strikeCounting = methodInStrikeCounting;
        }

        // ���� �����ϴ� �޼���
        public void distinguishBall() {
            int methodInBallCounting = 0;

            for (int number: inputNumberList) {
                if (computerNumberList.contains(number)) {
                    methodInBallCounting += 1;
                }
            }

            ballCounting = methodInBallCounting - strikeCounting;
        }

        // ��ǻ���� 3�ڸ� ���ڸ� �����ϴ� �޼���
        public void makeComputerNumber() {

            while (computerNumberList.size() < THE_NUMBER_OF_BALLS) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);

                if (!computerNumberList.contains(randomNumber)) {
                    computerNumberList.add(randomNumber);
                }
            }
        }

        // ����ڰ� �Է��� 3�ڸ� ���ڸ� ����Ʈ�� �и��ϴ� �޼���
        public void splitNumber(List<Integer> inputNumberList, String inputNumber) {

            for (char number: inputNumber.toCharArray()) {
                inputNumberList.add(Character.getNumericValue(number));
            }
        }

        // �������� �� �ٽ� �������� �����ϴ� �޼���
        public boolean decideRestart(String decidedRestartString) {
            int decidedRestart = Integer.parseInt(decidedRestartString);

            if (decidedRestart == 1) return true;
            else return false;
        }

        // 3�ڸ� ���ڸ� �Է��� �� ���� �߻���Ű�� �޼���
        public void discoverInputNumberException(String inputNumber) throws IllegalArgumentException {
            checkNumberItem(inputNumber);
            checkNumberRange(inputNumber);
            checkDuplicatedNumber(inputNumber);
        }

        // �Էµ� 3�ڸ� ���� �߿��� �ߺ��� ���ڰ� �ִ��� Ȯ���ϴ� �޼���
        public void checkDuplicatedNumber (String inputNumber) throws IllegalArgumentException{
            HashSet<Character> inputNumberInSet = new HashSet<>();

            for (int p = 0; p <THE_NUMBER_OF_BALLS; p++) {
                inputNumberInSet.add(inputNumber.charAt(p));
            }

            if (inputNumberInSet.size() < THE_NUMBER_OF_BALLS) throw new IllegalArgumentException();
        }

        // �־��� ���ڰ� 3�ڸ��� �ƴ� ��� ���� �߻�
        public void checkNumberItem (String inputNumber) throws IllegalArgumentException{
            if (inputNumber.length() != THE_NUMBER_OF_BALLS) {
                throw new IllegalArgumentException();
            }
        }

        // �Էµ� 3�ڸ��� ���ڰ� 1~9�� ���� ���� �����ϴ��� Ȯ��
        public void checkNumberRange (String inputNumber) throws  IllegalArgumentException{
            IntStream inputNumberStream = inputNumber.chars();
            inputNumberStream.forEach(number -> {
                        if (number < 49 || number > 57) throw new IllegalArgumentException();

            });
        }

        // ����� ���� �ÿ� �ԷµǴ� ������ ���ܸ� �߻���Ű�� �޼���
        public void discoverRestartNumberException (String restartNumber) throws IllegalArgumentException{
            if (!restartNumber.equals("1") && !restartNumber.equals("2")) throw new IllegalArgumentException();
        }

        public  String inputBallAndRestartNumber () {
            String inputNumber = Console.readLine();
            if (strikeCounting == THE_NUMBER_OF_BALLS) discoverRestartNumberException(inputNumber);
            else discoverInputNumberException(inputNumber);

            return inputNumber;
        }

    }

    // model���� ó���� ���� ����ϴ� Ŭ����
    public static class View {
        public void printResult(int ballCounting, int strikeCounting) {
            if (strikeCounting == THE_NUMBER_OF_BALLS) {
                System.out.println("3��Ʈ����ũ");
                System.out.println("3���� ���ڸ� ��� �����̽��ϴ�! ���� ����");
            } else if (strikeCounting > 0 && ballCounting == 0) {
                System.out.printf("%d��Ʈ����ũ\r\n", strikeCounting);
            } else if (strikeCounting == 0 && ballCounting > 0) {
                System.out.printf("%d��\r\n", ballCounting);
            } else if (strikeCounting > 0 && ballCounting > 0) {
                System.out.printf("%d�� %d��Ʈ����ũ\r\n", ballCounting, strikeCounting);
            } else {
                System.out.println("����");
            }
        }
    }

    public static void main(String[] args) {

        View view = new View();

        while (true) {
            Model model = new Model();

            System.out.println("���� �߱� ������ �����մϴ�.");
            model.makeComputerNumber();

            while (model.strikeCounting < THE_NUMBER_OF_BALLS) {
                model.proceedGame(model.inputBallAndRestartNumber());
                view.printResult(model.ballCounting, model.strikeCounting);
            }

            model.strikeCounting = 0;
            System.out.println("������ ���� �����Ϸ��� 1, �����Ϸ��� 2�� �Է��ϼ���.");

            if (!model.decideRestart(model.inputBallAndRestartNumber())) break;

            model.computerNumberList.clear();
        }
    }
}
