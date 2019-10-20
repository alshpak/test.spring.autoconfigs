//package test.spring.autoconfig.sampleapp.one.regs.support;
//
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//import static org.springframework.web.reactive.function.server.ServerResponse.ok;
//
//@Setter
//@Slf4j
//public class WebRouterFactoryBean implements FactoryBean, ApplicationContextAware {
//
//    private Class<Object> modelClass;
//    private String accessServiceBeanName;
//    private ApplicationContext applicationContext;
//
//
//    @Override
//    public Object getObject() throws Exception {
//
//        log.warn("Constructing the web router");
//        return route()
//                .GET("/reg1/{id}", accept(MediaType.APPLICATION_JSON), this::handleGetById)
//                .build();
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return RouterFunction.class;
//    }
//
//    public Mono<ServerResponse> handleGetById(ServerRequest request) {
//        long id = Long.parseLong(request.pathVariable("id"));
//
//        RegistryAccessService<?> registryAccessService =
//                (RegistryAccessService<?>) applicationContext.getBean(accessServiceBeanName);
//
//        Object result = registryAccessService.getById(id);
//
//        return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(result), modelClass);
//    }
//}
