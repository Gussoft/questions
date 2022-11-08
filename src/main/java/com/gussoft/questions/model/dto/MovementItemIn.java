package com.gussoft.questions.model.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovementItemIn {

    private String operationNumber;

    private BigDecimal amount;

}
