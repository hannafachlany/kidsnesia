# kidsnesia

# Kidsnesia API Documentation

## 1. Authentication

### Register
- **Endpoint:** `/api/pelanggan` (POST)
- **Request Body:**
  ```json
  {
    "namaPelanggan": "Hana",
    "email": "hana@gmail.com",
    "password": "password123",
    "noHpPelanggan": "08123456789"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Registrasi berhasil!",
    "status": "sukses"
  }
  ```

### Login
- **Endpoint:** `/api/auth/login` (POST)
- **Request Body:**
  ```json
  {
    "email": "hana@gmail.com",
    "password": "password123"
  }
  ```
- **Response:**
  ```json
  {
    "error": false,
    "message": "success",
    "loginResult": {
        "email": "kayla@gmail.com",
        "namaPelanggan": "Kayla",
        "token": "d65b0e88-78e8-4a62-817a-528baf812b65"
    }
  }
  ```

### Get Current User
- **Endpoint:** `/api/pelanggan/current` (GET)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "message": {
      "namaPelanggan": "Hana",
      "email": "hana@gmail.com",
      "noHpPelanggan": "08123456789"
    },
    "status": "sukses"
  }
  ```

## 2. Event and produk

### Get Event List
- **Endpoint:** `/api/event` (GET)
- **Response:**
  ```json
  {
    "error": false,
    "message": "Daftar event berhasil diambil",
    "listEvent": [
      {
           "idEvent": 1,
           "namaEvent": "Aku Cinta Indonesia",
           "jadwalEvent": "2025-03-20 14:00:00.0",
           "jadwalEventDay": "Kamis",
           "fotoEvent": "images/1737444813_1.png",
           "deskripsiEvent": "Lestarikan budaya leluhur dengan mengenal pakaian adat, rumah adat, alat musik tradisional, dan menjelajahi Indonesia dengan VR (Virtual Reality)",
           "kuota": 0,
           "kategori": "Kegiatan",
           "hargaEvent": 75000,
           "fullImageUrl": "http://192.168.1.33:8080/images/1737444813_1.png"
      }
    ],
    "status": "sukses"
  }
  ```

### Get Event Detail
- **Endpoint:** `/api/event/{id}` (GET)
- **Response:**
  ```json
  {
    "error": false,
    "message": "Detail event berhasil diambil",
    "detailEvent": {
        "idEvent": 1,
        "namaEvent": "Aku Cinta Indonesia",
        "jadwalEvent": "2025-03-20 14:00:00.0",
        "jadwalEventDay": "Kamis",
        "fotoEvent": "images/1737444813_1.png",
        "deskripsiEvent": "Lestarikan budaya leluhur dengan mengenal pakaian adat, rumah adat, alat musik tradisional, dan menjelajahi Indonesia dengan VR (Virtual Reality)",
        "kuota": 0,
        "kategori": "Kegiatan",
        "hargaEvent": 75000,
        "fullImageUrl": "http://192.168.1.33:8080/images/1737444813_1.png"
    }
  }
  ```
### Get produk all 
- **Endpoint:** `/api/produk` (GET)
- **Response:**
```json
  {
    "error": false,
    "message": "Detail event berhasil diambil",
    "listProduk": [
      {
        "idProduk": 2,
        "namaProduk": "Cap Pink",
        "fotoProduk": "images/cap_pink.jpg"
      }
    ]
}
 ````
## 3. Pembelian

### Buat Pembelian
- **Endpoint:** `/api/pembelian` (POST)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Request Body:**
  ```json
  {
    "items": [
      { 
        "idEvent": 1, 
        "jumlah": 2 
      }
    ]
  }
  ```
- **Response:**
  ```json
  {
    "error": false,
    "pembelianResponse": {
        "idPembelian": 26,
        "totalPembelian": 75000,
        "status": "Pending",
        "tanggalPembelian": "2025-03-15 10:23:32.108",
        "items": [
            {
                "idPembelianEvent": 45,
                "namaEvent": "Foto Studio",
                "jumlah": 1,
                "kategori": "kegiatan"
            }
        ]
    }
  }

  ```

## 4. Pembayaran
### Get Detail Pembayaran
- **Endpoint:** `/api/pembayaran/detail` (GET)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "error": false,
    "detailBayar": {
        "idPembayaran": null,
        "idPembelian": 25,
        "totalHarga": 75000,
        "tanggalBayar": "null",
        "status": "Menunggu Pilihan Bank",
        "detailEvent": [
            {
                "namaEvent": "Foto Studio",
                "jumlahTiket": 1,
                "hargaEvent": 75000
            }
        ]
    }
  }
  ```
  
### Pilih Bank
- **Endpoint:** `/api/pembayaran/pilih-bank` (POST)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Request Body:**
  ```json
  {
    "bank": "BCA"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Bank BCA telah dipilih. Silakan konfirmasi pembayaran!",
    "status": "sukses"
  }
  ```

### Konfirmasi Pembayaran
- **Endpoint:** `/api/pembayaran/konfirmasi-bayar` (POST)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "message": "Pembayaran berhasil! Total yang dibayar: Rp 150000",
    "status": "sukses"
  }
  ```

## 5. Nota Pembelian

### Get Nota Pembelian
- **Endpoint:** `/api/nota-pembelian/{idPembelian}` (GET)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "error": false,
    "nota": {
        "idPembayaran": 16,
        "idPembelian": 29,
        "bank": "OVO",
        "totalHarga": 125000,
        "tanggalBayar": "15 Mar 2025 22:04",
        "namaPelanggan": "Putri Rahayu",
        "email": "putrirahayu@gmail.com",
        "noHpPelanggan": "08123456789",
        "tanggalPembelian": "15 Mar 2025 22:03",
        "detailEvent": [
            {
                "namaEvent": "Foto Studio",
                "jumlahTiket": 1,
                "hargaEvent": 75000,
                "jadwalEvent": "Selasa, 15 Apr 2025"
            },
            {
                "namaEvent": "Sewa Pakaian Adat",
                "jumlahTiket": 1,
                "hargaEvent": 50000,
                "jadwalEvent": "Minggu, 20 Apr 2025"
            }
        ]
    }
  }
  ```

## 6. List event yang diikuti
### List Event yang Diikuti Pelanggan
- **Endpoint:** `/api/pembelian-event` (GET)
- **Headers:** `Authorization: Bearer eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "error": false,
    "detailBeliEvent": [
        {
            "idPembelian": 22,
            "idEvent": 6,
            "namaEvent": "Sewa Pakaian Adat",
            "kategori": "kegiatan",
            "jumlah": 1,
            "hargaTotal": 150000
        },
        {
            "idPembelian": 22,
            "idEvent": 4,
            "namaEvent": "3D Digital Printing",
            "kategori": "Kegiatan",
            "jumlah": 2,
            "hargaTotal": 50000
        }
    ]
  }
  ```
