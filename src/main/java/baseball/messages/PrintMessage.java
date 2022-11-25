package baseball.messages;

public enum PrintMessage {

    START("숫자 야구 게임을 시작합니다."),
    INPUT("숫자를 입력해주세요 : "),
    RESTART("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
    CLEAR("3개의 숫자를 모두 맞히셨습니다! 게임 종료");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public void print() {
        System.out.println(this.message);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
