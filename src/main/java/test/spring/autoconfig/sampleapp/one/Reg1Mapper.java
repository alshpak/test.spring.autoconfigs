package test.spring.autoconfig.sampleapp.one;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Reg1Mapper {

    Reg1Mapper INSTANCE = Mappers.getMapper(Reg1Mapper.class);

    Reg1 entityToDto(Reg1Entity entity);

}
