package test.spring.autoconfig.ext.jpa;

import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Setter
public class ConfiguredLocalContainerEntityManagerFactoryBean
        extends LocalContainerEntityManagerFactoryBean
        implements ApplicationContextAware, EnvironmentAware {
    private String dataSourceName;
    private String propertiesPrefix;
    private String entitiesPackage;
    private ApplicationContext applicationContext;
    private Environment environment;

    @Override
    public void afterPropertiesSet() throws PersistenceException {
        DataSource dataSource = (DataSource) applicationContext.getBean(dataSourceName);
        setDataSource(dataSource);
        setPackagesToScan(entitiesPackage);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", property("hbm2ddl.auto"));
        properties.put("hibernate.dialect", property("dialect"));
        setJpaPropertyMap(properties);

        super.afterPropertiesSet();
    }

    private String property(String postfix) {
        return environment.getProperty(propertiesPrefix + ".hibernate." + postfix);
    }
}
