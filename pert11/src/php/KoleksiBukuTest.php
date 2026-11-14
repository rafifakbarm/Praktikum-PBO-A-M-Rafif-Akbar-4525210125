<?php
declare(strict_types=1);

namespace Perpustakaan\Tests;

use PHPUnit\Framework\TestCase;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\BukuTidakDitemukanException;
use Perpustakaan\Model\KoleksiBuku;
use TypeError;

/**
 * Sesi 11 — unit test PHPUnit.
 *
 * Letakkan di tests/ dan pastikan autoload-dev pada composer.json sudah
 * memetakan Perpustakaan\Tests\ ke tests/.
 *
 * Jalankan: ./vendor/bin/phpunit tests
 */
final class KoleksiBukuTest extends TestCase
{
    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        // Dijalankan ulang sebelum SETIAP pengujian.
        $this->koleksi = new KoleksiBuku();
    }

    private function isi(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO', 'teknik', 100000.0));
        $this->koleksi->simpan(new Buku('978-2', 'Basis Data', 'teknik', 120000.0));
        $this->koleksi->simpan(new Buku('978-3', 'Laskar Pelangi', 'fiksi', 80000.0));
    }

    public function testItemYangDisimpanDapatDitemukanKembali(): void
    {
        $buku = new Buku('978-1', 'PBO');
        $this->koleksi->simpan($buku);

        $hasil = $this->koleksi->cariByIsbn('978-1');

        $this->assertNotNull($hasil);
        $this->assertSame('PBO', $hasil->judul);
        $this->assertSame($buku, $hasil);
    }

    public function testMencariIsbnYangTidakAdaMengembalikanNull(): void
    {
        $this->assertNull($this->koleksi->cariByIsbn('tidak-ada'));
    }

    public function testKoleksiBaruBerisiNolItem(): void
    {
        // count() bekerja karena kelasnya implements Countable.
        $this->assertCount(0, $this->koleksi);
        $this->assertSame(0, count($this->koleksi));
        $this->assertSame([], $this->koleksi->semua());
    }

    public function testFilterHanyaMengembalikanYangLolosPredikat(): void
    {
        $this->isi();

        $hasil = $this->koleksi->filter(fn(Buku $b): bool => $b->kategori === 'teknik');

        $this->assertCount(2, $hasil);
        $this->assertInstanceOf(KoleksiBuku::class, $hasil);
        $this->assertCount(3, $this->koleksi, 'koleksi asli tidak boleh berubah');
        // array_values: indeks harus berurutan 0,1 (tidak berlubang)
        $this->assertSame(['PBO', 'Basis Data'], $hasil->map(fn(Buku $b): string => $b->judul));
        $this->assertSame([0, 1], array_keys($hasil->semua()));
    }

    public function testJumlahkanMenghitungTotalDenganBenar(): void
    {
        $this->isi();

        $total = $this->koleksi->jumlahkan(fn(Buku $b): float => $b->harga);

        $this->assertEqualsWithDelta(300000.0, $total, 0.001);
    }

    // ---- TODO 6: skenario tambahan (minimal 5) ----

    public function testSimpanIsbnSamaMenimpaBukuLama(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO'));
        $this->koleksi->simpan(new Buku('978-1', 'PBO Edisi 2'));

        $this->assertCount(1, $this->koleksi);
        $this->assertSame('PBO Edisi 2', $this->koleksi->cariByIsbn('978-1')?->judul);
    }

    public function testJumlahkanKoleksiKosongMenghasilkanNol(): void
    {
        $this->assertSame(0.0, $this->koleksi->jumlahkan(fn(Buku $b): float => $b->harga));
    }

    public function testMapMengubahBentukSetiapItem(): void
    {
        $this->isi();

        $judul = $this->koleksi->map(fn(Buku $b): string => strtoupper($b->judul));

        $this->assertSame(['PBO', 'BASIS DATA', 'LASKAR PELANGI'], $judul);
    }

    public function testKelompokkanBerdasarkanKategori(): void
    {
        $this->isi();

        $grup = $this->koleksi->kelompokkan(fn(Buku $b): string => $b->kategori);

        $this->assertSame(['teknik', 'fiksi'], array_keys($grup));
        $this->assertCount(2, $grup['teknik']);
        $this->assertCount(1, $grup['fiksi']);
        $this->assertSame('Laskar Pelangi', $grup['fiksi'][0]->judul);
    }

    public function testIterasiForeachMengikutiUrutanPenyimpanan(): void
    {
        $this->isi();

        $isbn = [];
        foreach ($this->koleksi as $buku) {
            $isbn[] = $buku->isbn;
        }

        $this->assertSame(['978-1', '978-2', '978-3'], $isbn);
    }

    public function testKonstruktorMenerimaDaftarBuku(): void
    {
        $koleksi = new KoleksiBuku([new Buku('1', 'A'), new Buku('2', 'B')]);

        $this->assertCount(2, $koleksi);
        $this->assertNotNull($koleksi->cariByIsbn('2'));
    }

    public function testAmbilByIsbnYangTidakAdaMelemparException(): void
    {
        $this->expectException(BukuTidakDitemukanException::class);
        $this->expectExceptionMessage('tidak-ada');

        $this->koleksi->ambilByIsbn('tidak-ada');
    }

    public function testSimpanSelainBukuMelemparTypeError(): void
    {
        $this->expectException(TypeError::class);

        // @phpstan-ignore-next-line — sengaja salah tipe
        $this->koleksi->simpan('bukan buku');
    }
}
