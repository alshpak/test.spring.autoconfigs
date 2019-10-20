package test.spring.autoconfig.sampleapp.one.regs.support;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Method;

@Slf4j
@Setter
public class RegistryAccessServiceImpl<MODEL> implements RegistryAccessService<MODEL> {

    private Class<MODEL> modelClass;
    private Class<?> entityClass;
    private Class<?> mapper;
    private String entityToDtoMethod;

    @PersistenceContext(unitName = "myFirstPersistenceUnit")
    private EntityManager em;

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public MODEL getById(Long id) {
        log.warn("Got entity of type " + entityClass.getName());

        Object entity = em.find(entityClass, id);

        Object mapper = Mappers.getMapper(this.mapper);
        Method mappingMethod = mapper.getClass().getMethod(entityToDtoMethod, entityClass);
        MODEL dto = (MODEL) mappingMethod.invoke(mapper, entity);

        return dto;
    }
}
