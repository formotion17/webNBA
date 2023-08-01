package temporadas;

import lombok.Data;

@Data
public class Equipo {
	
	private int posicion;

	private String nombre;
	private String conferencia;
	private String division;
	private String foto;
	
	private int victorias;
	private int derrotas;
	private float porcentajeVictorias;
	
	private int victoriasLocal;
	private int victoriasVisitante;
	
	private int derrotasLocal;
	private int derrotasVisitante;
	
	private int victoriasDivisionLocal;
	private int victoriasDivisionVisitante;
	private int derrotasDivisionLocal;
	private int derrotasDivisionVisitante;
	private int victoriasDivision;
	private int derrotasDivision;
	
	private int victoriasConferenciaLocal;
	private int victoriasConferenciaVisitante;
	private int derrotasConferenciaLoca;
	private int derrotasConferenciaVisitante;
	private int victoriasConferencia;
	private int derrotasConferencia;
	
	public Equipo (String nombre, String conferencia, String division,String foto) {
		this.nombre=nombre;
		this.conferencia=conferencia;
		this.division=division;
		this.foto=foto;
	}
	
	public String getPorcentajeVictorias() {
		 return String.format("%.3f", porcentajeVictorias).replace("0,", ".");
	}
	
	public void porcentajes(){
		this.porcentajeVictorias = (float) (Double.valueOf(victorias) / Double.valueOf(victorias+derrotas));
	}
	
	public void addVictoriasLocal() {
		this.victoriasLocal++;
	}
	
	public void addVictoriasVisitante() {
		this.victoriasVisitante++;
	}
	
	public void addDerrotasLocal() {
		this.derrotasLocal++;
	}
	
	public void addDerrotasVisitante() {
		this.derrotasVisitante++;
	}
	
	public void addVictoriaDivisionLocal(){
		this.victoriasDivisionLocal++;
		this.victoriasDivision++;
	}
	
	public void addVictoriaDivisionVisitante(){
		this.victoriasDivisionVisitante++;
		this.victoriasDivision++;
	}
	
	public void addDerrotaDivisionLocal(){
		this.derrotasDivisionLocal++;
		this.derrotasDivision++;
	}
	
	public void addDerrotaDivisionVisitante() {
		this.derrotasDivisionVisitante++;
		this.derrotasDivision++;
	}
	
	public void addVictoriaConferenciaLocal() {
		this.victoriasConferenciaLocal++;
		this.victoriasConferencia++;
	}
	
	public void addVictoriaConferenciaVisitante() {
		this.victoriasConferenciaVisitante++;
		this.victoriasConferencia++;
	}
	
	public void addDerrotaConferenciaLocal() {
		this.derrotasConferenciaLoca++;
		this.derrotasConferencia++;
	}
	
	public void addDerrotaConferenciaVisitante() {
		this.derrotasConferenciaVisitante++;
		this.derrotasConferencia++;
	}
	
	public void addVictoria() {
		this.victorias++;
	}
	
	public void addDerrota() {
		this.derrotas++;
	}
	
	public void addVictoriaLocal() {
		this.victoriasLocal++;
	}
	
	public void addVictoriaVisitante(){
		this.victoriasVisitante++;
	}
	
	public void addDerrotaLocal() {
		this.derrotasLocal++;
	}
	
	public void addDerrotaVisitante() {
		this.derrotasVisitante++;
	}
}