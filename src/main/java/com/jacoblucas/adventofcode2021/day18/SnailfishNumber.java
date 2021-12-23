package com.jacoblucas.adventofcode2021.day18;

import com.jacoblucas.adventofcode2021.interfaces.ModifiablePair;

public class SnailfishNumber {
    private SnailfishNumber parent = null;

    private ModifiablePair<SnailfishNumber, SnailfishNumber> pair;
    private Integer value;

    public static SnailfishNumber parse(final String str) {
        if (str.length() == 1) {
            return new SnailfishNumber(Integer.parseInt(str));
        }

        int level = 0;
        int splitAt = -1;
        for (int i = 0; i < str.length() && splitAt == -1; i++) {
            char ch = str.charAt(i);
            if (ch == '[') {
                level++;
            } else if (ch == ']') {
                level--;
            } else if (ch == ',') {
                if (level == 1) {
                    splitAt = i;
                }
            }
        }

        return new SnailfishNumber(
                parse(str.substring(1, splitAt)),
                parse(str.substring(splitAt + 1, str.length() - 1)));
    }

    public SnailfishNumber(final int n) {
        this.value = n;
        this.pair = null;
    }

    public SnailfishNumber(final SnailfishNumber left, final SnailfishNumber right) {
        this.pair = ModifiablePair.create(left, right);
        this.value = null;

        left.setParent(this);
        right.setParent(this);
    }

    public SnailfishNumber(final int left, final int right) {
        this(new SnailfishNumber(left), new SnailfishNumber(right));
    }

    public SnailfishNumber copy() {
        return isValue() ? new SnailfishNumber(value) : new SnailfishNumber(getLeft().copy(), getRight().copy());
    }

    public SnailfishNumber getParent() {
        return parent;
    }

    public void setParent(final SnailfishNumber parent) {
        this.parent = parent;
    }

    public SnailfishNumber getLeft() {
        return pair.getFirst();
    }

    public SnailfishNumber getRight() {
        return pair.getSecond();
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.pair = null;
        this.value = value;
    }

    public void setPair(final int left, final int right) {
        final SnailfishNumber leftNumber = new SnailfishNumber(left);
        final SnailfishNumber rightNumber = new SnailfishNumber(right);
        leftNumber.setParent(this);
        rightNumber.setParent(this);
        this.pair = ModifiablePair.create(leftNumber, rightNumber);
        this.value = null;
    }

    public boolean isValue() {
        return value != null;
    }

    @Override
    public String toString() {
        if (isValue()) {
            return String.valueOf(value);
        } else {
            return "[" + getLeft().toString() + "," + getRight().toString() + "]";
        }
    }

    public int countParents() {
        int count = 0;
        SnailfishNumber parent = getParent();
        while (parent != null) {
            count++;
            parent = parent.getParent();
        }
        return count;
    }

    public SnailfishNumber getFirstNumberLeft() {
        final SnailfishNumber parent = getParent();
        if (parent == null) {
            return null;
        } else {
            if (parent.getRight().equals(this)) {
                // we're on the right, find the right most node of the parent's left
                SnailfishNumber left = parent.getLeft();
                while (!left.isValue()) {
                    left = left.getRight();
                }
                return left;
            } else {
                // we're on the left
                return parent.getFirstNumberLeft();
            }
        }
    }

    public SnailfishNumber getFirstNumberRight() {
        final SnailfishNumber parent = getParent();
        if (parent == null) {
            return null;
        } else {
            if (parent.getRight().equals(this)) {
                // we're on the right
                return parent.getFirstNumberRight();
            } else {
                // we're on the left, find the right most node of the parent's left
                SnailfishNumber right = parent.getRight();
                while (!right.isValue()) {
                    right = right.getLeft();
                }
                return right;
            }
        }
    }

    public long getMagnitude() {
        if (isValue()) {
            return getValue();
        } else {
            return 3 * getLeft().getMagnitude() + 2 * getRight().getMagnitude();
        }
    }
}
