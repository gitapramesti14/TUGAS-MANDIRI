import Model.Perhitungan;

public class JalankanPerhitungan {
    public static void main(String[] args) {
        Perhitungan s = new Perhitungan(4,5,0);


        System.out.println("-----------");
        System.out.println("Hasil Pertambahan :");
        int hasiltambahnya = s.hsltmbh(30,2);
        System.out.println(hasiltambahnya);


        System.out.println("------------");
        System.out.println("Hasil Perkalian :");
        int hasilkalinya = s.hslkli(45,60);
        System.out.println(hasilkalinya);
    }
}
