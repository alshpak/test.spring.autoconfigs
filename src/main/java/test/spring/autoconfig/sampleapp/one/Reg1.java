package test.spring.autoconfig.sampleapp.one;

import lombok.Data;
import test.spring.autoconfig.sampleapp.one.regs.support.Registry;

@Data
@Registry(
        entity = Reg1Entity.class,
        mapper = Reg1Mapper.class,
        entityToDtoMethod = "entityToDto"
)
public class Reg1 {

    private Long id;
    private String name;
    private String descr;

}
