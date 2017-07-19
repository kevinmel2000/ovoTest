###Cara Clean Project
- Masuk kedalam dalam folder project
- Cara Clean dan Build
`mvn clean install`

- Hasil dari clean install nanti kita akan mendapatkan hasil sebuah .jar yang berada didalam target pada project ini.
`ovo/target/ovo-0.0.1-SNAPSHOT.jar`

###Cara Menjalankan dari Project
- Masuk kedalam dalam folder project
- Cara Clean dan Build
`mvn springboot:run -dSkipTests`

- Hasil dari `-dSkipTests` digunakan untuk menskip test yang ada didalam project

- Hasil dari clean install nanti kita akan mendapatkan hasil sebuah .jar yang berada didalam target pada project ini.
`ovo/target/ovo-0.0.1-SNAPSHOT.jar`


###Cara Menjalankan Berdasarkan Jar
- Masuk kedalam dalam folder project
`ovo/target/`
- Cara menjalankan : 
`java -jar ovo-0.0.1-SNAPSHOT.jar`

Spesifikasi Rest API :


    | Post Apps  | 8081   |
    | Database   | Mysql  |

###Documentasi API
URL : `localhost:8081/api/mahasiswa`
```json
{
    "response": "404",
    "message": "DATA MAHASISWA MASIH KOSONG",
    "data": null
}
```
Simpan data

Url : `localhost:8081/api/mahasiswa`
Data yang dikirim
```json
{
	"email":"matius.prastowo@iconpln.co.id",
	"fName":"matius",
	"lName":"apry prastowo",
	"hp":"08579906615",
	"matakuliahs":[
		{"namaMataKuliah":"BASIS DATA"},
		{"namaMataKuliah":"PEMROGRAMAN JAVA"}
	]
}
```
Hasil Keluaran dari simpan
```json
{
    "response": "201",
    "message": "BERHASIL DISIMPAN",
    "data": "{id:1, email:'matius.prastowo@iconpln.co.id', fName:'matius', lName:'apry prastowo', hp:'08579906615'}"
}
```

Hapus data : 

URL : `localhost:8081/api/mahasiswa/del/1`
```json
{
    "response": "202",
    "message": "DATA MAHASISWA BERHASIL DIHAPUS",
    "data": null
}
```

Edit Data : (PUT)
```json
{
    "id":1,
	"email":"matius.prastowo@iconpln.co.id",
	"fName":"matius",
	"lName":"apry prastowo",
	"hp":"085799066159",
	"matakuliahs":[
		{"namaMataKuliah":"IPA"},
		{"namaMataKuliah":"BAHASA INGGRIS"}
	]
}
```

Data yang didapat ketika kita memanggil matakuliah :
URL : `localhost:8081/api/matakuliah`
```json
{
    "response": "202",
    "message": "DATA DITEMUKAN",
    "data": "[{id:5, namaMataKuliah:'BASIS DATA', mahasiswas:[{id:3, email:'matius.prastowo@iconpln.co.id', fName:'matius', lName:'apry prastowo', hp:'085799066159'}]}, {id:6, namaMataKuliah:'PEMROGRAMAN JAVA', mahasiswas:[{id:3, email:'matius.prastowo@iconpln.co.id', fName:'matius', lName:'apry prastowo', hp:'085799066159'}]}]"
}
```

###CATATAN
hasil dari database didapat 
3 table yang di generate berdasarkan relasi manytomany dari entity yang dibuat.
ketiga table tersebut adalah :
- KRS (menampung relasi antara Mahasiswa dan Matakuliah)
- Matakuliah
- Mahasiswa

Mahasiswa dan Matakuliah bisa menggunakan proses `CRUD`

- disini saya seting untuk propertiesnya `spring.jpa.hibernate.ddl-auto=create-drop` sehingga bila kita run project ini makan akan melakukan drop data dan membuat ulang berdasarkan entity yang ada. sehingga ketika dirun awal data dikosongkan. karena digunakan untuk proses test

###PENCARIAN
Baik mahasiswa ataupun Matakuliah terdapat rest untuk pencarian
`localhost:8081/api/matakuliah/{id}/{value}` --> untuk Matakuliah
id -> id, nama

`localhost:8081/api/matakuliah/{id}/{value}` --> untuk Mahasiswa
id -> email,hp

###SQL
Saya sertakan SQL yang dihasilkan untuk project ini
jangan lupa menseting database connection nya di
`ovo/src/main/resources/application.properties