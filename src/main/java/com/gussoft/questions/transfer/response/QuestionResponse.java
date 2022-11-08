package com.gussoft.questions.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionResponse {

    private Double pointMax;

    private Integer trueQuestion;

    private Integer Attempts;

}