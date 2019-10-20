package test.spring.autoconfig.sampleapp.two;

import org.springframework.context.annotation.Configuration;
import test.spring.autoconfig.ext.jpa.JpaSettings;
import test.spring.autoconfig.ext.simple.Simple;

@Configuration
@Simple(value = "twoSimple")
@JpaSettings(
        componentsPrefix = "mySecond",
        propertiesPrefix = "my_second",
        entitiesPackage = "test.spring.autoconfig.sampleapp.two",
        repositoriesPackage = "test.spring.autoconfig.sampleapp.two")
public class SampleConfigTwo {
}
