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
    
    private String radioDosFg="media";
    private String radioDosFga="media";
    private int porcentajeDos=300;
    
    private String radioTripleFg="media";
    private String radioTripleFga="media";
    private int porcentajeTriple=300;
    
    private String radioLibreFg="media";
    private String radioLibreFga="media";
    private int porcentajeLibre=300;
    
    private String radioRebote="media";
    private String radioReboteDefensivo="media";
    private String radioReboteOfensivo="media";
    
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
    
    public ArrayList<EstadisticasMaximos> getTirosCampoIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoIntentadosPartido(radioFga);
    	}
    	return null;
    }
    
    public ArrayList<EstadisticasMaximos> getTirosCampoPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosCampoPorcentajePartido(porcentajeTiroCampo);
    	}
    	return null;
    }
    
    public boolean esFgMedia() {
    	if("media".equals(radioFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esFgaMedia() {
    	if("media".equals(radioFga)) {
    		return true;
    	}
    	return false;
    }
    
    // TRIPLES
    public ArrayList<EstadisticasMaximos> getTriplesMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesMetidosPartido(radioTripleFg);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTriplesIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesIntentadosPartido(radioTripleFga);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTriplesPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTriplesPorcentajePArtido(porcentajeTriple);
    	}
    	return null;
    }
    
    public boolean esTripleFgMedia() {
    	if("media".equals(radioTripleFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esTripleFgaMedia() {
    	if("media".equals(radioTripleFga)) {
    		return true;
    	}
    	return false;
    }
    
    // DOS PUNTOS
    public ArrayList<EstadisticasMaximos> getDosMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosMetidosPartido(radioDosFg);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getDosInTentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosInTentadosPartido(radioDosFga);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getDosPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getDosPorcentajePartido(porcentajeDos);
    	}
    	return null;
    }
    
    public boolean esFgDosMedia() {
    	if("media".equals(radioDosFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esFgaDosMedia() {
    	if("media".equals(radioDosFga)) {
    		return true;
    	}
    	return false;
    }
    
    // TIROS LIBRES
    public ArrayList<EstadisticasMaximos> getTirosLibresMetidosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresMetidosPartido(radioLibreFg);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTirosLibresIntentadosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresIntentadosPartido(radioLibreFga);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getTirosLibresPorcentajePartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getTirosLibresPorcentajePartido(porcentajeLibre);
    	}
    	return null;
    }
    
    public boolean esFgLibresMedia() {
    	if("media".equals(radioLibreFg)) {
    		return true;
    	}
    	return false;
    }

    public boolean esFgaLibresMedia() {
    	if("media".equals(radioLibreFga)) {
    		return true;
    	}
    	return false;
    }
    
    // REBOTES
    public ArrayList<EstadisticasMaximos> getRebotesPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesPartido(radioRebote);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getRebotesDefensivosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesDefensivosPartido(radioReboteDefensivo);
    	}
    	return null;
    }
    public ArrayList<EstadisticasMaximos> getRebotesOfensivosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getRebotesOfensivosPartido(radioReboteOfensivo);
    	}
    	return null;
    }
    
    public boolean esReboteMedia() {
    	if("media".equals(radioRebote)) {
    		return true;
    	}
    	return false;
    }

    public boolean esReboteDefensivoMedia() {
    	if("media".equals(radioReboteDefensivo)) {
    		return true;
    	}
    	return false;
    }

    public boolean esReboteOfensivoMedia() {
    	if("media".equals(radioReboteOfensivo)) {
    		return true;
    	}
    	return false;
    }
    

    
    // PUNTOS PARTIDO
    public ArrayList<EstadisticasMaximos> getPuntosPartido(){
    	if(null!=maximos.getListaJugadores()) {
    		return maximos.getPuntosPartido();
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
