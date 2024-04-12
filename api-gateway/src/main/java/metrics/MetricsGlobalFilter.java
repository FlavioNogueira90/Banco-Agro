package metrics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MetricsGlobalFilter implements GlobalFilter, Ordered {

    private final ExporMetricas exporMetricas;

    public MetricsGlobalFilter(ExporMetricas exporMetricas) {
        this.exporMetricas = exporMetricas;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Incrementa a métrica de requisições
        exporMetricas.incrementRequestCount();

        // Continua o fluxo de execução do filtro
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Pós-processamento (pode ser útil para registrar métricas de resposta, por exemplo)
        }));
    }

    @Override
    public int getOrder() {
        return -1; // Define a ordem de execução do filtro
    }
}

