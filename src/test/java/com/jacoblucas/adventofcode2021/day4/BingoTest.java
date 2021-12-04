package com.jacoblucas.adventofcode2021.day4;

import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BingoTest {
    private static List<List<String>> INPUT;

    private List<Integer> numbers;
    private List<BingoBoard> boards;
    private Bingo bingo;

    @BeforeClass
    public static void setUpSuite() throws IOException {
        INPUT = InputReader.readGroups("src/test/resources/", "day4-test-input.txt");
    }

    @Before
    public void setUp() {
        numbers = Day4.parseNumbers(INPUT);
        boards = Day4.parseBoards(INPUT);
        bingo = new Bingo(boards);
    }

    @Test
    public void testDraw() {
        // draw the first 11 numbers
        IntStream.range(1, 12).forEach(n -> {
            final List<BingoBoard> winners = bingo.draw(numbers.remove(0));
            assertThat(winners.isEmpty(), is(true));
        });

        final List<BingoBoard> winners = bingo.draw(numbers.remove(0));
        assertThat(winners.size(), is(1));
        assertThat(winners.get(0).getScore(), is(4512));
    }

    @Test
    public void testRun() {
        bingo.run(numbers);
        assertThat(bingo.getFirst().getScore(), is(4512));
        assertThat(bingo.getLast().getScore(), is(1924));
    }
}
