package com.jacoblucas.adventofcode2021.day18;

import com.jacoblucas.adventofcode2021.utils.InputReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void testExplodeExample1() {
        // [[[[[9,8],1],2],3],4]
        final SnailfishNumber number =
                new SnailfishNumber(
                        new SnailfishNumber(
                                new SnailfishNumber(
                                        new SnailfishNumber(
                                                new SnailfishNumber(9, 8),
                                                new SnailfishNumber(1)
                                        ),
                                        new SnailfishNumber(2)
                                ),
                                new SnailfishNumber(3)
                        ),
                        new SnailfishNumber(4)
                );

        assertThat(number.toString(), is("[[[[[9,8],1],2],3],4]"));
        final boolean result = Calculator.explode(number);
        assertThat(result, is(true));
        assertThat(number.toString(), is("[[[[0,9],2],3],4]"));
    }

    @Test
    public void testExplodeExample2() {
        // [7,[6,[5,[4,[3,2]]]]]
        final SnailfishNumber number =
                new SnailfishNumber(
                        new SnailfishNumber(7),
                        new SnailfishNumber(
                                new SnailfishNumber(6),
                                new SnailfishNumber(
                                        new SnailfishNumber(5),
                                        new SnailfishNumber(
                                                new SnailfishNumber(4),
                                                new SnailfishNumber(3, 2)
                                        )
                                )
                        )
                );

        assertThat(number.toString(), is("[7,[6,[5,[4,[3,2]]]]]"));
        final boolean result = Calculator.explode(number);
        assertThat(result, is(true));
        assertThat(number.toString(), is("[7,[6,[5,[7,0]]]]"));
    }

    @Test
    public void testExplodeExample3() {
        // [[6,[5,[4,[3,2]]]],1]
        final SnailfishNumber number =
                new SnailfishNumber(
                        new SnailfishNumber(
                                new SnailfishNumber(6),
                                new SnailfishNumber(
                                        new SnailfishNumber(5),
                                        new SnailfishNumber(
                                                new SnailfishNumber(4),
                                                new SnailfishNumber(3, 2)
                                        )
                                )
                        ),
                        new SnailfishNumber(1)
                );

        assertThat(number.toString(), is("[[6,[5,[4,[3,2]]]],1]"));
        final boolean result = Calculator.explode(number);
        assertThat(result, is(true));
        assertThat(number.toString(), is("[[6,[5,[7,0]]],3]"));
    }

    @Test
    public void testExplodeExample4() {
        // [[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]
        final SnailfishNumber number =
                new SnailfishNumber(
                        new SnailfishNumber(
                                new SnailfishNumber(3),
                                new SnailfishNumber(
                                        new SnailfishNumber(2),
                                        new SnailfishNumber(
                                                new SnailfishNumber(1),
                                                new SnailfishNumber(7, 3))
                                )
                        ),
                        new SnailfishNumber(
                                new SnailfishNumber(6),
                                new SnailfishNumber(
                                        new SnailfishNumber(5),
                                        new SnailfishNumber(
                                                new SnailfishNumber(4),
                                                new SnailfishNumber(3, 2))
                                )
                        )
                );

        assertThat(number.toString(), is("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"));
        boolean result = Calculator.explode(number);
        assertThat(result, is(true));
        assertThat(number.toString(), is("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"));

        result = Calculator.explode(number);
        assertThat(result, is(true));
        assertThat(number.toString(), is("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"));
    }

    @Test
    public void testSplit() {
        final SnailfishNumber a = new SnailfishNumber(10);
        final SnailfishNumber b = new SnailfishNumber(11);
        final SnailfishNumber c = new SnailfishNumber(12);
        final SnailfishNumber d = new SnailfishNumber(7);

        boolean result = Calculator.split(a);
        assertThat(result, is(true));
        assertThat(a.toString(), is("[5,5]"));

        result = Calculator.split(b);
        assertThat(result, is(true));
        assertThat(b.toString(), is("[5,6]"));

        result = Calculator.split(c);
        assertThat(result, is(true));
        assertThat(c.toString(), is("[6,6]"));

        result = Calculator.split(d);
        assertThat(result, is(false));
        assertThat(d.toString(), is("7"));
    }

    @Test
    public void testSimpleAddition() {
        final SnailfishNumber left = new SnailfishNumber(
                new SnailfishNumber(1),
                new SnailfishNumber(2));

        final SnailfishNumber right = new SnailfishNumber(
                new SnailfishNumber(
                        new SnailfishNumber(3),
                        new SnailfishNumber(4)
                ),
                new SnailfishNumber(5));

        final SnailfishNumber expected = new SnailfishNumber(left, right);

        assertThat(Calculator.add(left, right).toString(), is("[[1,2],[[3,4],5]]"));
    }

    @Test
    public void testComplexAddition() {
        final SnailfishNumber left =
                new SnailfishNumber(
                        new SnailfishNumber(
                                new SnailfishNumber(
                                        new SnailfishNumber(4, 3),
                                        new SnailfishNumber(4)
                                ),
                                new SnailfishNumber(4)
                        ),
                        new SnailfishNumber(
                                new SnailfishNumber(7),
                                new SnailfishNumber(
                                        new SnailfishNumber(8, 4),
                                        new SnailfishNumber(9)
                                )
                        )
                );

        final SnailfishNumber right = new SnailfishNumber(1, 1);

        final SnailfishNumber result = Calculator.add(left, right);
        assertThat(result.toString(), is("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));
    }

    @Test
    public void testMultipleAdds1() {
        final SnailfishNumber result = Calculator.add(
                new SnailfishNumber(1,1),
                new SnailfishNumber(2,2),
                new SnailfishNumber(3,3),
                new SnailfishNumber(4,4));

        assertThat(result.toString(), is("[[[[1,1],[2,2]],[3,3]],[4,4]]"));
    }

    @Test
    public void testMultipleAdds2() {
        final SnailfishNumber result = Calculator.add(
                new SnailfishNumber(1,1),
                new SnailfishNumber(2,2),
                new SnailfishNumber(3,3),
                new SnailfishNumber(4,4),
                new SnailfishNumber(5,5));

        assertThat(result.toString(), is("[[[[3,0],[5,3]],[4,4]],[5,5]]"));
    }

    @Test
    public void testMultipleAdds3() {
        final SnailfishNumber result = Calculator.add(
                new SnailfishNumber(1,1),
                new SnailfishNumber(2,2),
                new SnailfishNumber(3,3),
                new SnailfishNumber(4,4),
                new SnailfishNumber(5,5),
                new SnailfishNumber(6,6));

        assertThat(result.toString(), is("[[[[5,0],[7,4]],[5,5]],[6,6]]"));
    }

    @Test
    public void testLargerExampleAddition() throws IOException {
        final List<String> lines = InputReader.readFile("src/test/resources/", "day18-test-input.txt");
        final SnailfishNumber[] numbers = lines.stream()
                .map(SnailfishNumber::parse)
                .collect(Collectors.toList())
                .toArray(new SnailfishNumber[]{});

        final SnailfishNumber result = Calculator.add(numbers);
        assertThat(result.toString(), is("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"));
    }
}
