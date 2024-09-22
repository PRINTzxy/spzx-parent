package website.yny84666.spzx.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import website.yny84666.spzx.gateway.handler.SentinelFallbackHandler;

/**
 * 网关限流配置
 * 
 * @author spzx
 */
@Configuration
public class GatewayConfig
{
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
    {
        return new SentinelFallbackHandler();
    }
}
