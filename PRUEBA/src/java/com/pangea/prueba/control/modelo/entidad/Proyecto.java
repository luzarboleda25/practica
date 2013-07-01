/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pangea.prueba.control.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByPoryectoid", query = "SELECT p FROM Proyecto p WHERE p.poryectoid = :poryectoid"),
    @NamedQuery(name = "Proyecto.findByNombre", query = "SELECT p FROM Proyecto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Proyecto.findByDescripcion", query = "SELECT p FROM Proyecto p WHERE p.descripcion = :descripcion")})
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORYECTOID")
    private BigDecimal poryectoid;
    @Size(max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 30)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMPLEADOID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado empId;

    public Proyecto() {
    }

    public Proyecto(BigDecimal poryectoid) {
        this.poryectoid = poryectoid;
    }

    public BigDecimal getPoryectoid() {
        return poryectoid;
    }

    public void setPoryectoid(BigDecimal poryectoid) {
        this.poryectoid = poryectoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Empleado getEmpId() {
        return empId;
    }

    public void setEmpId(Empleado empId) {
        this.empId = empId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poryectoid != null ? poryectoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.poryectoid == null && other.poryectoid != null) || (this.poryectoid != null && !this.poryectoid.equals(other.poryectoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.prueba.control.modelo.entidad.Proyecto[ poryectoid=" + poryectoid + " ]";
    }
    
}
