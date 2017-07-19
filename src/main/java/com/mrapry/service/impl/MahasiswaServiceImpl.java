package com.mrapry.service.impl;

import com.mrapry.Util.ReturnMessage;
import com.mrapry.dao.MahasiswaDao;
import com.mrapry.entity.Mahasiswa;
import com.mrapry.service.MahasiswaService;
import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mrapry on 7/19/17.
 */
@Service
public class MahasiswaServiceImpl implements MahasiswaService {
    private final Logger logger = LoggerFactory.getLogger(MahasiswaServiceImpl.class);

    @Autowired
    private MahasiswaDao mahasiswaDao;

    @Override
    public ReturnMessage searchData(String key, String value) {
        if (key.equals("email")) {
            if (mahasiswaDao.findAllByEmail(value) != null) {
                logger.info("PENCARIAN DATA DITEMUKAN BERDASARKAN EMAIL" + value);
                return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA  DITEMUKAN", key.toString() + "|" + value.toString());
            } else {
                logger.info("PENCARIAN TIDAK DATA DITEMUKAN BERDASARKAN EMAIL" + value);
                return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA TIDAK DITEMUKAN", key.toString() + "|" + value.toString());
            }
        } else if (key.equals("hp")) {
            if (mahasiswaDao.findAllByHp(value) != null) {
                logger.info("PENCARIAN DATA DITEMUKAN BERDASARKAN HP " + value);
                return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA  DITEMUKAN", key.toString() + "|" + value.toString());
            } else {
                logger.info("PENCARIAN TIDAK DATA DITEMUKAN BERDASARKAN HP " + value);
                return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA TIDAK DITEMUKAN", key.toString() + "|" + value.toString());
            }
        } else {
            logger.info("GAGAL PENCARIAN DATA KARENA CONTENT SALAH");
            return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "KEY SALAH! SILAHKAN CEK ULANG KEY ANDA", key.toString() + "|" + value.toString());
        }
    }

    @Override
    public ReturnMessage saveMahasiswa(Mahasiswa mahasiswa) {
        try {
            if (mahasiswa.getEmail().equals(null) || mahasiswa.getfName().equals(null) || mahasiswa.getHp().equals(null)) {
                logger.info("TIDAK BISA CREATE USER KARENA DATA USER BELUM LENGKAP");
                return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "TIDAK BISA CREATE USER KARENA DATA USER BELUM LENGKAP", mahasiswa.toString());
            } else {
                if (mahasiswaDao.findAllByEmail(mahasiswa.getEmail()) != null) {
                    logger.info("TIDAK BISA CREATE USER KARENA EMAIL SUDAH ADA");
                    return new ReturnMessage(HttpStatus.CONFLICT.toString(), "EMAIL EXISTING", mahasiswa.toString());
                } else if (mahasiswaDao.findAllByHp(mahasiswa.getHp()) != null) {
                    logger.info("TIDAK BISA CREATE USER KARENA NO HP SUDAH ADA");
                    return new ReturnMessage(HttpStatus.CONFLICT.toString(), "HP EXISTING", mahasiswa.toString());
                } else {
                    mahasiswaDao.save(mahasiswa);
                    logger.info("DATA MAHASISWA BERHASIL DISIMPAN");
                    return new ReturnMessage(HttpStatus.CREATED.toString(), "BERHASIL DISIMPAN", mahasiswa.toString());
                }
            }
        } catch (Exception e) {
            logger.info("TIDAK BISA CREATE USER KARENA DATA USER BELUM LENGKAP");
            return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "TIDAK BISA CREATE USER KARENA DATA USER BELUM LENGKAP", mahasiswa.toString());
        }
    }

    @Override
    public ReturnMessage delMahasiswa(String id) {
        if (mahasiswaDao.findOne(Integer.parseInt(id)) != null) {
            try {
                mahasiswaDao.delete(Integer.parseInt(id));
                logger.info("DATA MAHASISWA BERHASIL DIHAPUS");
                return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA MAHASISWA BERHASIL DIHAPUS", null);
            } catch (CacheException e) {
                logger.info("DATA MAHASISWA TIDAK BERHASIL DIHAPUS");
                return new ReturnMessage(HttpStatus.NOT_ACCEPTABLE.toString(), "DATA MAHASISWA TIDAK BERHASIL DIHAPUS KARENA MASIH BERHUBUNGAN DENGAN MATAKULIAH", null);
            }
        } else {
            logger.info("DATA MAHASISWA TIDAK DITEMUKAN");
            return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA MAHASISWA TIDAK DITEMUKAN", null);
        }
    }

    @Override
    public ReturnMessage editMahasiswa(Mahasiswa mahasiswa) {
        if (mahasiswaDao.findOne(mahasiswa.getId()) != null) {
            Mahasiswa m = new Mahasiswa();
            m.setId(mahasiswa.getId());
            m.setEmail(mahasiswa.getEmail());
            m.setfName(mahasiswa.getfName());
            m.setlName(mahasiswa.getlName());
            m.setHp(mahasiswa.getHp());
            mahasiswaDao.save(m);
            logger.info("DATA MAHASISWA BERHASIL DI UPDATE");
            return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA BASEHASIL DI UPDATE", mahasiswaDao.findOne(mahasiswa.getId()).toString());
        } else {
            logger.info("DATA MAHASISWA TIDAK DITEMUKAN");
            return new ReturnMessage(HttpStatus.NO_CONTENT.toString(), "DATA MAHASISWA TIDAK DITEMUKAN", null);
        }
    }

    @Override
    public ReturnMessage findAll() {
        if (!mahasiswaDao.findAll().isEmpty())
        {
            logger.info("MENGAMBIL DATA MAHASISWA");
            return new ReturnMessage(HttpStatus.ACCEPTED.toString(), "DATA MAHASISWA DITEMUKAN", mahasiswaDao.findAll().toString());
        } else {
            logger.info("DATA MAHASISWA MASIH KOSONG");
            return new ReturnMessage(HttpStatus.NO_CONTENT.toString(),"DATA MAHASISWA MASIH KOSONG", null);
        }
    }
}
