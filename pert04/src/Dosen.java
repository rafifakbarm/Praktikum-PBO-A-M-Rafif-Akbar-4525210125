public class Dosen extends PegawaiTetap{
    private double tunjanganFungsional;

    public Dosen (String nip, String nama, double gajiPokok, int masaKerja, double tunjanganfungsional){
        super (nip, nama, gajiPokok, masaKerja );

        this.tunjanganFungsional = tunjanganfungsional;
    }

    @Override
    public double hitungGaji (){
        return super.hitungGaji() + this.tunjanganFungsional;
    }

    @Override 
    public String jenis(){
        return "DOSEN";
    }

    @Override
    protected int getMasaKerjaTahun(){
        return super.getMasaKerjaTahun();
    }
}