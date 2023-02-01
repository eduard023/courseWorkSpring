package com.example.courseworkspring.service.impl;

import com.example.courseworkspring.dto.SockRequest;
import com.example.courseworkspring.exception.InSufficientQuantityException;
import com.example.courseworkspring.exception.InvalidSockRequestException;
import com.example.courseworkspring.model.Color;
import com.example.courseworkspring.model.Size;
import com.example.courseworkspring.model.Socks;
import com.example.courseworkspring.service.SocksService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {
    private final Map<Socks, Integer> sockMap = new HashMap<>();

    @Override
    public void addSock(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Socks sock = mapToSock(sockRequest);
        if (sockMap.containsKey(sock)) {
            sockMap.put(sock, sockMap.get(sock) + sockRequest.getQuantity());
        } else {
            sockMap.put(sock, sockRequest.getQuantity());
        }

    }
    @Override
    public void issueSock(SockRequest sockRequest){
        deleteSocks(sockRequest);
    }
    @Override
    public void removeDefectiveSock(SockRequest sockRequest){
        deleteSocks(sockRequest);
    }


    private void deleteSocks(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Socks socks = mapToSock(sockRequest);
        int socksQuantity = sockMap.getOrDefault(socks, 0);
        if (socksQuantity >= sockRequest.getQuantity()) {
            sockMap.put(socks, socksQuantity - sockRequest.getQuantity());
        } else {
            throw new InSufficientQuantityException("Носков больше нет");
        }
    }


@Override
    public int getSocksQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax){
        int total = 0;
        for (Map.Entry<Socks, Integer> entry : sockMap.entrySet()){
            if (color != null && !entry.getKey().getColor().equals(color)){
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)){
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPart() < cottonMin){
                continue;
            }if (cottonMax != null && entry.getKey().getCottonPart() > cottonMax){
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }


    private void validateRequest(SockRequest sockRequest) {
        if (sockRequest.getColor() == null || sockRequest.getSize() == null) {
            throw new InvalidSockRequestException("Заполнены не все поля");
        }
        if (sockRequest.getCottonPart() < 0 || sockRequest.getCottonPart() > 100) {
            throw new InvalidSockRequestException("Процент хлопка может быть только между 0 и 100");
        }
        if (sockRequest.getQuantity() <= 0) {
            throw new InvalidSockRequestException("колличество может быть только положительное");
        }

    }

    private Socks mapToSock(SockRequest sockRequest) {
        return new Socks(sockRequest.getCottonPart(),
                        sockRequest.getSize(),
                sockRequest.getColor());
    }

@Component
    public class ConverterString implements Converter<String, Size> {

        @Override
        public Size convert(String source) {
            return Size.forValues(source);
        }
    }

}
