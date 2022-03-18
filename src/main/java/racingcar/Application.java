package racingcar;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Application {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String COMMA_WITH_BLANK = ", ";
    private static final String WINNER_IS = "최종 우승자 : ";
    private static int MAX = 0;
    private static ArrayList<Car> carList;
    private static String winnerResult = "";

    public static void main(String[] args) {
        User user = makeUserEntity();
        Checker checker = makeCheckerEntity();
        gameStart(user, checker);
    }

    private static Checker makeCheckerEntity() {
        return new Checker();
    }

    public static void gameStart(User user, Checker checker) {
        gameSettingBeforeStart(user, checker);
        while (user.isGameNumberZero()) {
            makeEveryCarGoOrStop();
            user.decreaseGameNumber();
        }
        whoIsWinner();
    }

    public static void makeEveryCarGoOrStop() {
        System.out.println("carList size : " + carList.size());
        IntStream.range(ZERO, carList.size()).forEach(index -> {
            if (carList.get(index).isCarGo()) {
                carList.get(index).enhancePosition();
            }
            carList.get(index).printPosition();
        });

        System.out.println();
    }

    public static void setMAX() {
        IntStream.range(ZERO, carList.size()).filter(index -> carList.get(index).isBiggerThanPosition(MAX) != -ONE).forEach(index -> MAX = carList.get(index).isBiggerThanPosition(MAX));
    }

    public static void whoIsWinner() {
        setMAX();
        winnerResult += WINNER_IS;

        IntStream.range(ZERO, carList.size()).filter(index -> carList.get(index).isSameWithPosition(MAX) != -ONE).forEach(index -> winnerResult += carList.get(index).getName() + COMMA_WITH_BLANK);

        if (winnerResult.endsWith(COMMA_WITH_BLANK)) {
            winnerResult = winnerResult.substring(ZERO, winnerResult.length() - TWO);
        }
        System.out.println(winnerResult);
    }

    public static void gameSettingBeforeStart(User user, Checker checker) {
        user.userCarNameInput(checker, user);
        user.userGameNumberInput(checker);
        makeCarEntity(user);
    }

    public static void makeCarEntity(User user) {
        carList = new ArrayList<>(user.getCarCount());
        System.out.println("User getCarCount : " + user.getCarCount());
        for (int index = ZERO; index < user.getCarCount(); index++) {
            carList.add(new Car(user.carName.get(index)));
        }
    }

    public static User makeUserEntity() {
        return new User();
    }
}
