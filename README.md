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
    "message": "Login berhasil",
    "status": "sukses"
  }
  ```

### Get Current User
- **Endpoint:** `/api/pelanggan/current` (GET)
- **Headers:** `X-API-TOKEN: eab25c9b-08d8-424b-825e-1e6e00f80fdb`
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
- **Headers:** `X-API-TOKEN: eab25c9b-08d8-424b-825e-1e6e00f80fdb`
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

## 4. Checkout & Pembayaran

### Update Jumlah Tiket di Cart
- **Endpoint:** `/api/checkout` (PATCH)
- **Headers:** `X-API-TOKEN: eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Request Body:**
  ```json
  {
    "idPembelian": 10,
    "items": [
      { "idPembelianEvent": 20, "jumlahBaru": 3 }
    ]
  }
  ```
- **Response:**
  ```json
  {
    "message": "Jumlah tiket berhasil diperbarui!",
    "status": "sukses"
  }
  ```


### Get Detail Pembayaran
- **Endpoint:** `/api/pembayaran/detail` (GET)
- **Headers:** `X-API-TOKEN: eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "message": {
      "idPembayaran": null,
      "idPembelian": 10,
      "totalHarga": 150000,
      "tanggalBayar": null,
      "status": "Menunggu Pembayaran",
      "detailEvent": [
        { "namaEvent": "Foto Studio", "jumlahTiket": 2, "hargaEvent": 75000 }
      ]
    },
    "status": "sukses"
  }
  ```


### Checkout Pembelian
- **Endpoint:** `/api/checkout` (POST)
- **Request Body:**
  ```json
  {
    "idPembelian": 10
  }
  ```
- **Response:**
  ```json
  {
    "error": false,
    "checkoutWrapper": {
        "idPembelian": 25,
        "totalPembelian": 75000,
        "status": "Pending",
        "tanggalPembelian": "2025-03-15 09:32:29.82",
        "items": [
            {
                "idPembelianEvent": 44,
                "namaEvent": "Foto Studio",
                "jumlah": 1,
                "kategori": "kegiatan"
            }
        ]
    }
  }
  ```

### Pilih Bank
- **Endpoint:** `/api/pembayaran/pilih-bank` (POST)
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
- **Response:**
  ```json
  {
    "error": false,
    "nota": {
        "idPembayaran": 14,
        "idPembelian": 25,
        "bank": "OVO",
        "totalHarga": 75000,
        "tanggalBayar": "15 Mar 2025 10:19",
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

## 6. List event yang diikuti
### List Event yang Diikuti Pelanggan
- **Endpoint:** `/api/pembelian-event` (GET)
- **Headers:** `X-API-TOKEN: eab25c9b-08d8-424b-825e-1e6e00f80fdb`
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
