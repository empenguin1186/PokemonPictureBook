package jp.co.pokemon.pokemonApp.service;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResourceList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopService {

    static PokeApi pokeApi = new PokeApiClient();

    public List<String> getPokeList(Integer offset, Integer limit) {
        List<NamedApiResource> pokemons = pokeApi.getPokemonSpeciesList(1,1)
                .component4()
                .subList(offset, limit);

        List<String> result = new ArrayList<String>();

        // convert nidran-f to nidranf
        pokemons.stream().forEach(pokemon -> result.add(convertName(pokemon)));

        return result;
    }

    private String convertName(NamedApiResource nar) {

        // リネームを必要とするポケモン
        final Map<String, String> needConvetList = new HashMap<>();
        needConvetList.put("nidoran-f", "nidoranf");
        needConvetList.put("nidoran-m", "nidoranm");

        String result = nar.getName();

        if (needConvetList.containsKey(nar.getName())) {
            result = needConvetList.get(nar.getName());
        }

        return result;
    }

}
