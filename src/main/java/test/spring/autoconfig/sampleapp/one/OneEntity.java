package test.spring.autoconfig.sampleapp.one;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "one")
@Data
public class OneEntity {

    @Id
    @Column(name = "col_one")
    String one;
}
