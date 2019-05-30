package com.lislon;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CharCounterTest {

    @Test
    public void empty_string_should_result_in_empty_map() {
        assertThat(CharCounter.countCharacters(""), equalTo(Collections.<Character, Long>emptyMap()));
    }

    @Test
    public void null_string_should_result_in_empty_map() {
        assertThat(CharCounter.countCharacters(null), equalTo(Collections.<Character, Long>emptyMap()));
    }

    @Test
    public void single_character_should_should_result_single_entry() {
        assertThat(CharCounter.countCharacters("a"), equalTo(singletonMap('a', 1)));
    }

    @Test
    public void two_same_characters_should_result_single_entry() {
        assertThat(CharCounter.countCharacters("aa"), equalTo(singletonMap('a', 2)));
    }

    @Test
    public void two_different_characters_should_result_double_entry() {
        assertThat(CharCounter.countCharacters("aab"), equalTo(new HashMap<>() {{
            put('a', 2);
            put('b', 1);
        }}));
    }

    @Test
    public void test_non_ascii_symbol() {
        assertThat(CharCounter.countCharacters("яя"), equalTo(singletonMap('я', 2)));
    }

    @Test
    public void test_wide_range_symbol() {
        assertThat(CharCounter.countCharacters("\uffff"), equalTo(singletonMap("\uffff".charAt(0), 1)));
    }

    @Test
    @Ignore
    public void perfomance_test() {
        for (int i = 0; i < 10; i++) {
            calcTime(buildLongString(i), i + "");
        }

    }

    private StringBuilder buildLongString(int lengthInMil) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lengthInMil * 1000 * 1000; i++) {
            builder.append(random.nextInt(65536));
        }
        return builder;
    }

    private void calcTime(StringBuilder builder, final String prefix) {
        long nanoTime = System.nanoTime();
        CharCounter.countCharacters(builder.toString());
        System.out.printf(prefix + "\t%.0f%n", (System.nanoTime() - nanoTime) / 1_000_000.0);
    }

}
