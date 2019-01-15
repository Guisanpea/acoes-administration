

package models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "registro_economico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroEconomico.findAll", query = "SELECT r FROM RegistroEconomico r")
    , @NamedQuery(name = "RegistroEconomico.findById", query = "SELECT r FROM RegistroEconomico r WHERE r.id = :id")
    , @NamedQuery(name = "RegistroEconomico.findByFecha", query = "SELECT r FROM RegistroEconomico r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RegistroEconomico.findByTipo", query = "SELECT r FROM RegistroEconomico r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "RegistroEconomico.findByConcepto", query = "SELECT r FROM RegistroEconomico r WHERE r.concepto = :concepto")
    , @NamedQuery(name = "RegistroEconomico.findByImporte", query = "SELECT r FROM RegistroEconomico r WHERE r.importe = :importe")
    , @NamedQuery(name = "RegistroEconomico.findByObservaciones", query = "SELECT r FROM RegistroEconomico r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RegistroEconomico.findByCodigoServicio", query = "SELECT r FROM RegistroEconomico r WHERE r.codigoServicio = :codigoServicio")})
public class RegistroEconomico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Size(min = 1, max = 7)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private double importe;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "codigo_servicio")
    private Integer codigoServicio;
    @JoinColumn(name = "numero_socio", referencedColumnName = "numero_de_socio")
    @ManyToOne
    private Socio numeroSocio;
    @JoinColumn(name = "codigo_beneficiario", referencedColumnName = "codigo")
    @ManyToOne
    private Alumno codigoBeneficiario;
    @JoinColumn(name = "responsable", referencedColumnName = "id")
    @ManyToOne
    private Usuario responsable;
    @JoinColumn(name = "proyecto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public RegistroEconomico() {
    }

    public RegistroEconomico(Integer id) {
        this.id = id;
    }

    public RegistroEconomico(Integer id, Date fecha, String tipo, String concepto, double importe) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.concepto = concepto;
        this.importe = importe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(Integer codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public Socio getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(Socio numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public Alumno getCodigoBeneficiario() {
        return codigoBeneficiario;
    }

    public void setCodigoBeneficiario(Alumno codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroEconomico)) {
            return false;
        }
        RegistroEconomico other = (RegistroEconomico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.RegistroEconomico[ id=" + id + " ]";
    }

}
