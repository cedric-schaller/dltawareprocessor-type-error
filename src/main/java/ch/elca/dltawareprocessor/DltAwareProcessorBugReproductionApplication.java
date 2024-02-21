package ch.elca.dltawareprocessor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
public class DltAwareProcessorBugReproductionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DltAwareProcessorBugReproductionApplication.class, args);
    }

    @Bean
    public Function<KStream<String, Double>, KStream<String, Double>> sqrt() {
        return numbers -> numbers.mapValues(Math::sqrt);
    }

    @Bean
    public Consumer<KStream<String, Double>> log() {
        return sqrt -> sqrt.foreach((key, value) -> log.info("{}: {}", key, value));
    }

}
