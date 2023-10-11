package util;

public enum ListaClasificaciones {
	
	SEASON20002001ESTE(2000,"ESTE",
			new ListaEquipos[] {ListaEquipos.PHILADELPHIA76ERS,ListaEquipos.MILWAUKEEBUCKS,ListaEquipos.MIAMIHEAT,ListaEquipos.NEWYORKKNICKS,ListaEquipos.TORONTORAPTORS,ListaEquipos.CHARLOTTEHORNETS,ListaEquipos.ORLANDOMAGIC,ListaEquipos.INDIANAPACERS,ListaEquipos.BOSTONCELTICS,ListaEquipos.DETROITPISTONS,ListaEquipos.CLEVELANDCAVALIERS,ListaEquipos.NEWJERSEYNETS,ListaEquipos.ATLANTAHAWKS,ListaEquipos.WASHINGTONWIZARDS,ListaEquipos.CHICAGOBULLS}),
	SEASON20002001OESTE(2000,"OESTE",
			new ListaEquipos[] {ListaEquipos.SANANTONIOSPURS,ListaEquipos.LOSANGELESLAKERS,ListaEquipos.SACRAMENTOKINGS,ListaEquipos.UTAHJAZZ,ListaEquipos.DALLASMAVERICKS,ListaEquipos.PHOENIXSUNS,ListaEquipos.PORTLANDTRAILBLAZERS,ListaEquipos.MINNESOTATIMBERWOLVES,ListaEquipos.HOUSTONROCKETS,ListaEquipos.SEATTLESUPERSONICS,ListaEquipos.DENVERNUGGETS,ListaEquipos.LOSANGELESCLIPPERS,ListaEquipos.VANCOUVERGRIZZLIES,ListaEquipos.GOLDENSTATEWARRIORS});
	
	private final int season;
	private final String conferencia;
	private final ListaEquipos[] clasificacion;
	
	private ListaClasificaciones(int season, String conferencia, ListaEquipos[] clasificacion) {
		this.season=season;
		this.conferencia=conferencia;
		this.clasificacion=clasificacion;
	}
	
	public int getSeason() {
		return season;
	}
	
	public String getConferencia() {
		return conferencia;
	}
	
	public ListaEquipos[] getClasificacion() {
		return clasificacion;
	}
	
	public static ListaEquipos[] findClasificacionByYearConferencia(int season, String conferencia) {
		for(ListaClasificaciones clasificacion : ListaClasificaciones.values()) {
			if(clasificacion.getSeason()==season && clasificacion.getConferencia().equals(conferencia)) {
				return clasificacion.clasificacion;
			}
		}
		return null;
	}

}
