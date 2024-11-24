package com.example.foodorderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodItems;

    public FoodItemAdapter(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);

        holder.foodName.setText(foodItem.getName());
        holder.foodPrice.setText("$" + foodItem.getPrice());
        holder.foodImage.setImageResource(foodItem.getImageResId());
        holder.foodSelect.setChecked(foodItem.isSelected());

        // Handle checkbox click to update selection status
        holder.foodSelect.setOnCheckedChangeListener((buttonView, isChecked) -> foodItem.setSelected(isChecked));
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public List<FoodItem> getSelectedFoodItems() {
        List<FoodItem> selectedItems = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (foodItem.isSelected()) {
                selectedItems.add(foodItem);
            }
        }
        return selectedItems;
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodPrice;
        ImageView foodImage;
        CheckBox foodSelect;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.textFoodName);
            foodPrice = itemView.findViewById(R.id.textFoodPrice);
            foodImage = itemView.findViewById(R.id.imageFood);
            foodSelect = itemView.findViewById(R.id.checkboxFoodSelect);
        }
    }
}
