

package models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "envio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envio.findAll", query = "SELECT e FROM Envio e")
    , @NamedQuery(name = "Envio.findById", query = "SELECT e FROM Envio e WHERE e.envioPK.id = :id")
    , @NamedQuery(name = "Envio.findByEstado", query = "SELECT e FROM Envio e WHERE e.estado = :estado")
    , @NamedQuery(name = "Envio.findByApadrinamientoId", query = "SELECT e FROM Envio e WHERE e.envioPK.apadrinamientoId = :apadrinamientoId")
    , @NamedQuery(name = "Envio.findByApadrinamientoApadrinado", query = "SELECT e FROM Envio e WHERE e.envioPK.apadrinamientoApadrinado = :apadrinamientoApadrinado")
    , @NamedQuery(name = "Envio.findByApadrinamientoPadrino", query = "SELECT e FROM Envio e WHERE e.envioPK.apadrinamientoPadrino = :apadrinamientoPadrino")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    protected EnvioPK envioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "estado")
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "apadrinamiento_id", referencedColumnName = "id", insertable = false, updatable = false)
        , @JoinColumn(name = "apadrinamiento_apadrinado", referencedColumnName = "apadrinado", insertable = false, updatable = false)
        , @JoinColumn(name = "apadrinamiento_padrino", referencedColumnName = "padrino", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Apadrinamiento apadrinamiento;

}
