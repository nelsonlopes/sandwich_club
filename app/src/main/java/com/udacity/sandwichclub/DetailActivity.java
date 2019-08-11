package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView alsoKnownAsTitleTv;
    private TextView alsoKnownAsTv;
    private TextView placeOfOriginTitleTv;
    private TextView placeOfOriginTv;
    private TextView ingredientsTitleTv;
    private TextView ingredientsTv;
    private TextView descriptionTitleTv;
    private TextView descriptionTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownAsTitleTv = findViewById(R.id.also_known_title_tv);
        alsoKnownAsTv = findViewById(R.id.also_known_tv);
        placeOfOriginTitleTv = findViewById(R.id.origin_title_tv);
        placeOfOriginTv = findViewById(R.id.origin_tv);
        ingredientsTitleTv = findViewById(R.id.ingredients_title_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        descriptionTitleTv = findViewById(R.id.description_title_tv);
        descriptionTv = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                .into(ingredientsIv);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (sandwich == null)
            return;

        if (sandwich.getAlsoKnownAs() == null) {
            alsoKnownAsTitleTv.setVisibility(View.GONE);
            alsoKnownAsTv.setVisibility(View.GONE);
        } else {
            alsoKnownAsTitleTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setVisibility(View.VISIBLE);
            alsoKnownAsTv.setText(android.text.TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        }

        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOriginTitleTv.setVisibility(View.GONE);
            placeOfOriginTv.setVisibility(View.GONE);
        } else {
            placeOfOriginTitleTv.setVisibility(View.VISIBLE);
            placeOfOriginTv.setVisibility(View.VISIBLE);
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        }

        if (sandwich.getIngredients() == null) {
            ingredientsTitleTv.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);
        } else {
            ingredientsTitleTv.setVisibility(View.VISIBLE);
            ingredientsTv.setVisibility(View.VISIBLE);
            ingredientsTv.setText(android.text.TextUtils.join(", ", sandwich.getIngredients()));
        }

        if (sandwich.getDescription().isEmpty()) {
            descriptionTitleTv.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);
        } else {
            descriptionTitleTv.setVisibility(View.VISIBLE);
            descriptionTv.setVisibility(View.VISIBLE);
            descriptionTv.setText(sandwich.getDescription());
        }
    }
}
