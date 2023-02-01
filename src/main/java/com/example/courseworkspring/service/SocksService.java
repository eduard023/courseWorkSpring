package com.example.courseworkspring.service;

import com.example.courseworkspring.dto.SockRequest;
import com.example.courseworkspring.model.Color;
import com.example.courseworkspring.model.Size;

public interface SocksService {
    void addSock(SockRequest sockRequest);

    void issueSock(SockRequest sockRequest);

    void removeDefectiveSock(SockRequest sockRequest);

    int getSocksQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax);
}
