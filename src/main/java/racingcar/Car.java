package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int GO_NUMBER = 4;
    private final String name;
    private int position = 0;
    private static final String GO = "-";

    public Car(String name) {
        this.name = name;
    }

    public void enhancePosition() {
        position += ONE;
    }

    public void printPosition() {
        String positionStr = name + " : ";
        int temp = position;

        while (temp > ZERO) {
            positionStr += GO;
            temp--;
        }
        System.out.println(positionStr);
    }

    public int isBiggerThanPosition(int compareNumber) {
        if (position >= compareNumber) {
            return position;
        }
        if (position <= compareNumber) {
            return compareNumber;
        }
        return -ONE;
    }

    public int isSameWithPosition(int compareNumber) {
        if (compareNumber == position) {
            return compareNumber;
        }
        return -ONE;
    }

    //true -> 전진, false -> 정지
    public boolean goOrStop() {
        if (makeRandomNumber() >= GO_NUMBER) {
            return true;
        }
        return false;
    }

    public int makeRandomNumber() {
        return Randoms.pickNumberInRange(1, 9);
    }

    public String getName() {
        return name;
    }
}
