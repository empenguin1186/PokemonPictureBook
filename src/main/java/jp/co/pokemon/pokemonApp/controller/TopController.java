package jp.co.pokemon.pokemonApp.controller;

import jp.co.pokemon.pokemonApp.model.DisplayPoke;
import jp.co.pokemon.pokemonApp.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TopController {

    @Autowired
    TopService service;

    @GetMapping("/poke_book")
    public String displayPoke(@RequestParam(name = "offset", defaultValue = "0") Integer offset,
                              @RequestParam(name = "limit", defaultValue = "50") Integer limit,
                              Model model) {

        List<String> pokeList = service.getPokeList(offset, limit);
        model.addAttribute("pokeList", pokeList);
        return "top";
    }

    @GetMapping("/poke_book/{index}")
    public String detailsPoke(@PathVariable("index") Integer index, Model model) {
        DisplayPoke pokemon = service.getPokeDetails(index);
        model.addAttribute("pokemon", pokemon);
        return "details";
    }

}
