public class Mobil extends Kendaraan implements BisaDiskon, BisaDikenaiDenda {
    private int jumlahPintu;

    public Mobil(String nomorPlat, String merk, int jamMasuk, int jumlahPintu) {
        super(nomorPlat, merk, jamMasuk);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public double hitungBiayaParkir(int lamaParkir) {
        double biaya = Math.max(1, lamaParkir) * 5000.0;
        System.out.println("Abstract method Mobil: biaya parkir mobil = Rp" + (long) biaya);
        return biaya;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[MOBIL] Plat: " + getNomorPlat()
                + " | Merk: " + getMerk()
                + " | Pintu: " + jumlahPintu
                + " | Jam masuk: " + getJamMasuk() + ":00");
    }

    @Override
    public double hitungDiskon(double biayaAwal) {
        return biayaAwal * 0.10;
    }

    @Override
    public void tampilkanInfoDiskon() {
        System.out.println("Interface BisaDiskon: Mobil mendapat diskon 10%.");
    }

    @Override
    public double hitungDenda(int lamaParkir) {
        return lamaParkir > 8 ? 10000.0 : 0.0;
    }

    @Override
    public void tampilkanInfoDenda() {
        System.out.println("Interface BisaDikenaiDenda: Denda mobil Rp10.000 jika parkir lebih dari 8 jam.");
    }
}
