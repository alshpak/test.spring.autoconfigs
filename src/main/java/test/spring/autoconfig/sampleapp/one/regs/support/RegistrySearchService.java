package test.spring.autoconfig.sampleapp.one.regs.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface RegistrySearchService<MODEL> {

    @AllArgsConstructor
    @Getter
    class RegistryField {
        private String field;
        private Object value;
    }

    List<MODEL> findAllByFields(List<RegistryField> fields);

}
