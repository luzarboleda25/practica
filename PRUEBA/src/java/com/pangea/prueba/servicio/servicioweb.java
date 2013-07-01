/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pangea.prueba.servicio;

import com.pangea.prueba.control.modelo.bean.CargoFacade;
import com.pangea.prueba.control.modelo.bean.DepartamentoFacade;
import com.pangea.prueba.control.modelo.bean.EmpleadoFacade;
import com.pangea.prueba.control.modelo.entidad.Cargo;
import com.pangea.prueba.control.modelo.entidad.Departamento;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pc
 */
@WebService(serviceName = "servicioweb")
public class servicioweb {

    @EJB
    DepartamentoFacade departamentoFacade;
    @EJB
    CargoFacade cargoFacade;
    @EJB
    EmpleadoFacade empleado;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {

        return "Hola  " + txt + " esta es una prueba!";
    }

    @WebMethod(operationName = "listaDepartemento")
    public List<Departamento> listaDepartemento() {
        departamentoFacade.listarDepartemento();
        return departamentoFacade.listarDepartemento();

    }

    @WebMethod(operationName = "descipcionCargo")
    public Cargo descipcionCargo(@WebParam(name = "name") String txt) {
        return cargoFacade.descripCargo(txt);
    }

    @WebMethod(operationName = "departamentoMayores6")
    public List<Departamento> departamentoMayores6() {
        return departamentoFacade.listarDepartementoconNombreMayor6();
    }

    @WebMethod(operationName = "promedioPorDepartamentoSQL")
    public List<String[]> promedioPorDepartamentoSQL() {
        return departamentoFacade.promedioSueldoPorDepartamentoSQL();
    }

    @WebMethod(operationName = "promedioPorDepartamentoJPQL")
    public List<String[]> promedioPorDepartamentoJPQL() {
        return departamentoFacade.promedioSueldoPorDepartamentoJPQL();
    }

    @WebMethod(operationName = "NoroEmpleadosPorDepartamentoSQL")
    public List<String[]> NoroEmpleadosPorDepartamentoSQL() {
        return departamentoFacade.nroEmpleadosPorDepartamentoSQL();
    }

    @WebMethod(operationName = "NoroEmpleadosPorDepartamentoJPQL")
    public List<String[]> NoroEmpleadosPorDepartamentoJPQL() {
        return departamentoFacade.nroEmpleadosPorDepartamentoJPQL();
    }

    @WebMethod(operationName = "PromedioSueldoGeneralSQL")
    public BigDecimal PromedioSueldoGeneralSQL() {
        return empleado.PromedioGeneralEmpleadosSQL();
    }

    @WebMethod(operationName = "PromedioSueldoGeneralJPQL")
    public double PromedioSueldoGeneralJPQL() {
        return empleado.PromedioGeneralEmpleadosJPQL();
    }

    @WebMethod(operationName = "empleadoConDepartamentoCargoSQL")
    public List<String[]> empleadoConDepartamentoCargoSQL() {
        return empleado.empleadoConDepartamentoCargoSQL();
    }

    @WebMethod(operationName = "empleadoConDepartamentoCargoJPQL")
    public List<String[]> empleadoConDepartamentoCargoJPQL() {
        return empleado.empleadoConDepartamentoCargoJPQL();
    }
}
