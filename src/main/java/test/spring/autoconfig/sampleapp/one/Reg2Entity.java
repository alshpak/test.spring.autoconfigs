package test.spring.autoconfig.sampleapp.one;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reg2")
@Data
public class Reg2Entity {

    @Id
    Long id;
    String code;
    String title;

}
