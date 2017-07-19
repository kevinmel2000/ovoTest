package com.mrapry.dao;

import com.mrapry.entity.Matakuliah;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mrapry on 7/19/17.
 */
public interface MatakuliahDao extends JpaRepository<Matakuliah, Integer>{

    public Matakuliah findAllByNamaMataKuliah(String namaMataKuliah);
}
