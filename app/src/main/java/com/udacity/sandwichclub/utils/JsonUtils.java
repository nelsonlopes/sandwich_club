package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject details = new JSONObject(json);
            JSONObject name = details.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnowAs = name.getJSONArray("alsoKnownAs");
            String placeOfOrigin = details.getString("placeOfOrigin");
            String description = details.getString("description");
            String image = details.getString("image");
            JSONArray ingredients = details.getJSONArray("ingredients");

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
