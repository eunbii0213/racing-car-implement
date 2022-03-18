package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Checker {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String COMMA = ",";
    private static final String SAME_NAME_MESSAGE = " 각 자동차마다 다른 이름으로 입력해야한다.";
    private static final String MAX_LENGTH_MESSAGE = " 자동차의 이름은 5글자 이하로 입력해야 한다.";
    private static final String SPECIAL_LETTER_MESSAGE = " 이름으로 숫자와 문자는 입력가능하나 구분자를 쉼표(,)를 사용해야 한다.";
    private static final String ERROR_MESSAGE = "[ERROR]";
    private static final int MAX_NAME_LENGTH = 5;
    private static final String IS_SPECIAL_LETTER = "[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*";
    public static ArrayList<String> tempList = new ArrayList<>();

    public String catchErrorSameName(String userInput, User user) {
        try {
            isSameName(userInput, user);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + SAME_NAME_MESSAGE);
            userInput = Console.readLine();
            userInputChecker(userInput, user);
        }
        return userInput;
    }

    public String catchErrorNameLength(String userInput, User user) {
        try {
            userInputIsMoreThanFiveLetters(userInput, user);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + MAX_LENGTH_MESSAGE);
            userInput = Console.readLine();
            userInputChecker(userInput, user);
        }
        return userInput;
    }

    public String catchErrorSpecialLetter(String userInput, User user) {
        addTempList(userInput);

        int index = ZERO;
        while (index < tempList.size()) {
            try {
                isContainSpecialLetter(index);
            } catch (IllegalArgumentException e) {
                clearTempList();
                System.out.println(ERROR_MESSAGE + SPECIAL_LETTER_MESSAGE);
                String anotherUserInput = Console.readLine();
                userInputChecker(anotherUserInput, user);
            }
            index++;
        }
        clearTempList();
        return userInput;
    }

    public void userInputChecker(String userInput, User user) {
        userInput = catchErrorSpecialLetter(userInput, user);
        userInput = catchErrorSameName(userInput, user);
        userInput = catchErrorNameLength(userInput, user);

        addNameInTempList(userInput);
        user.setUserInput(userInput);
        user.carNameListDeepCopyFromOtherList(tempList);

        clearTempList();
    }

    private void clearTempList() {
        while (!tempList.isEmpty()) {
            tempList.remove(ZERO);
        }
    }

    public void isSameName(String userInput, User user) {
        addNameInTempList(userInput);

        IntStream.range(ZERO, tempList.size() - ONE).filter(index -> tempList.get(index).equals(tempList.get(index + ONE))).forEach(index -> {
            clearTempList();
            throw new IllegalArgumentException();
        });
        clearTempList();
    }

    public void addNameInTempList(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput, COMMA);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            tempList.add(str);
        }
    }

    public void addTempList(String userInput) {
        for (int index = ZERO; index < userInput.length(); index++) {
            String oneLetter = "";

            boolean isLastIndex = false;
            if (index == userInput.length() - ONE) {
                oneLetter = userInput.substring(userInput.length() - ONE);
                isLastIndex = true;
            }
            if (!isLastIndex) {
                oneLetter = userInput.substring(index, index + ONE);
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

    public void userInputIsMoreThanFiveLetters(String userInput, User user) {
        StringTokenizer st = new StringTokenizer(userInput, COMMA);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (str.length() > MAX_NAME_LENGTH) {
                user.carName.clear();
                throw new IllegalArgumentException();
            }
            user.carName.add(str);
        }
    }

    public void userInputNumberChecker(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
