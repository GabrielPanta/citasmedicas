package com.hospital.citasmedicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    

    @GetMapping("estatico")
    public String estatico() {
        return "estatico";
    }

    @GetMapping("addcitas")
    public String addcitas() {
        return "addcitas";
    }
    @GetMapping("dashboardCitas")
    public String dashboardCitas() {
        return "dashboardCitas";
    }
    
    @GetMapping("inicio_sesion")
    public String iniciosesion() {
        return "iniciosesion";
    }
}
