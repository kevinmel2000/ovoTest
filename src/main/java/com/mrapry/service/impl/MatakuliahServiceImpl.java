package com.mrapry.service.impl;

import com.mrapry.Util.ReturnMessage;
import com.mrapry.dao.MatakuliahDao;
import com.mrapry.entity.Matakuliah;
import com.mrapry.service.MatakuliahService;
import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by mrapry on 7/19/17.
 */
@Service
public class MatakuliahServiceImpl implements MatakuliahService {
    private final Logger logger = LoggerFactory.getLogger(MahasiswaServiceImpl.class);

    @Autowired
    private MatakuliahDao matakuliahDao;

    @Override
    public ReturnMessage findAllByNamaMataKuliah(String namaMataKuliah) {

        if (matakuliahDao.findAllByNamaMataKuliah(namaMataKuliah)!=null){
            logger.info("PENCARIAN DATA DITEMUKAN BERDASARKAN NAMA MATAKULIAH " +namaMataKuliah);
            return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA  DITEMUKAN", matakuliahDao.findAllByNamaMataKuliah(namaMataKuliah).toString());
        } else{
            logger.info("PENCARIAN TIDAK DATA DITEMUKAN BERDASARKAN NAMA MATAKULIAH" +namaMataKuliah);
            return new ReturnMessage(HttpStatus.NOT_FOUND.toString(), "DATA TIDAK DITEMUKAN", null);
        }

    }

    @Override
    public ReturnMessage saveMatakuliah(Matakuliah matakuliah) {
        try{
            if (matakuliah.getNamaMataKuliah().equals(null)){
                logger.info("TIDAK BISA CREATE MATAKULIAH KARENA DATA MATAKULIAH BELUM LENGKAP");
                return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "TIDAK BISA CREATE MATAKULIAH KARENA DATA MATAKULIAH BELUM LENGKAP", matakuliah.toString());
            } else{
                if (matakuliahDao.findAllByNamaMataKuliah(matakuliah.getNamaMataKuliah())!=null){
                    logger.info("TIDAK BISA CREATE MATAKULIAH KARENA MATAKULIAH SUDAH ADA");
                    return new ReturnMessage(HttpStatus.CONFLICT.toString(), "EMAIL EXISTING", matakuliah.toString());
                } else{
                    matakuliahDao.save(matakuliah);
                    logger.info("DATA MATAKULIAH BERHASIL DISIMPAN");
                    return new ReturnMessage(HttpStatus.CREATED.toString(), "BERHASIL DISIMPAN", matakuliah.toString());
                }
            }
        }catch (Exception e){
            logger.info("TIDAK BISA CREATE MATAKULIAH KARENA DATA MATAKULIAH BELUM LENGKAP");
            return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "TIDAK BISA CREATE MATAKULIAH KARENA DATA MATAKULIAH BELUM LENGKAP", matakuliah.toString());
        }
    }

    @Override
    public ReturnMessage delMatakuliah(Integer id) {
        if (matakuliahDao.findOne(id)!=null){
            try {
                logger.info("DATA MATAKULIAH BERHASIL DI HAPUS DENGAN ID" + id);
                matakuliahDao.delete(id);
                return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA MATAKULIAH BERHASIL DI HAPUS", null);
            }catch (CacheException e){
                logger.info("DATA MAHASISWA TIDAK BERHASIL DIHAPUS");
                return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "DATA MAHASISWA TIDAK BERHASIL DIHAPUS KARENA MASIH BERHUBUNGAN DENGAN MAHASISWA", null);
            }

        } else{
            logger.info("DATA MATAKULIAH TIDAK DITEMUKAN DENGAN ID "+id);
            return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA TIDAK DITEMUKAN", null);
        }
    }

    @Override
    public ReturnMessage editMatakuliah(Matakuliah matakuliah) {
        if (matakuliahDao.findOne(matakuliah.getId())!=null){
            Matakuliah m = new Matakuliah();
            m.setId(matakuliah.getId());
            m.setNamaMataKuliah(matakuliah.getNamaMataKuliah());
            matakuliahDao.save(m);
            return new ReturnMessage(HttpStatus.ACCEPTED.toString(),"DATA MATAKULIAH BERHASIL DI UPDATE",matakuliah.toString());
        }else{
            return new ReturnMessage(HttpStatus.NO_CONTENT.toString(),"DATA MATAKULIAH TIDAK DITEMUKAN", null);
        }
    }

    @Override
    public ReturnMessage findAll() {
        if (!matakuliahDao.findAll().isEmpty()) {
            logger.info("DATA MATAKULIAH DITEMUKAN");
            return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA DITEMUKAN", matakuliahDao.findAll().toString());
        } else{
            logger.info("DATA MASIH KOSONG");
            return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA KOSONG", null);
        }
    }

    @Override
    public ReturnMessage searchdata(String key, String value) {

        if (key.equals("id")){
            try {
                if (matakuliahDao.findOne(Integer.parseInt(value)) != null) {
                    logger.info("DATA MATAKULIAH DITEMUKAN");
                    return new ReturnMessage(HttpStatus.FOUND.toString(), "DATA DITEMUKAN", matakuliahDao.findOne(Integer.parseInt(value)).toString());
                } else {
                    logger.info("DATA MATAKULIAH TIDAK DITEMUKAN");
                    return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA TIDAK DITEMUKAN", null);
                }
            } catch (NumberFormatException e){
                logger.info("FORMAT INPUT ID TIDAK NUMBER");
                return new ReturnMessage(HttpStatus.METHOD_NOT_ALLOWED.toString(),"FORMAT ID TIDAK DI IJINKAN", null);
            }
        } else if (key.equals("nama")){
            if(matakuliahDao.findAllByNamaMataKuliah(value)!=null){
                logger.info("DATA MATAKULIAH DITEMUKAN");
                return new ReturnMessage(HttpStatus.FOUND.toString(),"DATA DITEMUKAN",matakuliahDao.findOne(Integer.parseInt(value)).toString());
            } else{
                logger.info("DATA MATAKULIAH TIDAK DITEMUKAN");
                return new ReturnMessage(HttpStatus.NO_CONTENT.toString(),"DATA TIDAK DITEMUKAN",null);
            }
        } else{
            logger.info("FORMAT PENCARIAN TIDAK DI IJINKAN");
            return new ReturnMessage(HttpStatus.METHOD_NOT_ALLOWED.toString(),"FORMAT PENCARIAN TIDAK DI IJINKAN", null);
        }
    }
}
