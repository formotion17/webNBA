package clases;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;

import contollers.JugadorController;
import util.ListaEquipos;

public class ControllerPartido {

    private ControllerEquipo equipoLocal = new ControllerEquipo();
    private ControllerEquipo equipoVisitante = new ControllerEquipo();
    private ArrayList<ControllerCuarto> listaCuartos = new ArrayList<ControllerCuarto>();
    private ArrayList<ControllerTiros> listaTirosLocal = new ArrayList<ControllerTiros>();
    private ArrayList<ControllerTiros> listaTirosVisitante = new ArrayList<ControllerTiros>();
    private ArrayList<Integer> listaTanteoLocal = new ArrayList<Integer>();
    private ArrayList<String> listaTanteoPartido = new ArrayList<String>();
    
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
    
    // Para ver los tiros de un partido
    private String cuartoPartidoTirosVisitante="";
    
    private ArrayList<String> listaJugadoresLocal = new ArrayList<String>();
    private ArrayList<String> listaJugadoresVisitante = new ArrayList<String>();

    private boolean playOff = false;
    private boolean temporadaRegular=false;
    private String rutaLogo="logo.png";
    private boolean logoPartido=false;
    private boolean playin=false;
    private ListaEquipos equipoSeleccionado;
    

    private BarChartModel tanteoGrafica;

	// Getter Methods 
    public Integer getAsistencia() {
        return asistencia;
    }

    public String getCambiosLider() {
        return cambiosLider;
    }

    public String getDia() {
        return dia;
    }

    public String getEmpates() {
        return empates;
    }

    public ControllerEquipo getEquipoLocal() {
        return equipoLocal;
    }

    public ControllerEquipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public String getEstadio() {
        return estadio;
    }

    public String getHora() {
        return hora;
    }

    public String getLocalPuntosConsecutivos() {
        return localPuntosConsecutivos;
    }

    public Integer getLocalSinAnotar() {
        return localSinAnotar;
    }

    public String getMes() {
        return mes;
    }

    public boolean getPlayOff() {
        return playOff;
    }

    public Integer getTiempoEmpate() {
        return tiempoEmpate;
    }

    public Integer getTiempoLocalGanando() {
        return tiempoLocalGanando;
    }

    public Integer getTiempoVisitanteGanando() {
        return tiempoVisitanteGanando;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getVisitantePuntosConsecutivos() {
        return visitantePuntosConsecutivos;
    }

    public Integer getVisitanteSinAnotar() {
        return visitanteSinAnotar;
    }

    public String getYear() {
        return year;
    }

	 // Setter Methods 
    public void setAsistencia(Integer asistencia) {
        this.asistencia = asistencia;
    }

    public void setCambiosLider(String cambiosLider) {
        this.cambiosLider = cambiosLider;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setEmpates(String empates) {
        this.empates = empates;
    }

    public void setEquipoLocal(ControllerEquipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public void setEquipoVisitante(ControllerEquipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setHora(String hora) {
        this.hora = hora.substring(1,6);
    }

    public void setLocalPuntosConsecutivos(String localPuntosConsecutivos) {
        this.localPuntosConsecutivos = localPuntosConsecutivos;
    }

    public void setLocalSinAnotar(Integer localSinAnotar) {
        this.localSinAnotar = localSinAnotar;
        setLocalSinAnotarMinutos(getMinutos(localSinAnotar));
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setPlayOff(boolean playOff) {
        this.playOff = playOff;
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

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setVisitantePuntosConsecutivos(String visitantePuntosConsecutivos) {
        this.visitantePuntosConsecutivos = visitantePuntosConsecutivos;
    }

    public void setVisitanteSinAnotar(Integer visitanteSinAnotar) {
        this.visitanteSinAnotar = visitanteSinAnotar;
        setVisitanteSinAnotarMinutos(getMinutos(visitanteSinAnotar));
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTiempoEmpateMinutos() {
        return tiempoEmpateMinutos;
    }

    public void setTiempoEmpateMinutos(String tiempoEmpateMinutos) {
        this.tiempoEmpateMinutos = tiempoEmpateMinutos;
    }

    public String getLocalSinAnotarMinutos() {
        return localSinAnotarMinutos;
    }

    public void setLocalSinAnotarMinutos(String localSinAnotarMinutos) {
        this.localSinAnotarMinutos = localSinAnotarMinutos;
    }

    public String getVisitanteSinAnotarMinutos() {
        return visitanteSinAnotarMinutos;
    }

    public void setVisitanteSinAnotarMinutos(String visitanteSinAnotarMinutos) {
        this.visitanteSinAnotarMinutos = visitanteSinAnotarMinutos;
    }

    public String getTiempoLocalGanandoMinutos() {
        return tiempoLocalGanandoMinutos;
    }

    public void setTiempoLocalGanandoMinutos(String tiempoLocalGanandoMinutos) {
        this.tiempoLocalGanandoMinutos = tiempoLocalGanandoMinutos;
    }

    public String getTiempoVisitanteGanandoMinutos() {
        return tiempoVisitanteGanandoMinutos;
    }

    public void setTiempoVisitanteGanandoMinutos(String tiempoVisitanteGanandoMinutos) {
        this.tiempoVisitanteGanandoMinutos = tiempoVisitanteGanandoMinutos;
    }

    public ArrayList<ControllerCuarto> getListaCuartos() {
        return listaCuartos;
    }

    public void setListaCuartos(ArrayList<ControllerCuarto> listaCuartos) {
        this.listaCuartos = listaCuartos;
    }
    
    
    
    public String getMinutos(int segundos){
        String min="";
        int iSeg, iMin;
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
        
    public ArrayList<ControllerTiros> getListaTirosLocal() {
		return listaTirosLocal;
	}

	public void setListaTirosLocal(ArrayList<ControllerTiros> listaTirosLocal) {
		this.listaTirosLocal = listaTirosLocal;
	}

	public ArrayList<ControllerTiros> getListaTirosVisitante() {
		return listaTirosVisitante;
	}

	public void setListaTirosVisitante(ArrayList<ControllerTiros> listaTirosVisitante) {
		this.listaTirosVisitante = listaTirosVisitante;
	}

	public void rellenarCuartos(){
        String[] lista = {"primero","segundo","tercero","cuarto","OT1","OT2","OT3","OT4","OT5","OT6"};
        String[] listaCuarto = {"1Q","2Q","3Q","4Q","OT1","OT2","OT3","OT4","OT5","OT6"};
        for(int i=0;i<equipoLocal.getTanteoCuartos().getCuartos().size();i++){
            listaCuartos.add(new ControllerCuarto(listaCuarto[i],equipoLocal.getTanteoCuartos().devolverCuarto(lista[i]),equipoVisitante.getTanteoCuartos().devolverCuarto(lista[i])));
        }
    }

	public void rellenarTirosEquipos() {
		
		actualizarTirosVisitante();
		
		listaJugadoresLocal.add(equipoLocal.getNombre());
		for(ControllerJugador jugador: equipoLocal.getJugadores()) {
			for(ControllerTiros tiro:jugador.getListaTiros()) {
				listaTirosLocal.add(new ControllerTiros(tiro.getPosicionTop(), tiro.getPosicionLeft(), tiro.isDentro()));
			}
			listaJugadoresLocal.add(jugador.getApeNom());
		}
		
		listaJugadoresVisitante.add(equipoVisitante.getNombre());
		for(ControllerJugador jugador: equipoVisitante.getJugadores()) {
			for(ControllerTiros tiro:jugador.getListaTiros()) {
				listaTirosVisitante.add(new ControllerTiros(tiro.getPosicionTop(), tiro.getPosicionLeft(), tiro.isDentro()));
			}
			listaJugadoresVisitante.add(jugador.getApeNom());
		}
	}
    
    public void cuartoPartidoTirosVisitante() {
    	System.out.println("ENTRAMOSOOO");
    }
    
    public void actualizarTirosVisitante() {
    	System.out.println(equipoLocal.getNombre());
    }

	public String getCuartoPartidoTirosVisitante() {
		return cuartoPartidoTirosVisitante;
	}

	public void setCuartoPartidoTirosVisitante(String cuartoPartidoTirosVisitante) {
		this.cuartoPartidoTirosVisitante = cuartoPartidoTirosVisitante;
	}

	public ArrayList<String> getListaJugadoresLocal() {
		return listaJugadoresLocal;
	}

	public void setListaJugadoresLocal(ArrayList<String> listaJugadoresLocal) {
		this.listaJugadoresLocal = listaJugadoresLocal;
	}

	public ArrayList<String> getListaJugadoresVisitante() {
		return listaJugadoresVisitante;
	}

	public void setListaJugadoresVisitante(ArrayList<String> listaJugadoresVisitante) {
		this.listaJugadoresVisitante = listaJugadoresVisitante;
	}

	public String getBracket() {
		return bracket;
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

	public String getConferencia() {
		return conferencia;
	}

	public void setConferencia(String conferencia) {
		this.conferencia=conferencia.toUpperCase();
	}

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
	}

	public boolean isTemporadaRegular() {
		return temporadaRegular;
	}

	public void setTemporadaRegular(boolean temporadaRegular) {
		this.temporadaRegular = temporadaRegular;
	}

	public String getRutaLogo() {
		return rutaLogo;
	}

	public void setRutaLogo(String rutaLogo) {
		this.rutaLogo = rutaLogo;
	}

	public boolean getLogoPartido() {
		return logoPartido;
	}

	public void setLogoPartido(boolean logoPartido) {
		this.logoPartido = logoPartido;
	}

	public boolean isPlayin() {
		return playin;
	}

	public void setPlayin(boolean playin) {
		this.playin = playin;
	}

	public ArrayList<Integer> getListaTanteoLocal() {
		return listaTanteoLocal;
	}

	public void setListaTanteoLocal(ArrayList<Integer> listaTanteoLocal) {
		this.listaTanteoLocal = listaTanteoLocal;
	}
	
	private void crearGrafica() {
		tanteoGrafica = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barraLocal = new BarChartDataSet();
        BarChartDataSet barraVisitante = new BarChartDataSet();
        
        barraLocal.setLabel(equipoLocal.getNombre());
        barraLocal.setBackgroundColor(ListaEquipos.findColorByAbreviatura(equipoLocal.getNombreAbreviado().toLowerCase()));
        System.out.println(ListaEquipos.findColorByAbreviatura(equipoLocal.getNombreAbreviado().toLowerCase()));
        
        barraVisitante.setLabel(equipoVisitante.getNombre());
        barraVisitante.setBackgroundColor(ListaEquipos.findColorByAbreviatura(equipoVisitante.getNombreAbreviado().toLowerCase()));
        System.out.println(ListaEquipos.findColorByAbreviatura(equipoVisitante.getNombreAbreviado().toLowerCase()));
        
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
        title.setText(equipoVisitante.getNombre()+" "+equipoVisitante.getTanteo()+" - "+
        		equipoLocal.getTanteo()+" "+equipoLocal.getNombre());
        options.setTitle(title);

        Tooltip tooltip = new Tooltip();
        tooltip.setMode("index");
        tooltip.setIntersect(true);
        options.setTooltip(tooltip);
        tanteoGrafica.setOptions(options);
	}

	public BarChartModel getTanteoGrafica() {
		return tanteoGrafica;
	}

	public void setTanteoGrafica(BarChartModel tanteoGrafica) {
		this.tanteoGrafica = tanteoGrafica;
	}

	public ArrayList<String> getListaTanteoPartido() {
		return listaTanteoPartido;
	}

	public void setListaTanteoPartido(ArrayList<String> listaTanteoPartido) {
		this.listaTanteoPartido = listaTanteoPartido;
		crearGrafica();
	}    
    
}
