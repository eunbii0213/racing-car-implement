package racingcar;

import java.util.ArrayList;

public class Application {
    private static final int ZERO = 0;
    private static final String GO = "-";
    private static ArrayList<Car> carList;
    private static ArrayList<String> carPrint;


    public static void main(String[] args) {
        // TODO 구현 진행
        User user = makeUserEntity();
        gameStart(user);
    }

    public static void gameStart(User user) {
        gameSettingBeforeStart(user);
        while (user.isGameNumberZero()) {
            makeEveryCarGoOrStop();
        }
    }

    public static void makeEveryCarGoOrStop() {
        int count = 0;
        for (int index = ZERO; index < carList.size(); index++) {
            if (carList.get(count).goOrStop()) {
                carList.get(count).enhancePosition();
            }
            count++;
        }
    }

    public static void gameSettingBeforeStart(User user) {
        user.userCarNameInput();
        user.addCarNameInList();
        user.userGameNumberInput();
        makeCarEntity(user);
    }

    public static void makeCarEntity(User user) {
        carList = new ArrayList<>(user.getCarCount());
        carPrint = new ArrayList<>();
        for (int index = ZERO; index < user.getCarCount(); index++) {
            carList.add(new Car(user.carName.get(index)));
            carPrint.add(user.carName.get(index) + " : ");
        }
    }

    public static User makeUserEntity() {
        return new User();
    }
}
