package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Checker {
    private static final int INITIAL_INDEX = 0;
    private static final String COMMA = ",";
    private static final String SAME_NAME_MESSAGE = " 각 자동차마다 다른 이름으로 입력해야한다.";
    private static final String MAX_LENGTH_MESSAGE = " 자동차의 이름은 5글자 이하로 입력해야 한다.";
    private static final String SPECIAL_LETTER_MESSAGE = " 이름으로 숫자와 문자는 입력가능하나 구분자를 쉼표(,)를 사용해야 한다.";
    private static final String ERROR_MESSAGE = "[ERROR]";
    private static final int MAX_NAME_LENGTH = 5;
    private static final String IS_SPECIAL_LETTER = "[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*";
    private List<String> tempList = new ArrayList<>();

    public String catchErrorSameName(String userInput, User user) {
        try {
            isSameName(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + SAME_NAME_MESSAGE);
            userInput = Console.readLine();
            userInputChecker(userInput, user);
        }
        return userInput;
    }

    public String catchErrorNameLength(String userInput, User user) {
        try {
            userInputIsMoreThanFiveLetters(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + MAX_LENGTH_MESSAGE);
            userInput = Console.readLine();
            userInputChecker(userInput, user);
        }
        return userInput;
    }

    public String catchErrorSpecialLetter(String userInput, User user) {
        addTempList(userInput);

        int index = INITIAL_INDEX;
        while (index < tempList.size()) {
            try {
                isContainSpecialLetter(index);
            } catch (IllegalArgumentException e) {
                tempList.clear();
                System.out.println(ERROR_MESSAGE + SPECIAL_LETTER_MESSAGE);
                userInput = Console.readLine();
                userInputChecker(userInput, user);
            }
            index++;
        }
        tempList.clear();
        return userInput;
    }

    public void userInputChecker(String userInput, User user) {
        userInput = catchErrorSpecialLetter(userInput, user);
        userInput = catchErrorSameName(userInput, user);
        userInput = catchErrorNameLength(userInput, user);

        tempList.clear();
    }

    public void isSameName(String userInput) {
        addNameInTempList(userInput);

        IntStream.range(INITIAL_INDEX, tempList.size() - 1)
                .filter(index -> tempList.get(index).equals(tempList.get(index + 1)))
                .forEach(index -> {
                    tempList.clear();
                    throw new IllegalArgumentException();
                });
        tempList.clear();
    }

    public void addNameInTempList(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput, COMMA);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            tempList.add(str);
        }
    }

    public void addTempList(String userInput) {
        for (int index = INITIAL_INDEX; index < userInput.length(); index++) {
            String oneLetter = "";

            boolean isLastIndex = false;
            if (index == userInput.length() - 1) {
                oneLetter = userInput.substring(userInput.length() - 1);
                isLastIndex = true;
            }
            if (!isLastIndex) {
                oneLetter = userInput.substring(index, index + 1);
            }
            tempList.add(oneLetter);
        }
    }

    public void isContainSpecialLetter(int index) {
        String str = tempList.get(index);

        if (!str.matches(IS_SPECIAL_LETTER)) {
            if (!str.equals(COMMA)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public void userInputIsMoreThanFiveLetters(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput, COMMA);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (str.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int userInputNumberChecker(String userInput) {
        int userInputToInt;
        try {
            userInputToInt = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return userInputToInt;
    }
}
