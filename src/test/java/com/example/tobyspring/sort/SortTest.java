package com.example.tobyspring.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {
    private Sort sort;

    @BeforeEach
    public void beforeEach() {
        sort = new Sort();
    }

    @Test
    public void sortByLengthTest() {
        // 준비(given)
//        Sort sort = new Sort();

        // 실행(when)
        List<String> list = sort.sortByLength(Arrays.asList("ab", "b", "abcd", "zyx"));

        // 검증(then)
        Assertions.assertThat(list).isEqualTo(List.of("b", "ab", "zyx", "abcd"));
    }

    @Test
    public void alreadySortedTest() {
//        Sort sort = new Sort();
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}
