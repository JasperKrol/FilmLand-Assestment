package com.filmland.assestment.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filmland.assestment.entity.Subscription;
import lombok.Setter;

import java.util.List;

@Setter
public class CategoryResponseDto {

    @JsonSerialize
    private List<CategoryDto> availableCategories;

    @JsonSerialize
    private List<SubscriptionDto> subscribedCategories;


}
