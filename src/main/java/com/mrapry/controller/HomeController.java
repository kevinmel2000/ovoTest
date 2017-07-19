package com.mrapry.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mrapry on 7/19/17.
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "DOKUMENTASI ADA DIDALAM README PROJECT";
    }
}
