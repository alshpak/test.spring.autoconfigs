package test.spring.autoconfig.autoconfigure.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import test.spring.autoconfig.ext.simple.Simple;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnClass(Simple.class)
@Import(SimpleAutoConfigurationRegistrar.class)
@Slf4j
public class SimpleAutoConfiguration {

    @PostConstruct
    public void init() {
        log.debug("Started simple auto configuration");
    }
}
