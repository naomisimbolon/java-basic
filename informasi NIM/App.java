import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nim = scanner.nextLine();

        if (nim.length() != 8) {
            System.out.println("NIM harus 8 karakter");
            return;
        }

        String prefix = nim.substring(0, 3);
        String prodi = getProdi(prefix);

        if (prodi == null) {
            System.out.println("Kode tidak tersedia");
            return;
        }

        String kodeAngkatan = nim.substring(3, 5);
        int angkatan = Integer.parseInt("20" + kodeAngkatan);

        String kodeUrutan = nim.substring(5, 8);
        int urutan = Integer.parseInt(kodeUrutan);

        System.out.println("Inforamsi NIM " + nim + ": ");
        System.out.println(">> Program Studi: " + prodi);
        System.out.println(">> Angkatan: " + angkatan);
        System.out.println(">> Urutan: " + urutan);
    }

    private static String getProdi(String prefix) {
        switch (prefix) {
            case "11S": return "Sarjana Informatika";
            case "12S": return "Sarjana Sistem Informasi";
            case "13S": return "Sarjana Teknik Elektro";
            case "21S": return "Sarjana Manajemen Rekayasa";
            case "22S": return "Sarjana Teknik Metalurgi";
            case "31S": return "Sarjana Teknik Bioproses";
            case "32S": return "Sarjana Bioteknologi";
            case "114": return "Diploma 4 Teknologi Rekasaya Perangkat Lunak";
            case "113": return "Diploma 3 Teknologi Informasi";
            case "133": return "Diploma 3 Teknologi Komputer";
            default: return null;
        }
    }
}
