package baseball;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static int distinguishStrike(List<Integer> inputNumberList, List<Integer> computerNumberList) {
        int strikeCounting = 0;

        for (int i = 0; i < 3; i++) {
            if (inputNumberList.get(i).equals(computerNumberList.get(i))) {
                strikeCounting += 1;
            }
        }

        return strikeCounting;
    }

    // ���� �ʿ� �ߺ��� ���� ó�� �� ��
    public static int distinguishBall(List<Integer> inputNumberList, List<Integer> computerNumberList, int strikeCounting) {
        int ballCounting = 0;

        for (int i = 0; i < 3; i++) {
            if (computerNumberList.contains(inputNumberList.get(i))) {
                ballCounting += 1;
            }
        }

        return ballCounting - strikeCounting;
    }

    public static void makeComputerNumber(List<Integer> computerNumberList) {

        for (int j = 0; j < 3; j++) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);

            if (!computerNumberList.contains(randomNumber)) {
                computerNumberList.add(randomNumber);
            }
        }
    }

    public static void splitNumber(List<Integer> inputNumberList, String inputNumber) {

        for (int k = 0; k < 3; k++) {
            inputNumberList.add(Character.getNumericValue(inputNumber.charAt(k)));
        }
    }

    public static void printResult(int ballCounting, int strikeCounting) {
        if (strikeCounting == 3) {
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

    public static boolean decideRestart() {
        System.out.println("������ ���� �����Ϸ��� 1, �����Ϸ��� 2�� �Է��ϼ���.");
        String decidedRestartString =  Console.readLine();
        discoverRestartNumberException(decidedRestartString);
        int decidedRestart = Integer.parseInt(decidedRestartString);

        if (decidedRestart == 1) return true;
        else return false;
    }

    public static void proceedGame (List<Integer> computerNumberList, List<Integer> inputNumberList) {
        while (true) {
            String inputString = Console.readLine();
            discoverInputNumberException(inputString);
            splitNumber(inputNumberList, inputString);
            int strikeCounting = distinguishStrike(inputNumberList, computerNumberList);
            int ballCounting = distinguishBall(inputNumberList, computerNumberList, strikeCounting);
            inputNumberList.clear();

            printResult(ballCounting, strikeCounting);

            if (strikeCounting == 3) break;
        }
    }

    public static boolean seekDuplicatedNumber (String inputNumber) {
        HashSet<Character> inputNubmerInSet = new HashSet<>();
        for (int p = 0; p < inputNumber.length(); p++) {
            inputNubmerInSet.add(inputNumber.charAt(p));
        }
        if (inputNubmerInSet.size() < 3) return true;
        else return false;
    }

    public static void discoverInputNumberException(String inputNumber) throws IllegalArgumentException {

        if (inputNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        if (seekDuplicatedNumber(inputNumber)) {
            throw new IllegalArgumentException();
        }

        for (int l = 0; l < inputNumber.length(); l++) {
            if (inputNumber.charAt(l) < 49 || inputNumber.charAt(l) > 57) {
                throw new IllegalArgumentException();
            }else if (inputNumber.charAt(l) == '0') {
                throw new IllegalArgumentException();
            }

        }
    }

    public static void discoverRestartNumberException (String decideRestartString) throws IllegalArgumentException{
        if (!decideRestartString.equals("1") && !decideRestartString.equals("2")) throw new IllegalArgumentException();
    }

    public static void main(String[] args) {

        List<Integer> computerNumberList = new ArrayList<>();
        List<Integer> inputNumberList = new ArrayList<>();

        while (true) {
            System.out.println("���� �߱� ������ �����մϴ�.");
            makeComputerNumber(computerNumberList);
            proceedGame(computerNumberList, inputNumberList);

            if (!decideRestart()) break;

            computerNumberList.clear();
        }
    }
}
