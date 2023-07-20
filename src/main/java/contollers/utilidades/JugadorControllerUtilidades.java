package contollers.utilidades;

import clases.Jugador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author hatashi
 */
public class JugadorControllerUtilidades {
	
	private static String RUTA_ARCHIVO_ID="/Users/formotion/tfg/java/archivos/idJugadoresNombre";
    
    public List<Jugador> devolverJugadores(String nombre) throws IOException{
        List<Jugador> listaJugadores;
        listaJugadores = completeTextJugadores();
            List<Jugador> lista = new ArrayList<>();
            for(int i=0;i<listaJugadores.size();i++){
                if(listaJugadores.get(i).getNombre().toUpperCase().contains(nombre) ||
                        listaJugadores.get(i).getApellido().toUpperCase().contains(nombre) ||
                        (listaJugadores.get(i).getNombre()+" "+listaJugadores.get(i).getApellido()).toUpperCase().contains(nombre) || 
                        (listaJugadores.get(i).getApellido()+" "+listaJugadores.get(i).getNombre()).toUpperCase().contains(nombre)){
                     lista.add(listaJugadores.get(i));
                }
            }
        return lista;
    }
    
    @SuppressWarnings({ "rawtypes", "unused" })
	public List<Jugador> completeTextJugadores() throws FileNotFoundException, IOException {
        
        List<Jugador> listaJugadores;
        List<String> listaJugadoresNombre;
        Dictionary d = new Hashtable();
        listaJugadores = new ArrayList<Jugador>();
        listaJugadoresNombre = new ArrayList<String>();
        try (BufferedReader readerJugadores = new BufferedReader(new FileReader(RUTA_ARCHIVO_ID))) {
        //try (BufferedReader readerJugadores = new BufferedReader(new FileReader("E://TFG//archivos varios//listaJugadoresIdNombreApellidoNuevoBis.txt"))) {
            String lineJugadores;
            while ((lineJugadores = readerJugadores.readLine()) != null)
            {
                String[] tokens = lineJugadores.split("/");
                if(tokens.length==3){
                    listaJugadores.add(new Jugador(tokens[0],tokens[1],tokens[2]));
                }else{
                    listaJugadores.add(new Jugador(tokens[0],"",""));
                }
                listaJugadoresNombre.add(tokens[0]);
                //d.put(tokens[0], tokens[1]);
            }
        }
            return listaJugadores;
    }
    
    public List<String> devolverMenuCuartos(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todos los cuartos");
            lista.add("1º Cuarto");
            lista.add("2º Cuarto");
            lista.add("3º Cuarto");
            lista.add("4º Cuarto");
            lista.add("1º Prorroga");
            lista.add("2º Prorroga");
            lista.add("3º Prorroga");
            lista.add("4º Prorroga");
          
        return lista;
    }
    
    public List<String> devolverMenuDentro(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todas las Canastas");
            lista.add("Dentro");
            lista.add("Fuera");
          
        return lista;
    }
    
    public List<String> devolverMenuDistancia(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todas las distancias");
            lista.add("0 - 2 Metros");
            lista.add("2 - 4 Metros");
            lista.add("4 - 6.75 Metros");
            lista.add("6.75 - 9 Metros");
            lista.add("Más de 9 Metros");
          
        return lista;
    }
    
    public List<String> devolverMenuTiempoRestante(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todo el cuarto");
            lista.add("5 Segundos");
            lista.add("1 Minuto");
            lista.add("2 Minutos");
            lista.add("3 Minutos");
            lista.add("4 Minutos");
            lista.add("5 Minutos");
            lista.add("6 Minutos");
            lista.add("7 Minutos");
            lista.add("8 Minutos");
            lista.add("9 Minutos");
            lista.add("10 Minutos");
            lista.add("11 Minutos");
            lista.add("12 Minutos");
          
        return lista;
    }
    
    public List<String> devolverMenuTipoCanasta(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todos los tipos");
            lista.add("2 Puntos");
            lista.add("3 Puntos");
          
        return lista;
    }
    
    public List<String> devolverSituacionesPartido(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todas las situaciones");
            lista.add("Perdiendo");
            lista.add("Empate");
            lista.add("Ganando");
        return lista;
    }
    
    public List<String> devolverPonerseDelante(){
        List<String> lista = new ArrayList<String>();
            lista.add("Todas las canastas");
            lista.add("Ponerse por Delante");
        return lista;
    }
    
    public String devolverCuarto(String cuarto){
        switch(cuarto){
            case "1º Cuarto":return "cuarto1";
            case "2º Cuarto":return "cuarto2";
            case "3º Cuarto":return "cuarto3";
            case "4º Cuarto":return "cuarto4";
            case "1º Prorroga":return "over1";
            case "2º Prorroga":return "over2";
            case "3º Prorroga":return "over3";
            case "4º Prorroga":return "over4";
            default:return "boxscore";
        }
    }
    
    public int devolverTiempoCuarto(String tiempoSeleccionado){
        switch(tiempoSeleccionado){
            case "5 Segundos":return 5;
            case "1 Minuto":return 60;
            case "2 Minutos":return 120;
            case "3 Minutos":return 180;
            case "4 Minutos":return 240;
            case "5 Minutos":return 300;
            case "6 Minutos":return 360;
            case "7 Minutos":return 420;
            case "8 Minutos":return 480;
            case "9 Minutos":return 540;
            case "10 Minutos":return 600;
            case "11 Minutos":return 660;
            case "12 Minutos":return 720;
            default:return 720;
        }
    }
    
    public double[] devolverDistanciaMetrosPies(String distancia){
        switch(distancia){
            case "0 - 2 Metros":return new double[] {0.0,6.55};
            case "2 - 4 Metros":return new double[] {6.56,13.11};
            case "4 - 6.75 Metros":return new double[] {13.12,22.13};
            case "6.75 - 9 Metros":return new double[] {22.14,29.51};
            case "Más de 9 Metros":return new double[] {29.52,92.0};
            default:return new double[] {0.0,92.0};
        }
    }
}
