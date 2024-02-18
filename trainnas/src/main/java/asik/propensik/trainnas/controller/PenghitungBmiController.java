package asik.propensik.trainnas.controller;

import asik.propensik.trainnas.model.PenghitungBmi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PenghitungBmiController {
    private String getPenghitungBmiPage(String beratBadan, String tinggiBadan, Model model) {

        if (beratBadan.isEmpty() || tinggiBadan.isEmpty() || beratBadan.equals("0") || tinggiBadan.equals("0")) {
            model.addAttribute("msg", "Isi berat dan tinggi badan anda dengan benar!");
        } else {
            final PenghitungBmi penghitungBmi = new PenghitungBmi(beratBadan, tinggiBadan);
            model.addAttribute("penghitungBmi", penghitungBmi);
        }
        return "PenghitungBmiPage.html";
    }

    @GetMapping(value = "/penghitung-BMI")
    public String penghitungBmiRequestParam(@RequestParam(value = "berat") String beratBadan,
            @RequestParam(value = "tinggi") String tinggiBadan, Model model) {
        return getPenghitungBmiPage(beratBadan, tinggiBadan, model);
    }

    @GetMapping(value = "/penghitung-BMI/{berat}/{tinggi}")
    public String penghitungBmiWithPathVariable(@PathVariable(value = "berat") String beratBadan,
            @PathVariable(value = "tinggi") String tinggiBadan, Model model) {
        return getPenghitungBmiPage(beratBadan, tinggiBadan, model);
    }
}
