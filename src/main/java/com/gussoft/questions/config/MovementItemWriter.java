package com.gussoft.questions.config;

import com.gussoft.questions.model.Movement;
import com.gussoft.questions.model.dto.MovementItemOut;
import com.gussoft.questions.repository.MovementRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MovementItemWriter implements ItemWriter<MovementItemOut> {

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public void write(List<? extends MovementItemOut> items) throws Exception {
        log.info("Escribiendo los movimientos {}", items);

        for (MovementItemOut item: items) {
            Movement movement = Movement
                    .builder()
                    .operationNumber(item.getOperationNumber())
                    .amount(item.getAmount())
                    .dateProcess(item.getDateProcess())
                    .build();
            movementRepository.save(movement);
        }
    }
}
