package test.spring.autoconfig.ext.jpa;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.data.config.ConfigurationUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.config.*;
import org.springframework.data.util.Streamable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Setter
@Slf4j
public class JpaRepositoriesRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {
    private ResourceLoader resourceLoader;
    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        log.warn("JpaRepositoriesRegistrar");
        log.warn("Anno class " + metadata.getClass().getName());

        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(JpaSettings.class.getName());
        AnnotationAttributes attributes = new AnnotationAttributes(annotationAttributes);
        String componentsPrefix = attributes.getString("componentsPrefix");
        String basePackage = attributes.getString("repositoriesPackage");

        log.warn("Base Package {}", basePackage);


        AnnotationMetadata jpaRepoMetadata = new StandardAnnotationMetadata(AnnoContainer.class) {
            @Override
            public Map<String, Object> getAnnotationAttributes(String annotationName) {

                if (annotationName.equals(EnableJpaRepositories.class.getName())) {
                    Map<String, Object> parentAttributes = super.getAnnotationAttributes(annotationName);
                    log.warn("Nothing found (((");
                    Map<String, Object> attrs = parentAttributes == null ? new HashMap<>() : parentAttributes;
                    attrs.put("basePackages", new String[] { basePackage });
                    attrs.put("entityManagerFactoryRef", componentsPrefix + "EntityManagerFactory");
                    attrs.put("transactionManagerRef", componentsPrefix + "PlatformTransactionManager");

                    return attrs;
                } else  {
                    return super.getAnnotationAttributes(annotationName);
                }
            }
        };
        AnnotationRepositoryConfigurationSource configurationSource =
                new AnnotationRepositoryConfigurationSource(jpaRepoMetadata, EnableJpaRepositories.class,
                        resourceLoader, environment, registry);

        RepositoryConfigurationDelegate delegate = new RepositoryConfigurationDelegate(configurationSource, resourceLoader, environment);
        JpaRepositoryConfigExtension extension = new JpaRepositoryConfigExtension();

        RepositoryConfigurationUtils.exposeRegistration(extension, registry, configurationSource);
        delegate.registerRepositoriesIn(registry, extension);
    }

    @EnableJpaRepositories
    private static class AnnoContainer {

    }
}
