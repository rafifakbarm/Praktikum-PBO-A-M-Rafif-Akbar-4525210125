# LAPORAN PRAKTIKUM PEMROGRAMAN BERBASIS OBJEK

| Informasi Praktikan | Keterangan |
| :--- | :--- |
| **Nama** | Muhammad Rafif Akbar |
| **NPM** | 4525210125 |
| **Kelas** | A |
| **Mata Kuliah** | Pemrograman Berbasis Objek (PBO) |
| **Pertemuan** | 06 - Abstract Class, Interface, dan Enum |
| **Tanggal** | Kamis 8 Oktober 2026 |

---

## 1. Implementasi Java

### 1.1. File: `Main.java`

**Penjelasan Kode:**
> Program uji yang menggunakan daftar objek Movable, mengisi bahan bakar melalui kontrak Fuelable, dan menampilkan perilaku enum TipeBahanBakar. Pemanggilan isiPenuh menjadi contoh kesalahan tipe karena Sepeda tidak mengimplementasikan Fuelable.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image.png)
- **After** ![alt text](image-1.png)

### 1.2. File: `Kendaraan.java`

**Penjelasan Kode:**
> Kelas abstrak yang menyimpan data umum kendaraan, menyediakan perilaku bersama, dan mewajibkan kelas turunan mendefinisikan jumlah roda.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-2.png)
- **After** ![alt text](image-3.png)

### 1.3. File: `Mobil.java`

**Penjelasan Kode:**
> Kelas turunan Kendaraan yang mengimplementasikan Movable dan Fuelable, menyediakan informasi kapasitas tangki serta tipe bahan bakar.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-4.png)
- **After** ![alt text](image-5.png)

### 1.4. File: `Sepeda.java`

**Penjelasan Kode:**
> Kelas turunan Kendaraan yang mengimplementasikan Movable. Sepeda dapat bergerak, tetapi tidak memenuhi kontrak Fuelable.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-6.png)
- **After** ![alt text](image-7.png)

### 1.5. File: `Movable.java`

**Penjelasan Kode:**
> Interface yang mendefinisikan kontrak bergerak dan kecepatan maksimum, termasuk default method untuk ringkasan gerak.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-8.png)
- **After** ![alt text](image-9.png)

### 1.6. File: `Fuelable.java`

**Penjelasan Kode:**
> Interface yang mendefinisikan kontrak pengisian bahan bakar, kapasitas tangki, dan tipe bahan bakar.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-10.png)
- **After** ![alt text](image-11.png)

### 1.7. File: `TipeBahanBakar.java`

**Penjelasan Kode:**
> Enum berisi pilihan bahan bakar yang valid beserta label, harga per satuan, perhitungan biaya pengisian, dan informasi ramah lingkungan.

**Bukti Eksekusi (Screenshot):**
- **Before** ![alt text](image-12.png)
- **After** ![alt text](image-13.png)

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