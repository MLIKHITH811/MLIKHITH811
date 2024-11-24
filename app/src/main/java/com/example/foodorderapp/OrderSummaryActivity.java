package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    private LinearLayout orderSummaryLayout;
    private TextView totalCostTextView;
    private Button selectLocationButton, placeOrderButton;

    private List<FoodItem> selectedItems;
    private double totalCost = 0.0;
    private LatLng selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        orderSummaryLayout = findViewById(R.id.layoutOrderSummary);
        totalCostTextView = findViewById(R.id.textViewTotalCost);
        selectLocationButton = findViewById(R.id.buttonSelectLocation);
        placeOrderButton = findViewById(R.id.buttonPlaceOrder);

        // Get selected food items passed from FoodItemsActivity
        selectedItems = getIntent().getParcelableArrayListExtra("selectedItems");

        if (selectedItems == null || selectedItems.isEmpty()) {
            Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Display order summary with images
        for (FoodItem item : selectedItems) {
            addFoodItemToSummary(item);
            totalCost += item.getPrice();
        }
        totalCostTextView.setText(String.format("Total: $%.2f", totalCost));

        // Handle Select Location button
        selectLocationButton.setOnClickListener(v -> {
            Intent mapIntent = new Intent(OrderSummaryActivity.this, MapsActivity.class);
            startActivityForResult(mapIntent, 1);
        });

        // Handle Place Order button
        placeOrderButton.setOnClickListener(v -> {
            if (selectedLocation == null) {
                Toast.makeText(OrderSummaryActivity.this, "Please select a delivery location", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent confirmationIntent = new Intent(OrderSummaryActivity.this, OrderConfirmationActivity.class);
            confirmationIntent.putParcelableArrayListExtra("orderedItems", new ArrayList<>(selectedItems));
            confirmationIntent.putExtra("totalCost", totalCost);
            confirmationIntent.putExtra("latitude", selectedLocation.latitude);
            confirmationIntent.putExtra("longitude", selectedLocation.longitude);
            startActivity(confirmationIntent);
            finish(); // Close this activity after launching the confirmation screen
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            double latitude = data.getDoubleExtra("latitude", 0.0);
            double longitude = data.getDoubleExtra("longitude", 0.0);
            selectedLocation = new LatLng(latitude, longitude);

            Toast.makeText(this, "Location selected: " + selectedLocation.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addFoodItemToSummary(FoodItem foodItem) {
        View itemView = getLayoutInflater().inflate(R.layout.item_order_summary, orderSummaryLayout, false);

        TextView nameTextView = itemView.findViewById(R.id.textViewFoodName);
        TextView priceTextView = itemView.findViewById(R.id.textViewFoodPrice);
        ImageView foodImageView = itemView.findViewById(R.id.imageViewFood);

        nameTextView.setText(foodItem.getName());
        priceTextView.setText(String.format("$%.2f", foodItem.getPrice()));
        foodImageView.setImageResource(foodItem.getImageResId());

        orderSummaryLayout.addView(itemView);
    }

    private void sendConfirmationMessage() {
        String phoneNumber = "1234567890"; // Replace with the registered phone number
        String message = "Your order has been placed successfully! Total: $" + totalCost;

        // Simulated message sending logic
        Toast.makeText(this, "Confirmation sent to: " + phoneNumber, Toast.LENGTH_SHORT).show();
    }
}
