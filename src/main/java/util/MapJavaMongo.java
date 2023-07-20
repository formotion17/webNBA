package util;

import clases.ControllerEquipo;
import clases.ControllerTanteoCuartos;
import clases.ControllerFullBoxscore;
import clases.ControllerTiros;
import contollers.IndexController;
import clases.ControllerPartido;
import clases.ControllerEstadisticaAvanzada;
import clases.ControllerEstadisticaNormal;
import clases.ControllerJugador;
import clases.ControllerPartidoJugador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

/**
 * Clase en la que vamos a convertir los Documentos que nos devuelve MongoDB en
 * clases Java
 *
 * @author hatashi
 *
 */
public class MapJavaMongo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346672511009422594L;
	private static final Logger logger = LogManager.getLogger(MapJavaMongo.class);

    @SuppressWarnings("unchecked")
	public static ControllerPartido rellenarPartido(Document partido) {
        ControllerPartido match = new ControllerPartido();
        match.setAsistencia((Integer) partido.get("asistencia"));
        match.setCambiosLider((String) partido.get("cambiosLider"));
        match.setDia((String) partido.get("dia"));
        match.setEmpates((String) partido.get("empates"));
        match.setEquipoLocal(rellenarEquipo((Document) partido.get("equipoLocal")));	//EquipoLocal
        match.setEquipoVisitante(rellenarEquipo((Document) partido.get("equipoVisitante"))); //EquipoVisitante
        match.setEstadio((String) partido.get("estadio"));
        match.setHora((String) partido.get("hora"));
        match.setLocalPuntosConsecutivos((String) partido.get("localPuntosConsecutivos"));
        match.setLocalSinAnotar((Integer) partido.get("localSinAnotar"));
        match.setMes((String) partido.get("mes"));
        match.setPlayOff((boolean) partido.get("playOff"));
        match.setTiempoEmpate((Integer) partido.get("tiempoEmpate"));
        match.setTiempoLocalGanando((Integer) partido.get("tiempoLocalGanando"));
        match.setTiempoVisitanteGanando((Integer) partido.get("tiempoVisitanteGanando"));
        match.setUbicacion((String) partido.get("ubicacion"));
        match.setVisitantePuntosConsecutivos((String) partido.get("visitantePuntosConsecutivos"));
        match.setVisitanteSinAnotar((Integer) partido.get("visitanteSinAnotar"));
        match.setYear((String) partido.get("year"));
        match.rellenarCuartos();
        match.setListaTanteoLocal((ArrayList<Integer>)partido.get("tanteoLocal"));
        match.setListaTanteoPartido((ArrayList<String>)partido.get("tanteo"));
        match.completarEstadisticasIndexPartido();
        
        if(partido.containsKey("playIn")) {
        	match.setPlayin((boolean)partido.get("playIn"));
        	match.setRutaLogo("playin.png");
        }
        
        if(match.getPlayOff()) {
        	
        	match.setBracket((String)partido.get("bracket"));
        	match.setGame((Integer)partido.get("game"));
        	
        	if(!match.getBracket().equals("Finales NBA")) {
            	match.setConferencia((String)partido.get("conferencia"));
            	if(match.getBracket().equals("Finales de la Conferencia ")) {
            		if(match.getConferencia().equals("ESTE")) {
            			match.setRutaLogo("esteFinales.jpeg");
            		}else {
            			match.setRutaLogo("oesteFinales.jpeg");
            		}
            	}
        	}
        	
        }else {
        	match.setBracket("Partido de Temporada Regular");
        	match.setTemporadaRegular(true);
        }
        
        match.setListaJugadoresLocal(match.getEquipoLocal().getListaJugadores());
        match.setListaJugadoresVisitante(match.getEquipoVisitante().getListaJugadores());
        logger.info("	"+match.getEquipoVisitante().getNombre().toUpperCase()+" - "+match.getEquipoLocal().getNombre().toUpperCase());
        return match;
    }

    @SuppressWarnings("unchecked")
	private static ControllerEquipo rellenarEquipo(Document team) {
        ControllerEquipo equipo = new ControllerEquipo();
        equipo.setDerrotas((String) team.get("derrotas"));
        equipo.setEstadisticaAvanzada(devolverEstadisticaAvanzada((Document) team.get("estadisticaAvanzada"))); // Estadistica Avanzada
        equipo.setEstadisticaNormal(devolverEstadisticaNormal((Document) team.get("estadisticaNormal"))); // Estadistica Normal
        equipo.setFullBoxscore(devolverFullBox((Document) team.get("fullBoxscore"))); // Full BoxScore
        equipo.setNombre((String) team.get("nombre"));
        equipo.setJugadores(devolverJugadores((ArrayList<Document>) team.get("jugadores"))); // Array Jugadores
        equipo.setNombreAbreviado(((String) team.get("nombreAbreviado")).toUpperCase());
        equipo.setPuntosConsecutivos((String) team.get("puntosConsecutivos"));
        equipo.setSinAnotar((Integer) team.get("sinAnotar"));
        equipo.setTanteo((String) team.get("tanteo"));
        equipo.setTanteoCuartos(devolverCuartos((Document) team.get("tanteoCuartos"))); // Tanteo Cuartos
        equipo.setTiempoLider((Integer) team.get("tiempoLider"));
        equipo.setVictorias((String) team.get("victorias"));
        equipo.obtenerEstrellas();
        equipo.rellenarTirosEquipos();

        return equipo;

    }

    @SuppressWarnings("unchecked")
	private static ControllerJugador devolverJugador(Document player, int i) {
        ControllerJugador jugador = new ControllerJugador();

        jugador.setApellido((String) player.get("apellido"));
        jugador.setBoxscore(devolverEstadisticaNormal((Document) player.get("boxscore"))); // BoxScore
        jugador.addCuartoJugador(devolverFullBox((Document) player.get("cuarto1"))); // Cuartos	
        jugador.addCuartoJugador(devolverFullBox((Document) player.get("cuarto2")));
        jugador.addCuartoJugador(devolverFullBox((Document) player.get("cuarto3")));
        jugador.addCuartoJugador(devolverFullBox((Document) player.get("cuarto4")));
        if (player.get("over1") != null) {
            jugador.addCuartoJugador(devolverFullBox((Document) player.get("over1")));
        }
        if (player.get("over2") != null) {
            jugador.addCuartoJugador(devolverFullBox((Document) player.get("over2")));
        }
        if (player.get("over3") != null) {
            jugador.addCuartoJugador(devolverFullBox((Document) player.get("over3")));
        }
        if (player.get("over4") != null) {
            jugador.addCuartoJugador(devolverFullBox((Document) player.get("over4")));
        }
        jugador.setEstadisticaAvanzada(devolverEstadisticaAvanzada((Document) player.get("estadisticaAvanzada"))); // EstadisticaAvanzada
        jugador.setId((String) player.get("id"));
        jugador.setInicio((Boolean) player.get("inicio"));
        jugador.setListaTiros(devolverCartaTiro((ArrayList<Document>) player.get("listaTiros"))); // ListaTiros
        jugador.setNombre((String) player.get("nombre"));
        if(player.get("segundos") != null){
            jugador.setSegundos((Integer) player.get("segundos"));
        }else{
            jugador.setSegundos(0);
        }
        jugador.setTotalPartido(devolverFullBox((Document) player.get("totalPartido"))); // TotalPartido
        jugador.setPosicionTabla(i);
        jugador.setApeNom(jugador.getNombre().substring(0, 1).concat(". ").concat(jugador.getApellido()));
        if(jugador.getSegundos()==0){
            return null;
        }
        return jugador;
    }

    private static ControllerTiros devolverTiros(Document tiro) {
        ControllerTiros carta = new ControllerTiros();

        	carta.setCuarto((String)tiro.get("cuarto"));
            carta.setDentro((boolean) tiro.get("dentro"));
            carta.setDistancia((Integer) tiro.get("distancia"));
            carta.setPosicionLeft(Integer.parseInt((String) tiro.get("posicionLeft")));
            carta.setPosicionTop(Integer.parseInt((String) tiro.get("posicionTop")));
            carta.setSituacionAntes((String) tiro.get("situacionAntes"));
            carta.setSituacionDespues((String) tiro.get("situacionDespues"));
            carta.setTanteo((String)tiro.get("tanteo"));
            carta.setTanteoEquipo((String)tiro.get("tanteoEquipo"));
            carta.setTanteoRival((String)tiro.get("tanteoRival"));
            carta.setTiempoRestante((Integer) tiro.get("tiempoRestante"));
            carta.setTipo((String) tiro.get("tipo"));

        return carta;
    }
    
    @SuppressWarnings("rawtypes")
	public static ControllerTiros devolverTiros(Map tiro) {
        ControllerTiros carta = new ControllerTiros();
            
	        carta.setCuarto((String)tiro.get("cuarto"));
	        carta.setDentro((boolean) tiro.get("dentro"));
	        carta.setDistancia((Integer) tiro.get("distancia"));
	        carta.setPosicionLeft(Integer.parseInt((String) tiro.get("posicionLeft")));
	        carta.setPosicionTop(Integer.parseInt((String) tiro.get("posicionTop")));
	        carta.setSituacionAntes((String) tiro.get("situacionAntes"));
	        carta.setSituacionDespues((String) tiro.get("situacionDespues"));
	        carta.setTanteo((String)tiro.get("tanteo"));
	        carta.setTanteoEquipo((String)tiro.get("tanteoEquipo"));
	        carta.setTanteoRival((String)tiro.get("tanteoRival"));
	        carta.setTiempoRestante((Integer) tiro.get("tiempoRestante"));
	        carta.setTipo((String) tiro.get("tipo"));

        return carta;
    }

    private static ControllerFullBoxscore devolverFullBox(Document box) {
        ControllerFullBoxscore full = new ControllerFullBoxscore();

        full.setAsistencias((Integer) box.get("asistencias"));
        full.setBandeja((Integer) box.get("bandeja"));
        full.setBandejaFallada((Integer) box.get("bandejaFallada"));
        full.setCuarto((String) box.get("cuarto"));
        full.setFaltaPersonalAtaque((Integer) box.get("faltaPersonalAtaque"));
        full.setFaltaPersonalDefensa((Integer) box.get("faltaPersonalDefensa"));
        full.setFaltaPersonalProvocadaEnAtaque((Integer) box.get("faltaPersonalProvocadaEnAtaque"));
        full.setFaltaPersonalProvocadaEnDefensa((Integer) box.get("faltaPersonalProvocadaEnDefensa"));
        full.setFaltaPersonalProvocadaEnTiro((Integer) box.get("faltaPersonalProvocadaEnTiro"));
        full.setFaltaPersonalTiro((Integer) box.get("faltaPersonalTiro"));
        full.setFaltaTecnica((Integer) box.get("faltaTecnica"));
        full.setFaltasPersonales((Integer) box.get("faltasPersonales"));
        full.setFaltasPersonalesProvocadas((Integer) box.get("faltasPersonalesProvocadas"));
        full.setGancho((Integer) box.get("gancho"));
        full.setGanchoFallado((Integer) box.get("ganchoFallado"));
        full.setMasMenos((Integer) box.get("masMenos"));
        full.setMate((Integer) box.get("mate"));
        full.setMateFallado((Integer) box.get("mateFallado"));
        full.setPerdidaBalonPerdido((Integer) box.get("perdidaBalonPerdido"));
        full.setPerdidaFueraBanda((Integer) box.get("perdidaFueraBanda"));
        full.setPerdidaGoaltending((Integer) box.get("pedidaGoalTending"));
        full.setPerdidaMalPase((Integer) box.get("perdidaMalPase"));
        full.setPerdidaPasos((Integer) box.get("perdidaPasos"));
        full.setPerdidaPisarFuera((Integer) box.get("perdidaPisarFuera"));
        full.setPerdidaCampoAtras((Integer)box.get("perdidaCampoAtras"));
		full.setPerdidaOtros((Integer)box.get("perdidaOtros"));
		full.setPerdidaTresSegundos((Integer)box.get("perdidaTresSegundos"));
		full.setPerdidaFalta((Integer)box.get("perdidaFalta"));
		full.setPerdidaDobles((Integer)box.get("perdidaDobles"));
		full.setPerdidaPie((Integer)box.get("perdidaPie"));
        full.setPerdidas((Integer) box.get("perdidas"));
        full.setPrimerTiroLibreDentro((Integer) box.get("primerTiroLibreDentro"));
        full.setPrimerTiroLibreFuera((Integer) box.get("primerTiroLibreFuera"));
        full.setPrimerTiroLibrePorcentaje((Double) revisarDatoIntegerDouble(box.get("primerTiroLibrePorcentaje")));
        full.setPrimerTiroLibreTotal((Integer) box.get("primerTiroLibreTotal"));
        full.setPuntos((Integer) box.get("puntos"));
        full.setReboteDefensivo((Integer) box.get("reboteDefensivo"));
        full.setReboteOfensivo((Integer) box.get("reboteOfensivo"));
        full.setRobos((Integer) box.get("robos"));
        full.setSegundoTiroLibreDentro((Integer) box.get("segundoTiroLibreDentro"));
        full.setSegundoTiroLibreFuera((Integer) box.get("segundoTiroLibreFuera"));
        full.setSegundoTiroLibrePorcentaje((Double) revisarDatoIntegerDouble(box.get("segundoTiroLibrePorcentaje")));
        full.setSegundoTiroLibreTotal((Integer) box.get("segundoTiroLibreTotal"));
        full.setSuspension((Integer) box.get("suspensi�n"));
        full.setSuspensionFallado((Integer) box.get("suspensionFallada"));
        full.setTaponRecibido((Integer) box.get("taponRecibido"));
        full.setTaponRecibidoTriple((Integer) box.get("taponRecibidoTriple"));
        full.setTapones((Integer) box.get("tapones"));
        full.setTercerTiroLibreDentro((Integer) box.get("tercerTiroLibreDentro"));
        full.setTercerTiroLibreFuera((Integer) box.get("tercerTiroLibreFuera"));
        full.setTercerTiroLibrePorcentaje((Double) revisarDatoIntegerDouble(box.get("tercerTiroLibrePorcentaje")));
        full.setTercerTiroLibreTotal((Integer) box.get("tercerTiroLiberTotal"));
        full.setTirosCampoIntentados((Integer) box.get("tirosCampoIntentados"));
        full.setTirosCampoMetidos((Integer) box.get("tirosCampoMetidos"));
        full.setTirosCampoPorcentaje((Double) revisarDatoIntegerDouble(box.get("tirosCampoPorcentaje")));
        full.setTirosLibresIntentados((Integer) box.get("tirosLibresIntentados"));
        full.setTirosLibresMetidos((Integer) box.get("tirosLibresMetidos"));
        full.setTirosLibresPorcentaje((Double) revisarDatoIntegerDouble(box.get("tirosLibresPorcentaje")));
        full.setTotalRebotes((Integer) box.get("totalRebotes"));
        full.setTriplesIntentados((Integer) box.get("triplesIntentados"));
        full.setTriplesMetidos((Integer) box.get("triplesMetidos"));
        full.setTriplesPorcentaje((Double) revisarDatoIntegerDouble(box.get("triplesPorcentaje")));

        return full;
    }

    private static ControllerEstadisticaNormal devolverEstadisticaNormal(Document nor) {
        ControllerEstadisticaNormal normal = new ControllerEstadisticaNormal();

        normal.setAsistencias((Integer) nor.get("asistencias"));
        normal.setCuarto((String) nor.get("cuarto"));
        normal.setFaltasPersonales((Integer) nor.get("faltasPersonales"));
        normal.setPerdidas((Integer) nor.get("perdidas"));
        normal.setPuntos((Integer) nor.get("puntos"));
        normal.setReboteDefensivo((Integer) nor.get("reboteDefensivo"));
        normal.setReboteOfensivo((Integer) nor.get("reboteOfensivo"));
        normal.setRobos((Integer) nor.get("robos"));
        normal.setTapones((Integer) nor.get("tapones"));
        normal.setTirosCampoIntentados((Integer) nor.get("tirosCampoIntentados"));
        normal.setTirosCampoMetidos((Integer) nor.get("tirosCampoMetidos"));
        normal.setTirosCampoPorcentaje((Double) revisarDatoIntegerDouble(nor.get("tirosCampoPorcentaje")));
        normal.setTirosLibresIntentados((Integer) nor.get("tirosLibresIntentados"));
        normal.setTirosLibresMetidos((Integer) nor.get("tirosLibresMetidos"));
        normal.setTirosLibresPorcentaje((Double) revisarDatoIntegerDouble(nor.get("tirosLibresPorcentaje")));
        normal.setTotalRebotes((Integer) nor.get("totalRebotes"));
        normal.setTriplesIntentados((Integer) nor.get("triplesIntentados"));
        normal.setTriplesMetidos((Integer) nor.get("triplesMetidos"));
        normal.setTriplesPorcentaje((Double) revisarDatoIntegerDouble(nor.get("triplesPorcentaje")));
        normal.setMasMenos((Integer)nor.get("masMenos"));

        return normal;
    }

    private static ControllerEstadisticaAvanzada devolverEstadisticaAvanzada(Document advan) {
        ControllerEstadisticaAvanzada avanzadas = new ControllerEstadisticaAvanzada();

        avanzadas.setFANTASY((Double) revisarDatoIntegerDouble(advan.get("FANTASY")));
        avanzadas.setFTARate((Double) revisarDatoIntegerDouble(advan.get("FTARate")));
        avanzadas.setNETRTG((Double) revisarDatoIntegerDouble(advan.get("NETRTG")));
        avanzadas.setAssistPercentage((Double) revisarDatoIntegerDouble(advan.get("assistPercentage")));
        avanzadas.setBlockPercentage((Double) revisarDatoIntegerDouble(advan.get("blockPercentage")));
        avanzadas.setDefensiveRating((Double) revisarDatoIntegerDouble(advan.get("defensiveRating")));
        avanzadas.setDefensiveReboundPer((Double) revisarDatoIntegerDouble(advan.get("defensiveReboundPer")));
        avanzadas.setEffectiveGoalPer((Double) revisarDatoIntegerDouble(advan.get("effectiveGoalPer")));
        avanzadas.setFreeThrowRate((Double) revisarDatoIntegerDouble(advan.get("freeThrowRate")));
        avanzadas.setOffensiveRating((Double) revisarDatoIntegerDouble(advan.get("offensiveRating")));
        avanzadas.setOffensiveReboundPer((Double) revisarDatoIntegerDouble(advan.get("offensiveReboundPer")));
        avanzadas.setPerPTS1((Double) revisarDatoIntegerDouble(advan.get("perPTS1")));
        avanzadas.setPerPTS2((Double) revisarDatoIntegerDouble(advan.get("perPTS2")));
        avanzadas.setPerPTS2PT((Double) revisarDatoIntegerDouble(advan.get("perPTS2PT")));
        avanzadas.setPerPTS2PTM((Double) revisarDatoIntegerDouble(advan.get("perPTS2PTM")));
        avanzadas.setPerPTS3((Double) revisarDatoIntegerDouble(advan.get("perPTS3")));
        avanzadas.setPerPTS3PT((Double) revisarDatoIntegerDouble(advan.get("perPTS3PT")));
        avanzadas.setPerPTS3PTM((Double) revisarDatoIntegerDouble(advan.get("perPTS3PTM")));
        avanzadas.setStealPercentage((Double) revisarDatoIntegerDouble(advan.get("stealPercentage")));
        avanzadas.setThreePointRate((Double) revisarDatoIntegerDouble(advan.get("threePointRate")));
        avanzadas.setTotalReboundPer((Double) revisarDatoIntegerDouble(advan.get("totalReboundPer")));
        avanzadas.setTrueShootPer((Double) revisarDatoIntegerDouble(advan.get("trueShootPer")));
        avanzadas.setTurnoverPercentage((Double) revisarDatoIntegerDouble(advan.get("turnoverPercentage")));
        avanzadas.setUsagePercentage((Double) revisarDatoIntegerDouble(advan.get("usagePercentage")));

        return avanzadas;
    }

    private static ControllerTanteoCuartos devolverCuartos(Document cuartos) {
        ControllerTanteoCuartos tanteoCuartos = new ControllerTanteoCuartos();

        tanteoCuartos.insertarCuarto("primero", Integer.parseInt((String) cuartos.get("primero")));
        tanteoCuartos.insertarCuarto("segundo", Integer.parseInt((String) cuartos.get("segundo")));
        tanteoCuartos.insertarCuarto("tercero", Integer.parseInt((String) cuartos.get("tercero")));
        tanteoCuartos.insertarCuarto("cuarto", Integer.parseInt((String) cuartos.get("cuarto")));
        if (cuartos.get("OT1") != null) {
            tanteoCuartos.insertarCuarto("OT1", Integer.parseInt((String) cuartos.get("OT1")));
        }
        if (cuartos.get("OT2") != null) {
            tanteoCuartos.insertarCuarto("OT2", Integer.parseInt((String) cuartos.get("OT2")));
        }
        if (cuartos.get("OT3") != null) {
            tanteoCuartos.insertarCuarto("OT3", Integer.parseInt((String) cuartos.get("OT3")));
        }
        if (cuartos.get("OT4") != null) {
            tanteoCuartos.insertarCuarto("OT4", Integer.parseInt((String) cuartos.get("OT4")));
        }
        if (cuartos.get("OT5") != null) {
            tanteoCuartos.insertarCuarto("OT5", Integer.parseInt((String) cuartos.get("OT5")));
        }
        if (cuartos.get("OT6") != null) {
            tanteoCuartos.insertarCuarto("OT6", Integer.parseInt((String) cuartos.get("OT6")));
        }

        return tanteoCuartos;
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static ControllerPartidoJugador devolverPartidoTemporadaJugador(Document partido,boolean lugar,String jugador){
        ControllerPartidoJugador match = new ControllerPartidoJugador();
        
        // Recogemos las fecha
        match.setFecha(new Date(Integer.parseInt((String)partido.get("year"))-1900,
                        Integer.parseInt((String)partido.get("mes"))-1,
                        Integer.parseInt((String)partido.get("dia"))));
        
        // Juega En casa o no
        match.setCasa(lugar);
        
        Document equipoJugador;
        Document equipoRival;
        if(lugar){
            equipoJugador =(Document)partido.get("equipoLocal");
            equipoRival = (Document)partido.get("equipoVisitante");
        }else{
            equipoJugador =(Document)partido.get("equipoVisitante");
            equipoRival = (Document)partido.get("equipoLocal");
        }
        
        // Tanteo
        match.setTanteoEquipoJugador(Integer.parseInt(((String)equipoJugador.get("tanteo")).trim()));
        match.setTanteoEquipoRival(Integer.parseInt(((String)equipoRival.get("tanteo")).trim()));
        
        // Nombre Equipos
        match.setJugandoConNombre((String)equipoJugador.get("nombre"));
        match.setJugandoConAbreviado((String)equipoJugador.get("nombreAbreviado"));
        match.setJugandoContraNombre((String)equipoRival.get("nombre"));
        match.setJugandoContraAbreviado((String)equipoRival.get("nombreAbreviado"));
        
        Document player = devolverJugadorPartidoTemporada((ArrayList<Document>)equipoJugador.get("jugadores"),jugador);
        
        if(null!=player){
            if(player.get("segundos") != null){
                if((boolean)player.get("inicio")){
                    match.setInicio(1);
                }
                match.setSegundos((Integer) player.get("segundos"));
                match.setBoxscore(devolverEstadisticaNormal((Document) player.get("boxscore"))); // BoxScore
            }else{
                match.setSegundos(0);
                match.setBoxscore(devolverEstadisticaNormal((Document) player.get("boxscore"))); // BoxScore
            }
        }
        
        return match;
    }
    
    public static Document devolverJugadorPartidoTemporada(ArrayList<Document> lista,String jugador){
        for(int i=0;i<lista.size();i++){
            if(jugador.equals((String)lista.get(i).get("id"))){
                return lista.get(i);
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
	public static ControllerEstadisticaNormal devolverEstadisticaJugadorEquipo(Map stats, ControllerEstadisticaNormal jugador,String cuarto) {
        Map stat = (Map) stats.get(cuarto);
        if(!jugador.isTienePartido()){
            jugador.setPartidosJugados(jugador.getPartidosJugados()+1);
            jugador.setTienePartido(true);
        }

        if (existeKey(stat, "asistencias")) {
            jugador.setAsistencias(jugador.getAsistencias() + (Integer) stat.get("asistencias"));
        }
        if (existeKey(stat, "faltasPersonales")) {
            jugador.setFaltasPersonales(jugador.getFaltasPersonales() + (Integer) stat.get("faltasPersonales"));
        }
        if (existeKey(stat, "masMenos")) {
            jugador.setMasMenos(jugador.getMasMenos() + (Integer) stat.get("masMenos"));
        }
        if (existeKey(stat, "perdidas")) {
            jugador.setPerdidas(jugador.getPerdidas() + (Integer) stat.get("perdidas"));
        }
        if (existeKey(stat, "puntos")) {
            jugador.setPuntos(jugador.getPuntos() + (Integer) stat.get("puntos"));
        }
        if (existeKey(stat, "reboteDefensivo")) {
            jugador.setReboteDefensivo(jugador.getReboteDefensivo() + (Integer) stat.get("reboteDefensivo"));
        }
        if (existeKey(stat, "reboteOfensivo")) {
            jugador.setReboteOfensivo(jugador.getReboteOfensivo() + (Integer) stat.get("reboteOfensivo"));
        }
        if (existeKey(stat, "robos")) {
            jugador.setRobos(jugador.getRobos() + (Integer) stat.get("robos"));
        }
        if (existeKey(stat, "tapones")) {
            jugador.setTapones(jugador.getTapones() + (Integer) stat.get("tapones"));
        }
        if (existeKey(stat, "tirosCampoIntentados")) {
            jugador.setTirosCampoIntentados(jugador.getTirosCampoIntentados() + (Integer) stat.get("tirosCampoIntentados"));
        }
        if (existeKey(stat, "tirosCampoMetidos")) {
            jugador.setTirosCampoMetidos(jugador.getTirosCampoMetidos() + (Integer) stat.get("tirosCampoMetidos"));
        }
        if (existeKey(stat, "tirosLibresIntentados")) {
            jugador.setTirosLibresIntentados(jugador.getTirosLibresIntentados() + (Integer) stat.get("tirosLibresIntentados"));
        }
        if (existeKey(stat, "tirosLibresMetidos")) {
            jugador.setTirosLibresMetidos(jugador.getTirosLibresMetidos() + (Integer) stat.get("tirosLibresMetidos"));
        }
        if (existeKey(stat, "triplesIntentados")) {
            jugador.setTriplesIntentados(jugador.getTriplesIntentados() + (Integer) stat.get("triplesIntentados"));
        }
        if (existeKey(stat, "triplesMetidos")) {
            jugador.setTriplesMetidos(jugador.getTriplesMetidos() + (Integer) stat.get("triplesMetidos"));
        }
        if (existeKey(stat, "totalRebotes")) {
            jugador.setTotalRebotes(jugador.getTotalRebotes() + (Integer) stat.get("totalRebotes"));
        }
        return jugador;
    }

    @SuppressWarnings("rawtypes")
	private static boolean existeKey(Map map, String atributo) {
        if (map.containsKey(atributo)) {
            return true;
        }
        return false;
    }

    public static ClaseEstadisticaNormalTotales devolverEstadisticasTotalesJugador(Document stats) {
        ClaseEstadisticaNormalTotales total = new ClaseEstadisticaNormalTotales();
        if (stats.get("MP") != null) {
            //if (!"0".equals((String) stats.get("MP").toString())) {
                total.setTiempo((String) stats.get("tiempo"));
                total.setTiempo((String) stats.get("tiporesultado"));
                total.setPartidosJugados((String) stats.get("G").toString());
                total.setPartidosQuintetoInicial((String) stats.get("GS").toString());
                total.setMinutos((String) stats.get("MP").toString());

                total.setTirosCampoMetidos((String) stats.get("FG").toString());
                total.setTirosCampoIntentados((String) stats.get("FGA").toString());
                total.setTirosCampoPorcentaje((String) stats.get("FG%").toString());

                total.setTriplesMetidos((String) stats.get("3P").toString());
                total.setTriplesIntentados((String) stats.get("3PA").toString());
                total.setTriplesPorcentaje((String) stats.get("3P%").toString());

                total.setDosPuntosMetidos((String) stats.get("2P").toString());
                total.setDosPuntosIntentados((String) stats.get("2PA").toString());
                total.setDosPuntosPorcentaje((String) stats.get("2P%").toString());

                total.setTirosLibresMetidos((String) stats.get("FT").toString());
                total.setTirosLibresIntentados((String) stats.get("FTA").toString());
                total.setTirosLibresPorcentaje((String) stats.get("FT%").toString());

                total.setReboteOfensivo((String) stats.get("ORB").toString());
                total.setReboteDefensivo((String) stats.get("DRB").toString());
                total.setTotalRebotes((String) stats.get("TRB").toString());

                total.setAsistencias((String) stats.get("AST").toString());
                total.setRobos((String) stats.get("STL").toString());
                total.setTapones((String) stats.get("BLK").toString());

                total.setPerdidas((String) stats.get("TOV").toString());
                total.setFaltasPersonales((String) stats.get("PF").toString());
                total.setPuntos((String) stats.get("PTS").toString());

                return total;
            //}
        }
        return null;
    }

    private static ArrayList<ControllerTiros> devolverCartaTiro(ArrayList<Document> lista) {
        ArrayList<ControllerTiros> listaTiros = new ArrayList<ControllerTiros>();
        for (int i = 0; i < lista.size(); i++) {
            listaTiros.add(devolverTiros(lista.get(i)));
        }
        return listaTiros;
    }

    private static Double revisarDatoIntegerDouble(Object object) {
        Double valor;
        try {
            valor = (Double) object;
        } catch (ClassCastException nfe) {
            return new Double(0);
        }
        return valor;
    }

    private static ArrayList<ControllerJugador> devolverJugadores(ArrayList<Document> lista) {
        ArrayList<ControllerJugador> listaJugadores = new ArrayList<ControllerJugador>();
        for (int i = 0; i < lista.size(); i++) {
            ControllerJugador jugador = devolverJugador(lista.get(i), i);
            if(jugador!=null){
                listaJugadores.add(jugador);
            }
            //listaJugadores.add(devolverJugador((Document) lista.get(i), i));
        }
        return listaJugadores;
    }

}
