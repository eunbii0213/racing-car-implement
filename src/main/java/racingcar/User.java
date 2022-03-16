package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class User {
    private final String USER_CAR_INPUT_GUIDE = "경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)";
    private final String USER_GAME_NUMBER_INPUT_GUIDE = "시도할 회수는 몇회인가요?";
    private ArrayList<String> carName;
    public StringTokenizer st;

    public User(){
        carName = new ArrayList<>();
    }

    public void userCarNameInput(){
        st = new StringTokenizer(Console.readLine(),",");
    }

    public void addCarNameInList(){
        while(st.hasMoreTokens()){
            carName.add(st.nextToken());
        }
    }

}
