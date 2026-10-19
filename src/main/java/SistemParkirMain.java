import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SistemParkirMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Kendaraan> daftarParkir = new ArrayList<>();
    private static final HashMap<String, Petugas> dataPetugas = new HashMap<>();

    public static void main(String[] args) {
        tambahDataAwal();
        int pilihan = 0;
        try {
            do {
                tampilkanMenu();
                try {
                    pilihan = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: pilihan menu harus berupa angka.");
                    continue;
                }

                switch (pilihan) {
                    case 1: tambahKendaraan(); break;
                    case 2: tampilkanSemuaKendaraan(); break;
                    case 3: cariKendaraanMenu(); break;
                    case 4: prosesBiayaParkir(); break;
                    case 5: simulasiInterface(); break;
                    case 6: menuPetugas(); break;
                    case 7: System.out.println("Terima kasih telah menggunakan Sistem Parkir."); break;
                    default: System.out.println("Pilihan tidak tersedia.");
                }
            } while (pilihan != 7);
        } finally {
            System.out.println("Finally: program selesai, scanner ditutup.");
            scanner.close();
        }
    }

    private static void tambahDataAwal() {
        daftarParkir.add(new Mobil("BE1234AA", "Toyota", 8, 4));
        daftarParkir.add(new Motor("BE2345BB", "Honda", 9, true));
        daftarParkir.add(new Truk("BE3456CC", "Mitsubishi", 7, 6));
        daftarParkir.add(new Mobil("BE4567DD", "Daihatsu", 10, 4));
        daftarParkir.add(new Motor("BE5678EE", "Yamaha", 11, false));
        dataPetugas.put("P001", new Petugas("P001", "Andi"));
        dataPetugas.put("P002", new Petugas("P002", "Siti"));
        dataPetugas.put("P003", new Petugas("P003", "Budi"));
    }

    private static void tampilkanMenu() {
        System.out.println("\\n========== SISTEM PARKIR ==========");
        System.out.println("1. Tambah kendaraan");
        System.out.println("2. Tampilkan semua kendaraan");
        System.out.println("3. Cari kendaraan berdasarkan plat");
        System.out.println("4. Proses biaya parkir");
        System.out.println("5. Simulasi interface dan instanceof");
        System.out.println("6. Kelola data petugas (HashMap)");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static String bacaTeks(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine().trim();
    }

    private static int bacaInt(String pesan) {
        System.out.print(pesan);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private static void tambahKendaraan() {
        try {
            System.out.println("1. Mobil  2. Motor  3. Truk");
            int jenis = bacaInt("Pilih jenis: ");
            String plat = bacaTeks("Nomor plat: ");
            String merk = bacaTeks("Merk: ");
            int jam = bacaInt("Jam masuk (0-23): ");
            if (plat.isEmpty() || merk.isEmpty()) throw new IllegalArgumentException("Plat dan merk tidak boleh kosong.");
            if (jam < 0 || jam > 23) throw new IllegalArgumentException("Jam harus 0 sampai 23.");

            switch (jenis) {
                case 1: daftarParkir.add(new Mobil(plat, merk, jam, bacaInt("Jumlah pintu: "))); break;
                case 2:
                    String box = bacaTeks("Memiliki box? (y/n): ");
                    daftarParkir.add(new Motor(plat, merk, jam, box.equalsIgnoreCase("y")));
                    break;
                case 3: daftarParkir.add(new Truk(plat, merk, jam, bacaInt("Jumlah roda: "))); break;
                default: throw new IllegalArgumentException("Jenis kendaraan tidak tersedia.");
            }
            System.out.println("Kendaraan berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            System.out.println("Error: masukkan angka yang valid.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: proses tambah kendaraan selesai.");
        }
    }

    private static void tampilkanSemuaKendaraan() {
        if (daftarParkir.isEmpty()) {
            System.out.println("Belum ada kendaraan.");
            return;
        }
        System.out.println("--- DAFTAR KENDARAAN ---");
        for (int i = 0; i < daftarParkir.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarParkir.get(i).tampilkanInfo();
        }
        System.out.println("Jumlah kendaraan: " + daftarParkir.size());
    }

    private static Kendaraan cariKendaraan(String plat) throws KendaraanTidakDitemukanException {
        if (plat == null || plat.trim().isEmpty())
            throw new KendaraanTidakDitemukanException("Nomor plat tidak boleh kosong.");
        for (Kendaraan k : daftarParkir)
            if (k.getNomorPlat().equalsIgnoreCase(plat.trim())) return k;
        throw new KendaraanTidakDitemukanException("Kendaraan dengan plat " + plat + " tidak ditemukan.");
    }

    private static void cariKendaraanMenu() {
        try {
            Kendaraan hasil = cariKendaraan(bacaTeks("Masukkan plat yang dicari: "));
            System.out.println("Kendaraan ditemukan:");
            hasil.tampilkanInfo();
        } catch (KendaraanTidakDitemukanException e) {
            System.out.println("Error pencarian: " + e.getMessage());
        } finally {
            System.out.println("Finally: pencarian kendaraan selesai.");
        }
    }

    private static void prosesBiayaParkir() {
        try {
            Kendaraan k = cariKendaraan(bacaTeks("Masukkan plat: "));
            int keluar = bacaInt("Jam keluar (0-23): ");
            if (keluar < 0 || keluar > 23) throw new IllegalArgumentException("Jam keluar harus 0-23.");
            int lama = keluar - k.getJamMasuk();
            if (lama <= 0) lama += 24;
            double total = k.hitungBiayaParkir(lama);
            if (k instanceof BisaDiskon) {
                BisaDiskon d = (BisaDiskon) k;
                d.tampilkanInfoDiskon();
                total -= d.hitungDiskon(total);
            }
            if (k instanceof BisaDikenaiDenda) {
                BisaDikenaiDenda d = (BisaDikenaiDenda) k;
                d.tampilkanInfoDenda();
                total += d.hitungDenda(lama);
            }
            System.out.println("Lama parkir: " + lama + " jam");
            System.out.println("Total biaya: Rp" + (long) total);
        } catch (KendaraanTidakDitemukanException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: jam keluar harus berupa angka.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: perhitungan biaya selesai.");
        }
    }

    private static void simulasiInterface() {
        for (Kendaraan k : daftarParkir) {
            k.tampilkanInfo();
            if (k instanceof BisaDiskon) ((BisaDiskon) k).tampilkanInfoDiskon();
            if (k instanceof BisaDikenaiDenda) ((BisaDikenaiDenda) k).tampilkanInfoDenda();
            System.out.println();
        }
    }

    private static void menuPetugas() {
        try {
            System.out.println("1. Tambah petugas  2. Cari petugas  3. Tampilkan semua");
            int opsi = bacaInt("Pilih: ");
            if (opsi == 1) {
                String id = bacaTeks("ID petugas: ").toUpperCase();
                String nama = bacaTeks("Nama petugas: ");
                if (id.isEmpty() || nama.isEmpty()) throw new IllegalArgumentException("ID dan nama wajib diisi.");
                dataPetugas.put(id, new Petugas(id, nama));
                System.out.println("Petugas berhasil ditambahkan.");
            } else if (opsi == 2) {
                String id = bacaTeks("ID yang dicari: ").toUpperCase();
                if (!dataPetugas.containsKey(id)) throw new IllegalArgumentException("Petugas tidak ditemukan.");
                dataPetugas.get(id).tampilkanInfo();
            } else if (opsi == 3) {
                System.out.println("Jumlah petugas: " + dataPetugas.size());
                for (String id : dataPetugas.keySet()) dataPetugas.get(id).tampilkanInfo();
            } else {
                System.out.println("Pilihan tidak tersedia.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: pilihan harus angka.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally: pengelolaan petugas selesai.");
        }
    }
}
