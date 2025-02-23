# Pelanggan API SPEC

## Register SPEC
- Endpoint: POST /api/pelanggan

Request Body:

````json
{
  "nama_pelanggan": "hanna",
  "email": "hanna@gmail.com",
  "password" : "1234",
  "no_hp": "081234567890"
}
````
Response Body (Success):
```json
{
  "data" : "Registrasi sukses"
}
```
Response Body (Fail):
```json
{
  "error" : "nama tidak boleh kosong, ???"
}
```


## Login SPEC
- Endpoint: POST /api/auth/login

Request Body:

````json
{
  "email": "",
  "password" : ""
}
````
Response Body (Success):
```json
{
  "data" : {
    "token": "TOKEN",
    "expiredAt":1221312412412
  }
}
```
Response Body (Fail):
```json
{
  "data" : "Login gagal",
  "error" : "username or password wrong"
}
```