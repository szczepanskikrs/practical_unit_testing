package assigments;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class PasswordValidator {
    String validate(String toValidate) {
        if (toValidate == null) {
            throw new IllegalArgumentException("Parameter can't be null");
        }
        if (toValidate.isEmpty()) {
            throw new IllegalArgumentException("Parameter can't be empty");
        }
        String toSplit = toValidate.replaceAll("[^0-9.]", " ");
        String result = Arrays.stream(toSplit.split(" "))
                .filter(checkIfNotEmpty())
                .filter(checkIfCorrectLength())
                .collect(Collectors.joining(","));

        if (!result.isEmpty()) {
            return result;
        } else {
            return "Not valid";
        }
    }

    private Predicate<String> checkIfNotEmpty() {
        return p -> !p.isEmpty();
    }

    private Predicate<String> checkIfCorrectLength() {
        return p -> p.length() >= 3;
    }

}
