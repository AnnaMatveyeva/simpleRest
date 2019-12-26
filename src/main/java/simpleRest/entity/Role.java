package simpleRest.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

}
