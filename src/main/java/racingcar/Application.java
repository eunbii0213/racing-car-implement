package racingcar;

import java.util.ArrayList;

public class Application {
    private static final int ZERO = 0;
    private static ArrayList<Car> carList;

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
    }

    public static void makeEveryCarGoOrStop() {
        for (int index = ZERO; index < carList.size(); index++) {
            if (carList.get(index).goOrStop()) {
                carList.get(index).enhancePosition();
            }
            carList.get(index).printPosition();
        }
        System.out.println();
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
