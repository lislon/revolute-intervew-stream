package com.lislon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CharCounter {

    private CharCounter() {
    }

    public static Map<Character, Integer> countCharacters(String string) {
        if (string == null) {
            return Collections.emptyMap();
        }

        return IntStream.range(0, string.length())
                .map(string::charAt)
                .boxed()
                .collect(Collectors.groupingBy(
                        c -> (char) c.shortValue(),
                        Collectors.summingInt(e -> 1)
                ));
    }

    public static Map<Character, Integer> countCharactersFast(String string) {
        if (string == null) {
            return Collections.emptyMap();
        }
        int[] counts = new int[0xffff];
        for (int i = 0; i < string.length(); i++) {
            counts[string.charAt(i)]++;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                map.put((char) i, counts[i]);
            }
        }
        return map;
    }
}
