package test.spring.autoconfig.sampleapp.one.regs.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public interface RegistryAccessService<MODEL> {

    MODEL getById(Long id);

}
