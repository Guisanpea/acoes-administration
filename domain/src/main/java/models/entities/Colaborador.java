

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "colaborador")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Colaborador.findAll", query = "SELECT c FROM Colaborador c")
      , @NamedQuery(name = "Colaborador.findById", query = "SELECT c FROM Colaborador c WHERE c.id = :id")
      , @NamedQuery(name = "Colaborador.findByNombre", query = "SELECT c FROM Colaborador c WHERE c.nombre = :nombre")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Colaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "beneficiarioColaborador")
    private Collection<Egreso> egresoCollection;

}
