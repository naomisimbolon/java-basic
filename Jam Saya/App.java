import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String jamAwalInput = scanner.nextLine();
        String[] parts = jamAwalInput.split(":");

        if (parts.length != 2) {
            System.out.println("Jam tidak valid");
            return;
        }

        int jam, menit;
        try {
            jam = Integer.parseInt(parts[0].trim());
            menit = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            System.out.println("Jam tidak valid");
            return;
        }

        if (jam < 0 || jam > 23 || menit < 0 || menit > 59) {
            System.out.println("Jam tidak valid");
            return;
        }

        int totalMenitAwal = jam * 60 + menit;
        int totalMenit = totalMenitAwal;
        int totalGeser = 0;
        int pergantianHari = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            if (line.length() < 2 || (line.charAt(0) != '+' && line.charAt(0) != '-')) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int n;
            try {
                n = Integer.parseInt(line.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int geser = (line.charAt(0) == '+') ? n : -n;

            totalMenit += geser;
            totalGeser += geser;

            while (totalMenit >= 1440) {
                totalMenit -= 1440;
                pergantianHari++;
            }
            while (totalMenit < 0) {
                totalMenit += 1440;
                pergantianHari++;
            }
        }

        int jamAkhir = totalMenit / 60;
        int menitAkhir = totalMenit % 60;

        String totalMenitStr;
        if (totalGeser > 0) {
            totalMenitStr = "+" + totalGeser;
        } else {
            totalMenitStr = String.valueOf(totalGeser);
        }

        System.out.println("Jam Awal: " + String.format("%02d:%02d", jam, menit));
        System.out.println("Jam Akhir: " + String.format("%02d:%02d", jamAkhir, menitAkhir));
        System.out.println("Total Menit: " + totalMenitStr);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}
