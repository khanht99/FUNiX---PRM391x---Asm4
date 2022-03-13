package com.example.android_assignment3;

import android.graphics.Bitmap;

public class Animal {

    private final String name;
    private final String group;
    private final Bitmap screen02_image;
    private final Bitmap screen03_image;
    private final String paragraph;

    public Animal(String group, String name, Bitmap screen02_image, Bitmap screen03_image, String paragraph) {
        this.group = group;
        this.name = name;
        this.screen02_image = screen02_image;
        this.screen03_image = screen03_image;
        this.paragraph = paragraph;
    }

    public String getName() {
        return name;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getGroup() {
        return group;
    }

    public Bitmap getScreen02_image() {
        return screen02_image;
    }

    public Bitmap getScreen03_image() {
        return screen03_image;
    }
}
