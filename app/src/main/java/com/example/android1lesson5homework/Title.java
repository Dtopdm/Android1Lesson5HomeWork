package com.example.android1lesson5homework;

import java.io.Serializable;

public class Title implements Serializable {
    public String name;
    public String data;

    public Title(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
