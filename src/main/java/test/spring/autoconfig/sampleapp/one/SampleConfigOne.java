package test.spring.autoconfig.sampleapp.one;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import test.spring.autoconfig.ext.jpa.JpaSettings;
import test.spring.autoconfig.ext.simple.Simple;

@Configuration
@Simple(value = "oneSimple")
@JpaSettings(
        componentsPrefix = "myFirst",
        propertiesPrefix = "my_first",
        entitiesPackage = "test.spring.autoconfig.sampleapp.one",
        repositoriesPackage = "test.spring.autoconfig.sampleapp.one")
@EnableJpaRepositories(
        basePackages = "test.spring.autoconfig.sampleapp.one",
        entityManagerFactoryRef = "myFirstEntityManagerFactory",
        transactionManagerRef = "myFirstPlatformTransactionManager"
)
public class SampleConfigOne {


}
