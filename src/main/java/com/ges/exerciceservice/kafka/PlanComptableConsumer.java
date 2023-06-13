package com.ges.exerciceservice.kafka;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.base.basemodel.dto.SocieteDTOKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PlanComptableConsumer {
    @Autowired
    private RedisTemplate<String, PlanComptableDTOKafka> redisTemplate;
    private static final Logger LOGGER= LoggerFactory.getLogger(PlanComptableConsumer.class);



    @KafkaListener(
            topics = "plan_topics"
            ,groupId = "exercice-groupe")
    public void consume(PlanComptableDTOKafka planComptableDTOKafka){
        redisTemplate.opsForValue().set(planComptableDTOKafka.getId(), planComptableDTOKafka);
        LOGGER.info(String.format("Societe for kafka => %s",planComptableDTOKafka.getId()));
    }
    @KafkaListener(
            topics = "deleted_plan_comptable"
            ,groupId = "exercice-groupe")
    public void consumeDeletedSociete(PlanComptableDTOKafka planComptableDTOKafka){
        redisTemplate.delete(planComptableDTOKafka.getId());
        LOGGER.info(String.format("Societe for kafka => %s",planComptableDTOKafka.getId()));
    }

}
