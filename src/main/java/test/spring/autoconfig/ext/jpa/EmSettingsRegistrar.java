package test.spring.autoconfig.ext.jpa;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;

@Setter
@Slf4j
public class EmSettingsRegistrar implements BeanFactoryAware, ImportBeanDefinitionRegistrar {
    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = new AnnotationAttributes(metadata.getAnnotationAttributes(JpaSettings.class.getName()));
        String componentsPrefix = attributes.getString("componentsPrefix");
        String propertiesPrefix = attributes.getString("propertiesPrefix");
        String entitiesPackage = attributes.getString("entitiesPackage");
        log.info("Registering jpa settings beans with prefix {}", componentsPrefix);
        registry.registerBeanDefinition(
                componentsPrefix + "DataSource",
                BeanDefinitionBuilder.rootBeanDefinition(DataSourceFactoryBean.class)
                        .addPropertyValue("propertiesPrefix", propertiesPrefix)
                        .getBeanDefinition());
        registry.registerBeanDefinition(
                componentsPrefix + "EntityManagerFactory",
                BeanDefinitionBuilder.rootBeanDefinition(ConfiguredLocalContainerEntityManagerFactoryBean.class)
                        .addPropertyValue("dataSourceName", componentsPrefix + "DataSource")
                        .addPropertyValue("propertiesPrefix", propertiesPrefix)
                        .addPropertyValue("entitiesPackage", entitiesPackage)
                        .addPropertyValue("persistenceUnitName", componentsPrefix + "PersistenceUnit")
                        .getBeanDefinition());
        registry.registerBeanDefinition(
                componentsPrefix + "PlatformTransactionManager",
                BeanDefinitionBuilder.rootBeanDefinition(PlatformTransactionManagerFactoryBean.class)
                        .addPropertyValue("entityManagerFactoryName", componentsPrefix + "EntityManagerFactory")
                        .getBeanDefinition());

    }

}
