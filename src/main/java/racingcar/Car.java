package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public void enhancePosition(){
        position++;
    }

    //true -> 전진, false -> 정지
    public boolean goOrStop(){
        if(makerandomNumber()>=4){
            return true;
        }
        return false;
    }

    public int makerandomNumber(){
        return Randoms.pickNumberInRange(1,9);
    }
}
