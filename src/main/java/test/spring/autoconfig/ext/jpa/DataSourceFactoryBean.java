package test.spring.autoconfig.ext.jpa;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Setter
@Slf4j
public class DataSourceFactoryBean implements FactoryBean<DataSource>, EnvironmentAware {
    private Environment environment;
    private String propertiesPrefix;

    @Override
    public DataSource getObject() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(property("driverClassName"));
        dataSource.setUrl(property("url"));
        dataSource.setUsername(property("user"));
        dataSource.setPassword(property("pass"));

        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    private String property(String postfix) {
        String property = propertiesPrefix + ".jdbc." + postfix;
        String value = environment.getProperty(property);
        if (value == null) throw new RuntimeException("No value for property " + property);
        return value;
    }
}
