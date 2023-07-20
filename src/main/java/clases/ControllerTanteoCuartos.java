package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerTanteoCuartos {

    private Map<String, Integer> cuartos = new HashMap<String, Integer>();
    private ArrayList<String> listaCuartosJugados= new ArrayList<String>();

    public void insertarCuarto(String cuarto, Integer puntos) {
    	listaCuartosJugados.add(insertarStringCuartoCorrecto(cuarto));
        cuartos.put(cuarto, puntos);
    }

    public Integer devolverCuarto(String cuarto) {
        return cuartos.get(cuarto);
    }

    // Getter
    public Map<String, Integer> getCuartos() {
        return cuartos;
    }

    // Setter
    public void setCuartos(Map<String, Integer> cuartos) {
        this.cuartos = cuartos;
    }
    
    public boolean existeCuarto(String cuarto){
        if(devolverCuarto(cuarto)==99){
            return false;
        }else{
            return true;
        }
    }

	public ArrayList<String> getListaCuartosJugados() {
		return listaCuartosJugados;
	}

	public void setListaCuartosJugados(ArrayList<String> listaCuartosJugados) {
		this.listaCuartosJugados = listaCuartosJugados;
	}
	
	private String insertarStringCuartoCorrecto(String cuarto) {
		switch(cuarto) {
		case "primero":
			return "1st quarter,";
		case "segundo":
			return "2nd quarter,";
		case "tercero":
			return "3rd quarter,";
		case "cuarto":
			return "4th quarter,";
		case "OT1":
			return "1st overtime,";
		case "OT2":
			return "2nd overtime,";
		case "OT3":
			return "3rd overtime,";
		case "OT4":
			return "4th overtime,";
		case "OT5":
			return "5th overtime,";
		case "OT6":
			return "6th overtime,";
		}
		return cuarto;
	}
}
