package Mapper;


import java.util.ArrayList;

import org.bson.Document;

import temporadas.EstadisticasMaximos;

public class MapJugadorEstadisticas extends Atributos{

	
	public static EstadisticasMaximos devolverEstadisticasTotalesJugador(Document stats) {
		EstadisticasMaximos total = new EstadisticasMaximos();
		total.setNombre((String)stats.get(ATRIBUTO_STAT_NOMBRE));
		total.setApellido((String)stats.get(ATRIBUTO_STAT_APELLIDO));
		total.setIdJugador((String)stats.get(ATRIBUTO_STAT_ID_JUGADOR));
		total.setTemporada((String)stats.get(ATRIBUTO_STAT_TEMPORADA));
		total.setTiempo((String)stats.get(ATRIBUTO_STAT_TIEMPO));
		total.setTiempo((String)stats.get(ATRIBUTO_STAT_TIPO_RESULTADO));
		System.out.println("JUGADOR:	"+total.getIdJugador());
		total.setPartidosJugados((String)stats.get(ATRIBUTO_STAT_G).toString());
		if(total.getPartidosJugados().equals("0")) {
			total.setPartidosJugados("9999999");
		}
		total.setPartidosQuintetoInicial((String)stats.get(ATRIBUTO_STAT_GS).toString());
		total.setMinutos((String)stats.get(ATRIBUTO_STAT_MP).toString());
		
		total.setTirosCampoMetidos((String)stats.get(ATRIBUTO_STAT_FG).toString());
		total.setTirosCampoIntentados((String)stats.get(ATRIBUTO_STAT_FGA).toString());
		total.setTirosCampoPorcentaje(Double.valueOf(valorValido((String)stats.get(ATRIBUTO_STAT_FGP).toString().replace(",", "."))));
		
		total.setTriplesMetidos((String)stats.get(ATRIBUTO_STAT_3P).toString());
		total.setTriplesIntentados((String)stats.get(ATRIBUTO_STAT_3PA).toString());
		total.setTriplesPorcentaje(Double.valueOf(valorValido((String)stats.get(ATRIBUTO_STAT_3PP).toString().replace(",", "."))));
		
		total.setDosPuntosMetidos((String)stats.get(ATRIBUTO_STAT_2P).toString());
		total.setDosPuntosIntentados((String)stats.get(ATRIBUTO_STAT_2PA).toString());
		total.setDosPuntosPorcentaje(Double.valueOf(valorValido((String)stats.get(ATRIBUTO_STAT_2PP).toString().replace(",", "."))));
		
		total.setTirosLibresMetidos((String)stats.get(ATRIBUTO_STAT_FT).toString());
		total.setTirosLibresIntentados((String)stats.get(ATRIBUTO_STAT_FTA).toString());
		total.setTirosLibresPorcentaje(Double.valueOf(valorValido((String)stats.get(ATRIBUTO_STAT_FTP).toString().replace(",", "."))));
		
		total.setReboteOfensivo((String)stats.get(ATRIBUTO_STAT_ORB).toString());
		total.setReboteDefensivo((String)stats.get(ATRIBUTO_STAT_DRB).toString());
		total.setTotalRebotes((String)stats.get(ATRIBUTO_STAT_TRB).toString());
		
		total.setAsistencias((String)stats.get(ATRIBUTO_STAT_AST).toString());
		total.setRobos((String)stats.get(ATRIBUTO_STAT_STL).toString());
		total.setTapones((String)stats.get(ATRIBUTO_STAT_BLK).toString());
		
		total.setPerdidas((String)stats.get(ATRIBUTO_STAT_TOV).toString());
		total.setFaltasPersonales((String)stats.get(ATRIBUTO_STAT_PF).toString());
		total.setPuntos((String)stats.get(ATRIBUTO_STAT_PTS).toString());
		total.calcularMedias();
			
			
		return total;
	}
	
	private static String valorValido(String valor) {
		if(valor.equals("�")) {
			return "";
		}else if(valor.isEmpty()) {
			return "0";
		}
		return valor;
	}
	
	private static Document devolverJugador(String jugador,ArrayList<Document> lista) {
		for(int i=0;i<lista.size();i++) {
			if(jugador.equals((String)lista.get(i).get("id"))) {
				return lista.get(i);
			}
		}
		return null;
	}
}