package contollers;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import temporadas.ClasificacionTemporada;
import temporadas.Equipo;
import util.Utilidades;

@ViewScoped
@ManagedBean(name ="temporadas")
@Data
@EqualsAndHashCode(callSuper=false)
public class TemporadasController extends BaseController{

    private String temporadaElegida="";
    private List<SelectItem> selectMenuTemporadas;
    
    // ClasificacionTemporadas
    private List<Equipo> clasificacionOeste;
    private List<Equipo> clasificacionEste;
    private String clasificacionEleccion="";
    
    @PostConstruct
    public void init() {
        Locale defaultLocale = Locale.getDefault();
        String decimalSeparator = getDecimalSeparatorFromLocale(defaultLocale);
        setDecimalSeparatorForJSF(decimalSeparator);
        clasificacionEleccion="conferencia";
    }
    
    public List<SelectItem> getSelectMenuTemporadas() {
        if(selectMenuTemporadas == null){
            selectMenuTemporadas = Utilidades.devolverTemporadas();
        }
        return selectMenuTemporadas;
    }
    
    public void verClasificacion(){
    	if(DIVISION.equals(clasificacionEleccion)) {
    		clasificacionDivision();
    	}else{
    		clasificacionConferencia();
    	}
    }
    
    public void clasificacionConferencia(){
    	
    	clasificacionOeste=ClasificacionTemporada.devolverClasificacionConferencia(
    			temporadaElegida,CONFERENCIA_OESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));
    	
    	clasificacionEste=ClasificacionTemporada.devolverClasificacionConferencia(
    			temporadaElegida,CONFERENCIA_ESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));

    	System.out.println("Entramos para actualizar temporada: "+temporadaElegida);
    }
    
    public void clasificacionDivision(){
    	
    	clasificacionOeste=ClasificacionTemporada.devolverClasificacionDivision(
    			temporadaElegida,CONFERENCIA_OESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));
    	
    	clasificacionEste=ClasificacionTemporada.devolverClasificacionDivision(
    			temporadaElegida,CONFERENCIA_ESTE,
    			Integer.parseInt(temporadaElegida.substring(6,10)));

    	System.out.println("Entramos para actualizar temporada: "+temporadaElegida);
    }
    
    public String devolverTemporadaPretty(){
    	
    	//return this.temporadaElegida.replace("season", "");
    	if(!this.temporadaElegida.equals("")) {
    		StringBuilder temporada = new StringBuilder(this.temporadaElegida.replace("season", ""));
        	return (temporada.insert(4, " / ")).toString();
    	}
    	return "";
    }
    
}
