package com.mrapry.dao;

import com.mrapry.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mrapry on 7/19/17.
 */
public interface MahasiswaDao extends JpaRepository<Mahasiswa,Integer> {

    public Mahasiswa findAllByEmail (String email);
    public Mahasiswa findAllByHp(String hp);
}
