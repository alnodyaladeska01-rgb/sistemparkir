public class Motor extends Kendaraan {
    private boolean adaBox;

    public Motor(String nomorPlat, String merk, int jamMasuk, boolean adaBox) {
        super(nomorPlat, merk, jamMasuk);
        this.adaBox = adaBox;
    }

    public boolean isAdaBox() { // Diperbaiki dari kode sebelumnya
        return adaBox;
    }

    public void setAdaBox(boolean adaBox) { this.adaBox = adaBox; }

    @Override
    public void tampilkanInfo() {
        String statusBox = adaBox ? "Ya" : "Tidak";
        System.out.printf("[MOTOR]  Plat: %-10s | Merk: %-10s | Box: %-3s  | Masuk Pukul: %02d:00 WIB%n", 
                getNomorPlat(), getMerk(), statusBox, getJamMasuk());
    }
}