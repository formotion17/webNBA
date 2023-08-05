package temporadas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import Mapper.MapJugadorEstadisticas;
import contollers.BaseController;
import lombok.Data;

@Data
public class CalcularMaximosTemporada extends BaseController {

	private static final long serialVersionUID = -5658458007370926154L;

	private static MongoClient mongoNuevo;
	private static MongoDatabase dbNuevo;
	private ArrayList<EstadisticasMaximos> listaJugadores;
	private static DecimalFormat df = new DecimalFormat("#.##");
	private static String season="";
	
	public void calcularMaximos(String temporada) {
		
		this.season=temporada;

		listaJugadores = new ArrayList<>();

		mongoNuevo = new MongoClient(host, puertoHost);

		if (null != mongoNuevo) {

			DB db = mongoNuevo.getDB("NBA");
			DBCursor cursor;
			
			DBCollection collection =db.getCollection("totales");

			BasicDBObject allQuery = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();

			obj.add(new BasicDBObject("temporada", temporada));
			obj.add(new BasicDBObject("tiempo", "regular"));
			obj.add(new BasicDBObject("tiporesultado", "total"));

			allQuery.put("$and", obj);

			cursor = collection.find(allQuery);
			
			while(cursor.hasNext()) {

                DBObject get = cursor.next();
                
                listaJugadores.add(MapJugadorEstadisticas.devolverEstadisticasTotalesJugador(new Document((Map<String, Object>) get)));

            }

		}
        mongoNuevo.close();
		
	}
	
	private ArrayList<EstadisticasMaximos> devolverLista(){
		ArrayList<EstadisticasMaximos> lista = new ArrayList<>();

		mongoNuevo = new MongoClient(host, puertoHost);

		if (null != mongoNuevo) {

			DB db = mongoNuevo.getDB("NBA");
			DBCursor cursor;
			
			DBCollection collection =db.getCollection("totales");

			BasicDBObject allQuery = new BasicDBObject();
			BasicDBObject fields = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();

			obj.add(new BasicDBObject("temporada", this.season));
			obj.add(new BasicDBObject("tiempo", "regular"));
			obj.add(new BasicDBObject("tiporesultado", "total"));

			allQuery.put("$and", obj);

			cursor = collection.find(allQuery);
			
			while(cursor.hasNext()) {

                DBObject get = cursor.next();
                
                lista.add(MapJugadorEstadisticas.devolverEstadisticasTotalesJugador(new Document((Map<String, Object>) get)));

            }

		}
		
		mongoNuevo.close();
		
		return lista;
	}
	
/////////////////////////TIROS DE CAMPO
	
	public List<EstadisticasMaximos> getTirosCampoMetidosPartido(String radioFg){
		
		if(radioFg.equals("media")) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTiroCampoAnotadosPartido).reversed());
		}else {
	        listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTirosCampoMetidos())).reversed());
		}
		
		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
				
	}
	
	public List<EstadisticasMaximos> getTirosCampoIntentadosPartido(String radioFga){
		
		if(radioFga.equals("media")) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTiroCampoIntentadosPartido).reversed());
		}else {
	        listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTirosCampoIntentados())).reversed());
		}
		
		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos> getTirosCampoPorcentajePartido(int porcentajeTiroCampo){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();

		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getTirosCampoIntentados())<porcentajeTiroCampo) {

		        iterador.remove();
		    }
		}
		
		lista.sort(Comparator.comparingDouble(EstadisticasMaximos::getTirosCampoPorcentaje).reversed());
		
		return lista.subList(0, Math.min(5, lista.size()));
	}
	
///////////////////////// DOS PUNTOS
	public List<EstadisticasMaximos> getDosMetidosPartido(String radioDosFg){
		
		if("media".equals(radioDosFg)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getDosPuntosMetidosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getDosPuntosMetidos())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
		
	}
	
	public List<EstadisticasMaximos> getDosInTentadosPartido(String radioDosFga){
		
		if("media".equals(radioDosFga)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getDosPuntosIntentadosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getDosPuntosIntentados())).reversed());
		}
		
		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos> getDosPorcentajePartido(int porcentajeDos){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();

		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getDosPuntosIntentados())<porcentajeDos) {

		        iterador.remove();
		    }
		}
		
		lista.sort(Comparator.comparingDouble(EstadisticasMaximos::getDosPuntosPorcentaje).reversed());
		
		return lista.subList(0, Math.min(5, lista.size()));
	}
	
///////////////////////////TRIPLES
	
	public List<EstadisticasMaximos> getTriplesMetidosPartido(String radioTripleFg){
		
		if("media".equals(radioTripleFg)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTriplesAnotadosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTriplesMetidos())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos> getTriplesIntentadosPartido(String radioTripleFga){
		
		if("media".equals(radioTripleFga)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTriplesIntentadosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTriplesIntentados())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos> getTriplesPorcentajePArtido(int porcentajeTriple){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getTriplesIntentados())<porcentajeTriple) {

		        iterador.remove();
		    }
		}

		
		lista.sort(Comparator.comparingDouble(EstadisticasMaximos::getTriplesPorcentaje).reversed());
		
		return lista.subList(0, Math.min(5, lista.size()));
	}
	
///////////////////////// TIROS LIBRES
	
	public List<EstadisticasMaximos>  getTirosLibresMetidosPartido(String radioLibreFg){
		
		if("media".equals(radioLibreFg)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTirosLibresAnotadosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTirosLibresMetidos())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos>  getTirosLibresIntentadosPartido(String radioLibreFga){
		
		if("media".equals(radioLibreFga)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTirosLibresIntentadosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTirosLibresIntentados())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos>  getTirosLibresPorcentajePartido(int porcentajeLibre){
		
		ArrayList<EstadisticasMaximos> lista=devolverLista();
		
		Iterator<EstadisticasMaximos> iterador = lista.iterator();
		while (iterador.hasNext()) {
			EstadisticasMaximos objeto = iterador.next();

		    if (Integer.parseInt(objeto.getTirosLibresIntentados())<porcentajeLibre) {

		        iterador.remove();
		    }
		}
		

		lista.sort(Comparator.comparingDouble(EstadisticasMaximos::getTirosLibresPorcentaje).reversed());
		
		return lista.subList(0, Math.min(5, lista.size()));
	}
	
	
///////////////////////// REBOTES
	public List<EstadisticasMaximos> getRebotesPartido(String radioRebote){
		
		if("media".equals(radioRebote)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getRebotesPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTotalRebotes())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos> getRebotesDefensivosPartido(String radioReboteDefensivo){
		
		if("media".equals(radioReboteDefensivo)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getReboteDefensivoPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getReboteDefensivo())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
	public List<EstadisticasMaximos> getRebotesOfensivosPartido(String radioReboteOfensivo){
		
		if("media".equals(radioReboteOfensivo)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getReboteOfensivoPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getReboteOfensivo())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
	}
	
///////////////////////// ASISTENCIAS
	
	public List<EstadisticasMaximos> getAsistenciasPartido(String radioAsistencia) {
		
		if("media".equals(radioAsistencia)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getAsistenciasPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getAsistencias())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));

	}
	
///////////////////////// ROBOS
	
	public List<EstadisticasMaximos> getRobosPartido(String radioRobo){
		
		if("media".equals(radioRobo)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getRobosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getRobos())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
		
	}
	
///////////////////////// TAPONES
	
	public List<EstadisticasMaximos>getTaponesPartido(String radioTapon){

		if("media".equals(radioTapon)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getTaponesPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getTapones())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
		
	}
	
///////////////////////// PERDIDAS
	
	public List<EstadisticasMaximos>getPerdidasPartido(String radioPerdida){
		
		if("media".equals(radioPerdida)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getPerdidasPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getPerdidas())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
		
	}
	
///////////////////////// PERSONALES
	
	public List<EstadisticasMaximos> getPersonalesPartido(String radioPersonal){
		
		if("media".equals(radioPersonal)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getFaltasPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getFaltasPersonales())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
		
	}
	
///////////////////////// MINUTOS

	public List<EstadisticasMaximos> getMinutosPartido(String radioMinuto){

		if("media".equals(radioMinuto)) {
			listaJugadores.sort(Comparator.comparingDouble(EstadisticasMaximos::getMinutosJugadosPartido).reversed());
		}else {
			listaJugadores.sort(Comparator.comparingDouble(jugador -> Double.valueOf(((EstadisticasMaximos) jugador).getMinutos())).reversed());
		}

		return listaJugadores.subList(0, Math.min(5, listaJugadores.size()));
		
	}
	
///////////////////////// PUNTOS PARTIDO
	
	public ArrayList<EstadisticasMaximos> getPuntosPartido() {
		Comparator<EstadisticasMaximos> comparator = new Comparator<EstadisticasMaximos>() {
			@Override
			public int compare(EstadisticasMaximos s1, EstadisticasMaximos s2) {
				return Double.compare(s2.getPuntosPartidos(), s1.getPuntosPartidos());
			}
		};
		Collections.sort(listaJugadores, comparator);

		return listaJugadores;
	}

}
