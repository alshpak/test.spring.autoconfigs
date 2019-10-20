package test.spring.autoconfig.sampleapp.one.regs.support;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import({RegistryRegistrar.class})
public @interface Registry {

    Class<?> entity();

    Class<?> mapper();

    String entityToDtoMethod();

}
