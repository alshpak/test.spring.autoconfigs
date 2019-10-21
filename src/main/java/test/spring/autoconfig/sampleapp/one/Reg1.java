package test.spring.autoconfig.sampleapp.one;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import test.spring.autoconfig.sampleapp.one.regs.support.Registry;

@Data
@Registry(
        entity = Reg1Entity.class,
        mapper = RegMappers.class,
        entityToDtoMethod = "entityReg1ToDto",
        path = "reg1"
)
public class Reg1 {

    private Long id;
    private String name;
    private String descr;

}
