package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Entity
@Table(name="mesAjustados")
public class MesAjustado {
    @Id
    @Column(name="idAño")
    private int año;
    @ElementCollection
    @MapKeyJoinColumn(name="mesAdaptado")
    @CollectionTable(name="Ajuste", joinColumns=@JoinColumn(name="Mes"))
    private Map<Date,Double> mesAdaptado=new HashMap<>();

    public void addValores(Date fecha, double valor){
        mesAdaptado.put(fecha,valor);
    }

    public double getValorMes(Date fecha) {
        for(Map.Entry<Date,Double> entry:mesAdaptado.entrySet()) {
            Date mes = entry.getKey();
            double valor = entry.getValue();
            if (fecha.getMonth() == mes.getMonth())
                return valor;
        }
        return 1;
    }
}


