package com.filmland.assestment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionDto {

    private String name;

    private int remainingContent;

    private double price;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate startDate;


}
