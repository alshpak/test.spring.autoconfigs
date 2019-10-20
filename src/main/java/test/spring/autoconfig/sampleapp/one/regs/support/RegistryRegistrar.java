package test.spring.autoconfig.sampleapp.one.regs.support;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

@Slf4j
public class RegistryRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    @SneakyThrows
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = new AnnotationAttributes(metadata.getAnnotationAttributes(Registry.class.getName()));

        Class<?> modelClass = Class.forName(metadata.getClassName());
        Class<?> entityClass = attributes.getClass("entity");

        log.warn("Constructing the registry for " + modelClass.getName());


        RootBeanDefinition regSrvDefinition = new RootBeanDefinition(RegistryAccessServiceImpl.class);
        regSrvDefinition.setTargetType(ResolvableType.forClassWithGenerics(RegistryAccessService.class, modelClass));
        regSrvDefinition.getPropertyValues().addPropertyValue("entityClass", entityClass);
        regSrvDefinition.getPropertyValues().addPropertyValue("modelClass", modelClass);
        regSrvDefinition.getPropertyValues().addPropertyValue("mapper", attributes.get("mapper"));
        regSrvDefinition.getPropertyValues().addPropertyValue("entityToDtoMethod", attributes.get("entityToDtoMethod"));

        String registryServiceBean = entityClass.getSimpleName() + "Registry";
        registry.registerBeanDefinition(registryServiceBean, regSrvDefinition);

//        RootBeanDefinition accessCtrlDef = new RootBeanDefinition(WebRouterFactoryBean.class);
//        accessCtrlDef.getPropertyValues().addPropertyValue("modelClass", modelClass);
//        accessCtrlDef.getPropertyValues().addPropertyValue("accessServiceBeanName", registryServiceBean);
//        registry.registerBeanDefinition(entityClass.getSimpleName() + "Controller", accessCtrlDef);

    }
}
