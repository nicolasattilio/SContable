package BackEnd;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="ejercicios")

public class Ejercicio {
    @Id
    @Column(name="idNumero")
    private int numero;

    @ElementCollection
    @JoinColumn(name="numeroAsiento")
    private List<Asiento> asientos = new ArrayList<>();
    @Column(name="fechaInicio")
    private Date fechaIni;
    @Column(name="fechaCierre")
    private Date fechaCier;

    public Ejercicio(int numero, Date fechaIni, Date fechaCier) {
        this.numero = numero;
        this.fechaIni = fechaIni;
        this.fechaCier = fechaCier;
    }

    public Ejercicio() {

    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public Date getFechaCier() {
        return fechaCier;
    }

    public void addAsiento(Asiento asiento) {
        this.asientos.add(asiento);
    }

    public Hashtable<Asiento,Double> listarAsientos(Cuenta c, boolean isAjustado,MesAjustado ajustado) {
        Hashtable<Asiento,Double>lAsiento=new Hashtable<>();
        for (Asiento a : asientos) {
            for(Map.Entry<Cuenta,Double> entry:a.getListadoLibroDiario().entrySet()) {
                Cuenta cuenta = entry.getKey();
                double valor = entry.getValue();
                if (cuenta.getCodigoCuenta() == c.getCodigoCuenta()) {
                    List<Cuenta> cuentaInsert= Arrays.asList(cuenta);
                    Asiento asientoCodigo=new Asiento(a.getNumero(),a.getFecha(),cuentaInsert,a.getDetalle());
                    if ((isAjustado == true)&&(cuenta.isAjusta())) {
                        lAsiento.put(asientoCodigo, valor * ajustado.getValorMes(a.getFecha()));
                    } else {
                        lAsiento.put(asientoCodigo, valor);
                    }
                }
            }
        }
        return lAsiento;
    }


    public Hashtable<Integer,Double> listarCuentas(boolean isAjustado,MesAjustado ajustado) {
        Hashtable<Integer,Double>aux = new Hashtable<>();
        int cantElementos=0;
        for(Asiento a:asientos) {
            for(Map.Entry<Cuenta,Double> entry:a.getListadoLibroDiario().entrySet()){
                Cuenta cuenta = entry.getKey();
                Double valor= entry.getValue();
                cantElementos++;
                if (!aux.isEmpty()){
                    for(Map.Entry<Integer,Double> hashFilter:aux.entrySet()){
                        Integer cuentaFiltrada=hashFilter.getKey();
                        Double valorFiltrado=hashFilter.getValue();
                        if(cuentaFiltrada.equals(cuenta.getCodigoCuenta())){
                            if ((isAjustado == true)&&(cuenta.isAjusta())){
                                aux.put(cuenta.getCodigoCuenta(),(valorFiltrado+valor)*ajustado.getValorMes(a.getFecha()));
                                break;
                            }else {
                                aux.put(cuenta.getCodigoCuenta(), valorFiltrado + valor);
                                break;
                            }
                        }else if(aux.size()+1==cantElementos){
                            if ((isAjustado == true)&&(cuenta.isAjusta())){
                                aux.put(cuenta.getCodigoCuenta(), valorFiltrado*ajustado.getValorMes(a.getFecha()));
                                break;
                            }else{
                                aux.put(cuenta.getCodigoCuenta(), valor);
                                break;
                            }
                        }

                    }
                }else{
                    if ((isAjustado == true)&&(cuenta.isAjusta())){
                        aux.put(cuenta.getCodigoCuenta(), valor*ajustado.getValorMes(a.getFecha()));
                    }else{
                        aux.put(cuenta.getCodigoCuenta(), valor);
                    }
                }
            }
        }
        return aux;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "numero=" + numero +
                ", asientos=" + asientos +
                ", fechaIni=" + fechaIni +
                ", fechaCier=" + fechaCier +
                '}';
    }
}



