package com.gussoft.questions.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Details {

    private int totalRegistros;

    @JsonProperty("startTimestamp")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm a")
    private LocalDateTime startTimestamp;

    @JsonProperty("endTimestamp")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm a")
    private LocalDateTime endTimestamp;

    private String notificationId;

    private String status;

}
