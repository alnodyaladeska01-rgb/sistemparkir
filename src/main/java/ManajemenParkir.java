import java.util.ArrayList;
import java.util.HashMap;

public class ManajemenParkir {
    // COMPOSITION: fasilitas dibuat langsung di dalam constructor.
    private final FasilitasParkir fasilitas;

    // AGGREGATION: kendaraan dan petugas dibuat dari luar,
    // lalu dimasukkan ke dalam objek ManajemenParkir.
    private final ArrayList<Kendaraan> daftarParkir;
    private final HashMap<String, Petugas> dataPetugas;

    public ManajemenParkir(String namaArea, int kapasitas) {
        this.fasilitas = new FasilitasParkir(namaArea, kapasitas);
        this.daftarParkir = new ArrayList<>();
        this.dataPetugas = new HashMap<>();
    }

    public void tampilkanInfoFasilitas() {
        fasilitas.tampilkanInfo();
    }

    public void tambahKendaraan(Kendaraan kendaraan) {
        if (kendaraan == null) throw new IllegalArgumentException("Objek kendaraan tidak boleh null.");
        daftarParkir.add(kendaraan);
    }

    public ArrayList<Kendaraan> getDaftarParkir() {
        return daftarParkir;
    }

    public Kendaraan cariKendaraan(String plat) throws KendaraanTidakDitemukanException {
        if (plat == null || plat.trim().isEmpty())
            throw new KendaraanTidakDitemukanException("Nomor plat tidak boleh kosong.");
        for (Kendaraan kendaraan : daftarParkir) {
            if (kendaraan.getNomorPlat().equalsIgnoreCase(plat.trim())) return kendaraan;
        }
        throw new KendaraanTidakDitemukanException("Kendaraan dengan plat " + plat + " tidak ditemukan.");
    }

    public void tampilkanSemuaKendaraan() {
        if (daftarParkir.isEmpty()) {
            System.out.println("Belum ada kendaraan terparkir.");
            return;
        }
        for (int i = 0; i < daftarParkir.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarParkir.get(i).tampilkanInfo();
        }
        System.out.println("Jumlah kendaraan: " + daftarParkir.size());
    }

    public void tambahPetugas(Petugas petugas) {
        if (petugas == null) throw new IllegalArgumentException("Objek petugas tidak boleh null.");
        dataPetugas.put(petugas.getId().toUpperCase(), petugas);
    }

    public Petugas cariPetugas(String id) {
        if (id == null || !dataPetugas.containsKey(id.trim().toUpperCase())) return null;
        return dataPetugas.get(id.trim().toUpperCase());
    }

    public void tampilkanSemuaPetugas() {
        System.out.println("Jumlah petugas: " + dataPetugas.size());
        for (String id : dataPetugas.keySet()) dataPetugas.get(id).tampilkanInfo();
    }

    public int getJumlahKendaraan() { return daftarParkir.size(); }
}
