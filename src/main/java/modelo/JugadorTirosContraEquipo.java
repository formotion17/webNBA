package modelo;

import lombok.Data;

@Data
public class JugadorTirosContraEquipo {
	
	private int puntos=0;
	
	private int tirosCampoMetidos=0;
	private int tirosCampoIntentados=0;
	private int tirosCampoFallados=0;
	private Double porcentajeTirosCampo=0.0;
	
	private int dosPuntosMetidos=0;
	private int dosPuntosIntentados=0;
	private int dosPuntosFallados=0;
	private Double porcentajeDosPuntos=0.0;
	
	private int tresPuntosMetidos=0;
	private int tresPuntosIntentados=0;
	private int tresPuntosFallados=0;
	private Double porcentajeTresPuntos;
	
	private void calcularTanteoyPorcentajes() {
		puntos=dosPuntosMetidos*2 + tresPuntosMetidos*3;
		
		if (tirosCampoIntentados != 0) {
			porcentajeTirosCampo = (Double.valueOf(tirosCampoMetidos) / Double.valueOf(tirosCampoIntentados));
        } else {
        	porcentajeTirosCampo = 0.0;
        }
		
		if (dosPuntosIntentados != 0) {
			porcentajeDosPuntos = (Double.valueOf(dosPuntosMetidos) / Double.valueOf(dosPuntosIntentados));
        } else {
        	porcentajeDosPuntos = 0.0;
        }
		
		if (tresPuntosIntentados != 0) {
			porcentajeTresPuntos = (Double.valueOf(tresPuntosMetidos) / Double.valueOf(tresPuntosIntentados));
        } else {
        	porcentajeTresPuntos = 0.0;
        }
	}
	
	public void calcularTirosCampo() {
		tirosCampoMetidos=dosPuntosMetidos + tresPuntosMetidos;
		tirosCampoIntentados=dosPuntosIntentados + tresPuntosIntentados;
		tirosCampoFallados = dosPuntosFallados + tresPuntosFallados;
		calcularTanteoyPorcentajes();
	}
	
	public void sumarDosMetido() {
		dosPuntosMetidos++;
		dosPuntosIntentados++;
	}
	
	public void sumarDosFallado(){
		dosPuntosFallados++;
		dosPuntosIntentados++;
	}
	
	public void sumarTresMetido() {
		tresPuntosMetidos++;
		tresPuntosIntentados++;
	}
	
	public void sumarTresFallado() {
		tresPuntosFallados++;
		tresPuntosIntentados++;
	}
	
	
	
}
