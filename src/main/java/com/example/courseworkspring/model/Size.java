package com.example.courseworkspring.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {
    S("36-38"),
    M("39-41"),
    L("42-45"),
    XL("45-48");




    private final String size;

    Size(String size) {
        this.size = size;
    }

    @JsonValue
    public String getSize(){
        return size;
    }

    @JsonCreator
    public static Size forValues(String size){
        for (Size value : Size.values()){
            if (value.size.equals(size)){
                return value;
            }
        }
        return null;
    }
}
