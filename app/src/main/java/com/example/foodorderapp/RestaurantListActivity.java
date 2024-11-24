package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize restaurant list
        List<Restaurant> restaurantList = getRestaurants();

        // Set up adapter
        // Handle restaurant click
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurantList, new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant restaurant) {
                // Handle restaurant click
                Intent intent = new Intent(RestaurantListActivity.this, FoodItemsActivity.class);
                intent.putExtra("restaurantId", restaurant.getId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(restaurantAdapter);
    }

    // Dummy restaurant data (replace with database or API)
    private List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1, "Pizza Paradise", "Best pizzas in town",R.drawable.pizza_image));
        restaurants.add(new Restaurant(2, "Burger Barn", "Delicious burgers and fries",R.drawable.burger_image));
        restaurants.add(new Restaurant(3, "Sushi World", "Fresh and authentic sushi",R.drawable.sushi_image));
        restaurants.add(new Restaurant(4, "Taco Fiesta", "Spicy and flavorful tacos",R.drawable.taco_image));
        return restaurants;
    }
}
