package test.spring.autoconfig.autoconfigure.simple;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

@Setter
@Slf4j
public class SimpleAutoConfigurationRegistrar implements BeanFactoryAware, ImportBeanDefinitionRegistrar {
    private BeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.debug("Introspecting " + importingClassMetadata.getClassName());
    }
}
