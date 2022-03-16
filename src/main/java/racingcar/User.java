package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

public class User {
    private static final String USER_CAR_INPUT_GUIDE = "경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)";
    private static final String USER_GAME_NUMBER_INPUT_GUIDE = "시도할 회수는 몇회인가요?";
    private static final String ERROR_MESSAGE = "[ERROR] 시도 횟수는 숫자여야 한다.";
    private static final int ZERO = 0;
    public static ArrayList<String> carName;
    public static int gameNumber;
    public static String userInput="";
    public static String userNumberInput="";

    public User() {
        carName = new ArrayList<>();
    }

    public void userCarNameInput(Checker checker, User user) {
        System.out.println(USER_CAR_INPUT_GUIDE);
        userInput = Console.readLine();

        checker.userInputChecker(userInput, user);

    }

    //addCarNameInList후에 호출되어야함에 유의합니다.
    public int getCarCount() {
        return carName.size();
    }

    public void userGameNumberInput(Checker checker) {
        System.out.println(USER_GAME_NUMBER_INPUT_GUIDE);

        userNumberInput = Console.readLine();
        try {
            checker.userInputNumberChecker(userNumberInput);
        }catch(IllegalArgumentException e){
            System.out.println(ERROR_MESSAGE);
            userGameNumberInput(checker);
        }
        gameNumber = Integer.parseInt(userNumberInput);
    }

    public static boolean isGameNumberZero() {
        if (gameNumber == ZERO) {
            return false;
        }
        return true;
    }

    public static void decreaseGameNumber() {
        gameNumber--;
    }
}