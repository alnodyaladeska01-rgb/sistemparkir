public class Motor extends Kendaraan implements BisaDiskon {
    private boolean memilikiBox;

    public Motor(String nomorPlat, String merk, int jamMasuk, boolean memilikiBox) {
        super(nomorPlat, merk, jamMasuk);
        this.memilikiBox = memilikiBox;
    }

    public boolean isMemilikiBox() {
        return memilikiBox;
    }

    public void setMemilikiBox(boolean memilikiBox) {
        this.memilikiBox = memilikiBox;
    }

    @Override
    public double hitungBiayaParkir(int lamaParkir) {
        double biaya = Math.max(1, lamaParkir) * 2000.0;
        System.out.println("Abstract method Motor: biaya parkir motor = Rp" + (long) biaya);
        return biaya;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("[MOTOR] Plat: " + getNomorPlat()
                + " | Merk: " + getMerk()
                + " | Box: " + (memilikiBox ? "Ada" : "Tidak ada")
                + " | Jam masuk: " + getJamMasuk() + ":00");
    }

    @Override
    public double hitungDiskon(double biayaAwal) {
        return biayaAwal * 0.05;
    }

    @Override
    public void tampilkanInfoDiskon() {
        System.out.println("Interface BisaDiskon: Motor mendapat diskon 5%.");
    }
}
