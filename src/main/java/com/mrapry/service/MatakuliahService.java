package com.mrapry.service;

import com.mrapry.Util.ReturnMessage;
import com.mrapry.entity.Matakuliah;

/**
 * Created by mrapry on 7/19/17.
 */
public interface MatakuliahService {

    public ReturnMessage findAllByNamaMataKuliah(String namaMataKuliah);
    public ReturnMessage saveMatakuliah(Matakuliah matakuliah);
    public ReturnMessage delMatakuliah(Integer id);
    public ReturnMessage editMatakuliah(Matakuliah matakuliah);
    public ReturnMessage findAll();
    public ReturnMessage searchdata(String key, String value);
}
