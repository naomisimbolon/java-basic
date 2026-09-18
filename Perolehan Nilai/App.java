import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] simbolUrut = {"PA", "T", "K", "P", "UTS", "UAS"};
        String[] namaLengkap = {"Partisipatif", "Tugas", "Kuis", "Proyek", "UTS", "UAS"};

        int[] bobotHeader = new int[6];
        int totalBobotHeader = 0;
        for (int i = 0; i < 6; i++) {
            bobotHeader[i] = Integer.parseInt(scanner.nextLine().trim());
            totalBobotHeader += bobotHeader[i];
        }

        if (totalBobotHeader != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        int[] totalBobotKomponen = new int[6];
        int[] totalPerolehanKomponen = new int[6];

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().equals("---")) {
                break;
            }

            String[] parts = line.split("\\|", -1);
            if (parts.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = parts[0].trim();
            String bobotStr = parts[1].trim();
            String perolehanStr = parts[2].trim();

            int bobot, perolehan;
            try {
                bobot = Integer.parseInt(bobotStr);
                perolehan = Integer.parseInt(perolehanStr);
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            int index = indexOf(simbolUrut, simbol);
            if (index == -1) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            if (perolehan > bobot) {
                perolehan = bobot;
            }
            if (perolehan < 0) {
                perolehan = 0;
            }

            totalBobotKomponen[index] += bobot;
            totalPerolehanKomponen[index] += perolehan;
        }

        System.out.println("Perolehan Nilai:");

        double nilaiAkhir = 0;
        for (int i = 0; i < 6; i++) {
            int totalX = totalBobotKomponen[i];
            int perolehanX = totalPerolehanKomponen[i];

            int perolehanX100 = (totalX == 0) ? 0 : (perolehanX * 100) / totalX;
            double kontribusiX = (perolehanX100 / 100.0) * bobotHeader[i];

            nilaiAkhir += kontribusiX;

            System.out.println(">> " + namaLengkap[i] + ": " + perolehanX100 + "/100 ("
                    + String.format("%.2f", kontribusiX) + "/" + bobotHeader[i] + ")");
        }

        // Bulatkan ke 2 desimal terlebih dahulu untuk menghindari galat floating-point
        // (mis. 57.0 bisa tersimpan sebagai 56.99999999999999) sebelum dibandingkan ke tabel grade.
        double nilaiAkhirBulat = Math.round(nilaiAkhir * 100.0) / 100.0;

        System.out.println();
        System.out.println(">> Nilai Akhir: " + String.format("%.2f", nilaiAkhirBulat));
        System.out.println(">> Grade: " + getGrade(nilaiAkhirBulat));

        scanner.close();
    }

    private static int indexOf(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private static String getGrade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";

    }
}
