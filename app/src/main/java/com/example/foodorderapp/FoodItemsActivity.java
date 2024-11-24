package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodItemsActivity extends AppCompatActivity {

    private FoodItemAdapter foodItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFoodItems);
        Button proceedToOrderButton = findViewById(R.id.buttonProceedToOrder);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get restaurant ID passed from RestaurantListActivity
        int restaurantId = getIntent().getIntExtra("restaurantId", -1);

        // Load food items for the selected restaurant (dummy data for now)
        List<FoodItem> foodItemList = getFoodItemsForRestaurant(restaurantId);

        // Set up the adapter
        foodItemAdapter = new FoodItemAdapter(foodItemList);
        recyclerView.setAdapter(foodItemAdapter);

        // Handle Proceed to Order button click
        proceedToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FoodItem> selectedItems = foodItemAdapter.getSelectedFoodItems();

                if (selectedItems.isEmpty()) {
                    Toast.makeText(FoodItemsActivity.this, "Please select at least one item", Toast.LENGTH_SHORT).show();
                } else {
                    // Pass selected items to Order Summary Activity
                    Intent intent = new Intent(FoodItemsActivity.this, OrderSummaryActivity.class);
                    intent.putParcelableArrayListExtra("selectedItems", new ArrayList<>(selectedItems));
                    startActivity(intent);
                }
            }
        });
    }

    // Dummy food item data (replace with database or API)
    private List<FoodItem> getFoodItemsForRestaurant(int restaurantId) {
        List<FoodItem> foodItems = new ArrayList<>();

        if (restaurantId == 1) {
            // Pizza Paradise (8 items with images)
            foodItems.add(new FoodItem(1, "Margherita Pizza", 8.99, R.drawable.pizza_image8));
            foodItems.add(new FoodItem(2, "Pepperoni Pizza", 10.99, R.drawable.pizza_image2));
            foodItems.add(new FoodItem(3, "Vegetarian Pizza", 9.99, R.drawable.pizza_image3));
            foodItems.add(new FoodItem(4, "BBQ Chicken Pizza", 12.99, R.drawable.pizza_image4));
            foodItems.add(new FoodItem(5, "Hawaiian Pizza", 11.99, R.drawable.pizza_image5));
            foodItems.add(new FoodItem(6, "Cheese Burst Pizza", 14.99, R.drawable.pizza_image6));
            foodItems.add(new FoodItem(7, "Mushroom Pizza", 10.99, R.drawable.pizza_image7));
            foodItems.add(new FoodItem(8, "Spicy Italian Pizza", 13.99, R.drawable.pizza_image8));
        } else if (restaurantId == 2) {
            // Burger Barn (8 items with images)
            foodItems.add(new FoodItem(9, "Classic Burger", 6.99, R.drawable.burger_image1));
            foodItems.add(new FoodItem(10, "Cheeseburger", 7.99, R.drawable.burger_image2));
            foodItems.add(new FoodItem(11, "BBQ Bacon Burger", 9.99, R.drawable.burger_image3));
            foodItems.add(new FoodItem(12, "Veggie Burger", 8.99, R.drawable.burger_image4));
            foodItems.add(new FoodItem(13, "Double Cheeseburger", 11.99, R.drawable.burger_image5));
            foodItems.add(new FoodItem(14, "Chicken Burger", 8.49, R.drawable.burger_image6));
            foodItems.add(new FoodItem(15, "Fish Burger", 7.49, R.drawable.burger_image7));
            foodItems.add(new FoodItem(16, "Spicy Jalapeno Burger", 10.99, R.drawable.burger_image8));
        } else if (restaurantId == 3) {
            // Sushi World (8 items with images)
            foodItems.add(new FoodItem(17, "California Roll", 12.99, R.drawable.sushi_image1));
            foodItems.add(new FoodItem(18, "Salmon Sashimi", 14.99, R.drawable.burger_image1));
            foodItems.add(new FoodItem(19, "Tuna Roll", 13.99, R.drawable.sushi_image3));
            foodItems.add(new FoodItem(20, "Ebi Nigiri", 9.99, R.drawable.sushi_image4));
            foodItems.add(new FoodItem(21, "Dragon Roll", 15.99, R.drawable.sushi_image5));
            foodItems.add(new FoodItem(22, "Spicy Tuna Roll", 14.49, R.drawable.sushi_image6));
            foodItems.add(new FoodItem(23, "Shrimp Tempura", 16.99, R.drawable.sushi_image7));
            foodItems.add(new FoodItem(24, "Maki Roll", 11.99, R.drawable.sushi_image8));
        } else if (restaurantId == 4) {
            // Taco Fiesta (8 items with images)
            foodItems.add(new FoodItem(25, "Chicken Taco", 5.99, R.drawable.taco_image1));
            foodItems.add(new FoodItem(26, "Beef Taco", 6.99, R.drawable.taco_image2));
            foodItems.add(new FoodItem(27, "Pork Taco", 7.49, R.drawable.taco_image3));
            foodItems.add(new FoodItem(28, "Fish Taco", 7.99, R.drawable.taco_image4));
            foodItems.add(new FoodItem(29, "Vegetarian Taco", 6.49, R.drawable.taco_image5));
            foodItems.add(new FoodItem(30, "Spicy Chicken Taco", 6.99, R.drawable.taco_image6));
            foodItems.add(new FoodItem(31, "Steak Taco", 8.49, R.drawable.taco_image7));
            foodItems.add(new FoodItem(32, "Shrimp Taco", 9.49, R.drawable.taco_image8));
        }

        return foodItems;
    }
}

