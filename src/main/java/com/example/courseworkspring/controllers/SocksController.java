package com.example.courseworkspring.controllers;

import com.example.courseworkspring.dto.SockRequest;
import com.example.courseworkspring.exception.InSufficientQuantityException;
import com.example.courseworkspring.exception.InvalidSockRequestException;
import com.example.courseworkspring.model.Color;
import com.example.courseworkspring.model.Size;
import com.example.courseworkspring.service.impl.SocksServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
@Tag(name = "Носки", description = "CRUD - операции")
public class SocksController {
    private final SocksServiceImpl sockService;

    public SocksController(SocksServiceImpl sockService) {
        this.sockService = sockService;
    }

    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockRequestException invalidSockRequestException){
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }
    @ExceptionHandler(InSufficientQuantityException.class)
    public ResponseEntity<String> handleInSufficientException(InSufficientQuantityException inSufficientQuantityException){
        return ResponseEntity.badRequest().body(inSufficientQuantityException.getMessage());
    }



    @PostMapping
    @Operation(summary = "Добавление носков на склад")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "удалось добавить приход"),
            @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")
    })
    public void addSock(@RequestBody SockRequest sockRequest) {
        sockService.addSock(sockRequest);
    }

    @PutMapping
    @Operation(summary = "Выдача носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "удалось произвести отпуск носков со склада"),
            @ApiResponse(responseCode = "400", description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")
    })
    public void issuesSock(@RequestBody SockRequest sockRequest){
        sockService.issueSock(sockRequest);
    }

    @GetMapping
    @Operation(summary = "Поиск носков на складе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос выполнен, результат в теле ответа в виде строкового представления целого числа"),
            @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат;"),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")
    })
    public int getSocksCount(@RequestParam(required = false, name = "color")Color color,
                             @RequestParam(required = false, name = "size")Size size,
                             @RequestParam(required = false, name = "cottonMin")Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax")Integer cottonMax){
        return sockService.getSocksQuantity(color, size, cottonMin, cottonMax);
    }


    @DeleteMapping
    @Operation(summary = "Списание носков со склада")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "запрос выполнен, товар списан со склада"),
            @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат;"),
            @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")
    })
    public void removeDefectiveProduct(@RequestBody SockRequest sockRequest){
        sockService.removeDefectiveSock(sockRequest);
    }

}