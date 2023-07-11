package util;

public enum ListaEquipos {

	AtlantaHawks("Atlanta Hawks","atl","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/ATL.png",0),
	BostonCeltics("Boston Celtics","bos","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/BOS.png",0),
	BrooklynNets("Brooklyn Nets","njn","https://d2p3bygnnzw9w3.cloudfront.net/req/202102091/tlogo/bbr/NJN.png",1),
	CharlotteBobcats("Charlotte Bobcats","cha","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/CHA.png",2),
	CharlotteHornets("Charlotte Hornets","chh","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/CHA.png",2),
	ChicagoBulls("Chicago Bulls","chi","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/CHI.png",0),
	ClevelandCavaliers("Cleveland Cavaliers","cle","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/CLE.png",0),
	DallasMavericks("Dallas Mavericks","dal","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/DAL.png",0),
	DenverNuggets("Denver Nuggets","den","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/DEN.png",0),
	DetroitPistons("Detroit Pistons","det","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/DET.png",0),
	GoldenStateWarriors("Golde State Warriors","gsw","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/GSW.png",0),
	HoustonRockets("Houston Rockets","hou","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/HOU.png",0),
	IndianaPacers("Indiana Pacers","ind","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/IND.png",0),
	LosAngelesClippers("Los Angeles Clippers","lac","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/LAC.png",0),
	LosAngelesLakers("Los Angeles Lakers","lal","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/LAL.png",0),
	MemphisGrizzlies("Memphis Grizzlies","mem","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/MEM.png",3),
	MiamiHeat("Miami Heat","mia","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/MIA.png",0),
	MilwaukeeBucks("Milwaukee Bucks","mil","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/MIL.png",0),
	MinnesotaTimberwolves("Minesota Timberwolves","min","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/MIN.png",0),
	NewJerseyNets("New Jersey Nets","njn","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/NJN.png",1),
	NewOrleansHornets("New Orleans Hornets","noh","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/NOH.png",4),
	NewOrleansPelicans("New Orleans Pelicans","nop","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/NOH.png",4),
	NewOrleansOklahomaCityHornets("Oklahoma City Hornets","nop","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/NOH.png",4),
	NewYorkKnicks("New York Knicks","nyk","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/NYK.png",0),
	OklahomaCityThunder("Oklahoma City Thunder","okc","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/OKC.png",5),
	OrlandoMagic("Orlando Magic","orl","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/ORL.png",0),
	Philadelphia76ers("Philadelphia 76ers","phi","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/PHI.png",0),
	PhoenixSuns("Phoenix Suns","pho","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/PHO.png",0),
	PortlandTrailBlazers("Portland Trail Blazers","por","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/POR.png",0),
	SacramentoKings("Sacramento Kings","sac","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/SAC.png",0),
	SanAntonioSpurs("San Antonio Spurs","sas","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/SAS.png",0),
	SeattleSuperSonics("Seattle Super Sonics","sea","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/OKC.png",5),
	TorontoRaptors("Toronto Raptors","tor","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/TOR.png",0),
	UtahJazz("Utah Jazz","uta","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/UTA.png",0),
	VancouverGrizzlies("Vancouver Grizzlies","van","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/MEM.png",3),
	WashingtonWizards("Washington Wizars","was","https://d2p3bygnnzw9w3.cloudfront.net/req/202002101/tlogo/bbr/WAS.png",0);
	
	
	private final String nombre;
	private final String abre;
	private final String foto;
	private final int indice;
	
	private ListaEquipos(String nombre, String abre, String foto,int indice) {
		this.nombre=nombre;
		this.abre=abre;
		this.foto=foto;
		this.indice=indice;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAbre() {
		return abre;
	}

	public String getFoto() {
		return foto;
	}
	
	public int getIndice() {
		return indice;
	}
}
