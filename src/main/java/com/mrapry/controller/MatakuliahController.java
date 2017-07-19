package com.mrapry.controller;

import com.mrapry.Util.ReturnMessage;
import com.mrapry.entity.Matakuliah;
import com.mrapry.service.MatakuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mrapry on 7/19/17.
 */
@RestController
@RequestMapping(value = "/api/matakuliah")
public class MatakuliahController {

    @Autowired
    private MatakuliahService matakuliahService;

    @RequestMapping(method = RequestMethod.GET)
    public ReturnMessage index(){
        return matakuliahService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ReturnMessage saveMatakuliah(@RequestBody Matakuliah matakuliah){
        return matakuliahService.saveMatakuliah(matakuliah);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReturnMessage editMatakuliah(@RequestBody Matakuliah matakuliah){
        return matakuliahService.editMatakuliah(matakuliah);
    }

    @RequestMapping(value = "/cari/{key}/{value}",method = RequestMethod.GET)
    public ReturnMessage pencarianMataKuliah(@PathVariable("key") String key, @PathVariable("value") String value){
        return matakuliahService.searchdata(key, value);
    }
    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    public ReturnMessage delMatakuliah(@PathVariable("id") Integer id){
        return matakuliahService.delMatakuliah(id);
    }
}
