package clases;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;

import lombok.Data;
import util.ListaEquipos;

@Data
public class ControllerPartido implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1101255755757932660L;
	private ControllerEquipo equipoLocal = new ControllerEquipo();
    private ControllerEquipo equipoVisitante = new ControllerEquipo();
    private ArrayList<ControllerCuarto> listaCuartos = new ArrayList<>();
    private ArrayList<ControllerTiros> listaTirosLocal = new ArrayList<>();
    private ArrayList<ControllerTiros> listaTirosVisitante = new ArrayList<>();
    private ArrayList<Integer> listaTanteoLocal = new ArrayList<>();
    private ArrayList<String> listaTanteoPartido = new ArrayList<>();
    private ArrayList<String> listaJugadoresLocal = new ArrayList<>();
    private ArrayList<String> listaJugadoresVisitante = new ArrayList<>();
    
    private Integer asistencia = 0;
    private Integer tiempoEmpate = 0;
    private Integer localSinAnotar = 0;
    private Integer visitanteSinAnotar = 0;
    private Integer tiempoLocalGanando = 0;
    private Integer tiempoVisitanteGanando = 0;
    private String tiempoEmpateMinutos = "";
    private String localSinAnotarMinutos = "";
    private String visitanteSinAnotarMinutos = "";
    private String tiempoLocalGanandoMinutos = "";
    private String tiempoVisitanteGanandoMinutos = "";

    private String dia = "";
    private String mes = "";
    private String hora = "";
    private String year = "";
    private String empates = "";
    private String estadio = "";
    private String ubicacion = "";
    private String cambiosLider = "";
    private String localPuntosConsecutivos = "";
    private String visitantePuntosConsecutivos = "";
    
    private String bracket="";
    private String conferencia="";
    private Integer game=0;

    private boolean playOff = false;
    private boolean temporadaRegular=false;
    private String rutaLogo="logo.png";
    private boolean logoPartido=false;
    private boolean playin=false;
    

    private BarChartModel tanteoGrafica;
    
    private List<EstadisticaIndexPartido> estadisticaIndexPartido;

    public void setHora(String hora) {
        this.hora = hora.substring(1,6);
    }

    public void setLocalSinAnotar(Integer localSinAnotar) {
        this.localSinAnotar = localSinAnotar;
        setLocalSinAnotarMinutos(getMinutos(localSinAnotar));
    }

    public void setTiempoEmpate(Integer tiempoEmpate) {
        this.tiempoEmpate = tiempoEmpate;
        setTiempoEmpateMinutos(getMinutos(tiempoEmpate));
    }

    public void setTiempoLocalGanando(Integer tiempoLocalGanando) {
        this.tiempoLocalGanando = tiempoLocalGanando;
        setTiempoLocalGanandoMinutos(getMinutos(tiempoLocalGanando));
    }

    public void setTiempoVisitanteGanando(Integer tiempoVisitanteGanando) {
        this.tiempoVisitanteGanando = tiempoVisitanteGanando;
        setTiempoVisitanteGanandoMinutos(getMinutos(tiempoVisitanteGanando));
    }

    public void setVisitanteSinAnotar(Integer visitanteSinAnotar) {
        this.visitanteSinAnotar = visitanteSinAnotar;
        setVisitanteSinAnotarMinutos(getMinutos(visitanteSinAnotar));
    }
    
    public String getMinutos(int segundos){
        int iSeg;
        int iMin;
        iMin=segundos/60;
        iSeg=segundos-iMin*60;
        return Integer.toString(iMin)+":"+devolverSegundos(iSeg);
    }
    
    public String devolverSegundos(int seg){
        if(seg<10){
            return "0"+Integer.toString(seg);
        }else{
            return Integer.toString(seg);
        }
    }

	public void rellenarCuartos(){
        String[] lista = {"primero","segundo","tercero","cuarto","OT1","OT2","OT3","OT4","OT5","OT6"};
        String[] listaCuarto = {"1Q","2Q","3Q","4Q","OT1","OT2","OT3","OT4","OT5","OT6"};
        for(int i=0;i<equipoLocal.getTanteoCuartos().getCuartos().size();i++){
            listaCuartos.add(new ControllerCuarto(listaCuarto[i],equipoLocal.getTanteoCuartos().devolverCuarto(lista[i]),equipoVisitante.getTanteoCuartos().devolverCuarto(lista[i])));
        }
    }


	public void setBracket(String bracket) {
		switch(bracket) {
		case "Conference First Round":
			this.bracket="Primera Ronda de la Conferencia ";
			this.logoPartido=true;
			this.rutaLogo="playoff.png";
			break;
		case "Conference Semifinals":
			this.bracket="Semifinales de la Conferencia ";
			this.logoPartido=true;
			this.rutaLogo="playoff.png";
			break;
		case "Conference Finals":
			this.logoPartido=true;
			this.bracket="Finales de la Conferencia ";
			break;
		case "NBA Finals":
			this.logoPartido=true;
			this.bracket="Finales NBA";
			this.rutaLogo="finals.png";
			break;
		default:
			this.bracket=bracket;
			this.logoPartido=false;
			}
	}
	
	public void setConferencia(String conferencia) {
		this.conferencia=conferencia.toUpperCase();
	}
	
	private void crearGrafica() {
		tanteoGrafica = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barraLocal = new BarChartDataSet();
        BarChartDataSet barraVisitante = new BarChartDataSet();
        
        barraLocal.setLabel(equipoLocal.getNombre());
        barraLocal.setBackgroundColor(ListaEquipos.findColorByAbreviatura(equipoLocal.getNombreAbreviado().toLowerCase()));
        
        barraVisitante.setLabel(equipoVisitante.getNombre());
        barraVisitante.setBackgroundColor(ListaEquipos.findColorByAbreviatura(equipoVisitante.getNombreAbreviado().toLowerCase()));
        
        List<Number> dataLocal = new ArrayList<>();
        List<Number> dataVisitante = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        for(int i=0;i<listaTanteoLocal.size();i++) {
        	if(listaTanteoLocal.get(i)>0) {
        		dataLocal.add(listaTanteoLocal.get(i));
        		dataVisitante.add(0);
        	}else if(listaTanteoLocal.get(i)<0) {
        		dataLocal.add(0);
        		dataVisitante.add(listaTanteoLocal.get(i));
        	}else {
        		dataLocal.add(0);
        		dataVisitante.add(0);
        	}
        	labels.add(listaTanteoPartido.get(i));
        }
        
        barraLocal.setData(dataLocal);
        barraVisitante.setData(dataVisitante);

        data.addChartDataSet(barraLocal);
        data.addChartDataSet(barraVisitante);
        data.setLabels(labels);
        
        tanteoGrafica.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        
        linearAxes.setStacked(true);
        linearAxes.setOffset(true);
        cScales.addXAxesData(linearAxes);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        
        title.setDisplay(true);
        title.setText(equipoVisitante.getNombre()+" "+equipoVisitante.getTanteo()+" - "+equipoLocal.getTanteo()+" "+equipoLocal.getNombre());
        options.setTitle(title);
        
     // Configurar el tooltip personalizado
        Tooltip tooltip = new Tooltip();
        tooltip.setEnabled(true);
        tooltip.setIntersect(false);
        
        options.setTooltip(tooltip);


        tanteoGrafica.setOptions(options);
	}
	
	public void setListaTanteoPartido(ArrayList<String> listaTanteoPartido) {
		this.listaTanteoPartido = listaTanteoPartido;
		crearGrafica();
	}
	
	private String getIndexEstadisticaEquipoLocalTirosCampo(){
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getTirosCampoMetidos())+" / "+
				Integer.toString(equipoLocal.getEstadisticaNormal().getTirosCampoIntentados())+" ( "+
				Double.toString(equipoLocal.getEstadisticaNormal().getTirosCampoPorcentaje())+" )";
	}
	
	private String getIndexEstadisticaEquipoVisitanteTirosCampo() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTirosCampoMetidos())+" / "+
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTirosCampoIntentados())+" ( "+
				Double.toString(equipoVisitante.getEstadisticaNormal().getTirosCampoPorcentaje())+" )";
	}
	
	private String getIndexEstadisticaEquipoLocalTriples(){
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getTriplesMetidos())+" / "+
				Integer.toString(equipoLocal.getEstadisticaNormal().getTriplesIntentados())+" ( "+
				Double.toString(equipoLocal.getEstadisticaNormal().getTriplesPorcentaje())+" )";
	}
	
	private String getIndexEstadisticaEquipoVisitanteTriples() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTriplesMetidos())+" / "+
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTriplesIntentados())+" ( "+
				Double.toString(equipoVisitante.getEstadisticaNormal().getTriplesPorcentaje())+" )";
	}
	
	private String getIndexEstadisticaEquipoLocalTirosLibres(){
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getTirosLibresMetidos())+" / "+
				Integer.toString(equipoLocal.getEstadisticaNormal().getTirosLibresIntentados())+" ( "+
				Double.toString(equipoLocal.getEstadisticaNormal().getTirosLibresPorcentaje())+" )";
	}
	
	private String getIndexEstadisticaEquipoVisitanteTirosLibres() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTirosLibresMetidos())+" / "+
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTirosLibresIntentados())+" ( "+
				Double.toString(equipoVisitante.getEstadisticaNormal().getTirosLibresPorcentaje())+" )";
	}

	private String getIndexEstadisticaEquipoVisitanteRebotes() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTotalRebotes())+" ( "+
				Integer.toString(equipoVisitante.getEstadisticaNormal().getReboteDefensivo())+" / "+
				Integer.toString(equipoVisitante.getEstadisticaNormal().getReboteOfensivo())+" )";
	}
	
	private String getIndexEstadisticaEquipoLocalRebotes() {
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getTotalRebotes())+" ( "+
				Integer.toString(equipoLocal.getEstadisticaNormal().getReboteDefensivo())+" / "+
				Integer.toString(equipoLocal.getEstadisticaNormal().getReboteOfensivo())+" )";
	}
	
	private String getIndexEstadisticaEquipoVisitanteAsistencias() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getAsistencias());
	}
	
	private String getIndexEstadisticaEquipoLocalAsistencias() {
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getAsistencias());
	}
	
	private String getIndexEstadisticaEquipoVisitanteRobos() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getRobos());
	}
	
	private String getIndexEstadisticaEquipoLocalRobos() {
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getRobos());
	}
	
	private String getIndexEstadisticaEquipoVisitanteTapones() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getTapones());
	}
	
	private String getIndexEstadisticaEquipoLocalTapones() {
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getTapones());
	}
	
	private String getIndexEstadisticaEquipoVisitanteFaltas() {
		return 
				Integer.toString(equipoVisitante.getEstadisticaNormal().getFaltasPersonales());
	}
	
	private String getIndexEstadisticaEquipoLocalFaltas() {
		return 
				Integer.toString(equipoLocal.getEstadisticaNormal().getFaltasPersonales());
	}
	
	public void completarEstadisticasIndexPartido() {
        
		estadisticaIndexPartido = new ArrayList<>();
        // Agregar estadísticas al azar solo como ejemplo
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Tiros de Campo",getIndexEstadisticaEquipoVisitanteTirosCampo(),getIndexEstadisticaEquipoLocalTirosCampo()));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Triples", getIndexEstadisticaEquipoVisitanteTriples(), getIndexEstadisticaEquipoLocalTriples()));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Tiros Libres",getIndexEstadisticaEquipoVisitanteTirosLibres(),getIndexEstadisticaEquipoLocalTirosLibres()));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Rebotes (Def/Ofe)", getIndexEstadisticaEquipoVisitanteRebotes(), getIndexEstadisticaEquipoLocalRebotes()));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Asistencias", getIndexEstadisticaEquipoVisitanteAsistencias(), getIndexEstadisticaEquipoLocalAsistencias()));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Robos", getIndexEstadisticaEquipoVisitanteRobos(), getIndexEstadisticaEquipoLocalRobos()));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Tapones", getIndexEstadisticaEquipoVisitanteTapones(), getIndexEstadisticaEquipoLocalTapones() ));
		
		estadisticaIndexPartido.add(new EstadisticaIndexPartido(
				"Faltas", getIndexEstadisticaEquipoVisitanteFaltas(), getIndexEstadisticaEquipoLocalFaltas()));
	}
	
	public boolean getPlayOff() {
        return playOff;
    }

}
