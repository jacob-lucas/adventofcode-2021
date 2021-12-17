package com.jacoblucas.adventofcode2021.day16;

import com.google.common.base.Preconditions;
import org.immutables.value.Value;

/**
 * Every packet begins with a standard header:
 * - the first three bits encode the packet version, and
 * - the next three bits encode the packet type ID.
 *
 * These two values are numbers; all numbers encoded in any packet are represented as binary with the most significant
 * bit first. For example, a version encoded as the binary sequence 100 represents the number 4.
 */
@Value.Immutable
public abstract class Header {
    public static final int LENGTH = 6;

    @Value.Parameter
    public abstract String getBinaryString();

    @Value.Derived
    public int getVersion() {
        return PacketDecoder.binaryToInt(getBinaryString().substring(0, 3));
    }

    @Value.Derived
    public PacketType getPacketType() {
        return PacketType.byId(PacketDecoder.binaryToInt(getBinaryString().substring(3)));
    }

    @Value.Check
    public void check() {
        Preconditions.checkState(getBinaryString().length() == 6, "Header length must be 6 bits");
    }

    @Override
    public String toString() {
        return "V=" + getVersion() + ",T=" + getPacketType().getId() + " (" + getPacketType().name() + ")";
    }
}
