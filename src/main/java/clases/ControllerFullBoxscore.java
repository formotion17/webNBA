package clases;

public class ControllerFullBoxscore {

    private String cuarto = "";

    private Integer mate = 0;
    private Integer robos = 0;
    private Integer gancho = 0;
    private Integer puntos = 0;
    private Integer tapones = 0;
    private Integer bandeja = 0;
    private Integer perdidas = 0;
    private Integer masMenos = 0;
    private Integer suspension = 0;
    private Integer asistencias = 0;
    private Integer mateFallado = 0;
    private Integer faltaTecnica = 0;
    private Integer perdidaPasos = 0;
    private Integer totalRebotes = 0;
    private Integer taponRecibido = 0;
    private Integer ganchoFallado = 0;
    private Integer triplesMetidos = 0;
    private Integer bandejaFallada = 0;
    private Integer perdidaMalPase = 0;
    private Integer reboteOfensivo = 0;
    private Integer reboteDefensivo = 0;
    private Integer faltasPersonales = 0;
    private Integer tirosCampoMetidos = 0;
    private Integer triplesIntentados = 0;
    private Integer perdidaFueraBanda = 0;
    private Integer suspensionFallado = 0;
    private Integer faltaPersonalTiro = 0;
    private Integer perdidaPisarFuera = 0;
    private Integer tirosLibresMetidos = 0;
    private Integer perdidaGoaltending = 0;
    private Integer perdidaBalonPerdido = 0;
    private Integer faltaPersonalAtaque = 0;
    private Integer taponRecibidoTriple = 0;
    private Integer primerTiroLibreFuera = 0;
    private Integer primerTiroLibreTotal = 0;
    private Integer tercerTiroLibreFuera = 0;
    private Integer tercerTiroLibreTotal = 0;
    private Integer tirosCampoIntentados = 0;
    private Integer faltaPersonalDefensa = 0;
    private Integer segundoTiroLibreFuera = 0;
    private Integer segundoTiroLibreTotal = 0;
    private Integer tercerTiroLibreDentro = 0;
    private Integer primerTiroLibreDentro = 0;
    private Integer tirosLibresIntentados = 0;
    private Integer segundoTiroLibreDentro = 0;
    private Integer faltasPersonalesProvocadas = 0;
    private Integer faltaPersonalProvocadaEnTiro = 0;
    private Integer faltaPersonalProvocadaEnAtaque = 0;
    private Integer faltaPersonalProvocadaEnDefensa = 0;

    private Double triplesPorcentaje = 0.0;
    private Double tirosCampoPorcentaje = 0.0;
    private Double tirosLibresPorcentaje = 0.0;
    private Double primerTiroLibrePorcentaje = 0.0;
    private Double tercerTiroLibrePorcentaje = 0.0;
    private Double segundoTiroLibrePorcentaje = 0.0;
	private Integer perdidaCampoAtras = 0;
	private Integer perdidaOtros = 0;
	private Integer perdidaTresSegundos = 0;
	private Integer perdidaFalta = 0;
	private Integer perdidaDobles = 0;
	private Integer perdidaPie = 0;

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public Integer getBandeja() {
        return bandeja;
    }

    public void setBandeja(Integer bandeja) {
        this.bandeja = bandeja;
    }

    public Integer getBandejaFallada() {
        return bandejaFallada;
    }

    public void setBandejaFallada(Integer bandejaFallada) {
        this.bandejaFallada = bandejaFallada;
    }

    public String getCuarto() {
        return cuarto;
    }

    public void setCuarto(String cuarto) {
        this.cuarto = cuarto;
    }

    public Integer getFaltaPersonalAtaque() {
        return faltaPersonalAtaque;
    }

    public void setFaltaPersonalAtaque(Integer faltaPersonalAtaque) {
        this.faltaPersonalAtaque = faltaPersonalAtaque;
    }

    public Integer getFaltaPersonalDefensa() {
        return faltaPersonalDefensa;
    }

    public void setFaltaPersonalDefensa(Integer faltaPersonalDefensa) {
        this.faltaPersonalDefensa = faltaPersonalDefensa;
    }

    public Integer getFaltaPersonalProvocadaEnAtaque() {
        return faltaPersonalProvocadaEnAtaque;
    }

    public void setFaltaPersonalProvocadaEnAtaque(Integer faltaPersonalProvocadaEnAtaque) {
        this.faltaPersonalProvocadaEnAtaque = faltaPersonalProvocadaEnAtaque;
    }

    public Integer getFaltaPersonalProvocadaEnDefensa() {
        return faltaPersonalProvocadaEnDefensa;
    }

    public void setFaltaPersonalProvocadaEnDefensa(Integer faltaPersonalProvocadaEnDefensa) {
        this.faltaPersonalProvocadaEnDefensa = faltaPersonalProvocadaEnDefensa;
    }

    public Integer getFaltaPersonalProvocadaEnTiro() {
        return faltaPersonalProvocadaEnTiro;
    }

    public void setFaltaPersonalProvocadaEnTiro(Integer faltaPersonalProvocadaEnTiro) {
        this.faltaPersonalProvocadaEnTiro = faltaPersonalProvocadaEnTiro;
    }

    public Integer getFaltaPersonalTiro() {
        return faltaPersonalTiro;
    }

    public void setFaltaPersonalTiro(Integer faltaPersonalTiro) {
        this.faltaPersonalTiro = faltaPersonalTiro;
    }

    public Integer getFaltaTecnica() {
        return faltaTecnica;
    }

    public void setFaltaTecnica(Integer faltaTecnica) {
        this.faltaTecnica = faltaTecnica;
    }

    public Integer getFaltasPersonales() {
        return faltasPersonales;
    }

    public void setFaltasPersonales(Integer faltasPersonales) {
        this.faltasPersonales = faltasPersonales;
    }

    public Integer getFaltasPersonalesProvocadas() {
        return faltasPersonalesProvocadas;
    }

    public void setFaltasPersonalesProvocadas(Integer faltasPersonalesProvocadas) {
        this.faltasPersonalesProvocadas = faltasPersonalesProvocadas;
    }

    public Integer getGancho() {
        return gancho;
    }

    public void setGancho(Integer gancho) {
        this.gancho = gancho;
    }

    public Integer getGanchoFallado() {
        return ganchoFallado;
    }

    public void setGanchoFallado(Integer ganchoFallado) {
        this.ganchoFallado = ganchoFallado;
    }

    public Integer getMasMenos() {
        return masMenos;
    }

    public void setMasMenos(Integer masMenos) {
        this.masMenos = masMenos;
    }

    public Integer getMate() {
        return mate;
    }

    public void setMate(Integer mate) {
        this.mate = mate;
    }

    public Integer getMateFallado() {
        return mateFallado;
    }

    public void setMateFallado(Integer mateFallado) {
        this.mateFallado = mateFallado;
    }

    public Integer getPerdidaBalonPerdido() {
        return perdidaBalonPerdido;
    }

    public void setPerdidaBalonPerdido(Integer perdidaBalonPerdido) {
        this.perdidaBalonPerdido = perdidaBalonPerdido;
    }

    public Integer getPerdidaFueraBanda() {
        return perdidaFueraBanda;
    }

    public void setPerdidaFueraBanda(Integer perdidaFueraBanda) {
        this.perdidaFueraBanda = perdidaFueraBanda;
    }

    public Integer getPerdidaGoaltending() {
        return perdidaGoaltending;
    }

    public void setPerdidaGoaltending(Integer perdidaGoaltending) {
        this.perdidaGoaltending = perdidaGoaltending;
    }

    public Integer getPerdidaMalPase() {
        return perdidaMalPase;
    }

    public void setPerdidaMalPase(Integer perdidaMalPase) {
        this.perdidaMalPase = perdidaMalPase;
    }

    public Integer getPerdidaPasos() {
        return perdidaPasos;
    }

    public void setPerdidaPasos(Integer perdidaPasos) {
        this.perdidaPasos = perdidaPasos;
    }

    public Integer getPerdidaPisarFuera() {
        return perdidaPisarFuera;
    }

    public void setPerdidaPisarFuera(Integer perdidaPisarFuera) {
        this.perdidaPisarFuera = perdidaPisarFuera;
    }

    public Integer getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(Integer perdidas) {
        this.perdidas = perdidas;
    }

    public Integer getPrimerTiroLibreDentro() {
        return primerTiroLibreDentro;
    }

    public void setPrimerTiroLibreDentro(Integer primerTiroLibreDentro) {
        this.primerTiroLibreDentro = primerTiroLibreDentro;
    }

    public Integer getPrimerTiroLibreFuera() {
        return primerTiroLibreFuera;
    }

    public void setPrimerTiroLibreFuera(Integer primerTiroLibreFuera) {
        this.primerTiroLibreFuera = primerTiroLibreFuera;
    }

    public Double getPrimerTiroLibrePorcentaje() {
        return primerTiroLibrePorcentaje;
    }

    public void setPrimerTiroLibrePorcentaje(Double primerTiroLibrePorcentaje) {
        this.primerTiroLibrePorcentaje = primerTiroLibrePorcentaje;
    }

    public Integer getPrimerTiroLibreTotal() {
        return primerTiroLibreTotal;
    }

    public void setPrimerTiroLibreTotal(Integer primerTiroLibreTotal) {
        this.primerTiroLibreTotal = primerTiroLibreTotal;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getReboteDefensivo() {
        return reboteDefensivo;
    }

    public void setReboteDefensivo(Integer reboteDefensivo) {
        this.reboteDefensivo = reboteDefensivo;
    }

    public Integer getReboteOfensivo() {
        return reboteOfensivo;
    }

    public void setReboteOfensivo(Integer reboteOfensivo) {
        this.reboteOfensivo = reboteOfensivo;
    }

    public Integer getRobos() {
        return robos;
    }

    public void setRobos(Integer robos) {
        this.robos = robos;
    }

    public Integer getSegundoTiroLibreDentro() {
        return segundoTiroLibreDentro;
    }

    public void setSegundoTiroLibreDentro(Integer segundoTiroLibreDentro) {
        this.segundoTiroLibreDentro = segundoTiroLibreDentro;
    }

    public Integer getSegundoTiroLibreFuera() {
        return segundoTiroLibreFuera;
    }

    public void setSegundoTiroLibreFuera(Integer segundoTiroLibreFuera) {
        this.segundoTiroLibreFuera = segundoTiroLibreFuera;
    }

    public Double getSegundoTiroLibrePorcentaje() {
        return segundoTiroLibrePorcentaje;
    }

    public void setSegundoTiroLibrePorcentaje(Double segundoTiroLibrePorcentaje) {
        this.segundoTiroLibrePorcentaje = segundoTiroLibrePorcentaje;
    }

    public Integer getSegundoTiroLibreTotal() {
        return segundoTiroLibreTotal;
    }

    public void setSegundoTiroLibreTotal(Integer segundoTiroLibreTotal) {
        this.segundoTiroLibreTotal = segundoTiroLibreTotal;
    }

    public Integer getSuspension() {
        return suspension;
    }

    public void setSuspension(Integer suspension) {
        this.suspension = suspension;
    }

    public Integer getSuspensionFallado() {
        return suspensionFallado;
    }

    public void setSuspensionFallado(Integer suspensionFallado) {
        this.suspensionFallado = suspensionFallado;
    }

    public Integer getTaponRecibido() {
        return taponRecibido;
    }

    public void setTaponRecibido(Integer taponRecibido) {
        this.taponRecibido = taponRecibido;
    }

    public Integer getTaponRecibidoTriple() {
        return taponRecibidoTriple;
    }

    public void setTaponRecibidoTriple(Integer taponRecibidoTriple) {
        this.taponRecibidoTriple = taponRecibidoTriple;
    }

    public Integer getTapones() {
        return tapones;
    }

    public void setTapones(Integer tapones) {
        this.tapones = tapones;
    }

    public Integer getTercerTiroLibreDentro() {
        return tercerTiroLibreDentro;
    }

    public void setTercerTiroLibreDentro(Integer tercerTiroLibreDentro) {
        this.tercerTiroLibreDentro = tercerTiroLibreDentro;
    }

    public Integer getTercerTiroLibreFuera() {
        return tercerTiroLibreFuera;
    }

    public void setTercerTiroLibreFuera(Integer tercerTiroLibreFuera) {
        this.tercerTiroLibreFuera = tercerTiroLibreFuera;
    }

    public Double getTercerTiroLibrePorcentaje() {
        return tercerTiroLibrePorcentaje;
    }

    public void setTercerTiroLibrePorcentaje(Double tercerTiroLibrePorcentaje) {
        this.tercerTiroLibrePorcentaje = tercerTiroLibrePorcentaje;
    }

    public Integer getTercerTiroLibreTotal() {
        return tercerTiroLibreTotal;
    }

    public void setTercerTiroLibreTotal(Integer tercerTiroLibreTotal) {
        this.tercerTiroLibreTotal = tercerTiroLibreTotal;
    }

    public Integer getTirosCampoIntentados() {
        return tirosCampoIntentados;
    }

    public void setTirosCampoIntentados(Integer tirosCampoIntentados) {
        this.tirosCampoIntentados = tirosCampoIntentados;
    }

    public Integer getTirosCampoMetidos() {
        return tirosCampoMetidos;
    }

    public void setTirosCampoMetidos(Integer tirosCampoMetidos) {
        this.tirosCampoMetidos = tirosCampoMetidos;
    }

    public Double getTirosCampoPorcentaje() {
        return tirosCampoPorcentaje;
    }

    public void setTirosCampoPorcentaje(Double tirosCampoPorcentaje) {
        this.tirosCampoPorcentaje = tirosCampoPorcentaje;
    }

    public Integer getTirosLibresIntentados() {
        return tirosLibresIntentados;
    }

    public void setTirosLibresIntentados(Integer tirosLibresIntentados) {
        this.tirosLibresIntentados = tirosLibresIntentados;
    }

    public Integer getTirosLibresMetidos() {
        return tirosLibresMetidos;
    }

    public void setTirosLibresMetidos(Integer tirosLibresMetidos) {
        this.tirosLibresMetidos = tirosLibresMetidos;
    }

    public Double getTirosLibresPorcentaje() {
        return tirosLibresPorcentaje;
    }

    public void setTirosLibresPorcentaje(Double tirosLibresPorcentaje) {
        this.tirosLibresPorcentaje = tirosLibresPorcentaje;
    }

    public Integer getTotalRebotes() {
        return totalRebotes;
    }

    public void setTotalRebotes(Integer totalRebotes) {
        this.totalRebotes = totalRebotes;
    }

    public Integer getTriplesIntentados() {
        return triplesIntentados;
    }

    public void setTriplesIntentados(Integer triplesIntentados) {
        this.triplesIntentados = triplesIntentados;
    }

    public Integer getTriplesMetidos() {
        return triplesMetidos;
    }

    public void setTriplesMetidos(Integer triplesMetidos) {
        this.triplesMetidos = triplesMetidos;
    }

    public Double getTriplesPorcentaje() {
        return triplesPorcentaje;
    }

    public void setTriplesPorcentaje(Double triplesPorcentaje) {
        this.triplesPorcentaje = triplesPorcentaje;
    }

	public Integer getPerdidaCampoAtras() {
		return perdidaCampoAtras;
	}

	public void setPerdidaCampoAtras(Integer perdidaCampoAtras) {
		this.perdidaCampoAtras = perdidaCampoAtras;
	}

	public Integer getPerdidaOtros() {
		return perdidaOtros;
	}

	public void setPerdidaOtros(Integer perdidaOtros) {
		this.perdidaOtros = perdidaOtros;
	}

	public Integer getPerdidaTresSegundos() {
		return perdidaTresSegundos;
	}

	public void setPerdidaTresSegundos(Integer perdidaTresSegundos) {
		this.perdidaTresSegundos = perdidaTresSegundos;
	}

	public Integer getPerdidaFalta() {
		return perdidaFalta;
	}

	public void setPerdidaFalta(Integer perdidaFalta) {
		this.perdidaFalta = perdidaFalta;
	}

	public Integer getPerdidaDobles() {
		return perdidaDobles;
	}

	public void setPerdidaDobles(Integer perdidaDobles) {
		this.perdidaDobles = perdidaDobles;
	}

	public Integer getPerdidaPie() {
		return perdidaPie;
	}

	public void setPerdidaPie(Integer perdidaPie) {
		this.perdidaPie = perdidaPie;
	}
    

}
