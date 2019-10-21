package test.spring.autoconfig.sampleapp.one.regs.support;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RegistryRegistrar.class)
@Component
public @interface Registry {

    Class<?> entity();

    Class<?> mapper();

    String entityToDtoMethod();

    String path();

}
