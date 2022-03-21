package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Checker {
    private static final int INITIAL_INDEX = 0;
    private static final String COMMA = ",";
    private static final int MAX_NAME_LENGTH = 5;
    private static final String IS_SPECIAL_LETTER = "[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*";

    public void isContainAnotherSpecialLetter(String userInput) {
        for (int index = INITIAL_INDEX; index < userInput.length() - 1; index++) {
            String oneLetter = userInput.substring(index, index + 1);
            if (!oneLetter.matches(IS_SPECIAL_LETTER)) {
                if (!oneLetter.equals(COMMA)) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public void isContainSameName(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput, COMMA);
        List<String> nameList = new ArrayList<>();

        int count = st.countTokens() - 1;
        for (int index = INITIAL_INDEX; index < st.countTokens(); index++) {
            nameList.add(st.nextToken());
        }
        int index = INITIAL_INDEX;
        while (index < count) {
            String target = nameList.get(index);
            nameList.remove(index);

            if (nameList.contains(target)) {
                throw new IllegalArgumentException();
            }

            count--;
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
