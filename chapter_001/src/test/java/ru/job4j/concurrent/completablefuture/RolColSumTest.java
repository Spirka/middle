package ru.job4j.concurrent.completablefuture;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class RolColSumTest {

    protected int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

    @Test
    public void testSum() {
        RolColSum.Sums[] data = RolColSum.sum(array);
        assertEquals(10, data[0].getRowSum());
        assertEquals(28, data[0].getColSum());
        assertEquals(26, data[1].getRowSum());
        assertEquals(32, data[1].getColSum());
        assertEquals(42, data[2].getRowSum());
        assertEquals(36, data[2].getColSum());
        assertEquals(58, data[3].getRowSum());
        assertEquals(40, data[3].getColSum());
    }

    @Test
    public void testAsyncSum() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] data = RolColSum.asyncSum(array);
        assertEquals(10, data[0].getRowSum());
        assertEquals(28, data[0].getColSum());
        assertEquals(26, data[1].getRowSum());
        assertEquals(32, data[1].getColSum());
        assertEquals(42, data[2].getRowSum());
        assertEquals(36, data[2].getColSum());
        assertEquals(58, data[3].getRowSum());
        assertEquals(40, data[3].getColSum());
    }

    @Test
    public void testSumCount() {
        RolColSum.Sums sums = new RolColSum.Sums();
        sums.setRowSum(10);
        sums.setColSum(28);
        RolColSum.Sums sums1 = new RolColSum.Sums();
        sums1.setRowSum(26);
        sums1.setColSum(32);
        RolColSum.Sums sums2 = new RolColSum.Sums();
        sums2.setRowSum(42);
        sums2.setColSum(36);
        RolColSum.Sums sums3 = new RolColSum.Sums();
        sums3.setRowSum(58);
        sums3.setColSum(40);
        Assert.assertArrayEquals(new RolColSum.Sums[]{sums, sums1, sums2, sums3}, RolColSum.sum(array));
    }
}
