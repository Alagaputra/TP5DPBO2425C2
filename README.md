# TP5DPBO2425C2

/*saya Ajipati Alaga Putra dengan NIM 2409682
mengerjakan TP5 dalam mata kuliah DPBO
untuk keberkahannya maka saya tidak akan melakukan kecurangan
sepertu yang telah di spesifikasikan Aamiin.*/

## Desain Program

### Struktur Kelas
1. **Database.java**
   - Mengatur koneksi ke database MySQL.
   - Menyediakan fungsi `selectQuery()` dan `insertUpdateDeleteQuery()` untuk menjalankan query.

2. **ProductMenu.java**
   - Mengatur tampilan GUI menggunakan Swing.
   - Menyediakan form input produk, tabel data, dan tombol aksi CRUD.
   - Mengelola event tombol: Insert, Update, Delete, Reset, dan Exit.

---

## Struktur Database

**Database:** `db_product`  
**Tabel:** `product`

| Kolom     | Tipe Data  | Keterangan                 |
|------------|-------------|--------------------------|
| id         | VARCHAR(10) | Primary Key (unik)       |
| nama       | VARCHAR(50) | Nama produk              |
| harga      | DOUBLE      | Harga produk             |
| kategori   | VARCHAR(30) | Kategori produk          |
| promo      | BOOLEAN     | Status promo (true/false)|



## Fitur Program

- Menampilkan seluruh data produk dari database.
- Menambahkan data baru (dengan validasi agar **ID tidak duplikat**).
- Mengubah data produk yang sudah ada.
- Menghapus data dari tabel.
- Reset form input agar mudah menambah data baru.
- Menandai produk sebagai **promo** dengan checkbox.
- Menampilkan pesan error/sukses menggunakan `JOptionPane`.

---

## Alur Program

1. Program dijalankan → Membuka koneksi database.
2. Data produk ditampilkan otomatis di `JTable`.
3. Saat menambah data:
   - Cek apakah **ID sudah terdaftar**.
   - Jika ya → tampilkan pesan error.
   - Jika tidak → simpan ke database.
4. Saat memilih baris di tabel:
   - Data akan muncul di form input.
   - User bisa melakukan update atau delete.
5. Tombol Reset mengosongkan seluruh input.
6. Tombol Exit menutup program.

Dokumentasi :

https://github.com/user-attachments/assets/00e28ee5-6969-4061-a106-8767ba630a34



