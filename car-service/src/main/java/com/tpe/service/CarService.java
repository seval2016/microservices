package com.tpe.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.tpe.domain.Car;
import com.tpe.dto.AppLogDTO;
import com.tpe.dto.CarDTO;
import com.tpe.dto.CarRequest;
import com.tpe.enums.AppLogLevel;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    public void save(CarRequest carRequest) {

        Car car =  modelMapper.map(carRequest, Car.class);//2.parametreyi 1.prametreye ceviriyor
        carRepository.save(car);

        InstanceInfo instanceInfo =  eurekaClient
                .getApplication("log-service")//eurokaClient sınıfından log-service bilgilerini istiyoruz
                .getInstances().get(0); // log-service bölümlendirmiş ve birden fazla ikiz service olabilir, ilk index de olani getir.

        String baseUrl =  instanceInfo.getHomePageUrl(); // http://localhost:8083 yani endpoint'in base url'i oluşturmak için yazılır
        String path = "/log";

        String servicePath = baseUrl + path ; // String Concatination yaptık -> http://localhost:8083/log

        // Log-Service icin gerekli olan DTO sinifi olusturuluyor
        AppLogDTO appLogDTO = new AppLogDTO();
        appLogDTO.setLevel(AppLogLevel.INFO.name());
        appLogDTO.setDescription("Save a Car: " + car.getId());
        appLogDTO.setTime(LocalDateTime.now());

        // POST MApping
        ResponseEntity<String> logResponse =
                restTemplate.postForEntity(servicePath, appLogDTO, String.class); // !!! PostMapping yapilacagini belirtiyorum
        //Http methodum post mappin olacak( endpoint'im, json, Bu endpoint'e bu json ile post ile bir istek de bulunduğumda String türünde bir değer dönecek)
        //!!! response da olusturulan HttpStatus codu CREATED degilse exception firlat

        if(!(logResponse.getStatusCode() == HttpStatus.CREATED)){
            throw new ResourceNotFoundException("ERROR: Log isn't created");
        }
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