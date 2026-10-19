public class Petugas {
    private String id;
    private String nama;

    public Petugas(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }

    public void tampilkanInfo() {
        System.out.println("ID Petugas: " + id + " | Nama: " + nama);
    }
}
