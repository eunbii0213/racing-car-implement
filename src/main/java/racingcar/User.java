package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class User {
    private static final String USER_CAR_INPUT_GUIDE = "경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분)";
    private static final String USER_GAME_NUMBER_INPUT_GUIDE = "시도할 회수는 몇회인가요?";
    private static ArrayList<String> carName;
    public static StringTokenizer st;
    public static int gameNumber;


    public User(){
        carName = new ArrayList<>();
    }

    public void userCarNameInput(){
        System.out.println(USER_CAR_INPUT_GUIDE);
        st = new StringTokenizer(Console.readLine(),",");
    }

    public void addCarNameInList(){
        while(st.hasMoreTokens()){
            carName.add(st.nextToken());
        }
    }

    public void userGameNumberInput(){
        System.out.println(USER_GAME_NUMBER_INPUT_GUIDE);
        gameNumber = Integer.parseInt(Console.readLine());
    }

}
