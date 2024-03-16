package com.tpe.domain;


import com.tpe.enums.AppLogLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document //NoSQL bir bir mongoDB kullanıyoruz bu yüzden tablo yok dokumanlar var. Entity değil document kullanacağız
public class AppLog {

    @Id
    private String id;

    private AppLogLevel level;

    private String description;

    private LocalDateTime time;
}