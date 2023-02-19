package com.example.kafkastreamdata.service;

import com.example.kafkastreamdata.entities.Bill;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Function;

@Service
public class KafkaStreamService {

    @Bean
    public Function<KStream<String, Bill>, KStream<String, Long>> kStreamFunction(){

        return input -> {
            return input
                    .map((k,v)->new KeyValue<>(String.valueOf(v.getCustomerId()), 0L))
                    .groupBy((k,v)->k, Grouped.with(Serdes.String(), Serdes.Long()))
                    .windowedBy(TimeWindows.of(Duration.ofMillis(5000)))
                    .count(Materialized.as("stores"))
                    .toStream()
                    .map((k,v)->new KeyValue<>("=>" + k.window().startTime() + k.window().endTime() + k.key() + "---------", v));
        };
    }
}
