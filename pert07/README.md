# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Rafif Akbar |
| **NPM** | 4525210125 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 07 - Komposisi dan Agregasi |
| **Tanggal** | Kamis 15 Oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Main.java`

**Penjelasan Kode:**
> Program uji yang menggunakan daftar objek Movable, mengisi bahan bakar melalui kontrak Fuelable, dan menampilkan perilaku enum TipeBahanBakar. Pemanggilan isiPenuh menjadi contoh kesalahan tipe karena Sepeda tidak mengimplementasikan Fuelable.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image.png)
- **After** ![alt text](image-1.png)

### 1.2. File: `Buku.java`

**Penjelasan Kode:**
> Kelas abstrak yang menyimpan data umum kendaraan, menyediakan perilaku bersama, dan mewajibkan kelas turunan mendefinisikan jumlah roda.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-2.png)
- **After** ![alt text](image-3.png)

### 1.3. File: `Jurusan.java`

**Penjelasan Kode:**
> Kelas turunan Kendaraan yang mengimplementasikan Movable dan Fuelable, menyediakan informasi kapasitas tangki serta tipe bahan bakar.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-4.png)
- **After** ![alt text](image-5.png)

### 1.4. File: `PerpustakaanService.java`

**Penjelasan Kode:**
> Kelas turunan Kendaraan yang mengimplementasikan Movable. Sepeda dapat bergerak, tetapi tidak memenuhi kontrak Fuelable.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-6.png)
- **After** ![alt text](image-7.png)


### Output

**Output Program:**

![alt text](image-14.png)

---

## 2. Implementasi PHP

### 2.1. File: `main.php`

**Penjelasan Kode:**
> Program PHP yang menjalankan objek kendaraan, mengisi bahan bakar melalui tipe Fuelable, menguji enum, dan memperlihatkan penggunaan trait.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-15.png)
- **After** ![alt text](image-16.png)

### 2.2. File: `abstraksi.php`

**Penjelasan Kode:**
> Berisi interface Movable dan Fuelable, enum TipeBahanBakar, serta trait Loggable untuk berbagi perilaku log. File ini juga menjadi tempat definisi abstraksi dan kelas pendukung PHP.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-17.png)
- **After** ![alt text](image-18.png)
### Output

**Output Program:**

![alt text](image-19.png)

---

## 3. Kesimpulan

Abstract class digunakan untuk berbagi keadaan dan perilaku dasar sekaligus memaksa kelas turunan melengkapi method abstrak. Interface mendefinisikan kemampuan yang harus dimiliki kelas, sedangkan enum membatasi pilihan nilai yang sah. Pemisahan kontrak membuat fungsi seperti isiPenuh hanya menerima objek yang benar-benar mendukung pengisian bahan bakar.