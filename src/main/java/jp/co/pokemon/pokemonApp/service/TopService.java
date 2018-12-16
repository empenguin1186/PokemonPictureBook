package jp.co.pokemon.pokemonApp.service;

import jp.co.pokemon.pokemonApp.model.DisplayPoke;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopService {

    static PokeApi pokeApi = new PokeApiClient();

    static private Integer THE_NUM_OF_POKEMON = 719;

    public List<String> getPokeList(Integer offset, Integer limit) {

        Integer end = limit < THE_NUM_OF_POKEMON + 1 ? limit : THE_NUM_OF_POKEMON;
        List<NamedApiResource> pokemons = pokeApi.getPokemonSpeciesList(1,1)
                .component4()
                .subList(offset, end);

        List<String> result = new ArrayList<String>();

        pokemons.stream().forEach(pokemon -> result.add(convertName(pokemon.getName())));

        return result;
    }

    public DisplayPoke getPokeDetails(Integer index) {
        Pokemon pokemon = pokeApi.getPokemon(index);
        PokemonSpecies species = pokeApi.getPokemonSpecies(index);

        String englishName = convertName(species.getNames().get(2).getName().toLowerCase());
        String japaneseName = species.getNames().get(1).getName();
        ArrayList<String> types = new ArrayList<>();
        pokemon.getTypes().stream().forEach(type -> types.add(type.getType().getName()));
        Integer height = pokemon.getHeight();
        Integer weight = pokemon.getWeight();
        String flavorText = species.getFlavorTextEntries().get(0).getFlavorText();

        DisplayPoke poke = new DisplayPoke(englishName, japaneseName,
                types, height, weight, flavorText);
        return poke;
    }

    public PokemonSpecies getSpecies(Integer index) {
        PokemonSpecies species = pokeApi.getPokemonSpecies(index);
        return species;
    }

    private String convertName(String pokeName) {

        // リネームを必要とするポケモン
        final Map<String, String> needConvetList = new HashMap<>();
        needConvetList.put("nidoran-f", "nidoranf"); // ニドラン♀
        needConvetList.put("nidoran-m", "nidoranm"); // ニドラン♂

        String result = pokeName;

        if (needConvetList.containsKey(pokeName)) {
            result = needConvetList.get(pokeName);
        }

        return result;
    }

}
