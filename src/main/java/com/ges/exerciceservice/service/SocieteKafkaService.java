package com.ges.exerciceservice.service;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.base.basemodel.dto.SocieteDTOKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author WIAM
 **/
@Service
public class SocieteKafkaService {
    @Autowired
    private RedisTemplate<String, SocieteDTOKafka> redisTemplate;

    public List<SocieteDTOKafka> allSocieteKafka(){
        List<SocieteDTOKafka> kafkaMessages = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys) {
            Object value = redisTemplate.opsForValue().get(key);
            if (value != null && value instanceof SocieteDTOKafka) {
                kafkaMessages.add((SocieteDTOKafka)value);
            }
        }
        return kafkaMessages;
    }
}
