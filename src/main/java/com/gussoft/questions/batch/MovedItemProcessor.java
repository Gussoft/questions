package com.gussoft.questions.batch;

import com.gussoft.questions.model.dto.MovementItemIn;
import com.gussoft.questions.model.dto.MovementItemOut;
import org.springframework.batch.item.ItemProcessor;


public class MovedItemProcessor implements ItemProcessor<MovementItemIn, MovementItemOut> {


    @Override
    public MovementItemOut process(MovementItemIn item) throws Exception {
        return null;
    }
}
