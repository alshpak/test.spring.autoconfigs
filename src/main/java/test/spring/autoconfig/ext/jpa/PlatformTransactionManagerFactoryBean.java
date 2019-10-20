package test.spring.autoconfig.ext.jpa;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Setter
public class PlatformTransactionManagerFactoryBean
        implements FactoryBean<PlatformTransactionManager>, ApplicationContextAware {

    private String entityManagerFactoryName;
    private ApplicationContext applicationContext;

    @Override
    public PlatformTransactionManager getObject() throws Exception {

        EntityManagerFactory emf = (EntityManagerFactory) applicationContext.getBean(entityManagerFactoryName);
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Override
    public Class<?> getObjectType() {
        return PlatformTransactionManager.class;
    }
}
