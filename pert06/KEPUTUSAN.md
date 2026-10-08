# **Mengapa Penolakan Saat Kompilasi Menguntungkan?**

- **Type Safety:** Menangkap kesalahan logika atau tipe data lebih awal sebelum program dijalankan (runtime).

- **Mencegah Crash:** Menghindari kegagalan program di lingkungan produksi (seperti ClassCastException).

- **Feedback Cepat:** Developer langsung mengetahui batas kemampuan suatu objek tanpa perlu melakukan manual testing.

## **Output Exception**

## JAVA OUTPUT

```output
[Running] cd "d:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\java\" && javac tempCodeRunnerFile.java && java tempCodeRunnerFile
tempCodeRunnerFile.java:1: error: invalid method declaration; return type required
isiPenuh(sepeda);
^
tempCodeRunnerFile.java:1: error: <identifier> expected
isiPenuh(sepeda);
               ^
2 errors
```

---

## PHP OUTPUT

```output
=== Semua Movable ===
[11:05:40] Mobil: melaju di jalan raya
    kecepatan maksimum 180 km/jam
Sepeda sedang dikayuh.
    kecepatan maksimum 25 km/jam

=== Hanya yang Fuelable ===
[11:05:40] Mobil: mengisi bahan bakar
  Diisi penuh Bensin — biaya Rp540.000
PHP Fatal error:  Uncaught TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given, called in D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php on line 31 and defined in D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php:7
Stack trace:
#0 D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php(31): isiPenuh(Object(Sepeda))
#1 {main}
  thrown in D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php on line 7

Fatal error: Uncaught TypeError: isiPenuh(): Argument #1 ($kendaraan) must be of type Fuelable, Sepeda given, called in D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php on line 31 and defined in D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php:7
Stack trace:
#0 D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php(31): isiPenuh(Object(Sepeda))
#1 {main}
  thrown in D:\Praktikum-PBO-A-M Rafif Akbar-4525210125\pert06\src\php\main.php on line 7
```