package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName;
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        ArrayList<String> ingredients = new ArrayList<>();

        Sandwich sandwich = new Sandwich();

        // Try to parse the JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject jsonObj = new JSONObject(json);

                if (jsonObj.has("placeOfOrigin")){
                    placeOfOrigin = jsonObj.getString("placeOfOrigin");
                }else{
                    placeOfOrigin = " ";
                }
                if (jsonObj.has("description")){
                    description = jsonObj.getString("description");
                }else{
                    description = " ";
                }
                if (jsonObj.has("image")){
                    image = jsonObj.getString("image");
                }else{
                    image = " ";
                }

                JSONArray ingredientsList = jsonObj.getJSONArray("ingredients");

                if (ingredientsList != null) {
                    for (int i = 0; i < ingredientsList.length(); i++) {
                        String ingredientsString = ingredientsList.getString(i);
                        ingredients.add(ingredientsString);
                    }
                }else{
                    ingredients = null;
                }

                JSONObject name = jsonObj.getJSONObject("name");
                mainName = name.getString("mainName");
                JSONArray alsoKnownAsList = name.getJSONArray("alsoKnownAs");

                if (alsoKnownAsList != null){

                    for (int i = 0; i < alsoKnownAsList.length(); i++) {
                        String alsoKnownAsString = alsoKnownAsList.getString(i);
                        alsoKnownAs.add(alsoKnownAsString);
                    }
                }else{
                    alsoKnownAs = null;
                }



                // adding strings to sandwiches array list
                sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JsonUtils", "Problem parsing the sandwich JSON results", e);
        }
        return sandwich;
    }
}
