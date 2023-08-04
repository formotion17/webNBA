package contollers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import temporadas.CalcularMaximosTemporada;
import temporadas.ClasificacionTemporada;
import temporadas.Equipo;
import temporadas.EstadisticasMaximos;
import util.ClaseEstadisticaNormalTotales;
import util.Utilidades;

@ViewScoped
@ManagedBean(name ="temporadas")
@Data
@EqualsAndHashCode(callSuper=false)
public class TemporadasController extends BaseController{

    private static final long serialVersionUID = -7503343479663707367L;
	private String temporadaElegida="";
    private List<SelectItem> selectMenuTemporadas;
    
    // ClasificacionTemporadas
    private List<Equipo> clasificacionOeste;
    private List<Equipo> clasificacionEste;
    private String clasificacionEleccion="";
    
    // Maximos temporada
    private ArrayList<EstadisticasMaximos> listaMaximos = new ArrayList<>();
    private CalcularMaximosTemporada maximos = new CalcularMaximosTemporada();
    private int porcentajeTiroCampo=300;
    
    private String radioFg="media";
    private String radioFga="media";
    
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
    	
    	// Clasificacion
    	if(DIVISION.equals(clasificacionEleccion)) {
    		clasificacionDivision();
    	}else{
    		clasificacionConferencia();
    	}
    	
    	// Maximos temporada
    	maximos.calcularMaximos(temporadaElegida);
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
    	
    	if(!this.temporadaElegida.equals("")) {
    		StringBuilder temporada = new StringBuilder(this.temporadaElegida.replace("season", ""));
        	return (temporada.insert(4, " / ")).toString();
    	}
    	return "";
    }
    
    // TIROS CAMPO
    public ArrayList<EstadisticasMaximos> getTirosCampoMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoMetidosPartido(radioFg);
    	}
    	return null;
    }
    public boolean esFgMedia() {
    	if("media".equals(radioFg)) {
    		System.out.println(true);
    		return true;
    	}
    	System.out.println(false);
    	return false;
    }
    public ArrayList<EstadisticasMaximos> getTirosCampoIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoIntentadosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTirosCampoPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		System.out.println("Entamos con "+porcentajeTiroCampo);
    		return maximos.getTirosCampoPorcentajePartido(porcentajeTiroCampo);
    	}
    	return null;
    }
    
    // TRIPLES
    public ArrayList<EstadisticasMaximos> getTriplesMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesMetidosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTriplesIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesIntentadosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTriplesPorcentajePArtido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesPorcentajePArtido();
    	}
    	return null;
    }
    
    // DOS PUNTOS
    public ArrayList<EstadisticasMaximos> getDosMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosMetidosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getDosInTentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosInTentadosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getDosPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosPorcentajePartido();
    	}
    	return null;
    }
    
    // TIROS LIBRES
    public ArrayList<EstadisticasMaximos> getTirosLibresMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresMetidosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTirosLibresIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresIntentadosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTirosLibresPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresPorcentajePartido();
    	}
    	return null;
    }
    
    // PUNTOS PARTIDO
    public ArrayList<EstadisticasMaximos> getPuntosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPuntosPartido();
    	}
    	return null;
    }
    
    // REBOTES
    public ArrayList<EstadisticasMaximos> getRebotesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getRebotesDefensivosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesDefensivosPartido();
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getRebotesOfensivosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesOfensivosPartido();
    	}
    	return null;
    }
    
    // ASISTENCIAS
    public ArrayList<EstadisticasMaximos> getAsistenciasPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getAsistenciasPartido();
    	}
    	return null;
    }
    
    // ROBOS
    public ArrayList<EstadisticasMaximos> getRobosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRobosPartido();
    	}
    	return null;
    }
    
    // TAPONES
    public ArrayList<EstadisticasMaximos> getTaponesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTaponesPartido();
    	}
    	return null;
    }
    
    // PERDIDAS
    public ArrayList<EstadisticasMaximos> getPerdidasPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPerdidasPartido();
    	}
    	return null;
    }
    
    // PERSONALES
    public ArrayList<EstadisticasMaximos> getPersonalesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPersonalesPartido();
    	}
    	return null;
    }
    
    // MINUTOS
    public ArrayList<EstadisticasMaximos> getMinutosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getMinutosPartido();
    	}
    	return null;
    }
    
    
    
}
