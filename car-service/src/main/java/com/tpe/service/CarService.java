package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.dto.CarDTO;
import com.tpe.dto.CarRequest;
import com.tpe.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public void save(CarRequest carRequest) {
       Car car = modelMapper.map(carRequest, Car.class);//ilk parametreyi alıp 2.paremetreye çeviriyor. DTO -> POJO
        carRepository.save(car);
    }

    public List<CarDTO> getAllCars() {
        List<Car> carList =  carRepository.findAll();
        return carList
                .stream()
                .map(this::mapCarToCarDTO)
                .collect(Collectors.toList());
    }
    private CarDTO mapCarToCarDTO(Car car){
        return modelMapper.map(car, CarDTO.class);
    }
}