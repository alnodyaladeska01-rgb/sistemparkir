public abstract class Kendaraan {
    private String nomorPlat;
    private String merk;
    private int jamMasuk;

    private static int totalKendaraanMasuk = 0;

    public Kendaraan(String nomorPlat, String merk, int jamMasuk) {
        this.nomorPlat = nomorPlat;
        this.merk = merk;
        this.jamMasuk = jamMasuk;
        totalKendaraanMasuk++;
    }

    public String getNomorPlat() {
        return nomorPlat;
    }

    public void setNomorPlat(String nomorPlat) {
        this.nomorPlat = nomorPlat;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(int jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public static int getTotalKendaraanMasuk() {
        return totalKendaraanMasuk;
    }

    public abstract double hitungBiayaParkir(int lamaParkir);

    public void tampilkanInfo() {
        System.out.println("Plat: " + nomorPlat
                + " | Merk: " + merk
                + " | Jam masuk: " + jamMasuk + ":00");
    }
}
