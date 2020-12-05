package de.birgitkratz.aoc2020.day02;

public class AlteredPasswordPolicy {

    private final int firstPosition;
    private final int secondPosition;
    private final char passwordLetter;

    public AlteredPasswordPolicy(final String policyDefinitionString) {
        final String[] split1 = policyDefinitionString.split("-");
        final String[] split2 = split1[1].split(" ");
        firstPosition = Integer.parseInt(split1[0]);
        secondPosition = Integer.parseInt(split2[0]);
        passwordLetter = split2[1].charAt(0);
    }

    public boolean apply(final String password) {
        final char c1 = password.charAt(firstPosition - 1);
        final char c2 = password.charAt(secondPosition - 1);

        final boolean b1 = c1 == passwordLetter;
        final boolean b2 = c2 == passwordLetter;

        return (!(b2 && b1) && (b1 || b2));
    }
}
