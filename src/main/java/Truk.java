public class Truk extends Kendaraan implements BisaDikenaiDenda {
    private int jumlahRoda;

    public Truk(String nomorPlat, String merk, int jamMasuk, int jumlahRoda) {
        super(nomorPlat, merk, jamMasuk);
        this.jumlahRoda = jumlahRoda;
    }

    public int getJumlahRoda() {
        return jumlahRoda;
    }

    public void setJumlahRoda(int jumlahRoda) {
        this.jumlahRoda = jumlahRoda;
    }

    @Override
    public double hitungBiayaParkir(int lamaParkir) {
        double biaya = Math.max(1, lamaParkir) * 8000.0;
        System.out.println("Abstract method Truk: biaya parkir truk = Rp" + (long) biaya);
        return biaya;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[TRUK] Plat: " + getNomorPlat()
                + " | Merk: " + getMerk()
                + " | Roda: " + jumlahRoda
                + " | Jam masuk: " + getJamMasuk() + ":00");
    }

    @Override
    public double hitungDenda(int lamaParkir) {
        return lamaParkir > 10 ? 20000.0 : 0.0;
    }

    @Override
    public void tampilkanInfoDenda() {
        System.out.println("Interface BisaDikenaiDenda: Denda truk Rp20.000 jika parkir lebih dari 10 jam.");
    }
}
