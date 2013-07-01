/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pangea.prueba.control.modelo.bean;

import com.pangea.prueba.control.modelo.entidad.Cargo;
import com.pangea.prueba.control.modelo.entidad.Departamento;
import com.pangea.prueba.control.modelo.entidad.Empleado;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "PRUEBAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public BigDecimal PromedioGeneralEmpleadosSQL() {
        return (BigDecimal) (em.createNativeQuery("SELECT avg(empleado.sueldo) FROM empleado ").getSingleResult());
    }

    public double PromedioGeneralEmpleadosJPQL() {
        List<Empleado> empleado = null;
        empleado = em.createNamedQuery("Empleado.findAll").getResultList();
        double sum = 0;
        for (int i = 0; i < empleado.size(); i++) {
            sum += empleado.get(i).getSueldo();
        }
        return sum / empleado.size();
    }

    public List<String[]> empleadoConDepartamentoCargoJPQL() {
        List<Empleado> empleado = null;
        List<String[]> listaMostrar = new ArrayList<String[]>();
        empleado = em.createNamedQuery("Empleado.findAll").getResultList();
        for (int i = 0; i < empleado.size(); i++) {
            String vect[] = new String[3];
            vect[0] = empleado.get(i).getNombre();
            vect[1] = empleado.get(i).getDepartamentoid().getNombre();
            vect[2] = empleado.get(i).getCargoid().getNombre();
            listaMostrar.add(vect);
        }
        return listaMostrar;
    }

    public List<String[]> empleadoConDepartamentoCargoSQL() {
        List<Object[]> listac = null;
        listac = em.createNativeQuery("SELECT empleado.nombre,departamento.nombre,cargo.nombre FROM empleado,departamento,cargo where empleado.DEPARTAMENTOID=departamento.DEPARTAMENTOID and empleado.cargoid=cargo.cargoid").getResultList();
        List<String[]> lista = new ArrayList<String[]>();
        for (int i = 0; i < listac.size(); i++) {
            String[] vect = new String[3];
            vect[0] = new String((String) listac.get(i)[0]);
            vect[1] = new String((String) listac.get(i)[1]);
            vect[2] = new String((String) listac.get(i)[2]);
            lista.add(vect);
        }
        return lista;
    }
    public Empleado ApellidoEmpleado(String nombre) {
        Empleado emp = null;
        emp = (Empleado) em.createNamedQuery("Empleado.findByNombre").setParameter("nombre", nombre).getSingleResult();
        return emp;
    }
}
