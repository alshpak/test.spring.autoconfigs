package test.spring.autoconfig.sampleapp.one.regs.support;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Setter
@Slf4j
public class WebRouterFactoryBean implements FactoryBean, ApplicationContextAware {

    private Class<Object> modelClass;
    private String accessServiceBeanName;
    private ApplicationContext applicationContext;
    private String path;


    @Override
    public Object getObject() throws Exception {

        log.warn("Constructing the web router");
        return route()
                .GET("/" + path + "/{id}", accept(MediaType.APPLICATION_JSON), this::handleGetById)
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return RouterFunction.class;
    }

    public ServerResponse handleGetById(ServerRequest request) {
        long id = Long.parseLong(request.pathVariable("id"));

        RegistryAccessService<?> registryAccessService =
                (RegistryAccessService<?>) applicationContext.getBean(accessServiceBeanName);

        Object result = registryAccessService.getById(id);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
