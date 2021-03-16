package com.assetco.search.results;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class UserSegmentTest {
    @Test
    void shouldHaveExpectedValues() {
        assertThat(Arrays.stream(UserSegment.values()).map(UserSegment::name))
                .containsOnly("NewsMedia", "OtherMedia", "GeneralPublic");
    }
}
