package com.tpe.controller;

import com.tpe.dto.CarDTO;
import com.tpe.dto.CarRequest;
import com.tpe.service.CarService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private CarService carService;


    @PostMapping // http://localhots:8085/car
    public ResponseEntity<String> saveCar(@RequestBody CarRequest carRequest){
        carService.save(carRequest);

        String response = "Car Successfully Saved";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(){
        List<CarDTO> allCars =  carService.getAllCars();
        return ResponseEntity.ok(allCars);
    }
}