import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;
import java.util.Collections;

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

        // Kasus tidak ada nilai sah: cetak pesan, bukan error.
        // Harus dicek sebelum daftar.get(0) di bawah, karena get(0) pada daftar kosong error.
        if (daftar.isEmpty()) {
            System.out.println();
            System.out.println("Tidak ada nilai yang dimasukkan.");
            return;
        }

        // Rata-rata: jumlahkan semua nilai, lalu bagi dengan banyaknya nilai
        int total = 0;
        for (int n : daftar) {
            total += n;
        }
        double rataRata = (double) total / daftar.size();


        // Tertinggi dan terendah dimulai dari nilai pertama di daftar (indeks 0),
        // bukan dari 0 atau 100. Kalau terendah dimulai dari 0, tidak akan pernah
        // tergantikan karena tidak ada nilai di bawah 0. Kalau tertinggi dimulai
        // dari 100, sama masalahnya. Nilai pertama pasti anggota daftar, jadi
        // aman dipakai sebagai titik awal untuk keduanya.
        int tertinggi = daftar.get(0);
        int terendah = daftar.get(0);
        for (int n : daftar) {
            if (n > tertinggi) tertinggi = n;
            if (n < terendah) terendah = n;
        }

        // Putaran kedua untuk "di atas rata-rata". Rata-rata baru diketahui setelah
        // semua nilai selesai dibaca dan dijumlahkan. Saat nilai pertama dibaca,
        // rata-ratanya belum ada, jadi perbandingan tidak bisa dilakukan di
        // putaran pertama (saat membaca).
        int diAtasRataRata = 0;
        for (int n : daftar) {
            if (n > rataRata) diAtasRataRata++;
        }

        System.out.println();
        System.out.println("Nilai tersimpan : " + daftar);
        System.out.println("Jumlah          : " + daftar.size());
        System.out.println("Rata-rata       : "
                + String.format(new Locale("id", "ID"), "%.2f", rataRata));
        System.out.println("Tertinggi       : " + tertinggi);
        System.out.println("Terendah        : " + terendah);
        System.out.println("Di atas rata2   : " + diAtasRataRata + " orang");

        // Distribusi grade disimpan di array int[5]. Index = nomor grade:
        // 0 = A (>= 90), 1 = B (>= 80), 2 = C (>= 70), 3 = D (>= 60), 4 = E (< 60)
        int[] jumlahGrade = new int[5];
        for (int n : daftar) {
            if (n >= 90) {
                jumlahGrade[0]++;
            } else if (n >= 80) {
                jumlahGrade[1]++;
            } else if (n >= 70) {
                jumlahGrade[2]++;
            } else if (n >= 60) {
                jumlahGrade[3]++;
            } else {
                jumlahGrade[4]++;
            }
        }

        // Daftar terurut dibuat dari salinan, supaya daftar asli tidak ikut terurut
        ArrayList<Integer> terurut = new ArrayList<>(daftar);
        Collections.sort(terurut);

        // Cetak distribusi dengan loop, bukan lima baris println
        String[] namaGrade = {"A", "B", "C", "D", "E"};
        System.out.print("Distribusi      : ");
        for (int i = 0; i < jumlahGrade.length; i++) {
            System.out.print(namaGrade[i] + "=" + jumlahGrade[i]);
            if (i < jumlahGrade.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.println("Terurut         : " + terurut);
        System.out.println("Urutan asli     : " + daftar);

    }
}