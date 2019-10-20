package test.spring.autoconfig.sampleapp.one;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reg1")
@Data
public class Reg1Entity {



    @Id
    private Long id;

    private String name;

    private String descr;
}
