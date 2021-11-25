package com.example.truyencuoi.model;

import java.util.Arrays;
import java.util.List;

public class Category {
    private String name;
    private String icon;

    public Category(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
