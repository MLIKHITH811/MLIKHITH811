package com.example.foodorderapp;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {
    private int id;
    private String name;
    private double price;
    private int imageResId;
    private boolean isSelected;

    public FoodItem(int id, String name, double price, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.isSelected = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public int getImageResId() {
        return imageResId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Parcelable implementation
    protected FoodItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        imageResId = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(imageResId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
