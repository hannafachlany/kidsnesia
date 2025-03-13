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
    "message": {
      "token": "eab25c9b-08d8-424b-825e-1e6e00f80fdb",
      "tokenExpiredAt": "2025-12-31 23:59:59"
    },
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

## 2. Event

### Get Event List
- **Endpoint:** `/api/event` (GET)
- **Response:**
  ```json
  {
    "message": [
      {
        "idEvent": 1,
        "namaEvent": "Foto Studio",
        "jadwalEvent": "2025-03-20",
        "jadwalEventDay": "Senin",
        "fotoEvent": "foto1.jpg",
        "deskripsiEvent": "Abadikan momen spesial...",
        "kuota": 10,
        "kategori": "Kegiatan",
        "hargaEvent": 75000
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
    "message": {
      "idEvent": 1,
      "namaEvent": "Foto Studio",
      "jadwalEvent": "2025-03-20",
      "jadwalEventDay": "Senin",
      "fotoEvent": "foto1.jpg",
      "deskripsiEvent": "Abadikan momen spesial...",
      "kuota": 10,
      "kategori": "Kegiatan",
      "hargaEvent": 75000
    },
    "status": "sukses"
  }
  ```

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
    "message": {
      "idPembelian": 10,
      "totalPembelian": 150000,
      "status": "Pending",
      "tanggalPembelian": "2025-03-11 10:00:00",
      "items": [
        { "idPembelianEvent": 20, 
          "namaEvent": "Foto Studio", 
          "jumlah": 2, 
          "kategori": "Kegiatan" 
        }
      ]
    },
    "status": "sukses"
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
    "message": {
        "idPembelian": 21,
        "totalPembelian": 150000,
        "status": "Pending",
        "tanggalPembelian": "2025-03-12 10:28:54.131",
        "items": [
            {
                "idPembelianEvent": 38,
                "namaEvent": "Sewa Pakaian Adat",
                "jumlah": 1,
                "kategori": "kegiatan"
            }
        ]
    },
    "status": "sukses"
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
    "message": {
      "idPembayaran": 5,
      "idPembelian": 10,
      "bank": "BCA",
      "totalHarga": 150000,
      "tanggalBayar": "2025-03-11 11:00:00",
      "detailEvent": [
        {
          "namaEvent": "Foto Studio",
          "jumlahTiket": 2,
          "hargaEvent": 150000
        }
      ]
    },
    "status": "sukses"
  }
  ```

## 6. List event yang diikuti
### List Event yang Diikuti Pelanggan
- **Endpoint:** `/api/pembelian-event` (GET)
- **Headers:** `X-API-TOKEN: eab25c9b-08d8-424b-825e-1e6e00f80fdb`
- **Response:**
  ```json
  {
    "message": [
      {
        "idPembelian": 10,
        "idEvent": 1,
        "namaEvent": "Foto Studio",
        "kategori": "Kegiatan",
        "jumlah": 2,
        "hargaTotal": 150000
      }
    ],
    "status": "sukses"
  }
  ```
