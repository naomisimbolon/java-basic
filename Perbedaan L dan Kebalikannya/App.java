import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int nilaiTengah = hitungNilaiTengah(matrix, n);

        if (n < 3) {
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + nilaiTengah);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + nilaiTengah);
            return;
        }

        int nilaiL = hitungNilaiL(matrix, n);
        int nilaiKebalikanL = hitungNilaiKebalikanL(matrix, n);
        int perbedaan = Math.abs(nilaiL - nilaiKebalikanL);

        int dominan;
        if (perbedaan == 0) {
            dominan = nilaiTengah;
        } else {
            dominan = Math.max(nilaiL, nilaiKebalikanL);
        }

        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }

    private static int hitungNilaiL(int[][] matrix, int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += matrix[i][0];
        }
        for (int j = 1; j <= n - 2; j++) {
            total += matrix[n - 1][j];
        }
        return total;
    }

    private static int hitungNilaiKebalikanL(int[][] matrix, int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += matrix[i][n - 1];
        }
        for (int j = 1; j <= n - 2; j++) {
            total += matrix[0][j];
        }
        return total;
    }

    private static int hitungNilaiTengah(int[][] matrix, int n) {
        if (n % 2 == 1) {
            return matrix[n / 2][n / 2];
        } else {
            int a = n / 2 - 1;
            int b = n / 2;
            return matrix[a][a] + matrix[a][b] + matrix[b][a] + matrix[b][b];
        }
    }
}
