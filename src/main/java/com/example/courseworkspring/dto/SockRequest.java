package com.example.courseworkspring.dto;

import com.example.courseworkspring.model.Color;
import com.example.courseworkspring.model.Size;
import lombok.Data;

@Data
public class SockRequest {

    private Color color;
    private Size size;
    private int cottonPart;
    private int quantity;
}
