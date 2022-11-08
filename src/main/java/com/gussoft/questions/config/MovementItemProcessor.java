package com.gussoft.questions.config;

import com.gussoft.questions.model.dto.MovementItemIn;
import com.gussoft.questions.model.dto.MovementItemOut;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class MovementItemProcessor implements ItemProcessor<MovementItemIn, MovementItemOut> {

    @Override
    public MovementItemOut process(MovementItemIn item) throws Exception {
        log.info("Procesando el movimiento {}", item);
        return MovementItemOut
                .builder()
                .operationNumber(item.getOperationNumber())
                .amount(item.getAmount())
                .dateProcess(LocalDate.now())
                .build();
    }
}
