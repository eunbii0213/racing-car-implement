package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final String name;
    private int position = 0;
    private static final String GO = "-";
    //public String positionStr = "";

    public Car(String name) {
        this.name = name;
    }

    public void enhancePosition() {
        position+=1;
    }

    public void printPosition() {
        String positionStr = name + " : ";
        int temp = position;

        while (temp > 0) {
            positionStr += GO;
            temp--;
        }
        System.out.println(positionStr);
    }

    public int isBiggerThanPosition(int compareNumber) {
        if (position >= compareNumber ) {
            return position;
        }
        if(position<= compareNumber){
            return compareNumber;
        }
        return -1;
    }

    public int isSameWithPosition(int compareNumber) {
        if (compareNumber == position) {
            return compareNumber;
        }
        return -1;
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

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
