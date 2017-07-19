package com.mrapry.service;

import com.mrapry.Util.ReturnMessage;
import com.mrapry.entity.Mahasiswa;

import java.util.List;

/**
 * Created by mrapry on 7/19/17.
 */
public interface MahasiswaService {


    public ReturnMessage searchData(String key, String value);
    public ReturnMessage saveMahasiswa(Mahasiswa mahasiswa);
    public ReturnMessage delMahasiswa(String id);
    public ReturnMessage editMahasiswa(Mahasiswa mahasiswa);
    public ReturnMessage findAll();

}
