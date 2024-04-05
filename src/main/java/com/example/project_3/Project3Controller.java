package com.example.project_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Project3Controller {
    @Autowired
    AutoRepository autoRepo;
    @Autowired
    OmistajaRepository omistajaRepo;

    @GetMapping("/maintenance")
    public String getMaintenance() {
        return "maintenance";
    }

    @GetMapping("/listing")
    public String list(Model model) {
        model.addAttribute("omistajat", omistajaRepo.findAll());
        return "listing";
    }

    @PostMapping("/maintenance")
    public String create(@RequestParam String etunimi, @RequestParam String sukunimi,
            @RequestParam String valmistenumero,
            @RequestParam String rekisterinumero, @RequestParam String merkki, @RequestParam String malli,
            @RequestParam int valmistusvuosi) {

        Omistaja existingOmistaja = omistajaRepo.findByEtuJaSukunimi(etunimi, sukunimi);

        if (existingOmistaja == null) {
            Omistaja om = new Omistaja();
            om.setEtunimi(etunimi);
            om.setSukunimi(sukunimi);
            omistajaRepo.save(om);
            existingOmistaja = om;
        }
        Auto exRekisteriNumero = autoRepo.findByRekisterinumero(rekisterinumero);

        if (exRekisteriNumero == null) {
            Auto aut = new Auto();
            aut.setValmistenumero(valmistenumero);
            aut.setRekisterinumero(rekisterinumero);
            aut.setMerkki(merkki);
            aut.setMalli(malli);
            aut.setValmistusvuosi(valmistusvuosi);
            autoRepo.save(aut);
            exRekisteriNumero = aut;
        }

        // Lisätään omistajalle auto
        existingOmistaja.getAutot().add(exRekisteriNumero);
        omistajaRepo.save(existingOmistaja);

        // Lisätään autolle omistaja
        exRekisteriNumero.getOmistajat().add(existingOmistaja);
        autoRepo.save(exRekisteriNumero);

        return "redirect:/listing";
    }

}
