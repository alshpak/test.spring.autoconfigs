package test.spring.autoconfig.sampleapp.one;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegMappers {

    RegMappers INSTANCE = Mappers.getMapper(RegMappers.class);

    Reg1 entityReg1ToDto(Reg1Entity entity);

    Reg2 entityReg2ToDto(Reg2Entity entity);

}
