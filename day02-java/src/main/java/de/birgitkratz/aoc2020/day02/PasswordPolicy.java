package de.birgitkratz.aoc2020.day02;

public class PasswordPolicy {

    private final int minOccurence;
    private final int maxOccurence;
    private final char passwordLetter;

    public PasswordPolicy(final String policyDefinitionString) {
        final String[] split1 = policyDefinitionString.split("-");
        final String[] split2 = split1[1].split(" ");
        minOccurence = Integer.parseInt(split1[0]);
        maxOccurence = Integer.parseInt(split2[0]);
        passwordLetter = split2[1].charAt(0);
    }

    public boolean apply(final String password) {
        final long count = password.chars().filter(value -> value == passwordLetter).count();
        return (count >= minOccurence && count <= maxOccurence);
    }
}
