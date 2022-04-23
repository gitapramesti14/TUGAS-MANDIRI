public class Perhitungan {
import interface.interface;
public class Perhitungan implements interface {
    public int a; //the value of the field perhitungan
    private int b; //the value of the field perhitungan
    private int hasil; //the value of the perhitungan

    //public Perhitungan ( int a, int b, int hasil){
    // this.a =a ;
    // this.b =b ;
    // this.hasil = hasil ;
    // printJudulClass("jadi judul dalam class perhitungan ");
    // }
    public Perhitungan(int a, int b, int hasil){
            this.a = a;
            this.b = b;
            this.hasil = hasil;
            printJudulClass("jadi judul dalam class Perhitungan ");
        }
        private void printJudulClass(String jdl) {System.out.println(jdl);}

        @Override
        public void PrintJudul() {
              // TODO A uto-generation method stub
            System.out.println("Judul Dalam Interface");
        }
        @Override
        public void HitungTambah(){
              // TODO Auto-generated method stub
                this.hasil = this.a + this.b;
        }
        @Override
        public  void Hitungkali(){
        // TODO Auto-generated method stub
        this.hasil = this.a+ this.b;
        }

        //Method Return dari hasil tambah dengan parameter
        public int hsltmbh(int a1, int b2){
        this.a = a1;
        this.b = b2;
        HitungTambah();
        return this.hasil;
        }

        //Method return dari hasil kali
        public int hslkli(int a1,int b2){
        this.a = a1
        this.b = b2
        HitungTambah();
        return this.hasil;
        }

    }

}
