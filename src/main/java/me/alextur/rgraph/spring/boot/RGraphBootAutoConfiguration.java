package me.alextur.rgraph.spring.boot;

import me.alextur.rgraph.logger.FileGraphLogger;
import me.alextur.rgraph.logger.RotationLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
@ConditionalOnClass(RotationLogger.class)
@EnableConfigurationProperties(RGraphProperties.class)
public class RGraphBootAutoConfiguration {

    private RGraphProperties properties;

    public RGraphBootAutoConfiguration(@Autowired RGraphProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    RotationLogger rotationLogger() {
        return new RotationLogger(FileGraphLogger::new, properties.getRotationLimit(),
                Paths.get(properties.getDefaultLogsPath(), properties.getFilePrefix()).toString());
    }


}
