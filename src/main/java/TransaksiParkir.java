public class TransaksiParkir {
    // ASSOCIATION: transaksi menerima objek Petugas dan Kendaraan
    // sebagai parameter. Keduanya tetap dapat hidup mandiri.
    public void prosesTransaksi(Petugas petugas, Kendaraan kendaraan, int jamKeluar) {
        if (petugas == null || kendaraan == null)
            throw new IllegalArgumentException("Petugas dan kendaraan wajib tersedia.");
        if (jamKeluar < 0 || jamKeluar > 23)
            throw new IllegalArgumentException("Jam keluar harus antara 0 sampai 23.");

        int lamaParkir = jamKeluar - kendaraan.getJamMasuk();
        if (lamaParkir <= 0) lamaParkir += 24;

        System.out.println("\n===== BUKTI TRANSAKSI PARKIR =====");
        System.out.println("Petugas: " + petugas.getNama() + " (" + petugas.getId() + ")");
        kendaraan.tampilkanInfo();

        double total = kendaraan.hitungBiayaParkir(lamaParkir);
        if (kendaraan instanceof BisaDiskon) {
            BisaDiskon diskon = (BisaDiskon) kendaraan;
            diskon.tampilkanInfoDiskon();
            double potongan = diskon.hitungDiskon(total);
            total -= potongan;
            System.out.println("Potongan: Rp" + (long) potongan);
        }
        if (kendaraan instanceof BisaDikenaiDenda) {
            BisaDikenaiDenda denda = (BisaDikenaiDenda) kendaraan;
            denda.tampilkanInfoDenda();
            double biayaDenda = denda.hitungDenda(lamaParkir);
            total += biayaDenda;
            System.out.println("Denda: Rp" + (long) biayaDenda);
        }
        System.out.println("Lama parkir: " + lamaParkir + " jam");
        System.out.println("Total pembayaran: Rp" + (long) total);
        System.out.println("Status transaksi: BERHASIL");
        System.out.println("==================================");
    }
}
