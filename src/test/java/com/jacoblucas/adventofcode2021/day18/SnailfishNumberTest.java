package com.jacoblucas.adventofcode2021.day18;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class SnailfishNumberTest {

    private final static SnailfishNumber A = new SnailfishNumber(4, 4);
    private final static SnailfishNumber B = new SnailfishNumber(4, 3);
    private final static SnailfishNumber NUMBER_SIX = new SnailfishNumber(6);

    // [[[[6,9],[1,7]],[[4,3],[4,3]]],[[[4,4],[3,6]],[7,[7,0]]]]
    private final static SnailfishNumber NUMBER = new SnailfishNumber(
            new SnailfishNumber(
                    new SnailfishNumber(
                            new SnailfishNumber(
                                    NUMBER_SIX,
                                    new SnailfishNumber(9)),
                            new SnailfishNumber(1, 7)
                    ),
                    new SnailfishNumber(
                            new SnailfishNumber(4, 3),
                            B
                    )
            ),
            new SnailfishNumber(
                    new SnailfishNumber(
                            A,
                            new SnailfishNumber(3, 6)
                    ),
                    new SnailfishNumber(
                            new SnailfishNumber(7),
                            new SnailfishNumber(7, 0)
                    )
            )
    );

    @Test
    public void testParse() {
        assertThat(SnailfishNumber.parse("6").toString(), is("6"));
        assertThat(SnailfishNumber.parse("[1,2]").toString(), is("[1,2]"));
        assertThat(SnailfishNumber.parse("[[[[6,9],[1,7]],[[4,3],[4,3]]],[[[4,4],[3,6]],[7,[7,0]]]]").toString(), is(NUMBER.toString()));
    }

    @Test
    public void testParentSetup() {
        final SnailfishNumber one = new SnailfishNumber(1);
        final SnailfishNumber two = new SnailfishNumber(2);
        final SnailfishNumber pair = new SnailfishNumber(one, two);

        assertThat(one.getParent(), is(pair));
        assertThat(two.getParent(), is(pair));
        assertThat(pair.getParent(), is(nullValue()));
    }

    @Test
    public void testCountParents() {
        final SnailfishNumber a = new SnailfishNumber(1);
        final SnailfishNumber b = new SnailfishNumber(2);
        final SnailfishNumber c = new SnailfishNumber(3);
        final SnailfishNumber d = new SnailfishNumber(4);
        final SnailfishNumber e = new SnailfishNumber(5);

        final SnailfishNumber root = new SnailfishNumber(
                new SnailfishNumber(
                        new SnailfishNumber(
                                new SnailfishNumber(a, b),
                                c),
                        d),
                e);

        assertThat(a.countParents(), is(4));
        assertThat(b.countParents(), is(4));
        assertThat(c.countParents(), is(3));
        assertThat(d.countParents(), is(2));
        assertThat(e.countParents(), is(1));
        assertThat(root.countParents(), is(0));
    }

    @Test
    public void testGetFirstNumberLeft() {
        assertThat(A.getFirstNumberLeft().toString(), is("3"));
        assertThat(NUMBER_SIX.getFirstNumberLeft(), is(nullValue()));
    }

    @Test
    public void testGetFirstNumberRight() {
        assertThat(B.getFirstNumberRight().toString(), is("4"));
    }

    @Test
    public void testToString() {
        assertThat(new SnailfishNumber(4, 5).toString(), is("[4,5]"));
        assertThat(new SnailfishNumber(5).toString(), is("5"));
        assertThat(NUMBER.toString(), is("[[[[6,9],[1,7]],[[4,3],[4,3]]],[[[4,4],[3,6]],[7,[7,0]]]]"));
    }

    @Test
    public void testGetMagnitude() {
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

        assertThat(result.getMagnitude(), is(1384L));
    }
}
