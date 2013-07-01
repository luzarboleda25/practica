/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pangea.prueba.control.modelo.bean;

import com.pangea.prueba.control.modelo.entidad.Cargo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class CargoFacade extends AbstractFacade<Cargo> {

    @PersistenceContext(unitName = "PRUEBAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoFacade() {
        super(Cargo.class);
    }

    public List<Cargo> retornaCargosList() {
        List<Cargo> lista = null;
        lista = findAll();
        return lista;
    }

    public Cargo descripCargo(String nombre) {
        Cargo cargo = null;
        cargo = (Cargo) em.createNamedQuery("Cargo.findByNombre").setParameter("nombre", nombre).getSingleResult();
        return cargo;
    }
}
