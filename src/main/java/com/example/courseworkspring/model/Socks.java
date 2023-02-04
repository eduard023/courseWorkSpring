package com.example.courseworkspring.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class Socks {

    private final Color color;
    private final Size size;
    private final int cottonPart;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Socks(@JsonProperty("cottonPart") int cottonPart,
                 @JsonProperty("size") Size size,
                 @JsonProperty("color") Color color) {
        this.cottonPart = cottonPart;
        this.size = size;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonPart() {
        return cottonPart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && color == socks.color && size == socks.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart);
    }
}
