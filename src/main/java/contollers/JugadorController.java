package contollers;

import Service.impl.JugadorDataImpl;
import clases.ControllerEstadisticaNormal;
import clases.ControllerPartido;
import clases.ControllerPartidoJugador;
import clases.ControllerTiros;
import clases.Jugador;
import util.ListaEquipos;
import util.Temporadas;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import contollers.utilidades.JugadorControllerUtilidades;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.bson.Document;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import util.ClaseEstadisticaNormalTotales;
import util.MapJavaMongo;
import util.Utilidades;

/**
 *
 * @author hatashi
 */

@ViewScoped
@ManagedBean(name ="jugadores")
public class JugadorController implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8870342600681672087L;
	private String nombreJugador;
    private List<Jugador> listaJugadores;
    private List<String> listaJ;
    private String imagenCarga="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";
    private Jugador jugadorSeleccionado = new Jugador();
    //private boolean verEstadisticasJugador = false;
    private ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasCabecera;
    private ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasJugador;
    private String error="";
    private HashMap<String, String> atributosMap = new HashMap<String, String>();
    
    // TAB PROGRESIÓN DEL JUGADOR
    private ArrayList<String> listaSeleccionTemporadasProgresion = new ArrayList<String>();
    private List<SelectItem> selectMenuTemporadas;
    private String atributoProgresion;
    private String tiempoPartidoProgresion;
    private String ubicacionProgresion;
    private String resultadoProgresion;
    private String cuandoProgresion;
    private String invisibleEstadisticasGlobales="invisible";
    private String invisibleTabs="invisible";
    private boolean invisibleFindJugador=true;
    private List<SelectItem> selectMenuAtributo;
    private List<SelectItem> selectMenuTiempoPartido;
    private List<SelectItem> selectMenuUbicacion;
    private List<SelectItem> selectMenuResultado;
    private List<SelectItem> selectMenuCuando;
    private ArrayList<Double> listaProgresiones = new ArrayList<>();
    private ArrayList<String> listaProgresionesDobles = new ArrayList<>();
    private ArrayList<ControllerPartido> listaPartidosLocal = new ArrayList<ControllerPartido>();
    private ArrayList<ControllerPartido> listaPartidosVisitante = new ArrayList<ControllerPartido>();
    
    //  TAB ESTADISTICAS CONTRA EQUIPOS
    private int partidosTotales=0;
    private int partidosLocal=0;
    private int partidosVisitante=0;
    private int partidosLocalRegular=0;
    private int partidosVisitanteRegular=0;
    private int partidosLocalPlayoff=0;
    private int partidosVisitantePlayoff=0;
    private int altoFoto=0;
    private ControllerEstadisticaNormal partidosDeLocalRegular = new  ControllerEstadisticaNormal();
    private ControllerEstadisticaNormal partidosDeLocalPlayoff = new  ControllerEstadisticaNormal();
    private ControllerEstadisticaNormal partidosDeVisitanteRegular = new  ControllerEstadisticaNormal();
    private ControllerEstadisticaNormal partidosDeVisitantePlayoff = new  ControllerEstadisticaNormal();
    private ArrayList<ControllerEstadisticaNormal> listaPartidosLocalVisitante = new ArrayList<ControllerEstadisticaNormal>();
    private ArrayList<ControllerTiros> listaTirosLocalRegular = new ArrayList<ControllerTiros>();
    private ArrayList<ControllerTiros> listaTirosVisitanteRegular = new ArrayList<ControllerTiros>();
    private ArrayList<ControllerTiros> listaTirosLocalPlayoff = new ArrayList<ControllerTiros>();
    private ArrayList<ControllerTiros> listaTirosVisitantePlayoff = new ArrayList<ControllerTiros>();
    
    private LineChartModel graficoLineas = new LineChartModel();
    private BarChartModel graficoBarras = new BarChartModel();
    private ListaEquipos equipoSeleccionado;
    
    private String classTiro="";
    private String make="●";
    private String miss="×";
    private UIComponent found;
    
    private JugadorDataImpl data=new JugadorDataImpl();
    private JugadorDataImpl dataPlayoff=new JugadorDataImpl();
    private JugadorControllerUtilidades jugadorUtil = new JugadorControllerUtilidades();
    
    
    private String listaCuartosElegidos;
    private List<String> listaCuartos= new ArrayList<String>();
    
    private String listaDentroElegidos;
    private List<String> listaDentro = new ArrayList<String>();
    
    private String listaDistanciasElegidas;
    private List<String> listaDistancias = new ArrayList<String>();
    
    private String listaTiempoRestanteElegido;
    private List<String> listaTiempoRestante = new ArrayList<String>();
    
    private String listaTipoCanastaElegida;
    private List<String> listaTipoCanasta = new ArrayList<String>();
    
    private String listaSituacionPartidoElegido;
    private List<String> listaSituacionPartido = new ArrayList<String>();
    
    private String listaPonerseDelanteElegido;
    private List<String> listaPonerseDelante = new ArrayList<String>();
    
    private String[] listaTemporadasElegidas;
    
    private String cuartoElegido;
    private String localVisitante;
    
    private DonutChartModel donutModel = new DonutChartModel();
    private DonutChartModel donutModel2;
    private DonutChartModel donutModel3;
    private DonutChartModel donutModel4;
    private DonutChartModel donutModel5;
    private DonutChartModel donutModel6;
    private DonutChartModel donutModel7;
    private DonutChartModel donutModel8;
    private DonutChartModel donutModel9;
    private DonutChartModel donutModel10;
    private DonutChartModel donutModel11;
    private DonutChartModel donutModel12;
    
    private String donutModelPor1;
    private String donutModelPor2;
    private String donutModelPor3;
    private String donutModelPor4;
    private String donutModelPor5;
    private String donutModelPor6;
    private String donutModelPor7;
    private String donutModelPor8;
    private String donutModelPor9;
    private String donutModelPor10;
    private String donutModelPor11;
    private String donutModelPor12;
    
    private int localRegularDosPuntosDentro=0;
    private int localRegularDosPuntosFuera=0;
    private int localRegularTresPuntosDentro=0;
    private int localRegularTresPuntosFuera=0;
    private int localRegularTotalDentro=0;
    private int localRegularTotalFuera=0;
    
    private int visitanteRegularDosPuntosDentro=0;
    private int visitanteRegularDosPuntosFuera=0;
    private int visitanteRegularTresPuntosDentro=0;
    private int visitanteRegularTresPuntosFuera=0;
    private int visitanteRegularTotalDentro=0;
    private int visitanteRegularTotalFuera=0;
    
    private int localPlayoffDosPuntosDentro=0;
    private int localPlayoffDosPuntosFuera=0;
    private int localPlayoffTresPuntosDentro=0;
    private int localPlayoffTresPuntosFuera=0;
    private int localPlayoffTotalDentro=0;
    private int localPlayoffTotalFuera=0;
    
    private int visitantePlayoffDosPuntosDentro=0;
    private int visitantePlayoffDosPuntosFuera=0;
    private int visitantePlayoffTresPuntosDentro=0;
    private int visitantePlayoffTresPuntosFuera=0;
    private int visitantePlayoffTotalDentro=0;
    private int visitantePlayoffTotalFuera=0;
    
    private ArrayList<ControllerPartidoJugador> listaPartidosTemporadaRegular;
    private ArrayList<ControllerPartidoJugador> listaPartidosPlayOff;
    
    //TAB 3
    private String partidosTemporada="";
    
    @PostConstruct
    public void init(){
        atributosMap = Utilidades.devolverHashMapAtributos();
        setInvisibleEstadisticasGlobales("invisible"); //Para cuando funcione todo invisible //Probando statsJugador
        setInvisibleTabs("invisible"); //Para cuando funcione todo invisible //Probando ""
        setInvisibleFindJugador(true);
        rellenarMenus();
        //createDonutModel();
    }
    
    private void rellenarMenus(){
       listaCuartos = jugadorUtil.devolverMenuCuartos();
       listaDentro = jugadorUtil.devolverMenuDentro();
       listaDistancias = jugadorUtil.devolverMenuDistancia();
       listaTiempoRestante = jugadorUtil.devolverMenuTiempoRestante();
       listaTipoCanasta = jugadorUtil.devolverMenuTipoCanasta();
       listaSituacionPartido = jugadorUtil.devolverSituacionesPartido();
       listaPonerseDelante = jugadorUtil.devolverPonerseDelante();
    }
    
    /**
     * Función que va buscando los jugadores dependiendo de lo que nos escriban
     * en el input del nombre. Segun vayamos escribiendo nos ira cribando
     * @throws IOException 
     */
    public void btnBuscarClick() throws IOException{
        
        //setVerEstadisticasJugador(false);        
        error="";
        buscarFiltro(nombreJugador.toUpperCase());
    }
    
    /**
     * Funcion que nos devuelve la lista de jugadores al buscar por nombre y apellido
     * Si esta vacio el campo, no rellenamos nada de los jugadores
     * @param nombre
     * @throws IOException 
     */
    private void buscarFiltro(String nombre) throws IOException{   
        if(!"".equals(nombre)){
            listaJugadores = jugadorUtil.devolverJugadores(nombre);
        }else{
            listaJugadores = new ArrayList<Jugador>();
        }
        devolverAltoFoto();
    }
    
    /**
     * Recargamos las estadisticas globales del jugador de toda su carrera
     */
    public void verEstadisticasJugadorTotales(){
        if(!"".equals(jugadorSeleccionado.getCodigo())){
            partidosDeLocalPlayoff = new ControllerEstadisticaNormal();
            partidosDeLocalRegular = new ControllerEstadisticaNormal();
            partidosDeVisitantePlayoff = new ControllerEstadisticaNormal();
            partidosDeVisitanteRegular = new ControllerEstadisticaNormal();
            listaProgresiones.clear();
            listaPartidosLocalVisitante.clear();
            listaSeleccionTemporadasProgresion.clear();
            listaEstadisticasJugador = new ArrayList<ClaseEstadisticaNormalTotales>();            
            listaEstadisticasCabecera = new ArrayList<ClaseEstadisticaNormalTotales>();
            conectamosBaseDatos(0);
            setInvisibleEstadisticasGlobales("statsJugador");
            setInvisibleTabs("");
            setInvisibleFindJugador(false);
            borrarDatosContraEquipo();
        }
    }
    
    /**
     * Función que nos busca las estadisticas por temporada de un jugador.
     */
    public void verEstadisticas(){
        if(listaSeleccionTemporadasProgresion.size() == 0){
            error = "Debe seleccionar al menos una temporada";
            listaEstadisticasJugador.clear();
        }else{
            conectamosBaseDatos(1);
            error ="Estamos mirando las temporadas: "+listaSeleccionTemporadasProgresion;
        }
    }
    
    /**
     * Hacemos las busquedas de los datos de la pestaña de 'Estadisticas por Temporada'
     * Si objeto = 0, vamos a recuperar las estadisticas globales de la carrera del jugador.
     * Las que saldran en la cabecera
     * Si objeto = 1, vamos a recuperar las estadisticas de las temporadas clicadas.
     * Saldran en la parte de abajo
     * @param objeto 
     */
    private void conectamosBaseDatos(int objeto){
        
        if(objeto == 0){
            listaEstadisticasCabecera = new ArrayList<ClaseEstadisticaNormalTotales>();
            listaEstadisticasCabecera = data.devolverListaEstadisticasCabecera(jugadorSeleccionado.getCodigo());
        }else if(objeto == 1){
            listaEstadisticasJugador = new ArrayList<ClaseEstadisticaNormalTotales>();
            listaEstadisticasJugador = data.devolverListaEstadisticasJugador(jugadorSeleccionado.getCodigo(),listaSeleccionTemporadasProgresion);
            if(listaEstadisticasJugador.isEmpty()){
                listaEstadisticasJugador.clear();
            }
        }
    }
    
     public void recogerPartidosTemporada(){
         listaPartidosTemporadaRegular = data.devolverListaPartidosTemporada(partidosTemporada,jugadorSeleccionado.getCodigo(),false);
         listaPartidosPlayOff = dataPlayoff.devolverListaPartidosTemporada(partidosTemporada,jugadorSeleccionado.getCodigo(),true);
    }

    /**
     * Recogemos las estadisticas que tiene un jugador contra un equipo en concreto
     */
    public void verEstadisticasContraEquipos(){
        
        MongoClient mongo = null;
        mongo = new MongoClient("localhost",27017);
		
        if(mongo!=null) {
                borrarDatosContraEquipo();
                ArrayList<BasicDBObject> listaTirosLocal = null;
                        
                DB db = mongo.getDB("nba");
                DBCursor cursor;

                for(String temporada:listaTemporadasElegidas){
                        
                            
                    DBCollection collection =db.getCollection(temporada);

                    BasicDBObject allQuery = new BasicDBObject();
                    BasicDBObject fields = new BasicDBObject();
                    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();

    //          EL JUGADOR COMO LOCAL ***************************************************************

                    obj.add(new BasicDBObject("equipoLocal.jugadores.id", jugadorSeleccionado.getCodigo()));
                    if(null!=equipoSeleccionado){
                        obj.add(new BasicDBObject("equipoVisitante.nombreAbreviado", equipoSeleccionado.getAbre()));
                    }
                    allQuery.put("$and", obj);

                    //allQuery.put("playOff", false); // SI QUEREMOS PLAYOFF

                    fields.put("_id", 0);

                    fields.put("equipoLocal.jugadores.id", 1);
                    fields.put("playOff", 1);
                    cuartoElegido = jugadorUtil.devolverCuarto(listaCuartosElegidos);
                    fields.put("equipoLocal.jugadores."+cuartoElegido, 1);
                    fields.put("equipoLocal.jugadores.listaTiros", 1);

                    cursor = collection.find(allQuery,fields);

                    while(cursor.hasNext()) {
                        setLocalVisitante("LOCAL");
                        DBObject get = cursor.next();
                        ArrayList<BasicDBObject> listaLocales =(ArrayList<BasicDBObject>) ((BasicDBObject) get.get("equipoLocal")).get("jugadores");

                        for(int i = 0;i<listaLocales.size();i++){
                            if(listaLocales.get(i).get("id").equals(jugadorSeleccionado.getCodigo())){
                                Map ver = (Map)listaLocales.get(i).toMap().get(cuartoElegido);
                                if(ver!=null){
                                    if(ver.size()>1){
                                        partidosTotales++;
                                        partidosLocal++;
                                        if((boolean)get.get("playOff")){
                                            partidosLocalPlayoff++;
                                            partidosDeLocalPlayoff = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaLocales.get(i).toMap(),partidosDeLocalPlayoff,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaLocales.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTiros((Map)listaTirosLocal.get(j).toMap()),listaTirosLocalPlayoff,2);
                                                }
                                            }
                                        }else{
                                            partidosLocalRegular++;
                                            partidosDeLocalRegular = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaLocales.get(i).toMap(),partidosDeLocalRegular,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaLocales.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTiros((Map)listaTirosLocal.get(j).toMap()),listaTirosLocalRegular,1);
                                                }
                                            }   
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    partidosDeLocalPlayoff.calcularTirosDosPuntos();
                    partidosDeLocalPlayoff.setUbicacion("");
                    partidosDeLocalPlayoff.setCuando("PlayOff");
                    partidosDeLocalPlayoff.setPartidosJugados(partidosLocalPlayoff);

                    partidosDeLocalRegular.calcularTirosDosPuntos();
                    partidosDeLocalRegular.setUbicacion("Local");
                    partidosDeLocalRegular.setCuando("Regular");
                    partidosDeLocalRegular.setPartidosJugados(partidosLocalRegular);

                    if(partidosLocalRegular!=0){
                        listaPartidosLocalVisitante.add(partidosDeLocalRegular);
                    }
                    if(partidosLocalPlayoff!=0){
                        listaPartidosLocalVisitante.add(partidosDeLocalPlayoff);
                    }


    //          EL JUGADOR COMO VISITANTE ***************************************************************

                    allQuery = new BasicDBObject();
                    fields = new BasicDBObject();
                    obj = new ArrayList<BasicDBObject>();

                    obj.add(new BasicDBObject("equipoVisitante.jugadores.id", jugadorSeleccionado.getCodigo()));
                    if(null!=equipoSeleccionado){
                        obj.add(new BasicDBObject("equipoLocal.nombreAbreviado", equipoSeleccionado.getAbre()));
                    }
                    allQuery.put("$and", obj);

                    //allQuery.put("playOff", false); // SI QUEREMOS PLAYOFF

                    fields.put("_id", 0);

                    fields.put("equipoVisitante.jugadores.id", 1);
                    fields.put("playOff", 1);
                    fields.put("equipoVisitante.jugadores."+cuartoElegido, 1);
                    fields.put("equipoVisitante.jugadores.listaTiros", 1);

                    cursor = collection.find(allQuery,fields);

                    while(cursor.hasNext()) {
                        setLocalVisitante("VISITANTE");
                        DBObject get = cursor.next();
                        ArrayList<BasicDBObject> listaVisitantes =
                                        (ArrayList<BasicDBObject>) ((BasicDBObject) get.get("equipoVisitante")).get("jugadores");
                        for(int i = 0;i<listaVisitantes.size();i++){
                            if(listaVisitantes.get(i).get("id").equals(jugadorSeleccionado.getCodigo())){
                                Map ver = (Map)listaVisitantes.get(i).toMap().get(cuartoElegido);
                                if(ver!=null){
                                    if(ver.size()>1){
                                        partidosTotales++;
                                        partidosVisitante++;
                                        if((boolean)get.get("playOff")){
                                            partidosVisitantePlayoff++;
                                            partidosDeVisitantePlayoff = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaVisitantes.get(i).toMap(),partidosDeVisitantePlayoff,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaVisitantes.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTiros((Map)listaTirosLocal.get(j).toMap()),listaTirosVisitantePlayoff,4);
                                                }
                                            }
                                        }else{
                                            partidosVisitanteRegular++;
                                            partidosDeVisitanteRegular = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaVisitantes.get(i).toMap(),partidosDeVisitanteRegular,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaVisitantes.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTiros((Map)listaTirosLocal.get(j).toMap()),listaTirosVisitanteRegular,3);
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    partidosDeVisitantePlayoff.calcularTirosDosPuntos();
                    partidosDeVisitantePlayoff.setUbicacion("");
                    partidosDeVisitantePlayoff.setCuando("PlayOff");
                    partidosDeVisitantePlayoff.setPartidosJugados(partidosVisitantePlayoff);

                    partidosDeVisitanteRegular.calcularTirosDosPuntos();
                    partidosDeVisitanteRegular.setUbicacion("Visitante");
                    partidosDeVisitanteRegular.setCuando("Regular");
                    partidosDeVisitanteRegular.setPartidosJugados(partidosVisitanteRegular);

                    if(partidosVisitanteRegular!=0){
                        listaPartidosLocalVisitante.add(partidosDeVisitanteRegular);
                    }

                    if(partidosVisitantePlayoff!=0){
                        listaPartidosLocalVisitante.add(partidosDeVisitantePlayoff);
                    }
                        
                }
        }
        rellenarGraficoDonut();
        rellenarGraficoDonut2();
        rellenarGraficoDonut3();
        rellenarGraficoDonut4();
        rellenarGraficoDonut5();
        rellenarGraficoDonut6();
        rellenarGraficoDonut7();
        rellenarGraficoDonut8();
        rellenarGraficoDonut9();
        rellenarGraficoDonut10();
        rellenarGraficoDonut11();
        rellenarGraficoDonut12();
        System.out.println("acabamos");
    }
    private void insertarTiro(ControllerTiros cartaTiro, ArrayList<ControllerTiros> listaTiros,int situacionTemporada) {
        
        // Miramos si dentro o fuera los tiros
        if(!comprobarCanastaDentroFuera(cartaTiro)){return;}
            
        // Miramos la distancia
        if(!comprobarDistanciaCanasta(cartaTiro)){return;}
            
        // Miramos el tiempo para acabar el cuarto
        if(!comprobarTiempoRestante(cartaTiro)){return;}
                
        // Miramos el tipo de canasta
        if(!comprobarTipoCanasta(cartaTiro)){return;}
        
        // Miramos en que cuarto es el tiro
        if(!comprobarCanastaCuarto(cartaTiro)){return;}
        
        // Miramos la situación del partido cuando se efectuo el tiro. Perdiendo - Empate - Ganando
        if(!comprobarSituacionTiro(cartaTiro)){return;}
        
        // Miramos situación de tiro, si al encestar se pone por delante el equipo
        if(!comprobarPonerseDelante(cartaTiro)){return;}
        
        switch(situacionTemporada){
            case 1:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        localRegularDosPuntosDentro++;
                        localRegularTotalDentro++;
                    }else{
                        localRegularDosPuntosFuera++;
                        localRegularTotalFuera++;
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        localRegularTresPuntosDentro++;
                        localRegularTotalDentro++;
                    }else{
                        localRegularTresPuntosFuera++;
                        localRegularTotalFuera++;
                    }
                }
                break;
            case 2:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        localPlayoffDosPuntosDentro++;
                        localPlayoffTotalDentro++;
                    }else{
                        localPlayoffDosPuntosFuera++;
                        localPlayoffTotalFuera++;
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        localPlayoffTresPuntosDentro++;
                        localPlayoffTotalDentro++;
                    }else{
                        localPlayoffTresPuntosFuera++;
                        localPlayoffTotalFuera++;
                    }
                }
                break;
            case 3:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        visitanteRegularDosPuntosDentro++;
                        visitanteRegularTotalDentro++;
                    }else{
                        visitanteRegularDosPuntosFuera++;
                        visitanteRegularTotalFuera++;
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        visitanteRegularTresPuntosDentro++;
                        visitanteRegularTotalDentro++;
                    }else{
                        visitanteRegularTresPuntosFuera++;
                        visitanteRegularTotalFuera++;
                    }
                }
                break;
            case 4:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        visitantePlayoffDosPuntosDentro++;
                        visitantePlayoffTotalDentro++;
                    }else{
                        visitantePlayoffDosPuntosFuera++;
                        visitantePlayoffTotalFuera++;
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        visitantePlayoffTresPuntosDentro++;
                        visitantePlayoffTotalDentro++;
                    }else{
                        visitantePlayoffTresPuntosFuera++;
                        visitantePlayoffTotalFuera++;
                    }
                }
                break;
        }
        listaTiros.add(cartaTiro);
            
    }
    
    private boolean comprobarCanastaCuarto(ControllerTiros cartaTiro){
        if(cuartoElegido.equals("boxscore")){
            return true;
        }else if(cuartoElegido.equals("cuarto1") && cartaTiro.getCuarto().contains("1st quarter")){
            return true;
        }else if(cuartoElegido.equals("cuarto2") && cartaTiro.getCuarto().contains("2nd quarter")){
            return true;
        }else if(cuartoElegido.equals("cuarto3") && cartaTiro.getCuarto().contains("3rd quarter")){
            return true;
        }else if(cuartoElegido.equals("cuarto4") && cartaTiro.getCuarto().contains("4th quarter")){
            return true;
        }else if(cuartoElegido.equals("over1") && cartaTiro.getCuarto().contains("1st over")){
            return true;
        }else if(cuartoElegido.equals("over2") && cartaTiro.getCuarto().contains("2nd over")){
            return true;
        }else if(cuartoElegido.equals("over3") && cartaTiro.getCuarto().contains("3rd over")){
            return true;
        }else if(cuartoElegido.equals("over4") && cartaTiro.getCuarto().contains("4th over")){
            return true;
        }
        return false;
    }
    
    private boolean comprobarDistanciaCanasta(ControllerTiros cartaTiro){
        if(listaDistanciasElegidas.equals("Todas las distancias")){
            return true;
        }
        double[] distancia = jugadorUtil.devolverDistanciaMetrosPies(listaDistanciasElegidas);
        if(cartaTiro.getDistancia() >=distancia[0] && cartaTiro.getDistancia() <=distancia[1]) {
        	return true;
        }else {
        	return false;
        }
    }
    
    
    private boolean comprobarCanastaDentroFuera(ControllerTiros cartaTiro) {
        if(listaDentroElegidos.equals("Todas las Canastas")){
            return true;
        }else if(listaDentroElegidos.equals("Dentro") && cartaTiro.isDentro()){
            return true;
        }else if(listaDentroElegidos.equals("Fuera") && !cartaTiro.isDentro()){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarTipoCanasta(ControllerTiros cartaTiro){
        if(listaTipoCanastaElegida.equals("Todos los tipos")){
            return true;
        }else if(listaTipoCanastaElegida.equals("2 Puntos") && cartaTiro.getTipo().equals("2")){
            return true;
        }else if(listaTipoCanastaElegida.equals("3 Puntos") && cartaTiro.getTipo().equals("3")){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarTiempoRestante(ControllerTiros cartaTiro){
        if(listaTiempoRestanteElegido.equals("Todo el cuarto")){
            return true;
        }
        int tiempoCuarto = jugadorUtil.devolverTiempoCuarto(listaTiempoRestanteElegido);
        if(cartaTiro.getTiempoRestante()<=tiempoCuarto){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarSituacionTiro(ControllerTiros cartaTiro){
        if(listaSituacionPartidoElegido.equals("Todas las situaciones")){
            return true;
        }
        
        String[] marcador = cartaTiro.getTanteo().split("-");
        if(marcador[0].equals("") || !cartaTiro.getTanteo().contains("-")){
            return true;
        }
        int visitante=Integer.parseInt(marcador[0]);
        int local=Integer.parseInt(marcador[1]);
        if(cartaTiro.isDentro()){
            if(localVisitante.equals("LOCAL")){
                local=local-Integer.parseInt(cartaTiro.getTipo());
            }else{
                visitante=visitante-Integer.parseInt(cartaTiro.getTipo());
            }
        }
        
        if(listaSituacionPartidoElegido.equals("Empate") && visitante == local){
            return true;
        }else if(localVisitante.equals("LOCAL")){
            if(listaSituacionPartidoElegido.equals("Ganando") && local>visitante){
                return true;
            }else if(listaSituacionPartidoElegido.equals("Perdiendo") && visitante>local){
                return true;
            }
        }else{ // ES VISITANTE
            if(listaSituacionPartidoElegido.equals("Ganando") && visitante>local){
                return true;
            }else if(listaSituacionPartidoElegido.equals("Perdiendo") && local>visitante){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean comprobarPonerseDelante(ControllerTiros cartaTiro){
        if(listaPonerseDelanteElegido.equals("Todas las canastas")){
            return true;
        }else{ // hemos elegido ponerse por delante, mirar que el tiro haya entrado y mirar la anotación anterior y la de despues
            if(cartaTiro.isDentro()){
                String[] marcador = cartaTiro.getTanteo().split("-");
                if(marcador[0].equals("") || !cartaTiro.getTanteo().contains("-")){
                    return false;
                }
                int visitante=Integer.parseInt(marcador[0]);
                int local=Integer.parseInt(marcador[1]);
                if(localVisitante.equals("LOCAL")){
                    local=local-Integer.parseInt(cartaTiro.getTipo());
                }else{
                    visitante=visitante-Integer.parseInt(cartaTiro.getTipo());
                }
                
                if(visitante == local){
                    return true;
                }else if(localVisitante.equals("LOCAL")){
                    if(local>visitante){
                        return false;
                    }else if(visitante>local){
                        if((visitante-local)<=Integer.parseInt(cartaTiro.getTipo())){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }else{ // ES VISITANTE
                    if(visitante>local){
                        return false;
                    }else if(local>visitante){
                        if((local-visitante)<=Integer.parseInt(cartaTiro.getTipo())){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }else{
                return false;
            }
        }
        return false;
    }
    
    private void doFind(FacesContext context, String clientId) {
        FacesContext.getCurrentInstance().getViewRoot().invokeOnComponent(context, clientId, new ContextCallback() {
            @Override
            public void invokeContextCallback(FacesContext context,
                    UIComponent component) {
                found = component;
            }
        });
    }
    
    public String comprobarTiro(Boolean dentro){
        if(dentro){
            return getMake();
        }else{
            return getMiss();
        }
    }
    
    public String comprobarTiroClase(Boolean dentro){
        if(dentro){
            return "make";
        }else{
            return "miss";
        }
    }

    public void borrarDatosContraEquipo() {
        partidosDeLocalPlayoff = new ControllerEstadisticaNormal();
        partidosDeLocalRegular = new ControllerEstadisticaNormal();
        partidosDeVisitantePlayoff = new ControllerEstadisticaNormal();
        partidosDeVisitanteRegular = new ControllerEstadisticaNormal();
        listaTirosLocalRegular.clear();
        listaTirosVisitanteRegular.clear();
        listaTirosLocalPlayoff.clear();
        listaTirosVisitantePlayoff.clear();
        listaPartidosLocalVisitante.clear();
        partidosTotales=0;
        partidosLocal=0;
        partidosVisitante=0;
        partidosLocalRegular=0;
        partidosVisitanteRegular=0;
        partidosLocalPlayoff=0;
        partidosVisitantePlayoff=0;
        resetearContadores();
    }
    
    public void devolverAltoFoto(){
        if(listaJugadores.size()>1){
            altoFoto = -43;
        }else if(listaJugadores.size()==1){
            altoFoto = -160;
        }else{
            altoFoto = 0;
        }
    }
    
    private void rellenarGraficoDonut(){
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(localRegularDosPuntosFuera);
        values.add(localRegularDosPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  TR Local 2 Puntos");
        labels.add("Canasta Dentro TR Local 2 Puntos");
        data.setLabels(labels);
                 
        donutModel.setData(data);
        
        donutModelPor1 = actualizarPorcentajeTirosDonut(localRegularDosPuntosDentro,localRegularDosPuntosFuera);
    }
    
    private void rellenarGraficoDonut2(){
        donutModel2 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(localRegularTresPuntosFuera);
        values.add(localRegularTresPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  TR Local 3 Puntos");
        labels.add("Canasta Dentro TR Local 3 Puntos");
        data.setLabels(labels);
         
        donutModel2.setData(data);
        
        setDonutModelPor2(actualizarPorcentajeTirosDonut(localRegularTresPuntosDentro,localRegularTresPuntosFuera));
    }
    
    private void rellenarGraficoDonut3(){
        donutModel3 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(localRegularTotalFuera);
        values.add(localRegularTotalDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  TR Local");
        labels.add("Canasta Dentro TR Local");
        data.setLabels(labels);
         
        donutModel3.setData(data);
        
        setDonutModelPor3(actualizarPorcentajeTirosDonut(localRegularTotalDentro,localRegularTotalFuera));
    }
    
    private void rellenarGraficoDonut4(){
        donutModel4 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(localPlayoffDosPuntosFuera);
        values.add(localPlayoffDosPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  PO Local 2 Puntos");
        labels.add("Canasta Dentro PO Local 2 Puntos");
        data.setLabels(labels);
         
        donutModel4.setData(data);
        
        setDonutModelPor4(actualizarPorcentajeTirosDonut(localPlayoffDosPuntosDentro,localPlayoffDosPuntosFuera));
    }
    
    private void rellenarGraficoDonut5(){
        donutModel5 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(localPlayoffTresPuntosFuera);
        values.add(localPlayoffTresPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  PO Local 3 Puntos");
        labels.add("Canasta Dentro PO Local 3 Puntos");
        data.setLabels(labels);
         
        donutModel5.setData(data);
        
        setDonutModelPor5(actualizarPorcentajeTirosDonut(localPlayoffTresPuntosDentro,localPlayoffTresPuntosFuera));
    }
    
    private void rellenarGraficoDonut6(){
        donutModel6 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(localPlayoffTotalFuera);
        values.add(localPlayoffTotalDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  PO Local");
        labels.add("Canasta Dentro PO Local");
        data.setLabels(labels);
         
        donutModel6.setData(data);
        
        setDonutModelPor6(actualizarPorcentajeTirosDonut(localPlayoffTotalDentro,localPlayoffTotalFuera));
    }
    
    private void rellenarGraficoDonut7(){
        donutModel7 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(visitanteRegularDosPuntosFuera);
        values.add(visitanteRegularDosPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  TR Visitante 2 Puntos");
        labels.add("Canasta Dentro TR Visitante 2 Puntos");
        data.setLabels(labels);
        
        donutModel7.setData(data);
        
        setDonutModelPor7(actualizarPorcentajeTirosDonut(visitanteRegularDosPuntosDentro,visitanteRegularDosPuntosFuera));
    }
    
    private void rellenarGraficoDonut8(){
        donutModel8 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(visitanteRegularTresPuntosFuera);
        values.add(visitanteRegularTresPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  TR Visitante 3 Puntos");
        labels.add("Canasta Dentro TR Visitante 3 Puntos");
        data.setLabels(labels);
         
        donutModel8.setData(data);
        
        setDonutModelPor8(actualizarPorcentajeTirosDonut(visitanteRegularTresPuntosDentro,visitanteRegularTresPuntosFuera));
    }
    
    private void rellenarGraficoDonut9(){
        donutModel9 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(visitanteRegularTotalFuera);
        values.add(visitanteRegularTotalDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  TR Visitante");
        labels.add("Canasta Dentro TR Visitante");
        data.setLabels(labels);
         
        donutModel9.setData(data);
        
        setDonutModelPor9(actualizarPorcentajeTirosDonut(visitanteRegularTotalDentro,visitanteRegularTotalFuera));
    }
    
    private void rellenarGraficoDonut10(){
        donutModel10 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(visitantePlayoffDosPuntosFuera);
        values.add(visitantePlayoffDosPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  PO Visitante 2 Puntos");
        labels.add("Canasta Dentro PO Visitante 2 Puntos");
        data.setLabels(labels);
         
        donutModel10.setData(data);
        
        setDonutModelPor10(actualizarPorcentajeTirosDonut(visitantePlayoffDosPuntosDentro,visitantePlayoffDosPuntosFuera));
    }
    
    private void rellenarGraficoDonut11(){
        donutModel11 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(visitantePlayoffTresPuntosFuera);
        values.add(visitantePlayoffTresPuntosDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  PO Visitante 3 Puntos");
        labels.add("Canasta Dentro PO Visitante 3 Puntos");
        data.setLabels(labels);
         
        donutModel11.setData(data);
        
        setDonutModelPor11(actualizarPorcentajeTirosDonut(visitantePlayoffTresPuntosDentro,visitantePlayoffTresPuntosFuera));
    }
    
    private void rellenarGraficoDonut12(){
        donutModel12 = new DonutChartModel();
        ChartData data = new ChartData();
         
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(visitantePlayoffTotalFuera);
        values.add(visitantePlayoffTotalDentro);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(178, 34, 34)");
        bgColors.add("rgb(34, 139, 34)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Canasta Fuera  PO Visitante");
        labels.add("Canasta Dentro PO Visitante");
        data.setLabels(labels);
        
        donutModel12.setData(data);
        
        setDonutModelPor12(actualizarPorcentajeTirosDonut(visitantePlayoffTotalDentro,visitantePlayoffTotalFuera));
    }
    
    private String actualizarPorcentajeTirosDonut(int dentro, int fuera) {
    	String porcentaje = new java.text.DecimalFormat("0.##").format(Double.valueOf((double) dentro/(dentro+fuera)));
    	
    	if(porcentaje.equals("NaN")) {
    		return "";
    	}
    	return (new java.text.DecimalFormat("0.##").format(Double.parseDouble(porcentaje.replace(",", "."))*100))+"%";
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public List<String> getListaJ() {
        return listaJ;
    }

    public void setListaJ(List<String> listaJ) {
        this.listaJ = listaJ;
    }

    public String getImgagenCarga() {
        return imagenCarga;
    }

    public void setImagenCarga(String photo) {
        this.imagenCarga = photo;
    }

    public Jugador getJugadorSeleccionado() {
        return jugadorSeleccionado;
    }

    public void setJugadorSeleccionado(Jugador  jugadorSeleccionado) {
        this.jugadorSeleccionado = jugadorSeleccionado;
    }
    
    public ArrayList<ClaseEstadisticaNormalTotales> getListaEstadisticasCabecera() {
        return listaEstadisticasCabecera;
    }

    public void setListaEstadisticasCabecera(ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasCabecera) {
        this.listaEstadisticasCabecera = listaEstadisticasCabecera;
    }

    public ArrayList<String> getListaSeleccionTemporadasProgresion() {
        return listaSeleccionTemporadasProgresion;
    }

    public void setListaSeleccionTemporadasProgresion(ArrayList<String> listaSeleccionTemporadasProgresion) {
        this.listaSeleccionTemporadasProgresion = listaSeleccionTemporadasProgresion;
    }

    public String getAtributoProgresion() {
        return atributoProgresion;
    }

    public void setAtributoProgresion(String atributoProgresion) {
        this.atributoProgresion = atributoProgresion;
    }

    public List<SelectItem> getSelectMenuAtributo() {
        if(selectMenuAtributo==null){
            selectMenuAtributo = Utilidades.devolverMenuAtributos();
        }
        return selectMenuAtributo;
    }

    public void setSelectMenuAtributo(List<SelectItem> selectMenuAtributo) {
        this.selectMenuAtributo = selectMenuAtributo;
    }

    public List<SelectItem> getSelectMenuTiempoPartido() {
        if(selectMenuTiempoPartido==null){
            selectMenuTiempoPartido=Utilidades.devolverMenuTiempoPartido();
        }
        return selectMenuTiempoPartido;
    }

    public void setSelectMenuTiempoPartido(List<SelectItem> selectMenuTiempoPartido) {
        this.selectMenuTiempoPartido = selectMenuTiempoPartido;
    }

    public List<SelectItem> getSelectMenuUbicacion() {
        if(selectMenuUbicacion==null){
            selectMenuUbicacion=Utilidades.devolverMenuUbicacion();
        }
        return selectMenuUbicacion;
    }

    public void setSelectMenuUbicacion(List<SelectItem> selectMenuUbicacion) {
        this.selectMenuUbicacion = selectMenuUbicacion;
    }

    public List<SelectItem> getSelectMenuResultado() {
        if(selectMenuResultado==null){
            selectMenuResultado=Utilidades.devolverMenuResultado();
        }
        return selectMenuResultado;
    }

    public void setSelectMenuResultado(List<SelectItem> selectMenuResultado) {
        this.selectMenuResultado = selectMenuResultado;
    }

    public List<SelectItem> getSelectMenuCuando() {
        if(selectMenuCuando==null){
            selectMenuCuando = Utilidades.devolverMenuCuando();
        }
        return selectMenuCuando;
    }

    public void setSelectMenuCuando(List<SelectItem> selectMenuCuando) {
        this.selectMenuCuando = selectMenuCuando;
    }

    public String getTiempoPartidoProgresion() {
        return tiempoPartidoProgresion;
    }

    public void setTiempoPartidoProgresion(String tiempoPartidoProgresion) {
        this.tiempoPartidoProgresion = tiempoPartidoProgresion;
    }

    public String getUbicacionProgresion() {
        return ubicacionProgresion;
    }

    public void setUbicacionProgresion(String ubicacionProgresion) {
        this.ubicacionProgresion = ubicacionProgresion;
    }

    public String getResultadoProgresion() {
        return resultadoProgresion;
    }

    public void setResultadoProgresion(String resultadoProgresion) {
        this.resultadoProgresion = resultadoProgresion;
    }

    public String getCuandoProgresion() {
        return cuandoProgresion;
    }

    public void setCuandoProgresion(String cuandoProgresion) {
        this.cuandoProgresion = cuandoProgresion;
    }

    public List<SelectItem> getSelectMenuTemporadas() {
        if(selectMenuTemporadas == null){
            selectMenuTemporadas = Utilidades.devolverTemporadas();
        }
        return selectMenuTemporadas;
    }

    public void setSelectMenuTemporadas(List<SelectItem> selectMenuTemporadas) {
        this.selectMenuTemporadas = selectMenuTemporadas;
    }
    
    public LineChartModel getGraficoLineas() {
        return graficoLineas;
    }

    public void setGraficoLineas(LineChartModel graficoLineas) {
        this.graficoLineas = graficoLineas;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<ControllerPartido> getListaPartidosLocal() {
        return listaPartidosLocal;
    }

    public void setListaPartidosLocal(ArrayList<ControllerPartido> listaPartidosLocal) {
        this.listaPartidosLocal = listaPartidosLocal;
    }

    public ArrayList<ControllerPartido> getListaPartidosVisitante() {
        return listaPartidosVisitante;
    }

    public void setListaPartidosVisitante(ArrayList<ControllerPartido> listaPartidosVisitante) {
        this.listaPartidosVisitante = listaPartidosVisitante;
    }
    
    public BarChartModel getGraficoBarras() {
        return graficoBarras;
    }

    public void setGraficoBarras(BarChartModel graficoBarras) {
        this.graficoBarras = graficoBarras;
    }

    public ArrayList<ClaseEstadisticaNormalTotales> getListaEstadisticasJugador() {
        return listaEstadisticasJugador;
    }

    public void setListaEstadisticasJugador(ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasJugador) {
        this.listaEstadisticasJugador = listaEstadisticasJugador;
    }
    
    public ListaEquipos[] getEquipos(){
        return ListaEquipos.values();
    }

    public ListaEquipos getEquipoSeleccionado() {
        return equipoSeleccionado;
    }

    public void setEquipoSeleccionado(ListaEquipos equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }

    public int getPartidosTotales() {
        return partidosTotales;
    }

    public void setPartidosTotales(int partidosTotales) {
        this.partidosTotales = partidosTotales;
    }

    public int getPartidosLocal() {
        return partidosLocal;
    }

    public void setPartidosLocal(int partidosLocal) {
        this.partidosLocal = partidosLocal;
    }

    public int getPartidosVisitante() {
        return partidosVisitante;
    }

    public void setPartidosVisitante(int partidosVisitante) {
        this.partidosVisitante = partidosVisitante;
    }

    public int getPartidosLocalRegular() {
        return partidosLocalRegular;
    }

    public void setPartidosLocalRegular(int partidosLocalRegular) {
        this.partidosLocalRegular = partidosLocalRegular;
    }

    public int getPartidosVisitanteRegular() {
        return partidosVisitanteRegular;
    }

    public void setPartidosVisitanteRegular(int partidosVisitanteRegular) {
        this.partidosVisitanteRegular = partidosVisitanteRegular;
    }

    public int getPartidosLocalPlayoff() {
        return partidosLocalPlayoff;
    }

    public void setPartidosLocalPlayoff(int partidosLocalPlayoff) {
        this.partidosLocalPlayoff = partidosLocalPlayoff;
    }

    public int getPartidosVisitantePlayoff() {
        return partidosVisitantePlayoff;
    }

    public void setPartidosVisitantePlayoff(int partidosVisitantePlayoff) {
        this.partidosVisitantePlayoff = partidosVisitantePlayoff;
    }

    public ArrayList<ControllerEstadisticaNormal> getListaPartidosLocalVisitante() {
        return listaPartidosLocalVisitante;
    }

    public void setListaPartidosLocalVisitante(ArrayList<ControllerEstadisticaNormal> listaPartidosLocalVisitante) {
        this.listaPartidosLocalVisitante = listaPartidosLocalVisitante;
    }

    public ArrayList<ControllerTiros> getListaTirosLocalRegular() {
        return listaTirosLocalRegular;
    }

    public void setListaTirosLocalRegular(ArrayList<ControllerTiros> listaTirosLocalRegular) {
        this.listaTirosLocalRegular = listaTirosLocalRegular;
    }
    
    public ArrayList<ControllerTiros> getListaTirosVisitanteRegular() {
        return listaTirosVisitanteRegular;
    }

    public void setListaTirosVisitanteRegular(ArrayList<ControllerTiros> listaTirosVisitanteRegular) {
        this.listaTirosVisitanteRegular = listaTirosVisitanteRegular;
    }

    public String getInvisibleEstadisticasGlobales() {
        return invisibleEstadisticasGlobales;
    }

    public void setInvisibleEstadisticasGlobales(String invisibleEstadisticasGlobales) {
        this.invisibleEstadisticasGlobales = invisibleEstadisticasGlobales;
    }

    public String getInvisibleTabs() {
        return invisibleTabs;
    }

    public void setInvisibleTabs(String invisibleTabs) {
        this.invisibleTabs = invisibleTabs;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMiss() {
        return miss;
    }

    public void setMiss(String miss) {
        this.miss = miss;
    }

    public ControllerEstadisticaNormal getPartidosDeLocalRegular() {
        return partidosDeLocalRegular;
    }

    public void setPartidosDeLocalRegular(ControllerEstadisticaNormal partidosDeLocalRegular) {
        this.partidosDeLocalRegular = partidosDeLocalRegular;
    }

    public ControllerEstadisticaNormal getPartidosDeLocalPlayoff() {
        return partidosDeLocalPlayoff;
    }

    public void setPartidosDeLocalPlayoff(ControllerEstadisticaNormal partidosDeLocalPlayoff) {
        this.partidosDeLocalPlayoff = partidosDeLocalPlayoff;
    }

    public ControllerEstadisticaNormal getPartidosDeVisitanteRegular() {
        return partidosDeVisitanteRegular;
    }

    public void setPartidosDeVisitanteRegular(ControllerEstadisticaNormal partidosDeVisitanteRegular) {
        this.partidosDeVisitanteRegular = partidosDeVisitanteRegular;
    }

    public ControllerEstadisticaNormal getPartidosDeVisitantePlayoff() {
        return partidosDeVisitantePlayoff;
    }

    public void setPartidosDeVisitantePlayoff(ControllerEstadisticaNormal partidosDeVisitantePlayoff) {
        this.partidosDeVisitantePlayoff = partidosDeVisitantePlayoff;
    }
    
    public int getAltoFoto() {
        return altoFoto;
    }

    public void setAltoFoto(int altoFoto) {
        this.altoFoto = altoFoto;
    }

    public ArrayList<ControllerTiros> getListaTirosLocalPlayoff() {
        return listaTirosLocalPlayoff;
    }

    public void setListaTirosLocalPlayoff(ArrayList<ControllerTiros> listaTirosLocalPlayoff) {
        this.listaTirosLocalPlayoff = listaTirosLocalPlayoff;
    }

    public ArrayList<ControllerTiros> getListaTirosVisitantePlayoff() {
        return listaTirosVisitantePlayoff;
    }

    public void setListaTirosVisitantePlayoff(ArrayList<ControllerTiros> listaTirosVisitantePlayoff) {
        this.listaTirosVisitantePlayoff = listaTirosVisitantePlayoff;
    }

    public String getListaCuartosElegidos() {
        return listaCuartosElegidos;
    }

    public void setListaCuartosElegidos(String listaCuartosElegidos) {
        this.listaCuartosElegidos = listaCuartosElegidos;
    }

    public List<String> getListaCuartos() {
        return listaCuartos;
    }

    public void setListaCuartos(List<String> listaCuartos) {
        this.listaCuartos = listaCuartos;
    }

    public String getListaDentroElegidos() {
        return listaDentroElegidos;
    }

    public void setListaDentroElegidos(String listaDentroElegidos) {
        this.listaDentroElegidos = listaDentroElegidos;
    }

    public List<String> getListaDentro() {
        return listaDentro;
    }

    public void setListaDentro(List<String> listaDentro) {
        this.listaDentro = listaDentro;
    }

    public String getListaDistanciasElegidas() {
        return listaDistanciasElegidas;
    }

    public void setListaDistanciasElegidas(String listaDistanciasElegidas) {
        this.listaDistanciasElegidas = listaDistanciasElegidas;
    }

    public List<String> getListaDistancias() {
        return listaDistancias;
    }

    public void setListaDistancias(List<String> listaDistancias) {
        this.listaDistancias = listaDistancias;
    }

    public String getListaTiempoRestanteElegido() {
        return listaTiempoRestanteElegido;
    }

    public void setListaTiempoRestanteElegido(String listaTiempoRestanteElegido) {
        this.listaTiempoRestanteElegido = listaTiempoRestanteElegido;
    }

    public List<String> getListaTiempoRestante() {
        return listaTiempoRestante;
    }

    public void setListaTiempoRestante(List<String> listaTiempoRestante) {
        this.listaTiempoRestante = listaTiempoRestante;
    }

    public String getListaTipoCanastaElegida() {
        return listaTipoCanastaElegida;
    }

    public void setListaTipoCanastaElegida(String listaTipoCanastaElegida) {
        this.listaTipoCanastaElegida = listaTipoCanastaElegida;
    }

    public List<String> getListaTipoCanasta() {
        return listaTipoCanasta;
    }

    public void setListaTipoCanasta(List<String> listaTipoCanasta) {
        this.listaTipoCanasta = listaTipoCanasta;
    }

    public String[] getListaTemporadasElegidas() {
        return listaTemporadasElegidas;
    }

    public void setListaTemporadasElegidas(String[] listaTemporadasElegidas) {
        this.listaTemporadasElegidas = listaTemporadasElegidas;
    }

    public String getLocalVisitante() {
        return localVisitante;
    }

    public void setLocalVisitante(String localVisitante) {
        this.localVisitante = localVisitante;
    }

    public String getListaSituacionPartidoElegido() {
        return listaSituacionPartidoElegido;
    }

    public void setListaSituacionPartidoElegido(String listaSituacionPartidoElegido) {
        this.listaSituacionPartidoElegido = listaSituacionPartidoElegido;
    }

    public List<String> getListaSituacionPartido() {
        return listaSituacionPartido;
    }

    public void setListaSituacionPartido(List<String> listaSituacionPartido) {
        this.listaSituacionPartido = listaSituacionPartido;
    }

    public String getListaPonerseDelanteElegido() {
        return listaPonerseDelanteElegido;
    }

    public void setListaPonerseDelanteElegido(String listaPonerseDelanteElegido) {
        this.listaPonerseDelanteElegido = listaPonerseDelanteElegido;
    }

    public List<String> getListaPonerseDelante() {
        return listaPonerseDelante;
    }

    public void setListaPonerseDelante(List<String> listaPonerseDelante) {
        this.listaPonerseDelante = listaPonerseDelante;
    }
    
    public DonutChartModel getDonutModel() {
        if(null == donutModel){
            donutModel = new DonutChartModel();
        }
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public DonutChartModel getDonutModel2() {
        if(null == donutModel2){
            donutModel2 = new DonutChartModel();
        }
        return donutModel2;
    }

    public void setDonutModel2(DonutChartModel donutModel2) {
        this.donutModel2 = donutModel2;
    }

    public DonutChartModel getDonutModel3() {
        if(null == donutModel3){
            donutModel3 = new DonutChartModel();
        }
        return donutModel3;
    }

    public void setDonutModel3(DonutChartModel donutModel3) {
        this.donutModel3 = donutModel3;
    }

    public DonutChartModel getDonutModel4() {
        if(null == donutModel4){
            donutModel4 = new DonutChartModel();
        }
        return donutModel4;
    }

    public void setDonutModel4(DonutChartModel donutModel4) {
        this.donutModel4 = donutModel4;
    }

    public DonutChartModel getDonutModel5() {
        if(null == donutModel5){
            donutModel5 = new DonutChartModel();
        }
        return donutModel5;
    }

    public void setDonutModel5(DonutChartModel donutModel5) {
        this.donutModel5 = donutModel5;
    }

    public DonutChartModel getDonutModel6() {
        if(null == donutModel6){
            donutModel6 = new DonutChartModel();
        }
        return donutModel6;
    }

    public void setDonutModel6(DonutChartModel donutModel6) {
        this.donutModel6 = donutModel6;
    }

    public DonutChartModel getDonutModel7() {
        if(null == donutModel7){
            donutModel7 = new DonutChartModel();
        }
        return donutModel7;
    }

    public void setDonutModel7(DonutChartModel donutModel7) {
        this.donutModel7 = donutModel7;
    }

    public DonutChartModel getDonutModel8() {
        if(null == donutModel8){
            donutModel8 = new DonutChartModel();
        }
        return donutModel8;
    }

    public void setDonutModel8(DonutChartModel donutModel8) {
        this.donutModel8 = donutModel8;
    }

    public DonutChartModel getDonutModel9() {
        if(null == donutModel9){
            donutModel9 = new DonutChartModel();
        }
        return donutModel9;
    }

    public void setDonutModel9(DonutChartModel donutModel9) {
        this.donutModel9 = donutModel9;
    }

    public DonutChartModel getDonutModel10() {
        if(null == donutModel10){
            donutModel10 = new DonutChartModel();
        }
        return donutModel10;
    }

    public void setDonutModel10(DonutChartModel donutModel10) {
        this.donutModel10 = donutModel10;
    }

    public DonutChartModel getDonutModel11() {
        if(null == donutModel11){
            donutModel11 = new DonutChartModel();
        }
        return donutModel11;
    }

    public void setDonutModel11(DonutChartModel donutModel11) {
        this.donutModel11 = donutModel11;
    }

    public DonutChartModel getDonutModel12() {
        if(null == donutModel12){
            donutModel12 = new DonutChartModel();
        }
        return donutModel12;
    }

    public void setDonutModel12(DonutChartModel donutModel12) {
        this.donutModel12 = donutModel12;
    }
    
    public int getLocalRegularDosPuntosDentro() {
        return localRegularDosPuntosDentro;
    }

    public void setLocalRegularDosPuntosDentro(int localRegularDosPuntosDentro) {
        this.localRegularDosPuntosDentro = localRegularDosPuntosDentro;
    }

    public int getLocalRegularDosPuntosFuera() {
        return localRegularDosPuntosFuera;
    }

    public void setLocalRegularDosPuntosFuera(int localRegularDosPuntosFuera) {
        this.localRegularDosPuntosFuera = localRegularDosPuntosFuera;
    }

    public int getLocalRegularTresPuntosDentro() {
        return localRegularTresPuntosDentro;
    }

    public void setLocalRegularTresPuntosDentro(int localRegularTresPuntosDentro) {
        this.localRegularTresPuntosDentro = localRegularTresPuntosDentro;
    }

    public int getLocalRegularTresPuntosFuera() {
        return localRegularTresPuntosFuera;
    }

    public void setLocalRegularTresPuntosFuera(int localRegularTresPuntosFuera) {
        this.localRegularTresPuntosFuera = localRegularTresPuntosFuera;
    }

    public int getLocalRegularTotalDentro() {
        return localRegularTotalDentro;
    }

    public void setLocalRegularTotalDentro(int localRegularTotalDentro) {
        this.localRegularTotalDentro = localRegularTotalDentro;
    }

    public int getLocalRegularTotalFuera() {
        return localRegularTotalFuera;
    }

    public void setLocalRegularTotalFuera(int localRegularTotalFuera) {
        this.localRegularTotalFuera = localRegularTotalFuera;
    }

    public int getVisitanteRegularDosPuntosDentro() {
        return visitanteRegularDosPuntosDentro;
    }

    public void setVisitanteRegularDosPuntosDentro(int visitanteRegularDosPuntosDentro) {
        this.visitanteRegularDosPuntosDentro = visitanteRegularDosPuntosDentro;
    }

    public int getVisitanteRegularDosPuntosFuera() {
        return visitanteRegularDosPuntosFuera;
    }

    public void setVisitanteRegularDosPuntosFuera(int visitanteRegularDosPuntosFuera) {
        this.visitanteRegularDosPuntosFuera = visitanteRegularDosPuntosFuera;
    }

    public int getVisitanteRegularTresPuntosDentro() {
        return visitanteRegularTresPuntosDentro;
    }

    public void setVisitanteRegularTresPuntosDentro(int visitanteRegularTresPuntosDentro) {
        this.visitanteRegularTresPuntosDentro = visitanteRegularTresPuntosDentro;
    }

    public int getVisitanteRegularTresPuntosFuera() {
        return visitanteRegularTresPuntosFuera;
    }

    public void setVisitanteRegularTresPuntosFuera(int visitanteRegularTresPuntosFuera) {
        this.visitanteRegularTresPuntosFuera = visitanteRegularTresPuntosFuera;
    }

    public int getVisitanteRegularTotalDentro() {
        return visitanteRegularTotalDentro;
    }

    public void setVisitanteRegularTotalDentro(int visitanteRegularTotalDentro) {
        this.visitanteRegularTotalDentro = visitanteRegularTotalDentro;
    }

    public int getVisitanteRegularTotalFuera() {
        return visitanteRegularTotalFuera;
    }

    public void setVisitanteRegularTotalFuera(int visitanteRegularTotalFuera) {
        this.visitanteRegularTotalFuera = visitanteRegularTotalFuera;
    }

    public int getLocalPlayoffDosPuntosDentro() {
        return localPlayoffDosPuntosDentro;
    }

    public void setLocalPlayoffDosPuntosDentro(int localPlayoffDosPuntosDentro) {
        this.localPlayoffDosPuntosDentro = localPlayoffDosPuntosDentro;
    }

    public int getLocalPlayoffDosPuntosFuera() {
        return localPlayoffDosPuntosFuera;
    }

    public void setLocalPlayoffDosPuntosFuera(int localPlayoffDosPuntosFuera) {
        this.localPlayoffDosPuntosFuera = localPlayoffDosPuntosFuera;
    }

    public int getLocalPlayoffTresPuntosDentro() {
        return localPlayoffTresPuntosDentro;
    }

    public void setLocalPlayoffTresPuntosDentro(int localPlayoffTresPuntosDentro) {
        this.localPlayoffTresPuntosDentro = localPlayoffTresPuntosDentro;
    }

    public int getLocalPlayoffTresPuntosFuera() {
        return localPlayoffTresPuntosFuera;
    }

    public void setLocalPlayoffTresPuntosFuera(int localPlayoffTresPuntosFuera) {
        this.localPlayoffTresPuntosFuera = localPlayoffTresPuntosFuera;
    }

    public int getLocalPlayoffTotalDentro() {
        return localPlayoffTotalDentro;
    }

    public void setLocalPlayoffTotalDentro(int localPlayoffTotalDentro) {
        this.localPlayoffTotalDentro = localPlayoffTotalDentro;
    }

    public int getLocalPlayoffTotalFuera() {
        return localPlayoffTotalFuera;
    }

    public void setLocalPlayoffTotalFuera(int localPlayoffTotalFuera) {
        this.localPlayoffTotalFuera = localPlayoffTotalFuera;
    }

    public int getVisitantePlayoffDosPuntosDentro() {
        return visitantePlayoffDosPuntosDentro;
    }

    public void setVisitantePlayoffDosPuntosDentro(int visitantePlayoffDosPuntosDentro) {
        this.visitantePlayoffDosPuntosDentro = visitantePlayoffDosPuntosDentro;
    }

    public int getVisitantePlayoffDosPuntosFuera() {
        return visitantePlayoffDosPuntosFuera;
    }

    public void setVisitantePlayoffDosPuntosFuera(int visitantePlayoffDosPuntosFuera) {
        this.visitantePlayoffDosPuntosFuera = visitantePlayoffDosPuntosFuera;
    }

    public int getVisitantePlayoffTresPuntosDentro() {
        return visitantePlayoffTresPuntosDentro;
    }

    public void setVisitantePlayoffTresPuntosDentro(int visitantePlayoffTresPuntosDentro) {
        this.visitantePlayoffTresPuntosDentro = visitantePlayoffTresPuntosDentro;
    }

    public int getVisitantePlayoffTresPuntosFuera() {
        return visitantePlayoffTresPuntosFuera;
    }

    public void setVisitantePlayoffTresPuntosFuera(int visitantePlayoffTresPuntosFuera) {
        this.visitantePlayoffTresPuntosFuera = visitantePlayoffTresPuntosFuera;
    }

    public int getVisitantePlayoffTotalDentro() {
        return visitantePlayoffTotalDentro;
    }

    public void setVisitantePlayoffTotalDentro(int visitantePlayoffTotalDentro) {
        this.visitantePlayoffTotalDentro = visitantePlayoffTotalDentro;
    }

    public int getVisitantePlayoffTotalFuera() {
        return visitantePlayoffTotalFuera;
    }

    public void setVisitantePlayoffTotalFuera(int visitantePlayoffTotalFuera) {
        this.visitantePlayoffTotalFuera = visitantePlayoffTotalFuera;
    }

    private void resetearContadores() {
        localRegularDosPuntosDentro=0;
        localRegularDosPuntosFuera=0;
        localRegularTresPuntosDentro=0;
        localRegularTresPuntosFuera=0;
        localRegularTotalDentro=0;
        localRegularTotalFuera=0;

        visitanteRegularDosPuntosDentro=0;
        visitanteRegularDosPuntosFuera=0;
        visitanteRegularTresPuntosDentro=0;
        visitanteRegularTresPuntosFuera=0;
        visitanteRegularTotalDentro=0;
        visitanteRegularTotalFuera=0;

        localPlayoffDosPuntosDentro=0;
        localPlayoffDosPuntosFuera=0;
        localPlayoffTresPuntosDentro=0;
        localPlayoffTresPuntosFuera=0;
        localPlayoffTotalDentro=0;
        localPlayoffTotalFuera=0;

        visitantePlayoffDosPuntosDentro=0;
        visitantePlayoffDosPuntosFuera=0;
        visitantePlayoffTresPuntosDentro=0;
        visitantePlayoffTresPuntosFuera=0;
        visitantePlayoffTotalDentro=0;
        visitantePlayoffTotalFuera=0;
    }

    public String getPartidosTemporada() {
        return partidosTemporada;
    }

    public void setPartidosTemporada(String partidosTemporada) {
        this.partidosTemporada = partidosTemporada;
    }

    public ArrayList<ControllerPartidoJugador> getListaPartidosTemporadaRegular() {
        return listaPartidosTemporadaRegular;
    }

    public void setListaPartidosTemporadaRegular(ArrayList<ControllerPartidoJugador> listaPartidos) {
        this.listaPartidosTemporadaRegular = listaPartidos;
    }

    public ArrayList<ControllerPartidoJugador> getListaPartidosPlayOff() {
        return listaPartidosPlayOff;
    }

    public void setListaPartidosPlayOff(ArrayList<ControllerPartidoJugador> listaPartidosPlayOff) {
        this.listaPartidosPlayOff = listaPartidosPlayOff;
    }
    
    public String devolverStyle(boolean maxima){
        if(maxima){
            return "yellowRaw";
        }else{
            return "white";
        }
    }

    public JugadorDataImpl getData() {
        return data;
    }

    public void setData(JugadorDataImpl data) {
        this.data = data;
    }

    public JugadorDataImpl getDataPlayoff() {
        return dataPlayoff;
    }

    public void setDataPlayoff(JugadorDataImpl dataPlayoff) {
        this.dataPlayoff = dataPlayoff;
    }

	public String getDonutModelPor1() {
		return donutModelPor1;
	}

	public void setDonutModelPor1(String donutModelPor1) {
		this.donutModelPor1 = donutModelPor1;
	}

	public boolean getInvisibleFindJugador() {
		return invisibleFindJugador;
	}

	public void setInvisibleFindJugador(boolean invisibleFindJugador) {
		this.invisibleFindJugador = invisibleFindJugador;
	}

	public String getDonutModelPor2() {
		return donutModelPor2;
	}

	public void setDonutModelPor2(String donutModelPor2) {
		this.donutModelPor2 = donutModelPor2;
	}

	public String getDonutModelPor3() {
		return donutModelPor3;
	}

	public void setDonutModelPor3(String donutModelPor3) {
		this.donutModelPor3 = donutModelPor3;
	}

	public String getDonutModelPor4() {
		return donutModelPor4;
	}

	public void setDonutModelPor4(String donutModelPor4) {
		this.donutModelPor4 = donutModelPor4;
	}

	public String getDonutModelPor5() {
		return donutModelPor5;
	}

	public void setDonutModelPor5(String donutModelPor5) {
		this.donutModelPor5 = donutModelPor5;
	}

	public String getDonutModelPor6() {
		return donutModelPor6;
	}

	public void setDonutModelPor6(String donutModelPor6) {
		this.donutModelPor6 = donutModelPor6;
	}

	public String getDonutModelPor7() {
		return donutModelPor7;
	}

	public void setDonutModelPor7(String donutModelPor7) {
		this.donutModelPor7 = donutModelPor7;
	}

	public String getDonutModelPor8() {
		return donutModelPor8;
	}

	public void setDonutModelPor8(String donutModelPor8) {
		this.donutModelPor8 = donutModelPor8;
	}

	public String getDonutModelPor9() {
		return donutModelPor9;
	}

	public void setDonutModelPor9(String donutModelPor9) {
		this.donutModelPor9 = donutModelPor9;
	}

	public String getDonutModelPor10() {
		return donutModelPor10;
	}

	public void setDonutModelPor10(String donutModelPor10) {
		this.donutModelPor10 = donutModelPor10;
	}

	public String getDonutModelPor11() {
		return donutModelPor11;
	}

	public void setDonutModelPor11(String donutModelPor11) {
		this.donutModelPor11 = donutModelPor11;
	}

	public String getDonutModelPor12() {
		return donutModelPor12;
	}

	public void setDonutModelPor12(String donutModelPor12) {
		this.donutModelPor12 = donutModelPor12;
	}
    
    
    
}
