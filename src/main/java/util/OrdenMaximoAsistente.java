package util;

import java.util.Comparator;

import clases.ControllerJugador;

public class OrdenMaximoAsistente  implements Comparator<ControllerJugador>{

	@Override
	public int compare(ControllerJugador e1, ControllerJugador e2) {
		  if(e1.getTotalPartido().getAsistencias()>e2.getTotalPartido().getAsistencias()){
	            return -1;
	        }else if(e1.getTotalPartido().getAsistencias()>e2.getTotalPartido().getAsistencias()){
	            return 0;
	        }else{
	            return 1;
	        }
	}

}
