package com.mrapry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * Created by mrapry on 7/19/17.
 */
@Entity
public class Mahasiswa {

    private int id;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.email}")
    private String email;

    @NotNull
    private String fName;
    private String lName;

    @Column(unique = true)
    private String hp;

    private Set<Matakuliah> matakuliahs;

    public Mahasiswa() {
    }

    public Mahasiswa(String email, String fName, String lName, String hp, Set<Matakuliah> matakuliahs) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.hp = hp;
        this.matakuliahs = matakuliahs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "KRS", joinColumns = @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "matakuliah_id", referencedColumnName = "id"))
    public Set<Matakuliah> getMatakuliahs() {
        return matakuliahs;
    }

    public void setMatakuliahs(Set<Matakuliah> matakuliahs) {
        this.matakuliahs = matakuliahs;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", email:'" + email + '\'' +
                ", fName:'" + fName + '\'' +
                ", lName:'" + lName + '\'' +
                ", hp:'" + hp + '\'' +
//                ", matakuliahs:" + matakuliahs +
                '}';
    }
}
