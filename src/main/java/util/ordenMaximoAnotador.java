package util;

import java.util.Comparator;

import clases.ControllerJugador;

public class ordenMaximoAnotador  implements Comparator<ControllerJugador>{

	@Override
	public int compare(ControllerJugador e1, ControllerJugador e2) {
		  if(e1.getTotalPartido().getPuntos()>e2.getTotalPartido().getPuntos()){
	            return -1;
	        }else if(e1.getTotalPartido().getPuntos()>e2.getTotalPartido().getPuntos()){
	            return 0;
	        }else{
	            return 1;
	        }
	}

}
