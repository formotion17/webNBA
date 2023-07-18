/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import clases.ControllerPartidoJugador;
import util.ClaseEstadisticaNormalTotales;
import util.MapJavaMongo;
import util.Temporadas;

/**
 *
 * @author hatashi
 */
public class JugadorDataImpl{
    
    private ArrayList<String> listaSeleccionTemporadasProgresion = new ArrayList<String>();
    private ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasJugador;
    private ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasCabecera;
    private ArrayList<ControllerPartidoJugador> listaPartidos;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    
    private Integer maximaAnotacion=0;
    private Integer maximaMasMenos=0;
    private Integer maximaTapones=0;
    private Integer maximaPerdidas=0;
    private Integer maximaRobos=0;
    private Integer maximaAsistencias=0;
    private Integer maximaRebotes=0;
    private Integer maximaRebotesDefensa=0;
    private Integer maximaRebotesAtaque=0;
    private Double  maximaTiroLibrePorcentaje=0.0;
    private Integer maximaTiroLibreTirado=0;
    private Integer maximaTiroLibreMetido=0;
    private Double  maximaTriplePorcentaje=0.0;
    private Integer maximaTripleTirado=0;
    private Integer maximaTripleMetido=0;
    private Double  maximaTirosCampoPorcentaje=0.0;
    private Integer maximaTirosCampoTirado=0;
    private Integer maximaTirosCampoMetido=0;
    private Integer maximaMinutos=0;
    
    
    
    public ArrayList<ClaseEstadisticaNormalTotales> devolverListaEstadisticasJugador(String codigoJugador,ArrayList<String> listaSeleccionTemporadasProgresion){
        
        MongoClient mongo = null;
        mongo = new MongoClient("localhost",27017);
        
        if(mongo!=null){
            listaEstadisticasJugador = new ArrayList<ClaseEstadisticaNormalTotales>();
        
            conectarBaseDatos("NBA",mongo);
            conectarColeccion("totales");

            for(Temporadas t : Temporadas.values()){
                        for(String temporada : listaSeleccionTemporadasProgresion){
                            if(t.toString().equals(temporada)){
                                System.out.println("Temporada a revisar: "+temporada);
                                ClaseEstadisticaNormalTotales stat = insertarFilaEstadisticasCabecera(collection,t.toString(),"regular","total",t.toString().substring(8,10)+"/"+t.toString().substring(12,t.toString().length()),codigoJugador);
                                if(null !=stat){
                                    listaEstadisticasJugador.add(stat);
                                }
                                stat =insertarFilaEstadisticasCabecera(collection,t.toString(),"regular","media","",codigoJugador) ;
                                if(null !=stat){
                                    listaEstadisticasJugador.add(stat);
                                }
                                stat = insertarFilaEstadisticasCabecera(collection,t.toString(),"playoff","total",t.toString().substring(8,10)+"/"+t.toString().substring(12,t.toString().length()),codigoJugador);
                                if(null !=stat){
                                    listaEstadisticasJugador.add(stat);
                                }
                                stat = insertarFilaEstadisticasCabecera(collection,t.toString(),"playoff","media","",codigoJugador);
                                if(null !=stat){
                                    listaEstadisticasJugador.add(stat);
                                }
                            }
                        }
                    }
        }
        
        
        
        return listaEstadisticasJugador;
    }
    
    public ArrayList<ClaseEstadisticaNormalTotales> devolverListaEstadisticasCabecera(String codigoJugador){
        
        MongoClient mongo = null;
        mongo = new MongoClient("localhost",27017);
        
        if(mongo!=null){
            
            listaEstadisticasCabecera = new ArrayList<ClaseEstadisticaNormalTotales>();

            conectarBaseDatos("NBA",mongo);
            conectarColeccion("totales");

            listaEstadisticasCabecera.add(insertarFilaEstadisticasCabecera(collection,"carrera","regular","total","Regular",codigoJugador)); //Indice 0
            listaEstadisticasCabecera.add(insertarFilaEstadisticasCabecera(collection,"carrera","regular","media","",codigoJugador));
            listaEstadisticasCabecera.add(insertarFilaEstadisticasCabecera(collection,"carrera","playoff","total","PlayOff",codigoJugador));
            listaEstadisticasCabecera.add(insertarFilaEstadisticasCabecera(collection,"carrera","playoff","media","",codigoJugador));
            listaEstadisticasCabecera.add(insertarFilaEstadisticasCabecera(collection,"carrera","temporada","total","PlayOff",codigoJugador));
            listaEstadisticasCabecera.add(insertarFilaEstadisticasCabecera(collection,"carrera","temporada","media","",codigoJugador));
        }
        
        return listaEstadisticasCabecera;
    }
    
    private ClaseEstadisticaNormalTotales insertarFilaEstadisticasCabecera(MongoCollection<Document> collection,String carrera, String tiempo,String tiporesultado,String temporada,String codigoJugador){
        ClaseEstadisticaNormalTotales statInsertar = new ClaseEstadisticaNormalTotales();
        
        Document findDocument = new Document("idJugador",codigoJugador);
        findDocument.put("temporada", carrera);
        findDocument.put("tiempo", tiempo);
        findDocument.put("tiporesultado",tiporesultado);

        MongoCursor<Document> lista = collection.find(findDocument).iterator();

        while(lista.hasNext()) {
                statInsertar = MapJavaMongo.devolverEstadisticasTotalesJugador((Document)lista.next());
                if(!temporada.isEmpty()) {
                	statInsertar.setMinutos(new java.text.DecimalFormat("###,###").format(Double.valueOf(((double) Integer.parseInt(statInsertar.getMinutos())/60))));
                }
        }
        if(statInsertar!=null){
            statInsertar.setTemporada(temporada);
            statInsertar.setTiempo(tiempo);
            
            
            if(!"GAMES".equals(statInsertar.getPartidosJugados())){
                return statInsertar;
            }
        }
        return null;
    }
    
    
    public ArrayList<ControllerPartidoJugador> devolverListaPartidosTemporada(String temporada,String jugador,boolean playoff) {
        
        
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        listaPartidos = new ArrayList<ControllerPartidoJugador>();
        
        MongoClient mongo = null;
        mongo = new MongoClient("localhost",27017);
        
        
        if(mongo!=null){

            conectarBaseDatos("NBA",mongo);
            conectarColeccion(temporada);

            Document findDocument = new Document("equipoLocal.jugadores.id",jugador);
            findDocument.put("playOff", playoff);

            MongoCursor<Document> lista = collection.find(findDocument).iterator();
            
            ControllerPartidoJugador match;

            while(lista.hasNext()) {
                Document partido = (Document)lista.next();
                
                match = MapJavaMongo.devolverPartidoTemporadaJugador(partido, true,jugador);
                
                verSiTieneMaximo(match);
                                
                listaPartidos.add(match);
            }
            
             findDocument = new Document("equipoVisitante.jugadores.id",jugador);
             findDocument.put("playOff", playoff);

            lista = collection.find(findDocument).iterator();

            while(lista.hasNext()) {
                Document partido = (Document)lista.next();
                
                match = MapJavaMongo.devolverPartidoTemporadaJugador(partido, false,jugador);
                
                verSiTieneMaximo(match);
                
                listaPartidos.add(match);
            }
            
            ordenamosFechas();
            calculamosMaximaAnotacion(maximaAnotacion);
            
            for(int i=0;i<listaPartidos.size();i++){
                listaPartidos.get(i).setPartidoNumero(i+1);
            }
            
            //colocarMaximosCero();

        }
        
        return listaPartidos;
    }
    
    private void colocarMaximosCero(){
        maximaAnotacion=0;
        maximaMasMenos=0;
        maximaTapones=0;
        maximaPerdidas=0;
        maximaRobos=0;
        maximaAsistencias=0;
        maximaRebotes=0;
        maximaRebotesDefensa=0;
        maximaRebotesAtaque=0;
        maximaTiroLibrePorcentaje=0.0;
        maximaTiroLibreTirado=0;
        maximaTiroLibreMetido=0;
        maximaTriplePorcentaje=0.0;
        maximaTripleTirado=0;
        maximaTripleMetido=0;
        maximaTirosCampoPorcentaje=0.0;
        maximaTirosCampoTirado=0;
        maximaTirosCampoMetido=0;
        maximaMinutos=0;
    }
    
    private void verSiTieneMaximo(ControllerPartidoJugador match){
        if(null!= match.getBoxscore().getPuntos()&& match.getBoxscore().getPuntos()>=maximaAnotacion){
            maximaAnotacion=match.getBoxscore().getPuntos();
        }
        if(null!= match.getBoxscore().getMasMenos()&& match.getBoxscore().getMasMenos()>=maximaMasMenos){
            maximaMasMenos=match.getBoxscore().getMasMenos();
        }
        if(null!= match.getBoxscore().getTapones()&& match.getBoxscore().getTapones()>=maximaTapones){
            maximaTapones=match.getBoxscore().getTapones();
        }
        if(null!= match.getBoxscore().getPerdidas()&& match.getBoxscore().getPerdidas()>=maximaPerdidas){
            maximaPerdidas=match.getBoxscore().getPerdidas();
        }
        if(null!= match.getBoxscore().getRobos()&& match.getBoxscore().getRobos()>=maximaRobos){
            maximaRobos=match.getBoxscore().getRobos();
        }
        if(null!= match.getBoxscore().getAsistencias()&& match.getBoxscore().getAsistencias()>=maximaAsistencias){
            maximaAsistencias=match.getBoxscore().getAsistencias();
        }
        if(null!= match.getBoxscore().getTotalRebotes()&& match.getBoxscore().getTotalRebotes()>=maximaRebotes){
            maximaRebotes=match.getBoxscore().getTotalRebotes();
        }
        if(null!= match.getBoxscore().getReboteDefensivo()&& match.getBoxscore().getReboteDefensivo()>=maximaRebotesDefensa){
            maximaRebotesDefensa=match.getBoxscore().getReboteDefensivo();
        }
        if(null!= match.getBoxscore().getReboteOfensivo()&& match.getBoxscore().getReboteOfensivo()>=maximaRebotesAtaque){
            maximaRebotesAtaque=match.getBoxscore().getReboteOfensivo();
        }
        if(null!= match.getBoxscore().getTirosLibresPorcentaje()&& match.getBoxscore().getTirosLibresPorcentaje()>=maximaTiroLibrePorcentaje){
            maximaTiroLibrePorcentaje=match.getBoxscore().getTirosLibresPorcentaje();
        }
        if(null!= match.getBoxscore().getTirosLibresIntentados()&& match.getBoxscore().getTirosLibresIntentados()>=maximaTiroLibreTirado){
            maximaTiroLibreTirado=match.getBoxscore().getTirosLibresIntentados();
        }
        if(null!= match.getBoxscore().getTirosLibresMetidos()&& match.getBoxscore().getTirosLibresMetidos()>=maximaTiroLibreMetido){
            maximaTiroLibreMetido=match.getBoxscore().getTirosLibresMetidos();
        }
        if(null!= match.getBoxscore().getTriplesPorcentaje()&& match.getBoxscore().getTriplesPorcentaje()>=maximaTriplePorcentaje){
            maximaTriplePorcentaje=match.getBoxscore().getTriplesPorcentaje();
        }
        if(null!= match.getBoxscore().getTriplesIntentados()&& match.getBoxscore().getTriplesIntentados()>=maximaTripleTirado){
            maximaTripleTirado=match.getBoxscore().getTriplesIntentados();
        }
        if(null!= match.getBoxscore().getTriplesMetidos()&& match.getBoxscore().getTriplesMetidos()>=maximaTripleMetido){
            maximaTripleMetido=match.getBoxscore().getTriplesMetidos();
        }
        if(null!= match.getBoxscore().getTirosCampoPorcentaje()&& match.getBoxscore().getTirosCampoPorcentaje()>=maximaTirosCampoPorcentaje){
            maximaTirosCampoPorcentaje=match.getBoxscore().getTirosCampoPorcentaje();
        }
        if(null!= match.getBoxscore().getTirosCampoIntentados()&& match.getBoxscore().getTirosCampoIntentados()>=maximaTirosCampoTirado){
            maximaTirosCampoTirado=match.getBoxscore().getTirosCampoIntentados();
        }
        if(null!= match.getBoxscore().getTirosCampoMetidos()&& match.getBoxscore().getTirosCampoMetidos()>=maximaTirosCampoMetido){
            maximaTirosCampoMetido=match.getBoxscore().getTirosCampoMetidos();
        }
        if(null!= match.getSegundos()&& match.getSegundos()>=maximaMinutos){
            maximaMinutos=match.getSegundos();
        }
    }
    
    private void calculamosMaximaAnotacion(Integer maximaAnotacion){
        for(int i=0;i<listaPartidos.size();i++){
            if(null!=listaPartidos.get(i).getBoxscore().getPuntos() && listaPartidos.get(i).getBoxscore().getPuntos()== maximaAnotacion){
                listaPartidos.get(i).setMaximaAnotacion(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getMasMenos()&& listaPartidos.get(i).getBoxscore().getMasMenos()==maximaMasMenos){
                listaPartidos.get(i).setMaximaMasMenos(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTapones()&& listaPartidos.get(i).getBoxscore().getTapones()==maximaTapones){
                listaPartidos.get(i).setMaximaTapones(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getPerdidas()&& listaPartidos.get(i).getBoxscore().getPerdidas()==maximaPerdidas){
                listaPartidos.get(i).setMaximaPerdidas(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getRobos()&& listaPartidos.get(i).getBoxscore().getRobos()==maximaRobos){
                listaPartidos.get(i).setMaximaRobos(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getAsistencias()&& listaPartidos.get(i).getBoxscore().getAsistencias()==maximaAsistencias){
                listaPartidos.get(i).setMaximaAsistencias(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTotalRebotes()&& listaPartidos.get(i).getBoxscore().getTotalRebotes()==maximaRebotes){
                listaPartidos.get(i).setMaximaRebotes(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getReboteDefensivo()&& listaPartidos.get(i).getBoxscore().getReboteDefensivo()==maximaRebotesDefensa){
                listaPartidos.get(i).setMaximaRebotesDefensa(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getReboteOfensivo()&& listaPartidos.get(i).getBoxscore().getReboteOfensivo()==maximaRebotesAtaque){
                listaPartidos.get(i).setMaximaRebotesAtaque(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTirosLibresPorcentaje()&& listaPartidos.get(i).getBoxscore().getTirosLibresPorcentaje()==maximaTiroLibrePorcentaje){
                listaPartidos.get(i).setMaximaTiroLibrePorcentaje(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTirosLibresIntentados()&& listaPartidos.get(i).getBoxscore().getTirosLibresIntentados()==maximaTiroLibreTirado){
                listaPartidos.get(i).setMaximaTiroLibreTirado(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTirosLibresMetidos()&& listaPartidos.get(i).getBoxscore().getTirosLibresMetidos()==maximaTiroLibreMetido){
                listaPartidos.get(i).setMaximaTiroLibreMetido(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTriplesPorcentaje()&& listaPartidos.get(i).getBoxscore().getTriplesPorcentaje()==maximaTriplePorcentaje){
                listaPartidos.get(i).setMaximaTriplePorcentaje(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTriplesIntentados()&& listaPartidos.get(i).getBoxscore().getTriplesIntentados()==maximaTripleTirado){
                listaPartidos.get(i).setMaximaTripleTirado(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTriplesMetidos()&& listaPartidos.get(i).getBoxscore().getTriplesMetidos()==maximaTripleMetido){
                listaPartidos.get(i).setMaximaTripleMetido(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTirosCampoPorcentaje()&& listaPartidos.get(i).getBoxscore().getTirosCampoPorcentaje()==maximaTirosCampoPorcentaje){
                listaPartidos.get(i).setMaximaTirosCampoPorcentaje(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTirosCampoIntentados()&& listaPartidos.get(i).getBoxscore().getTirosCampoIntentados()==maximaTirosCampoTirado){
                listaPartidos.get(i).setMaximaTirosCampoTirado(true);
            }
            if(null!= listaPartidos.get(i).getBoxscore().getTirosCampoMetidos()&& listaPartidos.get(i).getBoxscore().getTirosCampoMetidos()==maximaTirosCampoMetido){
                listaPartidos.get(i).setMaximaTirosCampoMetido(true);
            }
            if(null!= listaPartidos.get(i).getSegundos()&& listaPartidos.get(i).getSegundos()==maximaMinutos){
                listaPartidos.get(i).setMaximaMinutos(true);
            }
        }
    }
    
    private void ordenamosFechas() {
        Comparator<ControllerPartidoJugador> comparator = new Comparator<ControllerPartidoJugador>() {
            @Override
            public int compare(ControllerPartidoJugador s1, ControllerPartidoJugador s2) {
                return s1.getFecha().compareTo(s2.getFecha());
            }
        };
        Collections.sort(listaPartidos, comparator);
    }
    
    private void conectarBaseDatos(String baseDatos,MongoClient mongo){
        db = mongo.getDatabase(baseDatos);
    }
    
    private void conectarColeccion(String coleccion){
         collection = db.getCollection(coleccion);
    }

    public ArrayList<ControllerPartidoJugador> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(ArrayList<ControllerPartidoJugador> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public Integer getMaximaMasMenos() {
        return maximaMasMenos;
    }

    public void setMaximaMasMenos(Integer maximaMasMenos) {
        this.maximaMasMenos = maximaMasMenos;
    }

    public Integer getMaximaAnotacion() {
        return maximaAnotacion;
    }

    public void setMaximaAnotacion(Integer maximaAnotacion) {
        this.maximaAnotacion = maximaAnotacion;
    }

    public Integer getMaximaTapones() {
        return maximaTapones;
    }

    public void setMaximaTapones(Integer maximaTapones) {
        this.maximaTapones = maximaTapones;
    }

    public Integer getMaximaPerdidas() {
        return maximaPerdidas;
    }

    public void setMaximaPerdidas(Integer maximaPerdidas) {
        this.maximaPerdidas = maximaPerdidas;
    }

    public Integer getMaximaRobos() {
        return maximaRobos;
    }

    public void setMaximaRobos(Integer maximaRobos) {
        this.maximaRobos = maximaRobos;
    }

    public Integer getMaximaAsistencias() {
        return maximaAsistencias;
    }

    public void setMaximaAsistencias(Integer maximaAsistencias) {
        this.maximaAsistencias = maximaAsistencias;
    }

    public Integer getMaximaRebotes() {
        return maximaRebotes;
    }

    public void setMaximaRebotes(Integer maximaRebotes) {
        this.maximaRebotes = maximaRebotes;
    }

    public Integer getMaximaRebotesDefensa() {
        return maximaRebotesDefensa;
    }

    public void setMaximaRebotesDefensa(Integer maximaRebotesDefensa) {
        this.maximaRebotesDefensa = maximaRebotesDefensa;
    }

    public Integer getMaximaRebotesAtaque() {
        return maximaRebotesAtaque;
    }

    public void setMaximaRebotesAtaque(Integer maximaRebotesAtaque) {
        this.maximaRebotesAtaque = maximaRebotesAtaque;
    }

    public Double getMaximaTiroLibrePorcentaje() {
        return maximaTiroLibrePorcentaje;
    }

    public void setMaximaTiroLibrePorcentaje(Double maximaTiroLibrePorcentaje) {
        this.maximaTiroLibrePorcentaje = maximaTiroLibrePorcentaje;
    }

    public Integer getMaximaTiroLibreTirado() {
        return maximaTiroLibreTirado;
    }

    public void setMaximaTiroLibreTirado(Integer maximaTiroLibreTirado) {
        this.maximaTiroLibreTirado = maximaTiroLibreTirado;
    }

    public Integer getMaximaTiroLibreMetido() {
        return maximaTiroLibreMetido;
    }

    public void setMaximaTiroLibreMetido(Integer maximaTiroLibreMetido) {
        this.maximaTiroLibreMetido = maximaTiroLibreMetido;
    }

    public Double getMaximaTriplePorcentaje() {
        return maximaTriplePorcentaje;
    }

    public void setMaximaTriplePorcentaje(Double maximaTriplePorcentaje) {
        this.maximaTriplePorcentaje = maximaTriplePorcentaje;
    }

    public Integer getMaximaTripleTirado() {
        return maximaTripleTirado;
    }

    public void setMaximaTripleTirado(Integer maximaTripleTirado) {
        this.maximaTripleTirado = maximaTripleTirado;
    }

    public Integer getMaximaTripleMetido() {
        return maximaTripleMetido;
    }

    public void setMaximaTripleMetido(Integer maximaTripleMetido) {
        this.maximaTripleMetido = maximaTripleMetido;
    }

    public Double getMaximaTirosCampoPorcentaje() {
        return maximaTirosCampoPorcentaje;
    }

    public void setMaximaTirosCampoPorcentaje(Double maximaTirosCampoPorcentaje) {
        this.maximaTirosCampoPorcentaje = maximaTirosCampoPorcentaje;
    }

    public Integer getMaximaTirosCampoTirado() {
        return maximaTirosCampoTirado;
    }

    public void setMaximaTirosCampoTirado(Integer maximaTirosCampoTirado) {
        this.maximaTirosCampoTirado = maximaTirosCampoTirado;
    }

    public Integer getMaximaTirosCampoMetido() {
        return maximaTirosCampoMetido;
    }

    public void setMaximaTirosCampoMetido(Integer maximaTirosCampoMetido) {
        this.maximaTirosCampoMetido = maximaTirosCampoMetido;
    }

    public Integer getMaximaMinutos() {
        return maximaMinutos;
    }

    public void setMaximaMinutos(Integer maximaMinutos) {
        this.maximaMinutos = maximaMinutos;
    }

    
    
}
