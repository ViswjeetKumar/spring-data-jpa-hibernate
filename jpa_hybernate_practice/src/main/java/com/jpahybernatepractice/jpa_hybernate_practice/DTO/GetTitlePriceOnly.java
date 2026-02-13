package com.jpahybernatepractice.jpa_hybernate_practice.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTitlePriceOnly {
    private String title;
    private BigDecimal price;
}
