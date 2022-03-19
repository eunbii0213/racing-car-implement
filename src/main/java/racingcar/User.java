package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class User {
    private static final String USER_CAR_INPUT_GUIDE = "경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)";
    private static final String USER_GAME_NUMBER_INPUT_GUIDE = "시도할 회수는 몇회인가요?";
    private static final String ERROR_MESSAGE = "[ERROR] 시도 횟수는 숫자여야 한다.";
    private static final int DONT_START_GAME = 0;
    private static final String COMMA = ",";
    private List<String> carName;
    private int gameNumber;
    private String userInput = "";
    private String userNumberInput = "";

    public User() {
        carName = new ArrayList<>();
    }

    public List<String> getCarName() {
        return carName;
    }

    public void userCarNameInput(Checker checker, User user) {
        System.out.println(USER_CAR_INPUT_GUIDE);
        String beforeCheckInput = Console.readLine();

        checker.userInputChecker(beforeCheckInput, user);

        userInput = beforeCheckInput;
        carNameListAdd();
    }

    public void carNameListAdd() {
        StringTokenizer st = new StringTokenizer(userInput, COMMA);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            carName.add(str);
        }
    }

    public void userGameNumberInput(Checker checker) {
        System.out.println(USER_GAME_NUMBER_INPUT_GUIDE);

        userNumberInput = Console.readLine();
        try {
            gameNumber = checker.userInputNumberChecker(userNumberInput);
            System.out.println("gameNumber : " + gameNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE);
            userGameNumberInput(checker);
        }
    }

    public boolean isDontStartGame() {
        return gameNumber == DONT_START_GAME;
    }

    public void decreaseGameNumber() {
        gameNumber--;
    }
}