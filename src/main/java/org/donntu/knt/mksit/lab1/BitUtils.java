package org.donntu.knt.mksit.lab1;

public class BitUtils {
    public static String byteToBits(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }
}
