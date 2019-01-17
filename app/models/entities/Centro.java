

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "centro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centro.findAll", query = "SELECT c FROM Centro c")
    , @NamedQuery(name = "Centro.findById", query = "SELECT c FROM Centro c WHERE c.id = :id")
    , @NamedQuery(name = "Centro.findByNombre", query = "SELECT c FROM Centro c WHERE c.nombre = :nombre")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Centro implements Serializable {

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
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "proyecto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @OneToMany(mappedBy = "centro")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centro1")
    private Collection<EstaEn> estaEnCollection;
}
