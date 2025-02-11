import java.util.Arrays;
import java.util.Scanner;

class Kamar {
    String tipe;
    int harga;
    boolean tersedia;
    String namaPelanggan;

    Kamar(String tipe, int harga) {
        this.tipe = tipe;
        this.harga = harga;
        this.tersedia = true;
        this.namaPelanggan = "";
    }
}

public class UAS {
    static Scanner scanner = new Scanner(System.in);
    static Kamar[] daftarKamar = {
        new Kamar("Deluxe", 900000),
        new Kamar("Superior", 700000),
        new Kamar("Standard", 600000),
        new Kamar("Grand Suite", 1700000),
        new Kamar("Suite", 1200000)
    };

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Reservasi Hotel ===");
            System.out.println("1. Tampilkan Kamar");
            System.out.println("2. Pesan Kamar");
            System.out.println("3. Cari Kamar");
            System.out.println("4. Urutkan Kamar");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanKamar();
                    break;
                case 2:
                    pesanKamar();
                    break;
                case 3:
                    cariKamar();
                    break;
                case 4:
                    urutkanKamarBerdasarkanHarga();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    static void tampilkanKamar() {
        System.out.println("\nDaftar Kamar:");
        for (Kamar kamar : daftarKamar) {
            if (kamar.tersedia) {
                System.out.println("Tipe: " + kamar.tipe + " | Harga: " + kamar.harga + " | Tersedia");
            } else {
                System.out.println("Tipe: " + kamar.tipe + " | Harga: " + kamar.harga + " | Dipesan oleh: " + kamar.namaPelanggan);
            }
        }
    }

    static void pesanKamar() {
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scanner.next();
        System.out.print("Masukkan tipe kamar yang ingin dipesan: ");
        String tipe = scanner.next();
        boolean ditemukan = false;

        for (Kamar kamar : daftarKamar) {
            if (kamar.tipe.equalsIgnoreCase(tipe) && kamar.tersedia) {
                System.out.print("Masukkan durasi menginap (malam): ");
                int malam = scanner.nextInt();
                int totalHarga = kamar.harga * malam;
                
                if (malam >= 3) {
                    totalHarga *= 0.9; // Diskon 10% untuk 3 malam atau lebih
                    System.out.println("Anda mendapatkan diskon 10%!");
                }
                
                kamar.tersedia = false;
                kamar.namaPelanggan = namaPelanggan;
                boolean pembayaranBerhasil = prosesPembayaran(totalHarga);
                
                if (pembayaranBerhasil) {
                    System.out.println("Kamar " + tipe + " berhasil dipesan untuk " + malam + " malam atas nama " + namaPelanggan + ".");
                } else {
                    kamar.tersedia = true; // Batalkan pemesanan jika pembayaran gagal
                    kamar.namaPelanggan = "";
                    System.out.println("Pemesanan kamar " + tipe + " dibatalkan.");
                }
                
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Maaf, kamar tidak tersedia atau sudah dipesan.");
        }
    }

    static boolean prosesPembayaran(int jumlah) {
        System.out.println("\n=== Proses Pembayaran ===");
        System.out.println("Total yang harus dibayar: " + jumlah);
        System.out.print("Masukkan jumlah uang: ");
        int pembayaran = scanner.nextInt();

        if (pembayaran >= jumlah) {
            int kembalian = pembayaran - jumlah;
            System.out.println("Pembayaran berhasil! Kembalian Anda: " + kembalian);
            return true;
        } else {
            System.out.println("Uang tidak mencukupi, transaksi dibatalkan.");
            return false;
        }
    }

    static void cariKamar() {
        System.out.print("Masukkan tipe kamar yang dicari: ");
        String tipe = scanner.next();
        boolean ditemukan = false;

        for (Kamar kamar : daftarKamar) {
            if (kamar.tipe.equalsIgnoreCase(tipe)) {
                if (kamar.tersedia) {
                    System.out.println("Tipe: " + kamar.tipe + " | Harga: " + kamar.harga + " | Tersedia");
                } else {
                    System.out.println("Tipe: " + kamar.tipe + " | Harga: " + kamar.harga + " | Dipesan oleh: " + kamar.namaPelanggan);
                }
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Kamar tidak ditemukan.");
        }
    }

    static void urutkanKamarBerdasarkanHarga() {
        Arrays.sort(daftarKamar, (k1, k2) -> Integer.compare(k1.harga, k2.harga));
        System.out.println("Kamar telah diurutkan berdasarkan harga.");
        tampilkanKamar();
    }
}
