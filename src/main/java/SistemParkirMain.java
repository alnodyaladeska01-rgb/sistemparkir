import java.util.Scanner;

public class SistemParkirMain {

    public static void cariKendaraan(Kendaraan[] listKendaraan, int jumlahData, String cariPlat) {
        boolean ditemukan = false;
        System.out.println("\n--- HASIL PENCARIAN BERDASARKAN PLAT NOMOR ---");
        for (int i = 0; i < jumlahData; i++) {
            if (listKendaraan[i].getNomorPlat().equalsIgnoreCase(cariPlat)) {
                listKendaraan[i].tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Kendaraan dengan plat nomor '" + cariPlat + "' tidak ditemukan.");
        }
    }

    public static void cariKendaraan(Kendaraan[] listKendaraan, int jumlahData, int cariJam) {
        boolean ditemukan = false;
        System.out.println("\n--- HASIL PENCARIAN KENDARAAN MASUK JAM " + cariJam + ":00 ---");
        for (int i = 0; i < jumlahData; i++) {
            if (listKendaraan[i].getJamMasuk() == cariJam) {
                listKendaraan[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada kendaraan yang masuk pada pukul " + cariJam + ":00.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kendaraan[] daftarParkir = new Kendaraan[10];
        int jumlahData = 0;
        boolean isRunning = true;

        System.out.println("==================================================");
        System.out.println("   SELAMAT DATANG DI SISTEM MANAJEMEN PARKIR     ");
        System.out.println("==================================================");

        while (isRunning) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Tambah Kendaraan Masuk (Mobil / Motor)");
            System.out.println("2. Tampilkan Seluruh Daftar Parkir");
            System.out.println("3. Cari Kendaraan (Method Overloading)");
            System.out.println("4. Keluar Sistem");
            System.out.print("Pilih menu (1-4): ");

            if (scanner.hasNextInt()) {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1:
                        if (jumlahData < daftarParkir.length) {
                            System.out.println("\nPilih Jenis Kendaraan:");
                            System.out.println("1. Mobil");
                            System.out.println("2. Motor");
                            System.out.print("Masukkan pilihan jenis (1/2): ");
                            int jenis = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Masukkan Nomor Plat: ");
                            String plat = scanner.nextLine();
                            System.out.print("Masukkan Merk Kendaraan: ");
                            String merk = scanner.nextLine();
                            System.out.print("Masukkan Jam Masuk (0-24): ");
                            int jam = scanner.nextInt();

                            if (jenis == 1) {
                                System.out.print("Masukkan Jumlah Pintu: ");
                                int pintu = scanner.nextInt();
                                scanner.nextLine();
                                daftarParkir[jumlahData] = new Mobil(plat, merk, jam, pintu);
                                jumlahData++;
                                System.out.println("Data Mobil berhasil ditambahkan!");
                            } else if (jenis == 2) {
                                System.out.print("Apakah ada Box Belakang? (true/false): ");
                                boolean box = scanner.nextBoolean();
                                scanner.nextLine();
                                daftarParkir[jumlahData] = new Motor(plat, merk, jam, box);
                                jumlahData++;
                                System.out.println("Data Motor berhasil ditambahkan!");
                            } else {
                                System.out.println("Pilihan jenis kendaraan tidak valid!");
                            }
                        } else {
                            System.out.println("Area parkir penuh!");
                        }
                        break;

                    case 2:
                        System.out.println("\n==================================================");
                        System.out.println("           DAFTAR KENDARAAN DI PARKIRAN           ");
                        System.out.println("==================================================");
                        if (jumlahData == 0) {
                            System.out.println("Belum ada kendaraan yang terdaftar.");
                        } else {
                            for (int i = 0; i < jumlahData; i++) {
                                System.out.print((i + 1) + ". ");
                                daftarParkir[i].tampilkanInfo();
                            }
                            System.out.println("\nTotal kendaraan masuk: " + Kendaraan.getTotalKendaraanMasuk());
                        }
                        break;

                    case 3:
                        if (jumlahData == 0) {
                            System.out.println("Belum ada data untuk dicari.");
                        } else {
                            System.out.println("\nPilih Metode Pencarian (Overloading):");
                            System.out.println("1. Cari berdasarkan Nomor Plat (String)");
                            System.out.println("2. Cari berdasarkan Jam Masuk (Integer)");
                            System.out.print("Pilih (1/2): ");
                            int subPilihan = scanner.nextInt();
                            scanner.nextLine();

                            if (subPilihan == 1) {
                                System.out.print("Masukkan Nomor Plat yang dicari: ");
                                String cariPlat = scanner.nextLine();
                                cariKendaraan(daftarParkir, jumlahData, cariPlat);
                            } else if (subPilihan == 2) {
                                System.out.print("Masukkan Jam Masuk yang dicari: ");
                                int cariJam = scanner.nextInt();
                                scanner.nextLine();
                                cariKendaraan(daftarParkir, jumlahData, cariJam);
                            } else {
                                System.out.println("Pilihan tidak valid.");
                            }
                        }
                        break;

                    case 4:
                        isRunning = false;
                        System.out.println("Terima kasih!");
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } else {
                System.out.println("Input harus berupa angka!");
                scanner.next();
            }
        }
        scanner.close();
    }
}