package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final int ZERO = 0;
    private static final int RANDOM_RANGE_START = 1;
    private static final int RANDOM_RANGE_END = 9;
    private static final int GO_NUMBER = 4;
    private static final String COLON_WITH_BLANK = " : ";
    private final String name;
    private int position = 0;
    private static final String GO = "-";

    public Car(String name) {
        this.name = name;
    }

    public void enhancePosition() {
        position++;
    }

    public void printPosition() {
        String positionStr = name + COLON_WITH_BLANK;
        int tempPositionNumber = position;

        while (tempPositionNumber > ZERO) {
            positionStr += GO;
            tempPositionNumber--;
        }
        System.out.println(positionStr);
    }

    public int getPosition() {
        return position;
    }

    public boolean isSameWithPosition(int compareNumber) {
        return compareNumber == position;
    }

    public boolean isCarGo() {
        return makeRandomNumber() >= GO_NUMBER;
    }

    public int makeRandomNumber() {
        return Randoms.pickNumberInRange(RANDOM_RANGE_START, RANDOM_RANGE_END);
    }

    public String getName() {
        return name;
    }
}
