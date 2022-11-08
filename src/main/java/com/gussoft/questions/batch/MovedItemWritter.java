package com.gussoft.questions.batch;

import com.gussoft.questions.model.dto.MovementItemOut;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class MovedItemWritter implements ItemWriter<MovementItemOut> {
    @Override
    public void write(List<? extends MovementItemOut> list) throws Exception {

    }
}
