package clases;

import java.util.HashMap;
import java.util.Map;

public class ControllerTanteoCuartos {

    private Map<String, Integer> cuartos = new HashMap<String, Integer>();

    public void insertarCuarto(String cuarto, Integer puntos) {
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
        System.out.println(cuarto);
        if(devolverCuarto(cuarto)==99){
            System.out.println(false);
            return false;
        }else{
            System.out.println(true);
            return true;
        }
    }
}
