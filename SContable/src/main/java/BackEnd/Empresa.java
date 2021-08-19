package BackEnd;

import FiltrosEmpresa.cEmpresa;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="EMPRESAS")

public class Empresa extends SistemaContable {
    @Id
    @Column(name="idCuit")
    private int cuit;
    @ElementCollection
    @JoinColumn(name="codigoCuentaEmpresa")
    private List<Cuenta> planCuentas=new ArrayList<>();
    @ElementCollection
    @JoinColumn(name="numeroEjercicio")
    private List<Ejercicio>ejercicios=new ArrayList<>();

    @Column(name="nombreEmpresa")
    private String nombreEmpresa;
    @Column(name="direccion")
    private String direccion;
    @Column(name="localidad")
    private String localidad;
    @Column(name="telefono")
    private String telefono;


    public Empresa (int cuit,String nombre,String direccion,String localidad, String telefono){
        this.cuit = cuit;
        this.nombreEmpresa=nombre;
        this.direccion=direccion;
        this.localidad=localidad;
        this.telefono=telefono;
    }


    public Empresa() {
        super();
    }

    public int getCuit() {
        return cuit;
    }

    public ArrayList<Empresa> buscarEmpresa(cEmpresa c){
        ArrayList<Empresa> busqueda=new ArrayList<>();
        if (c.cumple(this))
            busqueda.add(this);
        return busqueda;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String toString(){
        return "Cuit: "+this.cuit+" Nombre Empresa: "+this.nombreEmpresa;
    }

    public List<Cuenta> getPlanCuentas(){//ordenar de menor a mayor
        List<Cuenta> planCuenta=new ArrayList<>();
        for (Cuenta c:planCuentas){
            planCuenta.addAll(c.getCuenta());
        }
        return planCuenta;
    }



    public Hashtable<Asiento,Double> gestionMayores(Date inicio,Date fin, boolean isAjustado,Cuenta cuenta,MesAjustado ajustado){
        Hashtable<Asiento,Double>aux=new Hashtable<>();
        for(Ejercicio ej:ejercicios){
            //if ((ej.getFechaIni().after(inicio))&&(ej.getFechaCier().before(fin))) {
                for (Map.Entry<Asiento, Double> entry : ej.listarAsientos(cuenta,isAjustado,ajustado).entrySet()) {
                    Asiento asientos=entry.getKey();
                    double valor=entry.getValue();
                    aux.put(asientos,valor);
                }
            //}
        }
        return aux;
    }

    public Hashtable<Integer,Double> gestionSumaSaldo(Date inicio, Date fin, boolean isAjustado,MesAjustado ajustado){
        int cantElementos=0;
        Hashtable<Integer,Double>aux = new Hashtable<>();
        for(Ejercicio ej:ejercicios) {
            if ((ej.getFechaIni().after(inicio)) && (ej.getFechaCier().before(fin))) {
                for (Map.Entry<Integer, Double> entry : ej.listarCuentas(isAjustado,ajustado).entrySet()) {
                    Integer cuenta = entry.getKey();
                    double valor=entry.getValue();
                    cantElementos++;
                    if (!aux.isEmpty()) {
                        for (Map.Entry<Integer, Double> hashFilter : aux.entrySet()) {
                            Integer cuentaFiltrada = hashFilter.getKey();
                            Double valorFiltrado = hashFilter.getValue();
                            if (cuentaFiltrada.equals(cuenta)) {
                                aux.put(cuenta, valorFiltrado + valor);
                                break;
                            } else if (aux.size() + 1 == cantElementos) {
                                aux.put(cuenta, valor);
                                break;
                            }
                        }
                    } else {
                        aux.put(cuenta, valor);
                    }
                }
            }

        }
        return aux;
    }

    public void addCuenta(Cuenta cuenta){
        this.planCuentas.add(cuenta);
    }

    public void setEmpresa(int cuit,String nombre,String direccion,String localidad, String telefono){
        this.cuit=cuit;
        this.nombreEmpresa=nombre;
        this.direccion=direccion;
        this.localidad=localidad;
        this.telefono=telefono;
    }
    public void addEjercicio(Ejercicio e){
        this.ejercicios.add(e);
    }
    public void imprimirSumaSaldo(Hashtable<Integer,Double> tabla){
        double totalD=0;
        double totalH=0;
        for (Map.Entry<Integer, Double> entry : tabla.entrySet()) {
            Integer nrocuenta = entry.getKey();
            double valor=entry.getValue();
            for(Cuenta pi:planCuentas) {
                List<Cuenta> pci = pi.getCuentaImputable();
                for (Cuenta ci : pci){
                    if (ci.getCodigoCuenta() == nrocuenta) {
                        if (ci.getTipo() == 'd') {
                            totalD += valor;
                        } else {
                            totalH += valor;
                        }
                        System.out.println(ci.toString() + " | Cantidad: " + valor);
                    }
                }
            }
        }
        System.out.println("\n Total: "+totalD+" | Total: "+totalH);
    }


    public void imprimirMayores(Hashtable<Asiento, Double> gestionMayores) {
        double totalD=0;
        double totalH=0;
        System.out.println("                                       |  Debe  | Haber        ");
        for (Map.Entry<Asiento, Double> entry : gestionMayores.entrySet()) {
            Asiento asientoI = entry.getKey();
            double valor = entry.getValue();
            for(Cuenta pi:asientoI.getListaCuentas()) {
                if (pi.getTipo()=='d'){
                    totalD+=valor;
                    System.out.println(asientoI.toString()+"-Cuenta : "+pi+" |"+valor+"|");
                }else{
                    totalH+=valor;
                    System.out.println(asientoI.toString()+"-Cuenta : "+pi+" |        |"+valor+"|");
                }
            }
        }
        System.out.println("\n Total: "+totalD+" | Total: "+totalH);
    }

    public List<Ejercicio> getEjercicios() {
        return new ArrayList<>(this.ejercicios);
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTelefono() {
        return telefono;
    }
}

