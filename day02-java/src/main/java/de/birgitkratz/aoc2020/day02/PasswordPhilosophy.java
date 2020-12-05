package de.birgitkratz.aoc2020.day02;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PasswordPhilosophy {
    public static void main( String[] args ) throws IOException {
        final PasswordPhilosophy passwordPhilosophy = new PasswordPhilosophy();
        String[] stringArray = passwordPhilosophy.readInputFile();

        final long count = Arrays.stream(stringArray).map(s -> {
            final String[] split = s.split(": ");
            return new PasswordPolicy(split[0]).apply(split[1]);
        }).filter(aBoolean -> aBoolean).count();

        System.out.println("number valid passwords: " + count);

        final long alteredCount = Arrays.stream(stringArray).map(s -> {
            final String[] split = s.split(": ");
            return new de.birgitkratz.aoc2020.day02.AlteredPasswordPolicy(split[0]).apply(split[1]);
        }).filter(aBoolean -> aBoolean).count();

        System.out.println("altered number valid passwords: " + alteredCount);
    }

    private String[] readInputFile() throws IOException {
        File file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());
        Path filePath = file.toPath();
        Charset charset = Charset.defaultCharset();
        List<String> stringList = Files.readAllLines(filePath, charset);
        return stringList.toArray(new String[]{});
    }
}
