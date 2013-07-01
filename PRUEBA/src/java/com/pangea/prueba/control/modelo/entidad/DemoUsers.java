/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pangea.prueba.control.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "DEMO_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemoUsers.findAll", query = "SELECT d FROM DemoUsers d"),
    @NamedQuery(name = "DemoUsers.findByUserId", query = "SELECT d FROM DemoUsers d WHERE d.userId = :userId"),
    @NamedQuery(name = "DemoUsers.findByUserName", query = "SELECT d FROM DemoUsers d WHERE d.userName = :userName"),
    @NamedQuery(name = "DemoUsers.findByPassword", query = "SELECT d FROM DemoUsers d WHERE d.password = :password"),
    @NamedQuery(name = "DemoUsers.findByCreatedOn", query = "SELECT d FROM DemoUsers d WHERE d.createdOn = :createdOn"),
    @NamedQuery(name = "DemoUsers.findByQuota", query = "SELECT d FROM DemoUsers d WHERE d.quota = :quota"),
    @NamedQuery(name = "DemoUsers.findByProducts", query = "SELECT d FROM DemoUsers d WHERE d.products = :products"),
    @NamedQuery(name = "DemoUsers.findByExpiresOn", query = "SELECT d FROM DemoUsers d WHERE d.expiresOn = :expiresOn"),
    @NamedQuery(name = "DemoUsers.findByAdminUser", query = "SELECT d FROM DemoUsers d WHERE d.adminUser = :adminUser")})
public class DemoUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @Size(max = 100)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 4000)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "QUOTA")
    private BigInteger quota;
    @Column(name = "PRODUCTS")
    private Character products;
    @Column(name = "EXPIRES_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresOn;
    @Column(name = "ADMIN_USER")
    private Character adminUser;

    public DemoUsers() {
    }

    public DemoUsers(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public BigInteger getQuota() {
        return quota;
    }

    public void setQuota(BigInteger quota) {
        this.quota = quota;
    }

    public Character getProducts() {
        return products;
    }

    public void setProducts(Character products) {
        this.products = products;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }

    public Character getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(Character adminUser) {
        this.adminUser = adminUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemoUsers)) {
            return false;
        }
        DemoUsers other = (DemoUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pangea.prueba.control.modelo.entidad.DemoUsers[ userId=" + userId + " ]";
    }
    
}
