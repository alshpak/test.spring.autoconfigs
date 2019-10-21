package test.spring.autoconfig.sampleapp.one;

import lombok.Data;
import test.spring.autoconfig.sampleapp.one.regs.support.Registry;

@Registry(
        entity = Reg2Entity.class,
        mapper = RegMappers.class,
        entityToDtoMethod = "entityReg2ToDto",
        path = "reg2"
)
@Data
public class Reg2 {
    Long id;
    String code;
    String title;
}
