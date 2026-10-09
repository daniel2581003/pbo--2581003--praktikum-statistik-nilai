import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class StatistikNilai {
    static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> daftar = new ArrayList<>();
        int nilai;

        System.out.println("===== STATISTIK NILAI KELAS =====");
        System.out.println("Ketik -1 kalau sudah selesai.");

        do {
            // Nomor prompt diambil dari size() + 1, bukan variabel pencacah terpisah
            System.out.print("Nilai ke-" + (daftar.size() + 1) + " : ");
            nilai = input.nextInt();

            if (nilai == SELESAI) {
                continue; // lompat ke kondisi while, kondisinya false, loop berhenti
            }
            if (nilai < 0 || nilai > 100) {
                System.out.println("  Ditolak, harus 0-100");
                continue; // tidak di-add, size() tetap, nomor prompt sama
            }
            daftar.add(nilai);
        } while (nilai != SELESAI);

        System.out.println();
        System.out.println("Nilai tersimpan : " + daftar);
    }
}