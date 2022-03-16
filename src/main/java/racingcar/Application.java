package racingcar;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Application {
    private static final int ZERO = 0;
    private static final String WINNER_IS = "최종 우승자 : ";
    private static int MAX = 0;
    private static ArrayList<Car> carList;
    private static String winnerResult="";

    public static void main(String[] args) {
        // TODO 구현 진행
        User user = makeUserEntity();
        gameStart(user);
    }

    public static void gameStart(User user) {
        gameSettingBeforeStart(user);
        while (user.isGameNumberZero()) {
            makeEveryCarGoOrStop();
            user.decreaseGameNumber();
        }
        whoIsWinner();
    }

    public static void makeEveryCarGoOrStop() {
        //for-each문으로 고치기
        for (int index = ZERO; index < carList.size(); index++) {
            if (carList.get(index).goOrStop()) {
                carList.get(index).enhancePosition();
            }
            carList.get(index).printPosition();
        }

        System.out.println();
    }

    public static void setMAX() {
        //for-each문으로 고치기
        IntStream.range(ZERO, carList.size()).filter(index -> carList.get(index).isBiggerThanPosition(MAX) != -1).forEach(index -> MAX = carList.get(index).isBiggerThanPosition(MAX));
    }

    public static void whoIsWinner() {
        setMAX();
        winnerResult += WINNER_IS;

        IntStream.range(ZERO, carList.size()).filter(index -> carList.get(index).isSameWithPosition(MAX) != -1).forEach(index -> winnerResult += carList.get(index).getName() + ", ");

        if(winnerResult.endsWith(", ")){
            winnerResult = winnerResult.substring(0,winnerResult.length()-2);
        }
        System.out.println(winnerResult);
    }

    public static void gameSettingBeforeStart(User user) {
        user.userCarNameInput();
        user.addCarNameInList();
        user.userGameNumberInput();
        makeCarEntity(user);
    }

    public static void makeCarEntity(User user) {
        carList = new ArrayList<>(user.getCarCount());
        for (int index = ZERO; index < user.getCarCount(); index++) {
            carList.add(new Car(user.carName.get(index)));
        }
    }

    public static User makeUserEntity() {
        return new User();
    }
}
