package de.birgitkratz.aoc2020.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PassportProcessingTest {

    @Test
    void acceptanceSample1() {
        List<String> input = List.of(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929",
                "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm",
                "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"
        );
        long valid = new PassportProcessing().processPassportsPart1(input);

        assertThat(valid).isEqualTo(2L);
    }

    @Test
    void acceptanceSample2Valid() {

        List<String> input = List.of(
                "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 hcl:#623a2f",
                "eyr:2029 ecl:blu cid:129 byr:1989 iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
                "hcl:#888785 hgt:164cm byr:2001 iyr:2015 cid:88 pid:545766238 ecl:hzl eyr:2022",
                "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
        );
        long valid = new PassportProcessing().processPassportsPart2(input);

        assertThat(valid).isEqualTo(4L);
    }

    @Test
    void acceptanceSample2Invalid() {
        List<String> input = List.of(
                "eyr:1972 cid:100 hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
                "iyr:2019 hcl:#602927 eyr:1967 hgt:170cm ecl:grn pid:012533040 byr:1946",
                "hcl:dab227 iyr:2012 ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
                "hgt:59cm ecl:zzz eyr:2038 hcl:74454a iyr:2023 pid:3556412378 byr:2007"
        );
        long valid = new PassportProcessing().processPassportsPart2(input);

        assertThat(valid).isEqualTo(0L);
    }

    @Test
    void parseBatchFileToStringArray() throws IOException {
        final Path tempFile = Files.createTempFile("prefix", "suffix");

        String fileContent = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n"
                + "byr:1937 iyr:2017 cid:147 hgt:183cm\n"
                + "\n"
                + "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n"
                + "hcl:#cfa07d byr:1929\n"
                + "\n"
                + "hcl:#ae17e1 iyr:2013\n"
                + "eyr:2024\n"
                + "ecl:brn pid:760753108 byr:1931\n"
                + "hgt:179cm\n"
                + "\n"
                + "hcl:#cfa07d eyr:2025 pid:166559648\n"
                + "iyr:2011 ecl:brn hgt:59in";

        Files.writeString(tempFile, fileContent);

        final PassportProcessing passportProcessing = new PassportProcessing();
        assertThat(passportProcessing.readPassportsFromBatchFile(tempFile)).containsExactly(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929",
                "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm",
                "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"
        );
    }

    @Test
    void processSample1() throws IOException {
        final PassportProcessing passportProcessing = new PassportProcessing();
        assertThat(passportProcessing.processPart1()).isEqualTo(192L);
    }

    @Test
    void processSample2() throws IOException {
        final PassportProcessing passportProcessing = new PassportProcessing();
        assertThat(passportProcessing.processPart2()).isEqualTo(101L);
    }
}