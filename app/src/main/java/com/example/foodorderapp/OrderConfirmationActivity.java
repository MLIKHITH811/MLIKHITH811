package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class OrderConfirmationActivity extends AppCompatActivity {

    private LinearLayout orderedItemsLayout;
    private TextView confirmationMessageTextView, deliveryLocationTextView, totalCostTextView;

    private List<FoodItem> orderedItems;
    private double totalCost;
    private LatLng deliveryLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        // Initialize views
        orderedItemsLayout = findViewById(R.id.layoutOrderedItems);
        confirmationMessageTextView = findViewById(R.id.textViewConfirmationMessage);
        deliveryLocationTextView = findViewById(R.id.textViewDeliveryLocation);
        totalCostTextView = findViewById(R.id.textViewTotalCost);

        // Get data from intent
        Intent intent = getIntent();
        orderedItems = intent.getParcelableArrayListExtra("orderedItems");
        totalCost = intent.getDoubleExtra("totalCost", 0.0);
        double latitude = intent.getDoubleExtra("latitude", 0.0);
        double longitude = intent.getDoubleExtra("longitude", 0.0);
        deliveryLocation = new LatLng(latitude, longitude);

        // Populate the UI with order details
        confirmationMessageTextView.setText("Thank you for your order!");
        deliveryLocationTextView.setText(String.format("Delivery Location: (%.4f, %.4f)", latitude, longitude));
        totalCostTextView.setText(String.format("Total Cost: $%.2f", totalCost));

        // Populate ordered items with images
        for (FoodItem item : orderedItems) {
            addOrderedItemToLayout(item);
        }

        // Display a toast as a simulated message confirmation
        Toast.makeText(this, "Order confirmation sent to your registered phone number.", Toast.LENGTH_LONG).show();
    }

    private void addOrderedItemToLayout(FoodItem foodItem) {
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_order_confirmation, orderedItemsLayout, false);

        TextView nameTextView = itemView.findViewById(R.id.textViewFoodName);
        TextView priceTextView = itemView.findViewById(R.id.textViewFoodPrice);
        ImageView foodImageView = itemView.findViewById(R.id.imageViewFood);

        nameTextView.setText(foodItem.getName());
        priceTextView.setText(String.format("$%.2f", foodItem.getPrice()));
        foodImageView.setImageResource(foodItem.getImageResId());

        orderedItemsLayout.addView(itemView);
    }
}
