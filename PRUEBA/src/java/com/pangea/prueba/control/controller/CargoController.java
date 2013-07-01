package com.pangea.prueba.control.controller;

import com.pangea.prueba.control.modelo.entidad.Cargo;
import com.pangea.prueba.control.controller.util.JsfUtil;
import com.pangea.prueba.control.controller.util.PaginationHelper;
import com.pangea.prueba.control.modelo.bean.CargoFacade;
import java.awt.event.ActionEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.context.FacesContext;
import java.util.UUID;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "cargoController")
@SessionScoped
public class CargoController implements Serializable {
// ALT INSERT PARA AGREGAR SET Y GET EN ATRIBUTOS

    private Cargo current;

    public Cargo getCurrent() {
        return current;
    }
    private DataModel items = null;
    @EJB
    private com.pangea.prueba.control.modelo.bean.CargoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String nombre, apellido;
    private Date date1;
    //bloc de notas
    private String value;
//ejemplo de implace
    private String text = "PrimeFaces";
// ejemplo de lista 
    private List<String> selectedMovies;
    private Map<String, String> movies;
//EJEMPLO DE CAMPOS DE PASWORD
    private String password1;
    private String password2;
    private String password3;
    private String password4;
    private String password5;
//EJEMPLO DE ESTRELLITAS
    private Integer rating1;
    private Integer rating2;
    private Integer rating3;
    private Integer rating4 = 3;
    // ejemplo de Grafico de Ovalos unidos
    private MindmapNode root;
    private MindmapNode selectedNode;
    //String para guardar nombre
    private String buscar;
    private Cargo descripcion;
    private List<Cargo> cargos;
    /**
     * LAZYYY *
     */
    private LazyDataModel<Cargo> lazyModel;
    private int pagIndex = 0;
    private Map<String, String> fields = new HashMap<String, String>();
    private String sortF = null;
    private boolean sortB = false;
    private int paginacion = 10;

    /**
     * FIN LAZY *
     */
    public void setCurrent(Cargo current) {
        this.current = current;
    }

    public Cargo getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Cargo descripcion) {
        this.descripcion = descripcion;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();

        //populate if not already loaded  
        if (node.getChildren().isEmpty()) {
            Object label = node.getLabel();

            if (label.equals("NS(s)")) {
                for (int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542"));
                }
            } else if (label.equals("IPs")) {
                for (int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1." + i, "IP Number: 1.1.1." + i, "fce24f"));
                }

            } else if (label.equals("Malware")) {
                for (int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-" + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }

    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }

    public Integer getRating1() {
        return rating1;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    public Integer getRating2() {
        return rating2;
    }

    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }

    public Integer getRating3() {
        return rating3;
    }

    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }

    public Integer getRating4() {
        return rating4;
    }

    public void setRating4(Integer rating4) {
        this.rating4 = rating4;
    }

    public String getPassword5() {
        return password5;
    }

    public void setPassword5(String password5) {
        this.password5 = password5;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword3() {
        return password3;
    }

    public void setPassword3(String password3) {
        this.password3 = password3;
    }

    public String getPassword4() {
        return password4;
    }

    public void setPassword4(String password4) {
        this.password4 = password4;
    }

    public List<String> getSelectedMovies() {
        return selectedMovies;
    }

    public void setSelectedMovies(List<String> selectedMovies) {
        this.selectedMovies = selectedMovies;
    }

    public Map<String, String> getMovies() {
        return movies;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public CargoController() {
        nombre = "mariela";
        apellido = "Arboleda";
        movies = new HashMap<String, String>();
        movies.put("Scarface", "Scarface");
        movies.put("Goodfellas", "Goodfellas");
        movies.put("Godfather", "Godfather");
        movies.put("Carlito's Way", "Carlito's Way");

        //Ejemplo de Grafico de Ovalos
        root = new DefaultMindmapNode("google.com", "Google WebSite", "FFCC00", false);
        MindmapNode ips = new DefaultMindmapNode("IPs", "IP Numbers", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("NS(s)", "Namespaces", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("Malware", "Malicious Software", "6e9ebf", true);
        root.addNode(ips);
        root.addNode(ns);
        root.addNode(malware);
        /*
         carsSmall = new ArrayList<Car>();  
  
         populateRandomCars(carsSmall, 9);  
         manufacturerOptions = createFilterOptions(manufacturers);  
         */
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Cargo getSelected() {
        if (current == null) {
            current = new Cargo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CargoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Cargo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Cargo();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {

            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CargoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void otro() {
        descripcion = getFacade().descripCargo(buscar);
    }

    public String prepareEdit() {
        current = (Cargo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CargoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Cargo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public void Eliminar_Cargo_() {
        System.out.println("Elimino:   " + current.getDescripcion());
        Cargo c = getFacade().find(current.getCargoid());
        if (c.getEmpleadoList().size() == 0) {
            getFacade().remove(c);
            mostrarMensaje(0, "Elimino", "Se elimino Satisfactoriamente");
        }else{
            mostrarMensaje(1, "No Elimino", "No Se Elimino por tener una relación con Empleado");
        }
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CargoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Cargo.class)
    public static class CargoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CargoController controller = (CargoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cargoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cargo) {
                Cargo o = (Cargo) object;
                return getStringKey(o.getCargoid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cargo.class.getName());
            }
        }
    }

    /**
     * INICIO *
     */
    public void inicializarLazy() {
        lazyModel = new LazyDataModel<Cargo>() {
            public List<Cargo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                paginacion = pageSize;
                int cantidad = getFacade().count();
                if (cantidad > 0) {
                    pagIndex = first;
                    fields = filters;
                    sortF = sortField;
                    sortB = true;
                    String cadena = "";
                    lazyModel.setWrappedData(null);
                    cantidad = getFacade().count();
                    lazyModel.setRowCount(cantidad);
                    return getFacade().findRange(sortF, sortB, new int[]{first, first + pageSize}, filters, cadena);

                }
                return null;
                // return lazymv;
            }
        };

        lazyModel.setRowCount((int) getFacade().count());
        //lazyModel.setRowCount(10);

        if (lazyModel.getPageSize() == 0) {
            lazyModel.setPageSize(1);
        }
        if (lazyModel.getRowCount() == 0) {
            lazyModel.setRowCount(10);
        }

    }

    public LazyDataModel<Cargo> getLazyModel() {
        if (lazyModel == null) {
            inicializarLazy();
        }
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Cargo> lazyModel) {
        this.lazyModel = lazyModel;
    }
//fin lazy

    public void onEdit(RowEditEvent event) {
        Cargo c = (Cargo) event.getObject();
        getFacade().edit(c);
        System.out.println("Edito");
    }

    public void onCancel(RowEditEvent event) {
        System.out.println("Cancelo");

    }
     public void mostrarMensaje(int _opcMensaje, String _cabeceraMensaje, String _cuerpomensaje) {
         //0 info 1 advertenciA 2 error 3 fatal
        FacesContext context = FacesContext.getCurrentInstance();
        switch (_opcMensaje) {
            case 0: {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, _cabeceraMensaje, _cuerpomensaje));
                break;
            }
            case 1: {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, _cabeceraMensaje, _cuerpomensaje));
                break;
            }
            case 2: {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, _cabeceraMensaje, _cuerpomensaje));
                break;
            }
            case 3: {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, _cabeceraMensaje, _cuerpomensaje));
                break;
            }
        }
    }
}
