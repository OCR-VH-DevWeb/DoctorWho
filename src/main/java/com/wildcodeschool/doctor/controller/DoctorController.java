package com.wildcodeschool.doctor.controller;

import com.wildcodeschool.doctor.model.Doctor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class DoctorController {

    // modification de la route /doctor/ pour accepter la variable {number}
    @GetMapping("/doctor/{number}")
    @ResponseBody
    public Doctor doctor(@PathVariable int number) {
        // pour numéro 13, renvoie les infos sur l'incarnation du Docteur au format json
        // {"number": 13, "name": "Jodie Whittaker"}
        // retourne une instance de Doctor
        if (number == 13) {
            return new Doctor(13, "Jodie Whittaker");
            // renvoie pour les autres numéros valides (1 à 12) un statut 303
        } else if (number >= 1 && number <= 12) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, "Voir autres");
            // sinon renvoie un statut 404 si le numéro n'est pas valide avec comme
            // information Impossible de récupérer l'incarnation {number}
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Impossible de récupérer l'incarnation " + number + ".");
        }
    }
}
