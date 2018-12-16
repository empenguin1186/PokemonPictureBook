package jp.co.pokemon.pokemonApp.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;

@Getter
public class DisplayPoke {

    @NonNull
    private String englishName;

    @NonNull
    private String japaneseName;

    @NonNull
    private ArrayList<String> types;

    @NonNull
    private Integer height;

    @NonNull
    private Integer weight;

    @NonNull
    private String flavorText;

    public DisplayPoke(String englishName, String japaneseName, ArrayList<String> types,
                       Integer height, Integer weight, String flavorText) {
        this.englishName = englishName;
        this.japaneseName = japaneseName;
        this.types = types;
        this.height = height;
        this.weight = weight;
        this.flavorText = flavorText;
    }

}
