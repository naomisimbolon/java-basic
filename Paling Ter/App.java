import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Integer> frekuensi = new LinkedHashMap<>();
        boolean adaData = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("---")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            int nilai = Integer.parseInt(line);
            frekuensi.merge(nilai, 1, Integer::sum);
            adaData = true;
        }

        if (!adaData) {
            return;
        }

        Integer tertinggi = null, terendah = null;
        Integer terbanyakNilai = null, tersedikitNilai = null;
        Integer jumlahTertinggiNilai = null, jumlahTerendahNilai = null;

        for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
            int nilai = entry.getKey();
            int freq = entry.getValue();
            long jumlah = (long) nilai * freq;

            if (tertinggi == null || nilai > tertinggi) tertinggi = nilai;
            if (terendah == null || nilai < terendah) terendah = nilai;

            if (terbanyakNilai == null
                    || freq > frekuensi.get(terbanyakNilai)
                    || (freq == frekuensi.get(terbanyakNilai) && nilai > terbanyakNilai)) {
                terbanyakNilai = nilai;
            }

            if (tersedikitNilai == null
                    || freq < frekuensi.get(tersedikitNilai)
                    || (freq == frekuensi.get(tersedikitNilai) && nilai < tersedikitNilai)) {
                tersedikitNilai = nilai;
            }

            long jumlahTerbaik = jumlahTertinggiNilai == null ? 0 : (long) jumlahTertinggiNilai * frekuensi.get(jumlahTertinggiNilai);
            if (jumlahTertinggiNilai == null
                    || jumlah > jumlahTerbaik
                    || (jumlah == jumlahTerbaik && nilai > jumlahTertinggiNilai)) {
                jumlahTertinggiNilai = nilai;
            }

            long jumlahTerkecil = jumlahTerendahNilai == null ? 0 : (long) jumlahTerendahNilai * frekuensi.get(jumlahTerendahNilai);
            if (jumlahTerendahNilai == null
                    || jumlah < jumlahTerkecil
                    || (jumlah == jumlahTerkecil && nilai < jumlahTerendahNilai)) {
                jumlahTerendahNilai = nilai;
            }
        }

        int freqTerbanyak = frekuensi.get(terbanyakNilai);
        int freqTersedikit = frekuensi.get(tersedikitNilai);
        int freqJumlahTertinggi = frekuensi.get(jumlahTertinggiNilai);
        int freqJumlahTerendah = frekuensi.get(jumlahTerendahNilai);

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakNilai + " (" + freqTerbanyak + "x)");
        System.out.println("Tersedikit: " + tersedikitNilai + " (" + freqTersedikit + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiNilai + " * " + freqJumlahTertinggi + " = " + ((long) jumlahTertinggiNilai * freqJumlahTertinggi));
        System.out.println("Jumlah Terendah: " + jumlahTerendahNilai + " * " + freqJumlahTerendah + " = " + ((long) jumlahTerendahNilai * freqJumlahTerendah));
    }
}
