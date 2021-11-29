package ru.job4j.concurrent.completablefuture;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Class RolColSum
 *
 * @author Kseniya Dergunova
 * @since 18.07.2021
 */
public class RolColSum {

    public static class Sums {
        /**
         * Сумма элементов по i-ой строке
         */
        private int rowSum;
        /**
         * Сумма элементов по i-му столбцу
         */
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{" +
                "rowSum=" + rowSum +
                ", colSum=" + colSum +
                '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Sums)) return false;
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    /**
     * Последовательный проход двумерного массива.
     *
     * @param matrix двумерный массив.
     * @return {@link Sums}
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (result[i] == null) {
                    result[i] = new Sums();
                }
                if (result[j] == null) {
                    result[j] = new Sums();
                }
                result[j].colSum += matrix[i][j];
                result[i].rowSum += matrix[i][j];
            }
        }
        return result;
    }

    /**
     * Асинхронный подсчет сумм.
     *
     * @param matrix матрица.
     * @return {@link Sums[]}
     * @throws ExecutionException   исключение выполнения.
     * @throws InterruptedException исключение прерывания.
     */
    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTaskSums(matrix, i));
        }
        for (Integer tmp : futures.keySet()) {
            sums[tmp] = futures.get(tmp).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTaskSums(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            for (int j = 0; j < matrix.length; j++) {
                sums.rowSum += matrix[i][j];
                sums.colSum += matrix[j][i];
            }
            return sums;
        });
    }

    /**
     * Метод подсчета сумм по диагоналям.
     * в 131 строке считаем сумму по главной диагонали.
     * в 133 строке считаем суммы по побочным диагоналям.
     *
     * @param matrix матрица
     * @return массив сумм
     * @throws ExecutionException   исключение выполнения.
     * @throws InterruptedException исключение прерывания.
     */
    public static int[] asyncSumDiagonal(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] sums = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        futures.put(0, getTask(matrix, 0, n - 1, n - 1));
        for (int k = 1; k <= n; k++) {
            futures.put(k, getTask(matrix, 0, k - 1, k - 1));
            if (k < n) {
                futures.put(2 * n - k, getTask(matrix, n - k, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int startRow, int endRow, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col];
                col--;
            }
            return sum;
        });
    }
}
