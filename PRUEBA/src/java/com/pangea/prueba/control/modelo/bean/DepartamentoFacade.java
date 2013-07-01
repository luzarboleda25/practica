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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "PRUEBAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public List<Departamento> listarDepartemento() {
        List<Departamento> departament = null;
        departament = findAll();
        return departament;
    }

    public List<Departamento> listarDepartementoconNombreMayor6() {
        List<Departamento> departament = null;
        departament = em.createNamedQuery("Departamento.findByNumeroLetras").getResultList();

        return departament;
    }

    public List<String[]> promedioSueldoPorDepartamentoJPQL() {
        List<Departamento> departament = null;
        List<String[]> listaMostrar = new ArrayList<String[]>();
        departament = em.createNamedQuery("Departamento.findAll").getResultList();
        double suma = 0;

        for (int i = 0; i < departament.size(); i++) {
            suma = 0;
            for (int j = 0; j < departament.get(i).getEmpleadoList().size(); j++) {

                suma += departament.get(i).getEmpleadoList().get(j).getSueldo();

            }
            String vect[] = new String[2];
            vect[1] = String.valueOf(suma / departament.get(i).getEmpleadoList().size());
            vect[0] = departament.get(i).getNombre();
            listaMostrar.add(vect);
        }

        return listaMostrar;
    }

    public List<String[]> promedioSueldoPorDepartamentoSQL() {
        List<Object[]> listac = null;
        listac = em.createNativeQuery("SELECT Departamento.nombre, AVG(empleado.sueldo) FROM departamento,empleado where departamento.DEPARTAMENTOID = empleado.DEPARTAMENTOID GROUP BY Departamento.nombre").getResultList();

        List<String[]> lista = new ArrayList<String[]>();

        BigDecimal promedio;
        for (int i = 0; i < listac.size(); i++) {
            String[] vect = new String[2];
            vect[0] = new String((String) listac.get(i)[0]);
            promedio = (BigDecimal) listac.get(i)[1];
            vect[1] = new String(promedio.toString());
            lista.add(vect);
        }
        return lista;
    }

    public List<String[]> nroEmpleadosPorDepartamentoSQL() {
        List<Object[]> listac = null;
        listac = em.createNativeQuery("SELECT Departamento.nombre, count(empleado.EMPLEADOID) FROM departamento,empleado where departamento.DEPARTAMENTOID=empleado.DEPARTAMENTOID GROUP BY Departamento.nombre").getResultList();
        List<String[]> lista = new ArrayList<String[]>();
        BigDecimal cant;
        for (int i = 0; i < listac.size(); i++) {
            String[] vect = new String[2];
            vect[0] = new String((String) listac.get(i)[0]);
            cant = (BigDecimal) listac.get(i)[1];
            vect[1] = new String(cant.toString());
            lista.add(vect);
        }
        return lista;
    }

    public List<String[]> nroEmpleadosPorDepartamentoJPQL() {
        List<Departamento> departament = null;
        List<String[]> listaMostrar = new ArrayList<String[]>();
        departament = em.createNamedQuery("Departamento.findAll").getResultList();
        for (int i = 0; i < departament.size(); i++) {
            String vect[] = new String[2];
             vect[0] = departament.get(i).getNombre();
            vect[1] = String.valueOf(departament.get(i).getEmpleadoList().size());
            listaMostrar.add(vect);
        }
        return listaMostrar;
    }
      public Departamento descripDepartamento(String nombre) {
        Departamento depa = null;
        depa = (Departamento) em.createNamedQuery("Departamento.findByNombre").setParameter("nombre", nombre).getSingleResult();
        return depa;
    }

}
