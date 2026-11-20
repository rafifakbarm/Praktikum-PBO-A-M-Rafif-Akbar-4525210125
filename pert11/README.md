# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Rafif Akbar |
| **NPM** | 4525210125 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 11 - Collection, Generics, dan Operasi Fungsional |
| **Tanggal** | Kamis 12 November 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Main.java`

**Penjelasan Kode:**
> Titik masuk program Java. Saat dijalankan, method demoCollections() dipanggil lebih dulu: membuat Repository<Buku> berisi dua buku, lalu mencetak tiga laporan fungsional, yaitu buku yang tersedia (filter), judul digabung dengan pemisah (map + String.join), dan jumlah buku per penulis (kelompokkan). Setelah itu menu interaktif (1. Tambah Buku, 2. Tambah Anggota, 3. Pinjam Buku, 0. Keluar) berjalan dalam perulangan while dengan switch, dan tiap aksi diteruskan ke PerpustakaanService. Pilihan di luar 0-3 ditolak dengan peringatan. Setelah pengguna keluar, eksporLaporan() menulis daftar ISBN, judul, stok, dan status Tersedia/Tidak Tersedia ke laporan_buku.txt memakai try-with-resources agar file otomatis tertutup.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image.png)

### 1.2. File: `Repository.java`

**Penjelasan Kode:**
> Kelas generik Repository<T> yang menyimpan data di LinkedHashMap<String, T> sehingga pencarian berdasarkan kunci tidak lagi linear dan urutan simpan tetap terjaga. Kunci diambil lewat Function<T, String> yang dikirim saat constructor (misalnya Buku::getNomorInduk). simpan(T) menolak item null dan kunci kosong, serta menimpa item lama bila kunci sama. cariById() mengembalikan Optional<T> (bukan null), cariSemua() mengembalikan List.copyOf sehingga tidak bisa diubah dari luar, hapus() mengembalikan boolean, dan jumlah() mengembalikan banyak item. Tiga method fungsional juga disediakan: filter(Predicate), map(Function) dengan tipe hasil generik R, dan kelompokkan(Function) yang memakai Collectors.groupingBy + counting. Karena bertipe T, Repository<Buku> menolak objek selain Buku pada tahap kompilasi.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-16.png)

### 1.3. File: `RepositoryTest.java`

**Penjelasan Kode:**
> Kelas uji JUnit 5 untuk Repository, memakai record Buku sederhana dan @BeforeEach agar setiap uji mulai dari Repository kosong. Ada delapan kasus uji: item yang disimpan dapat ditemukan kembali, mencari id yang tidak ada menghasilkan Optional kosong, item yang dihapus tidak ditemukan lagi, repository baru berisi nol item, koleksi dari cariSemua() tidak bisa diubah (assertThrows UnsupportedOperationException), kunci yang sama menimpa item lama, simpan(null) atau kunci kosong ditolak (IllegalArgumentException), dan pinjam() dengan ISBN tidak terdaftar melempar BukuTidakDitemukanException. Uji terakhir mengubah skenario kegagalan sesi 10 menjadi unit test yang benar-benar dijalankan lewat mvn test.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-2.png)

### 1.4. File: `AnggotaTidakDitemukanException.java`

**Penjelasan Kode:**
> Exception domain yang dilempar saat ID anggota belum terdaftar. Turunan PerpustakaanException dan membawa data idAnggota atribut private final beserta getter getIdAnggota(), sehingga pemanggil bisa mengambil ID penyebab error.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-3.png)

### 1.5. File: `BatasPinjamTerlampauiException.java`

**Penjelasan Kode:**
> Exception yang dilempar saat anggota sudah mencapai batas maksimum peminjaman. Membawa dua data, yaitu idAnggota dan batas, lengkap dengan getter-nya, sehingga pemanggil dapat menampilkan batas tanpa harus mengetahui aturannya sendiri.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-4.png)

### 1.6. File: `BukuSedangDipinjamException.java`

**Penjelasan Kode:**
> Exception yang dilempar saat stok buku habis (semua eksemplar sedang dipinjam). Membawa isbn untuk diolah sistem dan judul agar pesan ke pengguna lebih bermakna, masing-masing dengan getter.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-5.png)

### 1.7. File: `BukuTidakDitemukanException.java`

**Penjelasan Kode:**
> Exception yang dilempar saat ISBN tidak ada di katalog. Membawa data isbn (bukan sekadar teks pesan) dan menyediakan getIsbn(), sehingga pemanggil bisa mengambil kembali penyebab error secara terstruktur. Exception inilah yang diuji pada RepositoryTest.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-6.png)

### 1.8. File: `PerpustakaanException.java`

**Penjelasan Kode:**
> Kelas induk abstract untuk seluruh exception domain perpustakaan, diturunkan dari RuntimeException (unchecked) karena kegagalannya menandakan permintaan yang tidak sah, bukan kondisi luar yang wajar. Dengan induk bersama, semua kegagalan domain bisa ditangkap sekaligus lewat satu blok catch tanpa ikut menelan kesalahan pemrograman lain. Menyediakan dua constructor protected (pesan saja, dan pesan beserta penyebab).

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-7.png)

### 1.9. File: `LibraryService.java`

**Penjelasan Kode:**
> Data disimpan dalam tiga HashMap: stok, judul, dan pinjaman, dengan konstanta BATAS_PINJAM = 3. Pencarian berdasarkan ISBN atau ID anggota cukup lewat kunci Map, tanpa perulangan linear dan tanpa array paralel. Method pinjam() memakai guard clause: tiap syarat (anggota ada, buku ada, stok tersedia, batas pinjam belum tercapai) diperiksa berurutan dan langsung throw exception yang sesuai. Alur utama (kurangi stok, tambah pinjaman) berada di akhir tanpa percabangan bersarang. Method pendukung: tambahBuku, daftarkanAnggota, sisaEksemplar, judulDari, dan getSemuaJudul. Class inilah yang diuji pada kasus pinjam ISBN tidak terdaftar di RepositoryTest.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-8.png)

### 1.10. File: `LibraryServiceKotor.java`

**Penjelasan Kode:**
> Method pinjam() memakai if bersarang empat tingkat dan hanya mengembalikan true/false, sehingga pemanggil tidak tahu penyebab peminjaman gagal. Berkas ini dipertahankan sebagai pembanding dengan LibraryService.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-9.png)

### 1.11. File: `PerpustakaanService.java`

**Penjelasan Kode:**
> Kelas penghubung antara input pengguna dan LibraryService, berisi tiga method statis. tambahAnggota memvalidasi ID tidak boleh kosong. tambahBuku memvalidasi ISBN, judul, dan jumlah eksemplar (harus angka dan lebih dari 0, NumberFormatException ditangkap). pinjamBuku berjalan dalam perulangan sampai pengguna mengetik exit; exception domain ditangkap lewat PerpustakaanException, lalu instanceof dipakai untuk menampilkan detail spesifik (ISBN, ID anggota, judul, atau batas). Exception umum ditangkap sebagai error sistem.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-10.png)

### 1.12. File: `Buku.java`

**Penjelasan Kode:**
> Model buku yang dipakai sebagai tipe Repository<Buku> pada demo di Main. Menyimpan nomorInduk, judul, penulis, dan jumlahEksemplar; constructor menolak jumlah eksemplar negatif. Bab adalah kelas bersarang dengan constructor private sehingga hanya Buku yang bisa membuatnya lewat tambahBab(nomor, judul), dan getBab() mengembalikan List.copyOf agar relasinya benar-benar komposisi. kurangiStok() melempar IllegalStateException bila stok sudah nol, sedangkan tersedia() dipakai sebagai predikat filter pada laporan "buku tersedia".

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-11.png)

### 1.13. File: `Jurusan.java`

**Penjelasan Kode:**
> Contoh agregasi sebagai pembanding komposisi pada Buku. Objek Mahasiswa (kelas bersarang dengan constructor publik) dibuat di luar lalu diterima lewat daftarkan(), sehingga tetap hidup meski jurusan dibubarkan. daftarkan() menolak null dan mahasiswa yang sudah terdaftar, keluarkan() menolak mahasiswa yang belum terdaftar, dan getMahasiswa() mengembalikan salinan tak-berubah (List.copyOf).

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-12.png)

### 1.14. File: `Anggota.java`

**Penjelasan Kode:**
> Turunan Jurusan yang menambah data kodeAnggota, anggotaSejak, dan anggotaPerpus (Mahasiswa). daftarkan() menyimpan mahasiswa beserta tanggal mulai keanggotaan dan menolak mahasiswa kosong. checkMasaKeanggotaan() membandingkan tanggal sekarang dengan anggotaSejak ditambah tiga tahun untuk menentukan status valid atau tidak. getKeanggotaan() mencetak nama, jurusan, kode anggota, dan tanggal bergabung yang diformat dd-MM-yyyy lewat method private formatDateMasaAnggota().

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-13.png)

### 1.15. File: `Format.java`

**Penjelasan Kode:**
> Kelas utilitas dengan constructor private sehingga tidak bisa dibuat objeknya. Method static rupiah(long) memformat angka ke mata uang Rupiah memakai NumberFormat dengan locale id-ID tanpa angka desimal.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-14.png)

### Output

**Output Program:**

![alt text](image-15.png)
---

## 2. Implementasi PHP

### 2.1. File: `main.php`

**Penjelasan Kode:**
> Program utama PHP yang berfungsi sebagai demo. Memuat autoloader Composer, membuat objek LibraryService, mendaftarkan dua buku dan dua anggota, lalu menjalankan tujuh skenario peminjaman dalam foreach. Setiap skenario dibungkus try-catch untuk menangkap PerpustakaanException dan mencetak BERHASIL atau GAGAL beserta alasannya. Selanjutnya "LAPORAN FUNGSIONAL KOLEKSI" membuat KoleksiBuku berisi dua buku lalu mencetak tiga laporan: buku tersedia (filter), judul digabung dengan implode (map), dan jumlah buku per penulis (kelompokkan). Fungsi createReport() menulis laporan buku ke laporan_buku.txt memakai fopen/fwrite, dengan try-catch-finally agar file selalu ditutup lewat fclose meski terjadi error.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-17.png)

### 2.2. File: `KoleksiBuku.php`

**Penjelasan Kode:**
> Typed collection khusus Buku, padanan Repository<T> di Java. Data disimpan dalam array asosiatif $items dengan kunci nomor induk, dan hanya menerima objek Buku (dijaga type hint pada simpan()). Mengimplementasikan IteratorAggregate agar bisa dipakai di foreach dan Countable agar bisa dihitung dengan count(). cariByIsbn() mengembalikan ?Buku lewat operator null-coalescing (??). Method fungsional: filter() memakai array_filter lalu array_values dan mengembalikan KoleksiBuku baru, map() memakai array_map, jumlahkan() memakai array_reduce, dan kelompokkan() mengelompokkan buku berdasarkan kunci hasil callable. Hasil filter dibungkus array_values agar indeks tidak berlubang.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-18.png)

### 2.3. File: `KoleksiBukuTest.php`

**Penjelasan Kode:**
> Kelas uji PHPUnit untuk KoleksiBuku dengan setUp() yang membuat koleksi kosong sebelum tiap uji. Ada delapan kasus uji: item yang disimpan dapat ditemukan kembali, ISBN yang tidak ada mengembalikan null, koleksi baru berisi nol item, filter hanya mengembalikan yang lolos predikat, map mengembalikan daftar judul, jumlahkan menghitung total dengan benar, kunci yang sama menimpa buku lama, dan pinjam() dengan ISBN tidak terdaftar melempar BukuTidakDitemukanException (expectException). Dijalankan dengan ./vendor/bin/phpunit tests.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-19.png)

### 2.4. File: `AnggotaTidakDitemukanException.php`

**Penjelasan Kode:**
> Exception yang dilempar saat ID anggota tidak terdaftar. Berupa final class turunan PerpustakaanException dan memakai constructor property promotion (private readonly string $idAnggota) untuk menyimpan ID, dilengkapi getter getIdAnggota().

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-20.png)

### 2.5. File: `BatasPinjamTerlampauiException.php`

**Penjelasan Kode:**
> Exception yang dilempar saat anggota sudah mencapai batas peminjaman. Membawa $idAnggota dan $batas sebagai properti readonly, dengan pesan yang menyebut ID dan batas buku, serta getter getIdAnggota() dan getBatas().

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-21.png)

### 2.6. File: `BukuSedangDipinjamException.php`

**Penjelasan Kode:**
> Exception yang dilempar saat tidak ada eksemplar tersisa. Membawa $isbn dan $judul sebagai properti readonly agar pesan jelas dan data bisa diambil kembali lewat getIsbn() dan getJudul().

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-22.png)

### 2.7. File: `BukuTidakDitemukanException.php`

**Penjelasan Kode:**
> Exception yang dilempar saat ISBN tidak ada di katalog. Menyimpan $isbn lewat constructor property promotion sebagai properti readonly, dengan getter getIsbn(). Exception inilah yang diuji pada KoleksiBukuTest.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-22.png)

### 2.8. File: `PerpustakaanException.php`

**Penjelasan Kode:**
> Kelas abstract untuk semua exception domain PHP, diturunkan dari RuntimeException. Menjadi satu titik tangkap bersama untuk kegagalan domain. Karena PHP tidak punya checked exception, dokumentasi @throws pada PHPDoc menjadi penting bagi pembaca kode.

**Bukti Eksekusi (Screenshot):**
- **After** ![alt text](image-23.png)

### Output

**Output Program:**

![alt text](image-24.png)

---

## 3. Kesimpulan

Pada pertemuan 11, struktur data manual diganti dengan Collections Framework dan generics. Di Java, Repository<T> menyimpan data dalam Map sehingga pencarian berdasarkan kunci lebih cepat daripada perulangan linear, mengembalikan Optional agar tidak ada pencarian yang mengembalikan null, serta menolak tipe yang salah pada saat kompilasi. Di PHP, KoleksiBuku berperan sebagai typed collection dengan tipe nullable (?Buku) dan operator ??. Operasi fungsional (filter, map, dan pengelompokan) menghasilkan tiga laporan yang sama di kedua bahasa, dengan catatan urutan argumen array_map dan array_filter di PHP berbeda. Unit test dengan JUnit 5 dan PHPUnit membuktikan perilaku kode secara otomatis, termasuk skenario exception dari sesi 10 yang kini benar-benar dijalankan, masing-masing dengan 8 kasus uji.
