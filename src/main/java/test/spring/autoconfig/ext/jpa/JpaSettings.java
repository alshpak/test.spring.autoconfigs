package test.spring.autoconfig.ext.jpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({EmSettingsRegistrar.class})
public @interface JpaSettings {

    String componentsPrefix();

    String propertiesPrefix();

    String entitiesPackage();

    String repositoriesPackage();

}

