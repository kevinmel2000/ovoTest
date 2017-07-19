package com.mrapry.controller;

import com.mrapry.Util.ReturnMessage;
import com.mrapry.entity.Mahasiswa;
import com.mrapry.service.impl.MahasiswaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mrapry on 7/19/17.
 */
@RestController
@RequestMapping(value = "/api/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaServiceImpl mahasiswaService;

    @RequestMapping(method = RequestMethod.GET)
    public ReturnMessage index(){
        return mahasiswaService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ReturnMessage simpanMahasiswa(@RequestBody Mahasiswa mahasiswa){
        return mahasiswaService.saveMahasiswa(mahasiswa);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReturnMessage updateMahasiswa(@RequestBody Mahasiswa mahasiswa){
        return  mahasiswaService.editMahasiswa(mahasiswa);
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    public ReturnMessage delMahasiswa(@PathVariable("id") String id){
        return mahasiswaService.delMahasiswa(id);
    }

    @RequestMapping(value = "/{key}/{value}",method = RequestMethod.GET)
    public ReturnMessage searchData(@PathVariable("key") String key, @PathVariable("value") String value){
        return mahasiswaService.searchData(key, value);
    }





}
