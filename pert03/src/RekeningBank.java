/**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
public class RekeningBank {

    // TODO 1: konstanta bernama, menggantikan angka ajaib
    public static final double BUNGA_TAHUNAN = 0.025;
    public static final double BIAYA_ADMIN = 5000;
    public static final double BATAS_PENARIKAN_SEKALI = 5000000;

    // TODO 2: field statis penghitung jumlah rekening
    private static int jumlahRekening = 0;

    private final String nomor;
    private final String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     * TODO 3: delegasikan ke constructor lengkap.
     */
    public RekeningBank(String nomor, String pemilik) {
        this(nomor, pemilik, 0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        // TODO 4: validasi
        if (nomor == null || nomor.isBlank()) {
            throw new IllegalArgumentException("Nomor rekening tidak boleh kosong");
        }
        if (saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        // TODO 5: naikkan penghitung DI SINI SAJA
        // Karena constructor ringkas mendelegasikan lewat this(...), setiap
        // objek—lewat jalur manapun ia dibuat—pasti melewati baris ini tepat
        // satu kali. Jika counter juga dinaikkan di constructor ringkas,
        // objek yang dibuat lewat constructor ringkas akan terhitung dua kali.
        jumlahRekening++;
    }

    public void setor(double jumlah) {
        // TODO 6
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah setoran harus positif");
        }
        saldo += jumlah;
    }

    public void tarik(double jumlah) {
        // TODO 7
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penarikan harus positif");
        }
        if (jumlah > BATAS_PENARIKAN_SEKALI) {
            throw new IllegalArgumentException(
                "Jumlah penarikan melebihi batas sekali transaksi (Rp" + BATAS_PENARIKAN_SEKALI + ")");
        }
        if (jumlah > saldo) {
            throw new IllegalArgumentException("Saldo tidak mencukupi");
        }
        saldo -= jumlah;
    }

    /** TODO 8: kurangi saldo sebesar biaya admin, tidak boleh sampai negatif. */
    public void potongBiayaAdmin() {
        saldo -= BIAYA_ADMIN;
        if (saldo < 0) {
            saldo = 0;
        }
    }

    /** TODO 9: kembalikan jumlah rekening yang pernah dibuat. */
    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    /**
     * TODO 10: hitung bunga setahun dari pokok (murni fungsi, tanpa state objek).
     */
    public static double bungaSetahun(double pokok) {
        return pokok * BUNGA_TAHUNAN;
    }

    public double getSaldo()  { return saldo; }
    public String getNomor()  { return nomor; }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}