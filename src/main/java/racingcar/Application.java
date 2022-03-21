package racingcar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Application {
    private static final int INITIAL_INDEX = 0;
    private static final String COMMA_WITH_BLANK = ", ";
    private static final String WINNER_IS = "최종 우승자 : ";
    private static List<Car> carList;
    private static List<String> winnerResult;

    public static void main(String[] args) {
        User user = makeUserEntity();
        ErrorMessageView view = maekeErrorMessageViewEntity();
        gameStart(user, view);
    }

    private static ErrorMessageView maekeErrorMessageViewEntity() {
        return new ErrorMessageView();
    }

    public static void gameStart(User user, ErrorMessageView view) {
        gameSettingBeforeStart(user, view);
        while (!user.isDontStartGame()) {
            makeEveryCarGoOrStop();
            user.decreaseGameNumber();
        }
        whoIsWinner();
    }

    public static void makeEveryCarGoOrStop() {
        IntStream.range(INITIAL_INDEX, carList.size()).forEach(index -> {
            if (carList.get(index).isCarGo()) {
                carList.get(index).enhancePosition();
            }
            carList.get(index).printPosition();
        });

        System.out.println();
    }

    public static Car setMAX() {
        Car maxMember = carList
                .stream()
                .max(Comparator.comparing(Car::getPosition))
                .orElseThrow(IllegalArgumentException::new);

        return maxMember;
    }

    public static void whoIsWinner() {
        Car maxCarEntity = setMAX();
        winnerResult = new ArrayList<>();

        IntStream.range(INITIAL_INDEX, carList.size()).filter(
                index -> carList.get(index).isSameWithPosition(maxCarEntity.getPosition())).forEachOrdered(
                index -> winnerResult.add(carList.get(index).getName()));

        System.out.println(WINNER_IS + String.join(COMMA_WITH_BLANK, winnerResult));
    }

    public static void gameSettingBeforeStart(User user, ErrorMessageView view) {
        user.userCarNameInput(view, user);
        user.userGameNumberInput(view);
        makeCarEntity(user);
    }

    public static void makeCarEntity(User user) {
        carList = new ArrayList<>();
        for (int index = INITIAL_INDEX; index < user.getCarName().size(); index++) {
            carList.add(new Car(user.getCarName().get(index)));
        }
    }

    public static User makeUserEntity() {
        return new User();
    }
}
