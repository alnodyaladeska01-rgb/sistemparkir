public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String nomorPlat, String merk, int jamMasuk, int jumlahPintu) {
        super(nomorPlat, merk, jamMasuk);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() { return jumlahPintu; }
    public void setJumlahPintu(int jumlahPintu) { this.jumlahPintu = jumlahPintu; }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[MOBIL]  Plat: %-10s | Merk: %-10s | Pintu: %d   | Masuk Pukul: %02d:00 WIB%n", 
                getNomorPlat(), getMerk(), jumlahPintu, getJamMasuk());
    }
}