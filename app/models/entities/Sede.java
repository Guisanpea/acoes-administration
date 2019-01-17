

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
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
    , @NamedQuery(name = "Sede.findByRegion", query = "SELECT s FROM Sede s WHERE s.region = :region")
    , @NamedQuery(name = "Sede.findById", query = "SELECT s FROM Sede s WHERE s.id = :id")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "region")
    private String region;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;
    @OneToMany(mappedBy = "sedeUsuarioLocal")
    private Collection<Usuario> usuarioCollection;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

}
