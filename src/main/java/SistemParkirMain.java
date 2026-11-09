import java.util.Scanner;

public class SistemParkirMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManajemenParkir sistem = new ManajemenParkir("Parkir Sistem Parkir", 50);
    private static final TransaksiParkir transaksi = new TransaksiParkir();

    public static void main(String[] args) {
        isiDataAwal();
        int pilihan = 0;
        try {
            do {
                tampilkanMenu();
                try {
                    pilihan = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: pilihan menu harus angka.");
                    continue;
                }
                switch (pilihan) {
                    case 1: tambahKendaraan(); break;
                    case 2: sistem.tampilkanSemuaKendaraan(); break;
                    case 3: cariKendaraan(); break;
                    case 4: prosesTransaksi(); break;
                    case 5: menuPetugas(); break;
                    case 6: sistem.tampilkanInfoFasilitas(); break;
                    case 7: System.out.println("Program selesai."); break;
                    default: System.out.println("Pilihan tidak tersedia.");
                }
            } while (pilihan != 7);
        } finally {
            System.out.println("Finally: program ditutup.");
            scanner.close();
        }
    }

    private static void isiDataAwal() {
        // Objek kendaraan dan petugas dibuat di luar ManajemenParkir,
        // kemudian dimasukkan melalui method (AGGREGATION).
        sistem.tambahKendaraan(new Mobil("BE1234AA", "Toyota", 8, 4));
        sistem.tambahKendaraan(new Motor("BE2345BB", "Honda", 9, true));
        sistem.tambahKendaraan(new Truk("BE3456CC", "Mitsubishi", 7, 6));
        sistem.tambahKendaraan(new Mobil("BE4567DD", "Daihatsu", 10, 4));
        sistem.tambahKendaraan(new Motor("BE5678EE", "Yamaha", 11, false));

        sistem.tambahPetugas(new Petugas("P001", "Andi"));
        sistem.tambahPetugas(new Petugas("P002", "Siti"));
        sistem.tambahPetugas(new Petugas("P003", "Budi"));
    }

    private static void tampilkanMenu() {
        System.out.println("\n========== SISTEM PARKIR ==========");
        System.out.println("1. Tambah kendaraan");
        System.out.println("2. Tampilkan semua kendaraan");
        System.out.println("3. Cari kendaraan");
        System.out.println("4. Proses transaksi parkir (Association)");
        System.out.println("5. Kelola data petugas");
        System.out.println("6. Informasi fasilitas parkir (Composition)");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static String inputTeks(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine().trim();
    }

    private static int inputAngka(String pesan) {
        System.out.print(pesan);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private static void tambahKendaraan() {
        try {
            System.out.println("1. Mobil  2. Motor  3. Truk");
            int jenis = inputAngka("Jenis: ");
            String plat = inputTeks("Nomor plat: ");
            String merk = inputTeks("Merk: ");
            int jam = inputAngka("Jam masuk (0-23): ");
            if (plat.isEmpty() || merk.isEmpty()) throw new IllegalArgumentException("Plat dan merk wajib diisi.");
            if (jam < 0 || jam > 23) throw new IllegalArgumentException("Jam harus 0-23.");
            if (jenis == 1) sistem.tambahKendaraan(new Mobil(plat, merk, jam, inputAngka("Jumlah pintu: ")));
            else if (jenis == 2) {
                String box = inputTeks("Memiliki box? (y/n): ");
                sistem.tambahKendaraan(new Motor(plat, merk, jam, box.equalsIgnoreCase("y")));
            } else if (jenis == 3) sistem.tambahKendaraan(new Truk(plat, merk, jam, inputAngka("Jumlah roda: ")));
            else throw new IllegalArgumentException("Jenis kendaraan tidak tersedia.");
            System.out.println("Kendaraan berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            System.out.println("Error: input harus berupa angka.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: proses tambah kendaraan selesai.");
        }
    }

    private static void cariKendaraan() {
        try {
            Kendaraan k = sistem.cariKendaraan(inputTeks("Masukkan plat: "));
            k.tampilkanInfo();
        } catch (KendaraanTidakDitemukanException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: pencarian selesai.");
        }
    }

    private static void prosesTransaksi() {
        try {
            String id = inputTeks("ID petugas: ");
            Petugas petugas = sistem.cariPetugas(id);
            if (petugas == null) throw new IllegalArgumentException("Petugas tidak ditemukan.");
            Kendaraan kendaraan = sistem.cariKendaraan(inputTeks("Plat kendaraan: "));
            int jamKeluar = inputAngka("Jam keluar (0-23): ");
            // ASSOCIATION: dua objek mandiri dikirim ke method transaksi.
            transaksi.prosesTransaksi(petugas, kendaraan, jamKeluar);
        } catch (KendaraanTidakDitemukanException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: jam keluar harus angka.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: proses transaksi selesai.");
        }
    }

    private static void menuPetugas() {
        try {
            System.out.println("1. Tambah petugas  2. Cari petugas  3. Tampilkan semua");
            int pilih = inputAngka("Pilih: ");
            if (pilih == 1) {
                String id = inputTeks("ID petugas: ");
                String nama = inputTeks("Nama: ");
                if (id.isEmpty() || nama.isEmpty()) throw new IllegalArgumentException("ID dan nama wajib diisi.");
                sistem.tambahPetugas(new Petugas(id, nama));
                System.out.println("Petugas berhasil ditambahkan.");
            } else if (pilih == 2) {
                Petugas p = sistem.cariPetugas(inputTeks("ID dicari: "));
                if (p == null) throw new IllegalArgumentException("Petugas tidak ditemukan.");
                p.tampilkanInfo();
            } else if (pilih == 3) sistem.tampilkanSemuaPetugas();
            else System.out.println("Pilihan tidak tersedia.");
        } catch (NumberFormatException e) {
            System.out.println("Error: pilihan harus angka.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: pengelolaan petugas selesai.");
        }
    }
}
