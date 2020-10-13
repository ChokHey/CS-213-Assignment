package arch.sm213.machine.student;

import machine.AbstractMainMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainMemoryTest {
    MainMemory test;


    @BeforeEach
    void setup() {
        test = new MainMemory(0);

    }

    @Test
    void testIsisAccessAligned() {
        assertTrue(test.isAccessAligned(0,4));
        assertTrue(test.isAccessAligned(8,4));
        assertFalse(test.isAccessAligned(1,2));
        assertTrue(test.isAccessAligned(4,4));
        assertFalse(test.isAccessAligned(3,4));
    }

    @Test
    void testBytesToInteger() {
        assertEquals(0,test.bytesToInteger((byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00));
        assertEquals(1,test.bytesToInteger((byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01));
        assertEquals(-1610612735,test.bytesToInteger((byte)0xA0,(byte)0x00,(byte)0x00,(byte)0x01));
        assertEquals(208,test.bytesToInteger((byte)0x00,(byte)0x00,(byte)0x00,(byte)0xD0));
        assertEquals(4304,test.bytesToInteger((byte)0x00,(byte)0x00,(byte)0x10,(byte)0xD0));
    }

    @Test
    void testIntegerToBytes() {
        byte[] t1 = test.integerToBytes(0);
        assertEquals(0,t1[0]);
        assertEquals(0,t1[1]);
        assertEquals(0,t1[2]);
        assertEquals(0,t1[3]);

        byte[] t2 = test.integerToBytes(12345);
        assertEquals(0,t2[0]);
        assertEquals(0,t2[1]);
        assertEquals(48,t2[2]);
        assertEquals(57,t2[3]);


    }
    @Test
    public void TestSetGet() throws AbstractMainMemory.InvalidAddressException {
        byte[] regularByes = {1,2,3,4};
        byte[] longBytes = {1,2,3,4,5,6,7,8};
        byte[] shortBytes = {9};

        MainMemory test2 = new MainMemory(128);
        test2.set(0, regularByes);
        bytesEqual(regularByes, test2.get(0, 4));

        test2.set(4, longBytes);
        bytesEqual(longBytes, test2.get(4, 8));

        test2.set(8,longBytes);
        bytesEqual(longBytes,test2.get(8,8));

        test2.set(0, shortBytes);
        bytesEqual(shortBytes, test2.get(0, 1));

        test2.set(127, shortBytes);
        bytesEqual(shortBytes, test2.get(127, 1));

        try {
            test2.set(-1, longBytes);
            fail();
        } catch (Exception e) {}
        try {
            test2.get(-1, 1);
            fail();
        } catch (Exception e) {}
        try {
            test2.set(121, longBytes);
            fail();
        } catch (Exception e) {}
        try {
            test2.get(121, 8);
            fail();
        } catch (Exception e) {}
    }


    private void bytesEqual(byte[] a, byte[] b) {
        assertEquals(a.length, b.length);
        for(int i=0; i < a.length; i++) {
            assertEquals(a[i],b[i]);
        }
    }
}
