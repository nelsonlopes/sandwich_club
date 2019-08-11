package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject details = new JSONObject(json);
            JSONObject name = details.getJSONObject(KEY_NAME);
            String mainName = name.getString(KEY_MAIN_NAME);
            JSONArray alsoKnowAs = name.getJSONArray(KEY_ALSO_KNOW_AS);
            String placeOfOrigin = details.getString(KEY_PLACE_OF_ORIGIN);
            String description = details.getString(KEY_DESCRIPTION);
            String image = details.getString(KEY_IMAGE);
            JSONArray ingredients = details.getJSONArray(KEY_INGREDIENTS);

            //
            sandwich = new Sandwich();
            sandwich.setMainName(mainName);
            if (alsoKnowAs.length() > 0) {
                List<String> alsoKnownAsList = new ArrayList<>();
                for (int i = 0; i < alsoKnowAs.length(); i++) {
                    alsoKnownAsList.add(alsoKnowAs.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnownAsList);
            }
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            if (ingredients.length() > 0) {
                List<String> ingredientsList = new ArrayList<>();
                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientsList.add(ingredients.getString(i));
                }
                sandwich.setIngredients(ingredientsList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
