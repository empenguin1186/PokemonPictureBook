package jp.co.pokemon.pokemonApp.controller;

import jp.co.pokemon.pokemonApp.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TopController {

    @Autowired
    TopService service;

    @GetMapping("/top")
    public String gettemp(Model model) {
        List<String> pokeList = service.getPokeList(0, 50);
        model.addAttribute("pokeList", pokeList);
        return "top";
    }

}
