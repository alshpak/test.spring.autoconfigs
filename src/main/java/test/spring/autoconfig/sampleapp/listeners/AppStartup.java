package test.spring.autoconfig.sampleapp.listeners;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import test.spring.autoconfig.sampleapp.one.OneJpaRepository;
import test.spring.autoconfig.sampleapp.one.SampleConfigOne;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import java.sql.ResultSet;

@Component
@Slf4j
public class AppStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SampleConfigOne sampleConfig;
    @Autowired
    @Qualifier("myFirstDataSource")
    private DataSource dataSource;

    @PersistenceContext(unitName = "myFirstPersistenceUnit")
    private EntityManager em;

    @Autowired
    private OneJpaRepository oneRepository;

    @Override
    @SneakyThrows
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("App ready notice");
        log.debug("Sample config(w): " + sampleConfig);
        log.debug("Sample config(i): " + applicationContext.getBean("sampleConfigOne"));
        //log.debug("My DS prefix {}", object.getPrefix());


        //log.info("Injected DS of type {}", applicationContext.getBean("myDataSourceOne").getClass().getName());

        for (String beanName : applicationContext.getBeanNamesForType(EntityManager.class)) {
            log.info("DS bean name {}", beanName);
        }

        log.info("Queried data from DB count is {}", em.createNativeQuery("select * from one").getResultList().size())
        ;
        ;
//        applicationContext.getBean("[]");

        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT * from one");
        resultSet.next();
        log.info("Result of select {}", resultSet.getString(1));

        oneRepository.findAll().forEach(one -> log.info("Queried entity " + one.toString()));
    }
}
