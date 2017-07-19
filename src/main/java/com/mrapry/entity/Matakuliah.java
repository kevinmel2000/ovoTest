package com.mrapry.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by mrapry on 7/19/17.
 */
@Entity
public class Matakuliah {

    private int id;

    @NotNull
    @Column(unique = true)
    private String namaMataKuliah;

    private Set<Mahasiswa> mahasiswas;

    public Matakuliah() {
    }

    public Matakuliah(String namaMataKuliah, Set<Mahasiswa> mahasiswas) {
        this.namaMataKuliah = namaMataKuliah;
        this.mahasiswas = mahasiswas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    @ManyToMany(mappedBy = "matakuliahs",fetch = FetchType.LAZY)
    public Set<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", namaMataKuliah:'" + namaMataKuliah + '\'' +
                ", mahasiswas:" + mahasiswas +
                '}';
    }
}
