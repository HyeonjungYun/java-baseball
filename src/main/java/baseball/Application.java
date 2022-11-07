package baseball;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static int THE_NUMBER_OF_BALLS = 3;

    // main()�޼��忡�� ���� �޾� ó���ϴ� Ŭ����
    public static class model {

        private List<Integer> computerNumberList = new ArrayList<>();
        private List<Integer> inputNumberList = new ArrayList<>();
        private int strikeCounting;
        private int ballCounting;
        private int THE_NUMBER_OF_BALLS = 3;

        public void proceedGame (String inputString) {
            discoverInputNumberException(inputString);
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

            for (int i = 0; i < THE_NUMBER_OF_BALLS; i++) {
                if (computerNumberList.contains(inputNumberList.get(i))) {
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

            for (int k = 0; k < 3; k++) {
                inputNumberList.add(Character.getNumericValue(inputNumber.charAt(k)));
            }
        }

        // �������� �� �ٽ� �������� �����ϴ� �޼���
        public boolean decideRestart(String decidedRestartString) {
            discoverRestartNumberException(decidedRestartString);
            int decidedRestart = Integer.parseInt(decidedRestartString);

            if (decidedRestart == 1) return true;
            else return false;
        }

        // �Էµ� 3�ڸ� ���� �߿��� �ߺ��� ���ڰ� �ִ��� Ȯ���ϴ� �޼���
        public boolean seekDuplicatedNumber (String inputNumber) {
            HashSet<Character> inputNubmerInSet = new HashSet<>();

            for (int p = 0; p <THE_NUMBER_OF_BALLS; p++) {
                inputNubmerInSet.add(inputNumber.charAt(p));
            }

            return inputNubmerInSet.size() < THE_NUMBER_OF_BALLS;
        }

        // 3�ڸ� ���ڸ� �Է��� �� ���� �߻���Ű�� �޼���
        public void discoverInputNumberException(String inputNumber) throws IllegalArgumentException {

            // �־��� ���ڰ� 3�ڸ��� �ƴ� ��� ���� �߻�
            if (inputNumber.length() != THE_NUMBER_OF_BALLS) {
                throw new IllegalArgumentException();
            }

            // �־��� ���ڰ� ���� �ٸ��� ���� ��� ���� �߻�
            if (seekDuplicatedNumber(inputNumber)) {
                throw new IllegalArgumentException();
            }

            // �� ���ڰ� 1~9�� �ƴ� ��� ���� �߻�
            for (int l = 0; l < inputNumber.length(); l++) {
                if (inputNumber.charAt(l) < 49 || inputNumber.charAt(l) > 57) {
                    throw new IllegalArgumentException();
                }else if (inputNumber.charAt(l) == '0') {
                    throw new IllegalArgumentException();
                }

            }
        }

        // ����� ���� �ÿ� �ԷµǴ� ������ ���ܸ� �߻���Ű�� �޼���
        public void discoverRestartNumberException (String decideRestartString) throws IllegalArgumentException{
            if (!decideRestartString.equals("1") && !decideRestartString.equals("2")) throw new IllegalArgumentException();
        }

    }

    // model���� ó���� ���� ����ϴ� Ŭ����
    public static class view {
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

        view baseballView = new view();

        while (true) {
            model baseballModel = new model();

            System.out.println("���� �߱� ������ �����մϴ�.");
            baseballModel.makeComputerNumber();

            while (baseballModel.strikeCounting < THE_NUMBER_OF_BALLS) {
                String inputString = Console.readLine();
                baseballModel.proceedGame(inputString);
                baseballView.printResult(baseballModel.ballCounting, baseballModel.strikeCounting);
                baseballModel.inputNumberList.clear();
            }

            baseballModel.strikeCounting = 0;
            System.out.println("������ ���� �����Ϸ��� 1, �����Ϸ��� 2�� �Է��ϼ���.");
            String decidedRestartString =  Console.readLine();

            if (!baseballModel.decideRestart(decidedRestartString)) break;

            baseballModel.computerNumberList.clear();
        }
    }
}
