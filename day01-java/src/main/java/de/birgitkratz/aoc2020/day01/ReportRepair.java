package de.birgitkratz.aoc2020.day01;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class ReportRepair
{
    public static void main( String[] args ) throws IOException {

        final ReportRepair reportRepair = new ReportRepair();
        String[] stringArray = reportRepair.readInputFile();
        final int[] ints = Stream.of(stringArray).mapToInt(Integer::valueOf).toArray();

        final Map<Pair<Integer, Integer>, Integer> addPair = reportRepair.addPair(ints);
        final Pair<Integer, Integer> sumPair = reportRepair.findPairSum(2020, addPair);
        System.out.println(sumPair);

        System.out.println(reportRepair.pairProduct(sumPair));

        final Map<Triple<Integer, Integer, Integer>, Integer> addTriple = reportRepair.addTriple(ints);
        final Triple<Integer, Integer, Integer> sumTriple = reportRepair.findTripleSum(2020, addTriple);
        System.out.println(sumTriple);

        System.out.println(reportRepair.tripleProduct(sumTriple));
    }

    String[] readInputFile() throws IOException {
        File file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("input.txt")).getFile());
        Path filePath = file.toPath();
        Charset charset = Charset.defaultCharset();
        List<String> stringList = Files.readAllLines(filePath, charset);
        return stringList.toArray(new String[]{});
    }

    Map<Pair<Integer, Integer>, Integer> addPair(final int[] intArray) {
        Map<Pair<Integer, Integer>, Integer> result = new HashMap<>();
        for (int i=0; i<intArray.length; i++) {
            for (int j=i+1; j<intArray.length; j++) {
                result.put(Pair.of(intArray[i], intArray[j]), (intArray[i] + intArray[j]));
            }
        }
        return result;
    }

    Map<Triple<Integer, Integer, Integer>, Integer> addTriple(final int[] intArray) {
        Map<Triple<Integer, Integer, Integer>, Integer> result = new HashMap<>();
        for (int i=0; i<intArray.length; i++) {
            for (int j=i+1; j<intArray.length; j++) {
                for(int k=j+1; k<intArray.length; k++)
                result.put(Triple.of(intArray[i], intArray[j], intArray[k]), (intArray[i] + intArray[j] + intArray[k]));
            }
        }
        return result;
    }

    Pair<Integer, Integer> findPairSum(final int sum,
            final Map<Pair<Integer, Integer>, Integer> input) {

        return input.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(sum))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    Triple<Integer, Integer, Integer> findTripleSum(final int sum,
            final Map<Triple<Integer, Integer, Integer>, Integer> input) {

        return input.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(sum))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    int pairProduct(final Pair<Integer, Integer> summands) {
        return summands.getLeft() * summands.getRight();
    }
    int tripleProduct(final Triple<Integer, Integer, Integer> summands) {
        return summands.getLeft() * summands.getMiddle() * summands.getRight();
    }
}
