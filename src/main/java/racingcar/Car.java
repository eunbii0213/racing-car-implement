package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final String name;
    private int position = 0;
    private static final String GO = "-";
    String positionStr="";

    public Car(String name) {
        this.name = name;
        positionStr = name + " : ";
    }

    public void enhancePosition() {
        position++;
    }

    public void printPosition() {
        while (position > 0) {
            positionStr += GO;
            position--;
        }
        System.out.println(positionStr);
    }

    //true -> 전진, false -> 정지
    public boolean goOrStop() {
        if (makerandomNumber() >= 4) {
            return true;
        }
        return false;
    }

    public int makerandomNumber() {
        return Randoms.pickNumberInRange(1, 9);
    }
}
