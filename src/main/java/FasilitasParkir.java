public class FasilitasParkir {
    private String namaArea;
    private int kapasitas;

    public FasilitasParkir(String namaArea, int kapasitas) {
        this.namaArea = namaArea;
        this.kapasitas = kapasitas;
    }

    public void tampilkanInfo() {
        System.out.println("Area parkir: " + namaArea);
        System.out.println("Kapasitas area: " + kapasitas + " kendaraan");
    }
}
