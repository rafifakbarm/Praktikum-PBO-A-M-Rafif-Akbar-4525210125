package id.ac.univpancasila.perpustakaan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sesi 11 — unit test pertama Anda.
 *
 * Letakkan berkas ini di src/test/java/id/ac/univpancasila/perpustakaan/
 * Jalankan dengan: mvn test
 */
class RepositoryTest {

    /** Objek sederhana untuk diuji. */
    record Buku(String isbn, String judul) {}

    private Repository<Buku> repo;

    @BeforeEach
    void siapkan() {
        repo = new Repository<>(Buku::isbn);
    }

    @Test
    @DisplayName("Item yang disimpan dapat ditemukan kembali")
    void simpanLaluTemukan() {
        repo.simpan(new Buku("978-1", "PBO"));

        var hasil = repo.cariById("978-1");

        assertTrue(hasil.isPresent());
        assertEquals("PBO", hasil.get().judul());
    }

    @Test
    @DisplayName("Mencari id yang tidak ada mengembalikan Optional kosong")
    void cariYangTidakAda() {
        var hasil = repo.cariById("tidak-ada");

        assertNotNull(hasil);          // tidak boleh null
        assertTrue(hasil.isEmpty());
    }

    @Test
    @DisplayName("Item yang dihapus tidak dapat ditemukan lagi")
    void hapusItem() {
        repo.simpan(new Buku("978-1", "PBO"));

        assertTrue(repo.hapus("978-1"));
        assertTrue(repo.cariById("978-1").isEmpty());
        assertEquals(0, repo.jumlah());
    }

    @Test
    @DisplayName("Repository baru berisi nol item")
    void repositoryKosong() {
        assertEquals(0, repo.jumlah());
        assertTrue(repo.cariSemua().isEmpty());
    }

    @Test
    @DisplayName("Koleksi yang dikembalikan cariSemua tidak dapat diubah dari luar")
    void koleksiTidakBocor() {
        repo.simpan(new Buku("978-1", "PBO"));

        List<Buku> hasil = repo.cariSemua();

        assertThrows(UnsupportedOperationException.class,
                () -> hasil.add(new Buku("978-2", "Basis Data")));
        assertEquals(1, repo.jumlah());   // isi internal tidak berubah
    }

    // ---- TODO 6: pengujian tambahan ----

    @Test
    @DisplayName("Menyimpan item dengan kunci sama menimpa item lama")
    void simpanKunciSamaMenimpa() {
        repo.simpan(new Buku("978-1", "PBO"));
        repo.simpan(new Buku("978-1", "PBO Edisi 2"));

        assertEquals(1, repo.jumlah());
        assertEquals("PBO Edisi 2", repo.cariById("978-1").orElseThrow().judul());
    }

    @Test
    @DisplayName("Menghapus id yang tidak ada mengembalikan false")
    void hapusYangTidakAda() {
        repo.simpan(new Buku("978-1", "PBO"));

        assertFalse(repo.hapus("tidak-ada"));
        assertEquals(1, repo.jumlah());
    }

    @Test
    @DisplayName("cariSemua mempertahankan urutan penyimpanan")
    void cariSemuaMenjagaUrutan() {
        var a = new Buku("978-1", "PBO");
        var b = new Buku("978-2", "Basis Data");
        var c = new Buku("978-3", "Jaringan");
        repo.simpan(a);
        repo.simpan(b);
        repo.simpan(c);

        assertEquals(List.of(a, b, c), repo.cariSemua());
    }

    @Test
    @DisplayName("Menyimpan null melemparkan NullPointerException")
    void simpanNullMelemparException() {
        assertThrows(NullPointerException.class, () -> repo.simpan(null));
        assertEquals(0, repo.jumlah());
    }
}
