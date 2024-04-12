package metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class ExporMetricas {
    private final Counter requestsCounter;

    public ExporMetricas(MeterRegistry meterRegistry) {
        requestsCounter = Counter.builder("Total_de_requisicoes")
                .description("Total de requisicoes na API")
                .register(meterRegistry);
    }

    public void incrementRequestCount() {
        requestsCounter.increment();
    }
}
