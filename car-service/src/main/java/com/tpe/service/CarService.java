package com.tpe.service;

import com.tpe.dto.CarRequest;
import com.tpe.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private CarRepository carRepository;

    public void save(CarRequest carRequest) {
    }
}