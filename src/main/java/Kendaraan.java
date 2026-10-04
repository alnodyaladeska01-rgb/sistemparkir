public class Kendaraan {
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

    public String getNomorPlat() { return nomorPlat; }
    public void setNomorPlat(String nomorPlat) { this.nomorPlat = nomorPlat; }
    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }
    public int getJamMasuk() { return jamMasuk; }
    public void setJamMasuk(int jamMasuk) {
        if (jamMasuk >= 0 && jamMasuk <= 24) {
            this.jamMasuk = jamMasuk;
        } else {
            System.out.println("Jam masuk tidak valid! Harus antara 0 - 24.");
        }
    }
    public static int getTotalKendaraanMasuk() { return totalKendaraanMasuk; }

    public void tampilkanInfo() {
        System.out.printf("Plat: %-10s | Merk: %-10s | Masuk Pukul: %02d:00 WIB%n", nomorPlat, merk, jamMasuk);
    }
}