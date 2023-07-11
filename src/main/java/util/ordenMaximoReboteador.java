package util;

import java.util.Comparator;

import clases.ControllerJugador;

public class ordenMaximoReboteador  implements Comparator<ControllerJugador>{

	@Override
	public int compare(ControllerJugador e1, ControllerJugador e2) {
		  if(e1.getTotalPartido().getTotalRebotes()>e2.getTotalPartido().getTotalRebotes()){
	            return -1;
	        }else if(e1.getTotalPartido().getTotalRebotes()>e2.getTotalPartido().getTotalRebotes()){
	            return 0;
	        }else{
	            return 1;
	        }
	}
}
