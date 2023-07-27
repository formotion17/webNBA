package contollers;

import Service.impl.JugadorDataImpl;
import clases.ControllerEstadisticaNormal;
import clases.ControllerPartido;
import clases.ControllerPartidoJugador;
import clases.ControllerTiros;
import clases.Jugador;
import util.ListaEquipos;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import contollers.utilidades.JugadorControllerUtilidades;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.JugadorTirosContraEquipo;
import modelo.TablaTirosJugador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.line.LineChartModel;
import util.ClaseEstadisticaNormalTotales;
import util.MapJavaMongo;
import util.Utilidades;

/**
 *
 * @author hatashi
 */

@ViewScoped
@ManagedBean(name ="jugadores")
@Data
@EqualsAndHashCode(callSuper=false)
public class JugadorController extends BaseController implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8870342600681672087L;
	private String nombreJugador;
    private List<Jugador> listaJugadores;
    private List<String> listaJ;
    private String urlFoto="https://www.basketball-reference.com/req/202106291/images/headshots/";
    private String imagenCarga="R0lGODlh3AATAPQeAO7u7r6+vqamptbW1sLCwqqqqri4uLKyssjIyNjY2MTExNTU1Nzc3ODg4OTk5LCwsLy8vOjo6MrKyvLy8vT09M7Ozvb29sbGxtDQ0KCgoPj4+Ozs7JycnJaWlv///wAAACH/C05FVFNDQVBFMi4wAwEAAAAh+QQECgD/ACwAAAAA3AATAAAF/6AnjmRpnmiqrmzrvnAsz3Rt33iu73zv/8CgcEj0BAScpHLJbDqf0Kh0Sq1ar9isdioItAKGw+MAKYMFhbF63CW438f0mg1R2O8EuXj/aOPtaHx7fn96goR4hmuId4qDdX95c4+RBIGCB4yAjpmQhZN0YGYGXitdZBIVGAsLoq4BBKQDswm1CQRkcG6ytrYKubq8vbfAcMK9v7q7EMO1ycrHvsW6zcTKsczNz8HZw9vG3cjTsMIYqQkCLBwHCgsMDQ4RDAYIqfYSFxDxEfz88/X38Onr16+Bp4ADCco7eC8hQYMAEe57yNCew4IVBU7EGNDiRn8Z831cGLHhSIgdFf9chIeBg7oA7gjaWUWTVQAGE3LqBDCTlc9WOHfm7PkTqNCh54rePDqB6M+lR536hCpUqs2gVZM+xbrTqtGoWqdy1emValeXKzggYBBB5y1acFNZmEvXAoN2cGfJrTv3bl69Ffj2xZt3L1+/fw3XRVw4sGDGcR0fJhxZsF3KtBTThZxZ8mLMgC3fRatCbYMNFCzwLEqLgE4NsDWs/tvqdezZf13Hvk2A9Szdu2X3pg18N+68xXn7rh1c+PLksI/Dhe6cuO3ow3NfV92bdArTqC2Ebd3A8vjf5QWfH6Bg7Nz17c2fj69+fnq+8N2Lty+fuP78/eV2X13neIcCeBRwxorbZrA1ANoCDGrgoG8RTshahQ9iSKEEzUmYIYfNWViUhheCGJyIP5E4oom7WWjgCeBFAJNv1DVV01MAdJhhjdkplWNzO/5oXI846njjVEIqR2OS2B1pE5PVscajkxhMycqLJghQSwT40PgfAl4GqNSXYdZXJn5gSkmmmmJu1aZYb14V51do+pTOCmA40AqVCIhG5IJ9PvYnhIFOxmdqhpaI6GeHCtpooisuutmg+Eg62KOMKuqoTaXgicQWoIYq6qiklmoqFV0UoeqqrLbq6quwxirrrLTWauutJ4QAACH5BAUKABwALAcABADOAAsAAAX/IPd0D2dyRCoUp/k8gtGmxNpyxxHfhFKwp5wOMuv9bi8BsWhrJWVMYHB4U/ikptzuZL0iYVCut3UA82pYHGwpPjphh1smvRgQ4kUD25RI3Kt3exx9CnhcgTeEhiYpeokJOGeOLYqAB4J9fy0KHJMnlXmYCYWAnnyQiygEpoOjqQqrYSZzFS0REQwGCC0SvRAOtrgQu1y+wCe3DYIcEhe/wcpVxtDL08jCxCYK1ibJgr3Ox93YN9wc3tLh1OnP18rZzM3tfBkELRP4VpQJGAEM9/kkPFLwD6C+ff5uTABw8NOChAYFIix4YuG2RA8pmgg4UCMHi5wmKpzQkE9GhQxD/56oA7EiQ4mfErTsVgKgn4G1bKo8pSDnCQsWGOxshcHnxqBDEyzodQOoUJwjR0G9h3RqxQlP9xVtWpXSUqMfsSZNwFTno60ALwBkyGFAiwUPP1a0wLBOzLhrFSzQSgBAXowL+ubde1fwXAAECJt8OIEq4rYnBkg2vNHiWQL4riJWTBTzYK+B/VbUUBe0vcYbNWzgWtZhgAasYZ6ayQFoa5MEY9+YDDtix5EXRbawLbtVbqq3By1Q0Htu8skeie+209yEdOFXdwJtkHR5cQ0auFtNLV4r2AldVw5IDr68QwlgnQ6Fyz78/KXf7T+q754n2Pb38dddcD+lRwFdY4XW1L9mfKF2FIMOBebgRqW1sB5lJmhAEgYNrkUAhxF6dlhi+yio2X0DBCBaZY91ONoG9pg24Ucthjijah9ehQE8yjGnm1ev/RgTbfjs+BtARmJ3VHKEeDSBBkm6Vl1YUZrAUnQWVNkKkRYwKZOTE2gJF23XnTNBfxzUIUGMBQoFIm4SUECVm29dKOdVWa20FAJ3ksfmKb30WZsFDZDo0DaC4iPVW/gJKt+bae6Z6ASjQNpHUY4SGiCfwxH6Z5p26tRPCAAh+QQFCgAbACwHAAQAzgALAAAF/+C2ZQJ3iChBCAUqPo9guJvK0s8h04pStK6DDuJS+TYPlHBII/iAqNyuuIKKhAIi9RZkuno/Gnb29SWVWG2KEO5OUYoqrpA5bwSbAhm1GBBOVAZqIgsJG4Bwf4MbCYaIInGCNI0KjzWKk448mC6FlZuSnQmfgYuNh00EoSiUlioHpqOuqrGkiUIoAg8GBwwuEREMBgg0EhIQDr/BEMRwxsjKDYvGF9AowNI8z8nXEdnF1dwiwAyLChLh0cwuxsfiG9iLG+jW497m9O/k0/nqzZDbUOgxMIDGhAk9RGEI4MvFBAAJRSloiOJgRFajKG44CFHBpAUMDSKUkGmiw4fnaP940rhxZEmNB126oMTSosdOA0JWRElyJkiYPF92ubmzRskKIkepHIX0JAOiIig13fn0aFKojJiKrOrT2NWlCqaKOKh0ptaTZVktkCB2owWufM5SxSpVjIEIJyFmAnlwJwACC3zyzQtYIQEAhGlsGDDYb2EUjBuPtQAx8EwMBPpO/rv3cGLDEwhbjppggWfHo6NiDo3arGnEFQv0rGjBq8SaE2xjNHlStwjGvCfXnv3bTwORKV0MGBB8rMzduH0z+unQwnCh1aX3ad5SOk2D15UvUHDcoR3ncAnJRY91O/ENGjQ0aD9Aevz5Vqvjl9h2goX9fKz13n3tCUgDgfnNlSDHewu6BWBUa/U3AQGfqfVahRBeSNtfGEyyWnUcgkaDBc9tUMiHKMRXYiMaOscZDQMsBNtkE37UYksv8hHjaZsR0CFGN/oXooU84jiiBRj8A6FOOyWJHQrWOYkTd1EqaWJGIklgZR9MuoXkltTR9uWTIlinHZbZgcndQVqSuUGVbrLpAgVvUcgfeA3YqeM5FDilwI+khdUnbfMBOh1bg06Wp6HuJdrSU4zWh4Cj1hXayVqT+skoU5TWyaiAnVoal6CamkUqVQGEAAAh+QQFCgAcACwHAAQAzgALAAAF/yAnckJmjGMgFKj4CGdLrC33xqjCse0B1wRe6wWR6YQjIpDW+8mYKB9uNHvUfMVnzSbI5rZc7+hYK4kPj6loQDjUFAaxKNEGxmt0t+zeyr/5KH57chyCKASAIwkKeocHhIuNVI94jH+QljKUfXUtEA+SEAEGDS0RDAYINRcQDqaob6yuKKeEErKvhBy4tAyECrwjEQ22wSK1sa2mxMmzwr41t8q0zC3Szse+qoeILQoQCTUAHBJ9GAEMLRPj5Zzp6uSV7yPr8e41EwrtKAvo6gD6agzwpy5gi37zRgDcp0hBQhET7AUiSG+hQIoQLd6jEoAhRDqVKuBjoMOcSHUkQ/+OLMlPwkkUFlKaXKkSJcsRA1zSnGmzJkyZLV/SA4pT56EAG/4pWGCOAD6ATANhIBARJtSD/cahWLe0xgKqSqPizBq2D1mrBMSu+Vp1hAUAac2C3QpXbaGpbTN2DXS2Yly+c0UcMDAAn8FACkoV9FjIoWHGdB5ysCARJwHFMCuvSfxYXg3KkB3DCy05ImQCpTVzGMB5tGcUBQhpaHBzjuqYtRsznp2b6W7aPof2Vj2B6BwFQj8O/718Z1DnRZNzKJ47HOMCdjUAxIO3xVunTfNOrxv+H3h+X7W6JY+egHqFuTmcez/+/IgF6b3DFZifLgEM5aGgnX1ruafUFvOpI518Bhhsww9GEDX4GkwSbrRVhSgMVBqG90E4HYdzeMiggw1JZgGIHCD0GYoa4oNiAh5O8KJomXnHAIGF3LISgPxwUAEFNvEYHZA/KSDkHBIgQKRwR+q2pHJNLtDgk9OR1CQdStpoJCdZCkhblLtQGdN/ByVJJXVXEtAlPQGEAAAh+QQFCgAbACwHAAQAzgALAAAF/+AmbkWZGeNGrEKZPo+ApmyRbkcM3YrS3o+DjOd7AIWzEaF4Owh3qZ7r9bwtf6nckMbMVqOK6ci5HUlhWR2tliolRRDIAToaDAhzroEuSiRwfEt7N34KeWZ4fBt+gGuHI4wHRIMphY8qBJSQCYaTipaehJySep+jgYmieFYGBQgjEBcKEA0pEREMBq8jEr20triKvbMOwA1vGwq+xSO3x0QXv83B0NIizsLLxtnE29XM19Qpw9Yb2N/buyLKeAMishUBDCkT9T2VCRjy9PYSogrz+N3Dt29EPQADIS0oaHBCQhELFgYUcfDhhgESb/T7N3FDRX8EO3p0CFIhw5EIS/9CzNgwJSF9f0T4U1BBowUV/2ryY6Agp02ePnf2xIdBZ0OgRI1StIBUYa+fQzfR1Dih6cqnQoMejdpngQSlI61e9FqSpj6qAAgsqBQxgMeWCiKOiLgQrdq1IjDW5ZcWb16MbvmqJUoAgGC/F+0UPkzYcMvBmzAsfow4QcTJFCf0ZbuAwFuKGhBWzof5QjyREzRgneu1lkCVYyW4brg6b2ubtRMTmJ05t2KRFiz4HqCAt0fVsBUbnyA8+R3UzTlSVSad3/DiY1TA1qChAdcNEXNz964VNPmkVMX6KZr+eyGw47+H395dPtkb8Ql5BctcfT7+VbnHCXz1lTeHAREwxlqzZ45llhYGjQkGYWSYHUdShCNwd6FCndmFmF4VpuaQUutVyF1a+nWo4EoMSsiZZy4uWKEEByDmUXQh4QabZSdtEJwE6ly1HI6syUYVBkHGttwESN4QHnAWNFkJBtjxIyUkVEJ55Uo91pNbPgDpeANGRt4AW3/u7dfehF19RYFQbC7y35tbxRkeAnRmxlOcZOIJJ1tu/lmknwZ1B+OgeYalAJ9eESpCcN7Z2Wii9eyZVKIbhAAAIfkEBQoAGwAsBwAEAM4ACwAABf/gVmwk+RSZUZIEIYzrKailYhfPuj0npLcvHW/2UwRjB+KKYISVDknfUoETPgTSWtOKLR6fPa+TBFVqv6YkrUQIoElrku2QJQ0SBPrUUN8k8HpagSV/eXUKhjqFB0V8igkbgxstkn6AjX1/kXUtjiuajHuZkJKUo3k6c6eSBxIpKzYQDSsREQwQCDoSErK0tn27F70ltQ19G7sQDr7GuhLCs8QRzSvByr63Osi8y9LUJdbRJMXAEgrX0tnVyd3j0+XC7RvkuVp1GPgBDDoTEzafCTDo4+dPwqOBK/r9A4iQRD8AC0ssWNDQoT8FOgZQ3FfiYUQSCTYSVGAQIAGOFiH/lpQoMmHBjC0tvmSIcoPHlSQqVKgzcRc/C5Me+XTJACNACRV+FhWaNKGFpQAxNO04ASrLoVStggw41eFTo4QSICWo1dJYomDtLMDqdUNaP2tx2qz6tufKBXfeepy4YmKAnxAXRCVAEKK2iYRdAiAsWC3FwgQag/QL+REGwhNKaNAQeIXGBYmpLt4g+c5jp4slk0AMQHHorZchq54IuvXqteIsknx08ifbDXlzz/09YICCmsPlFj/uW3kCBcInWPhdCLlCuX8qzt3dF3fzjMaRW5iuPPx3muA2XJC7uUHduDrav/1ztqMF95a7zi37RyrZ+WLp109ZdsWnAX7d/Saf0Q4FrrBggnJJxx9XPyF41UoGJGNbSpENJlt3tbkGU4grWLAYPiyRWIKJBGAwWGb2ncigig5BhKJjBGw4V2qesSbiJ4gB1mKPNEonIyE+khAAAtr1I0E9V0VH3owSRDcBBlCqpd14T+pQQUwWdZmglMSBadOUK3zJnEtYztjkdFmStsCaVIkZVm8u2bmVdi0o9d5vAypw4wZqIkABWi6GJdWhWQmaqJy7MOqVe4P2ZCiiEEq6n6MXajoepY9aqmmgj1qyKKYlaPTkqPcJGlUFkoYAACH5BAUKABsALAcABADOAAsAAAX/4GZw2WZuT5oZp0kQQtGiTwbNilI883MIt1NOJ2v5gDhY8ZRCtgjE3sMpjBp/wepOSjXlttfuRqHsYWfQ2OxwPr0CAtyyesiaBgNC/akw2DcJCQp7dH+Bg3ZvhgkbhF56i5BPBAGLjUl+M4cHmJacLWSZLYF6nZqCjhsvqYAJBDxGcTMSChANLRERDBAIOBe2uLp/tL+3J7kNfxu0wMcRybPMDsHQoBK/0867ONfNJsh/Ct3Z38LRteQbudugORDpyCwtEuPUBr1CAp8nCRgBDDMmTMgxaoE/gC0mACBY8F/AgRI0HUwoUJxEhxQZnjCI0YTAhQpmcER4omLIFgMW/3Tc8NFiQ5IeIYpUCZPlwoijJpasiPOEBFn8XFUIaGHMKQlDKTI4GRTpw6VHk5a0ALWg04RUmZpIsODqzqobu0qNCdbEArFPtW5YMMArWbVs3bKcUHZDSrkC6xqcgUflw4V7T6QM8FfBgoIqJ1AEQGADBsFnCS9Wu9bv5MMoFxAAsJgA5q1nN3cObDel6J2MSePJwxm155mnY6ZuwVbzX8+P7wzILTiPsZ1y8RD4HdPlxrbE5wb3TXS5guQCnde0YEF68553uk6vjr308OunCGy3TpEe3zy0McjVoKEBXIPdJ2SNmpa+UrWB8NLFn0B/3fxjbcCee/adMOB767XHX72CBFoVoHz/oWUCBhy1Jttr/IRm4VyzZajZhh9hCNqHnc1QWWwcEsBbZSS6ZqKGJfKlmWKufZZdY6Nl1qIJ7MnkIYohvrhjBTpNZd5L4NGm3QzUSYBPWBLUJN+Rx0kAHXcyRpnkRkV6hKWSK7H0ZVjPbWnWAgqM9+RWXSrXXT8dKVABAmvOFaF/CqwYFwIU3LfiXXz66WCfX+U5qKBQVkDoW3qqF2iheoq1qJ2GVvkoo0qqNyl17kVKCwUBhAAAIfkEBQoAGwAsBwAEAM4ACwAABf/gtj1HJogomRmoSBBC0Y6PAM2KUpDtUd4tgk7W++GGs0cNiFLAiKnlMTbz2WbCXVLacmqL12auABVZmSIvD3XGQUzsgOGBIxjQm8GAcAgEFXczCQkKfV0bgS2DhXWJKIuGKC+OIpCNeJB+TXyYhAeXgp5YgJ0bn3+UG4N8mi52nTpxEpEiErYQDi0REQwQCC22F7i6vHjBwyi7Dca3ucnFMxvNxMs4EgrIIsoswNPP1d3CztrQ4dkb29HX5+nm4+jFCigBGL6hBAwzExM5ign1+VDsA9DPX4CAAvnJU7TgoD5+Eu4hFLFPQUR/+FoMtBjKoUaIHSduvPgoQcaPBVH/LGgocgLBhSgGsNT3sgUCbPcq6LOwAWalBLZ2MvCpCqjOj0NDSTgq0EJSfxiYUpzwtGRUoUQHLX1YtdKCoEizGuVKdMEAsAm7Ft2q0QKhGQEOkBQhs9VUAAQErbS7YeOGBS1k5m1LcIbMhg/xroxZd6diwHT1DE5IEPJPDJPvErBcFDOAj5UZLsjc1wJew41baNBQGCqBz5QVcF7wAFaDnWhFmFVw+2PuPAt44577d4ACkRYs/JbcOyHHwMGRKyfOfLhE64H3NKc4HTWB7aV/D/KYcPkJqFL7UhV7NWyo9k0blP1KfLV8pents8+v4T7D3/rNsBKA/c1HoH8qGVdfxIH4zRCgP3RY9RpopCWw0oSUbWbYaLBxp5hrHZb24SMXhugXdKM5qAE/GICo2ooKtEgihxRuSCMKq7G4IYYCtdbCV6TlGCNUAn4lHQa/GDSRekhe92KTSmJX0kkoJAcliRIcmSSWS07Q3X9dTiCeSUsmNyaVIli5pW7Gdfllgj5h8NWapTVQ4T/pJSefjHQFhwAFYfH5F5CApjWkSnMWyp2dgg4ogaIb2FchfZDqSdpuj7aQHCGNJhooepVasCeon2IZQAgAIfkEBQoAGwAsBwAEAM4ACwAABf/gJm5GJozjkxmoGAhFuz0CJCtF3B71vekonq1F+Mlow5GiCEz1WgrY8Yki5GTCm+AxTSqNO6poeQ17NwrWASWBcImG82ZAWMPlCYUdlcbrb3EyeXtKgS2DMgSGKIhQiyONfI8ikSMEAX6EIoqZgHhuKAsLBxc3BA4tEQwGCC0SFxCoKKoQrXwEsakNcmi5s7s3sLIjtDIbwqmrwb7EDLVQyL9yr8wiEcCu0cTYbNrWzqG7paELAQwyABtFjBjmLRPpEoIK5+/qghD1KABLMuX6IyYokHco3zt+61AMcPeu3yGGAfkRDAUx4L2HADfA2zAREr2Dx1AwaDevggwLeUr/nmSgQOU7li5RTIB5SIHJly1r3kRhgWYoCTsD+hwxYENQET1zMrIpY6bSEQuMNh1KCejUpxLoYDiE4akIeF5FLEgYsWVUomOb8hPUNZ3MtVwVuC3rL+1BBWfREphwkEBeSnbf4j0UOCJZSm37imJU+CuAw/LCJgjQ4CSaeRk1DsTc9HLNzHzD/rPcUcRC0J4VVkTKkXPD0hsmV37tmmfrz003o2Dhz2qLpDGFSmaKM/jXBqJTB0TuT/nx4Uc1Mm8RFfaE6Uuja8DuMbpT4xu2hxUzZ+xcx379ETiv8fFW6uvv/o3dWKPA93rZg52/oRx7jYf1Zx466Sk04FsFQnKggUwK4IeYXH05GFti7xAgYUrUffQbBraoBhqHtQUEIm7vjEjRhx1CQtlJJhK1mkYtUrLihimKRUBmFsQY24wy6ZgHioL0FhZw1PkmE0sXSqAABThdaBOTRzZYJAJQCidhVEs2SZ1RVT535QZUaqmQBGFG6WQFXWqE5CFkpunUhRCEAAAh+QQFCgAcACwHAAQAzgALAAAF/yAnclApFKP4PJmRErCQqiw0K8Q5H4dgv4oCKsXz3QjC2cqYUgSHIl7vJyI4k8TpzKrLMkdXKO0rwmFHRSrH+dz1XKMSYVbBcA63gCEwSyTuWwp7fQkEeE2CfCl+gE0Eg4sJCocjMJAjfpN5lyKZlFWCahyMn2t6op43oYSNlauRrWWnMxgSlwwNCQYIKRK+EA4pEREMELxgTsDCxKK+F8ojww3Gvb/B0cy01svT2s/XItLN29jF3tDhzMdl5Ond1d/c4zjgHA4OAweKHMM43gwpJghUIGHRAgwBAI4QCIAgrQUJZwwsaDDiwokPLV50WFGhCIYcMUH0+HFCyE4jA/9i7CjRJEWRGjmAfIky5sxoEwoRUlChJYc5Bn35VLCzp8o1RVsyIBrU6MWlfRYIVQk1Ek+lTDFJclqyqkgJXGXmzIoSLNaoZqlmbbAhAoGDIiG2bMjBDkq5KhsuMIj3IoC3fAkAyAs0rmDCdbX2Lak38FzAhgf7fWuXw4DLhycnFnF5QGbGhe+GlqmhsQi2U2E2aJm65mqVrUdJer2RpmwFJEnHxky75O4BCnqL/U1AuMDYmXJbsPA7OGvbl3HPWN7cuMsREhgsvh12goYGZG/b1vA9fKaw5MGj7T7Bq4iDsdOHXwC/pSS048vPoB9fv1X0/n3VXYDvSZWfeiMgABGvVwnQ95lYf+1l2GMSikahYxL9FdUCozE0WoMcSraQhoGJCOGHDT7o3XUTZgiZaBMQVuEoDpooE4kjTCMKBimlsFwtGeV2nG0NxjScbTwaOWRUSk4AJEuwrePac0xahxxEVhKJJZUVWYeBlCKAyWN/CH51IIoEUaBWZTT6ouZTCrDZoAQIvNlVnFZJYKdYS8kpVQV7egcem/DVuWZQeh76laEjLDdoCvTVsqejeKYQAgA7";
    private Jugador jugadorSeleccionado = new Jugador();
    private ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasCabecera;
    private ArrayList<ClaseEstadisticaNormalTotales> listaEstadisticasJugador;
    private String error="";
    private HashMap<String, String> atributosMap = new HashMap<>();
    
    // TAB PROGRESIÓN DEL JUGADOR
    private ArrayList<String> listaSeleccionTemporadasProgresion = new ArrayList<>();
    private List<SelectItem> selectMenuTemporadas;
    private String atributoProgresion;
    private String tiempoPartidoProgresion;
    private String ubicacionProgresion;
    private String resultadoProgresion;
    private String cuandoProgresion;
    private String invisibleEstadisticasGlobales="invisible";
    private String invisibleTabs="invisible";
    private boolean invisibleFindJugador=true;
    private List<SelectItem> selectMenuAtributo;
    private List<SelectItem> selectMenuTiempoPartido;
    private List<SelectItem> selectMenuUbicacion;
    private List<SelectItem> selectMenuResultado;
    private List<SelectItem> selectMenuCuando;
    private ArrayList<Double> listaProgresiones = new ArrayList<>();
    private ArrayList<String> listaProgresionesDobles = new ArrayList<>();
    private ArrayList<ControllerPartido> listaPartidosLocal = new ArrayList<>();
    private ArrayList<ControllerPartido> listaPartidosVisitante = new ArrayList<>();
    
    //  TAB ESTADISTICAS CONTRA EQUIPOS
    private int partidosTotales=0;
    private int partidosLocal=0;
    private int partidosVisitante=0;
    private int partidosLocalRegular=0;
    private int partidosVisitanteRegular=0;
    private int partidosLocalPlayoff=0;
    private int partidosVisitantePlayoff=0;
    
    private ArrayList<TablaTirosJugador> tablaTirosJugador = new ArrayList<>(); 
    private TablaTirosJugador localRegularTirosJugador;
    private TablaTirosJugador localPlayoffTirosJugador;
    private TablaTirosJugador visitanteRegularTirosJugador;
    private TablaTirosJugador visitantePlayoffTirosJugador;
    
    private ControllerEstadisticaNormal partidosDeLocalRegular = new  ControllerEstadisticaNormal();
    private ArrayList<ControllerEstadisticaNormal> listaPartidosDeLocalRegular = new  ArrayList<>();
    
    private ControllerEstadisticaNormal partidosDeLocalPlayoff = new  ControllerEstadisticaNormal();
    private ArrayList<ControllerEstadisticaNormal> listaPartidosDeLocalPlayoff= new  ArrayList<>();
    
    private ControllerEstadisticaNormal partidosDeVisitanteRegular = new  ControllerEstadisticaNormal();
    private ArrayList<ControllerEstadisticaNormal> listaPartidosDeVisitanteRegular = new  ArrayList<>();
    
    private ControllerEstadisticaNormal partidosDeVisitantePlayoff = new  ControllerEstadisticaNormal();
    private ArrayList<ControllerEstadisticaNormal> listaPartidosDeVisitantePlayoff = new  ArrayList<>();
    
    
    private ArrayList<ControllerEstadisticaNormal> listaPartidosLocalVisitante = new ArrayList<>();

    
    private LineChartModel graficoLineas = new LineChartModel();
    private BarChartModel graficoBarras = new BarChartModel();
    private ListaEquipos equipoSeleccionado;
    
    private String classTiro="";
    private String make="●";
    private String miss="×";
    private UIComponent found;
    
    private JugadorDataImpl data=new JugadorDataImpl();
    private JugadorDataImpl dataPlayoff=new JugadorDataImpl();
    private JugadorControllerUtilidades jugadorUtil = new JugadorControllerUtilidades();
    
    
    private String listaCuartosElegidos;
    private List<String> listaCuartos= new ArrayList<>();
    
    private String listaDentroElegidos;
    private List<String> listaDentro = new ArrayList<>();
    
    private String listaDistanciasElegidas;
    private List<String> listaDistancias = new ArrayList<>();
    
    private String listaTiempoRestanteElegido;
    private List<String> listaTiempoRestante = new ArrayList<>();
    
    private String listaTipoCanastaElegida;
    private List<String> listaTipoCanasta = new ArrayList<>();
    
    private String listaSituacionPartidoElegido;
    private List<String> listaSituacionPartido = new ArrayList<>();
    
    private String listaPonerseDelanteElegido;
    private List<String> listaPonerseDelante = new ArrayList<>();
    
    private String[] listaTemporadasElegidas;
    
    private String cuartoElegido;
    private String localVisitante;
    
    
    private JugadorTirosContraEquipo localRegular = new JugadorTirosContraEquipo();
    private JugadorTirosContraEquipo visitanteRegular = new JugadorTirosContraEquipo();
    private JugadorTirosContraEquipo localPlayoff = new JugadorTirosContraEquipo();
    private JugadorTirosContraEquipo visitantePlayoff = new JugadorTirosContraEquipo();
    
    private ArrayList<ControllerPartidoJugador> listaPartidosTemporadaRegular;
    private ArrayList<ControllerPartidoJugador> listaPartidosPlayOff;
    private ClaseEstadisticaNormalTotales mediaTemporadaRegular=new ClaseEstadisticaNormalTotales();
    private ClaseEstadisticaNormalTotales mediaPlayoff=new ClaseEstadisticaNormalTotales();
    
    //TAB 3
    private String partidosTemporada="";
    private String dosDentro="2pt Dentro";
    private String dosFuera="2pt Fuera";
    private String tresDentro="3pt Dentro";
    private String tresFuera="3pt Fuera";
    
    @SuppressWarnings("unchecked")
	@PostConstruct
    public void init(){
        atributosMap = Utilidades.devolverHashMapAtributos();
        setInvisibleEstadisticasGlobales("invisible"); //Para cuando funcione todo invisible //Probando statsJugador
        setInvisibleTabs("invisible"); //Para cuando funcione todo invisible //Probando ""
        setInvisibleFindJugador(true);
        rellenarMenus();
    }
    
    private void rellenarMenus(){
       listaCuartos = jugadorUtil.devolverMenuCuartos();
       listaDentro = jugadorUtil.devolverMenuDentro();
       listaDistancias = jugadorUtil.devolverMenuDistancia();
       listaTiempoRestante = jugadorUtil.devolverMenuTiempoRestante();
       listaTipoCanasta = jugadorUtil.devolverMenuTipoCanasta();
       listaSituacionPartido = jugadorUtil.devolverSituacionesPartido();
       listaPonerseDelante = jugadorUtil.devolverPonerseDelante();
    }
    
    /**
     * Función que va buscando los jugadores dependiendo de lo que nos escriban
     * en el input del nombre. Segun vayamos escribiendo nos ira cribando
     * @throws IOException 
     */
    public void btnBuscarClick() throws IOException{
        
        //setVerEstadisticasJugador(false);        
        error="";
        buscarFiltro(nombreJugador.toUpperCase());
    }
    
    /**
     * Funcion que nos devuelve la lista de jugadores al buscar por nombre y apellido
     * Si esta vacio el campo, no rellenamos nada de los jugadores
     * @param nombre
     * @throws IOException 
     */
    private void buscarFiltro(String nombre) throws IOException{   
        if(!"".equals(nombre)){
            listaJugadores = jugadorUtil.devolverJugadores(nombre);
        }else{
            listaJugadores = new ArrayList<Jugador>();
        }
    }
    
    /**
     * Recargamos las estadisticas globales del jugador de toda su carrera
     */
    public void verEstadisticasJugadorTotales(){
        if(!"".equals(jugadorSeleccionado.getCodigo())){
            partidosDeLocalPlayoff = new ControllerEstadisticaNormal();
            partidosDeLocalRegular = new ControllerEstadisticaNormal();
            partidosDeVisitantePlayoff = new ControllerEstadisticaNormal();
            partidosDeVisitanteRegular = new ControllerEstadisticaNormal();
            listaProgresiones.clear();
            listaPartidosLocalVisitante.clear();
            listaSeleccionTemporadasProgresion.clear();
            listaEstadisticasJugador = new ArrayList<>();            
            listaEstadisticasCabecera = new ArrayList<>();
            conectamosBaseDatos(0);
            setInvisibleEstadisticasGlobales("statsJugador");
            setInvisibleTabs("");
            setInvisibleFindJugador(false);
            borrarDatosContraEquipo();
        }
    }
    
    /**
     * Función que nos busca las estadisticas por temporada de un jugador.
     */
    public void verEstadisticas(){
        if(listaSeleccionTemporadasProgresion.isEmpty()){
            error = "Debe seleccionar al menos una temporada";
            listaEstadisticasJugador.clear();
        }else{
            conectamosBaseDatos(1);
            error ="Estamos mirando las temporadas: "+listaSeleccionTemporadasProgresion;
        }
    }
    
    /**
     * Hacemos las busquedas de los datos de la pestaña de 'Estadisticas por Temporada'
     * Si objeto = 0, vamos a recuperar las estadisticas globales de la carrera del jugador.
     * Las que saldran en la cabecera
     * Si objeto = 1, vamos a recuperar las estadisticas de las temporadas clicadas.
     * Saldran en la parte de abajo
     * @param objeto 
     */
    private void conectamosBaseDatos(int objeto){
        
        if(objeto == 0){
            listaEstadisticasCabecera = new ArrayList<>();
            listaEstadisticasCabecera = data.devolverListaEstadisticasCabecera(jugadorSeleccionado.getCodigo());
        }else if(objeto == 1){
            listaEstadisticasJugador = new ArrayList<>();
            listaEstadisticasJugador = data.devolverListaEstadisticasJugador(jugadorSeleccionado.getCodigo(),listaSeleccionTemporadasProgresion);
            if(listaEstadisticasJugador.isEmpty()){
                listaEstadisticasJugador.clear();
            }
        }
    }
    
     public void recogerPartidosTemporada(){
         listaPartidosTemporadaRegular = data.devolverListaPartidosTemporada(partidosTemporada,jugadorSeleccionado.getCodigo(),false);
         listaPartidosPlayOff = dataPlayoff.devolverListaPartidosTemporada(partidosTemporada,jugadorSeleccionado.getCodigo(),true);
         
         int partidosJugados=0;
         int partidosJugadosPlayoff=0;
         int masMenos=0;
         int masMenosPlayoff=0;
         for(ControllerPartidoJugador partido:listaPartidosTemporadaRegular) {
        	 System.out.println(partido.getFecha());
        	 System.out.println("Marmo: hay que revisar, porque igual el jugador no ha jugado y esto esta a null");
        	 if(null!=partido.getBoxscore().getMasMenos()){
            	 masMenos += partido.getBoxscore().getMasMenos();
            	 partidosJugados++;
        	 }
         }
         for(ControllerPartidoJugador partido:listaPartidosPlayOff) {
        	 if(null!=partido.getBoxscore().getMasMenos()){
            	 masMenosPlayoff += partido.getBoxscore().getMasMenos();
            	 partidosJugadosPlayoff++;
        	 }
         }
         
         mediaTemporadaRegular = data.devolverMediaTemporada(jugadorSeleccionado.getCodigo(), partidosTemporada, "regular");
         if(partidosJugados != 0) {
             mediaTemporadaRegular.setMasMenos(masMenos/partidosJugados);
         }
         
         mediaPlayoff = data.devolverMediaTemporada(jugadorSeleccionado.getCodigo(), partidosTemporada, "playoff");
         if(partidosJugadosPlayoff!=0) {
             mediaPlayoff.setMasMenos(masMenosPlayoff/partidosJugadosPlayoff);
         }
    }

    /**
     * Recogemos las estadisticas que tiene un jugador contra un equipo en concreto
     */
    @SuppressWarnings({ "resource", "unchecked", "rawtypes" })
	public void verEstadisticasContraEquipos(){
        
        MongoClient mongo = null;
        mongo = new MongoClient(host,puertoHost);
		
        if(mongo!=null) {
                borrarDatosContraEquipo();
                ArrayList<BasicDBObject> listaTirosLocal = null;
                localRegularTirosJugador = new TablaTirosJugador();
                localPlayoffTirosJugador = new TablaTirosJugador();
                visitanteRegularTirosJugador = new TablaTirosJugador();
                visitantePlayoffTirosJugador = new TablaTirosJugador();
                        
                @SuppressWarnings("deprecation")
				DB db = mongo.getDB(baseDatos);
                DBCursor cursor;

                for(String temporada:listaTemporadasElegidas){
                        
                            
                    DBCollection collection =db.getCollection(temporada);

                    BasicDBObject allQuery = new BasicDBObject();
                    BasicDBObject fields = new BasicDBObject();
                    List<BasicDBObject> obj = new ArrayList<>();

    //          EL JUGADOR COMO LOCAL ***************************************************************

                    obj.add(new BasicDBObject("equipoLocal.jugadores.id", jugadorSeleccionado.getCodigo()));
                    if(null!=equipoSeleccionado){
                        obj.add(new BasicDBObject("equipoVisitante.nombreAbreviado", equipoSeleccionado.getAbre()));
                    }
                    allQuery.put("$and", obj);

                    //allQuery.put("playOff", false); // SI QUEREMOS PLAYOFF

                    fields.put("_id", 0);

                    fields.put("equipoLocal.jugadores.id", 1);
                    fields.put("playOff", 1);
                    cuartoElegido = jugadorUtil.devolverCuarto(listaCuartosElegidos);
                    fields.put("equipoLocal.jugadores."+cuartoElegido, 1);
                    fields.put("equipoLocal.jugadores.listaTiros", 1);
                    fields.put("dia",1);
                    fields.put("mes",1);
                    fields.put("year",1);
                    fields.put("equipoVisitante.nombre",1);
                    fields.put("equipoLocal.nombre",1);

                    cursor = collection.find(allQuery,fields);

                    while(cursor.hasNext()) {
                        setLocalVisitante("LOCAL");
                        DBObject get = cursor.next();
                        String dia = (String) get.get("dia");
                        String mes = (String) get.get("mes");
                        String year = (String) get.get("year");
                        String equipo = (String) ((BasicDBObject) get.get("equipoLocal")).get("nombre");
                        String rival = (String) ((BasicDBObject) get.get("equipoVisitante")).get("nombre");
                        ArrayList<BasicDBObject> listaLocales =(ArrayList<BasicDBObject>) ((BasicDBObject) get.get("equipoLocal")).get("jugadores");

                        for(int i = 0;i<listaLocales.size();i++){
                            if(listaLocales.get(i).get("id").equals(jugadorSeleccionado.getCodigo())){
                                Map ver = (Map)listaLocales.get(i).toMap().get(cuartoElegido);
                                if(ver!=null){
                                    if(ver.size()>1){
                                        partidosTotales++;
                                        partidosLocal++;
                                        if((boolean)get.get("playOff")){
                                            partidosLocalPlayoff++;
                                            partidosDeLocalPlayoff = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaLocales.get(i).toMap(),partidosDeLocalPlayoff,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaLocales.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTirosContraEquipo((Map)listaTirosLocal.get(j).toMap(),dia,mes,year,rival,jugadorSeleccionado.getNombre(),equipo),localPlayoffTirosJugador.getListaTiros(),2);
                                                }
                                            }
                                        }else{
                                            partidosLocalRegular++;
                                            partidosDeLocalRegular = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaLocales.get(i).toMap(),partidosDeLocalRegular,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaLocales.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTirosContraEquipo((Map)listaTirosLocal.get(j).toMap(),dia,mes,year,rival,jugadorSeleccionado.getNombre(),equipo),localRegularTirosJugador.getListaTiros(),1);
                                                }
                                            }   
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    partidosDeLocalPlayoff.calcularTirosDosPuntos();
                    partidosDeLocalPlayoff.setUbicacion("");
                    partidosDeLocalPlayoff.setCuando("PlayOff");
                    partidosDeLocalPlayoff.setPartidosJugados(partidosLocalPlayoff);
                    partidosDeLocalPlayoff.setSituacionTiro("Estadisticas Totales del Jugador");
                    
                    //listaPartidosDeLocalPlayoff.add(partidosDeLocalPlayoff);
                    localPlayoffTirosJugador.getListadiferentesStats().add(partidosDeLocalPlayoff);

                    partidosDeLocalRegular.calcularTirosDosPuntos();
                    partidosDeLocalRegular.setUbicacion("Local");
                    partidosDeLocalRegular.setCuando("Regular");
                    partidosDeLocalRegular.setPartidosJugados(partidosLocalRegular);
                    partidosDeLocalRegular.setSituacionTiro("Estadisticas Totales del Jugador");
                    
                   // listaPartidosDeLocalRegular.add(partidosDeLocalRegular);
                    localRegularTirosJugador.getListadiferentesStats().add(partidosDeLocalRegular);

                    if(partidosLocalRegular!=0){
                        listaPartidosLocalVisitante.add(partidosDeLocalRegular);
                    }
                    if(partidosLocalPlayoff!=0){
                        listaPartidosLocalVisitante.add(partidosDeLocalPlayoff);
                    }


    //          EL JUGADOR COMO VISITANTE ***************************************************************

                    allQuery = new BasicDBObject();
                    fields = new BasicDBObject();
                    obj = new ArrayList<>();

                    obj.add(new BasicDBObject("equipoVisitante.jugadores.id", jugadorSeleccionado.getCodigo()));
                    if(null!=equipoSeleccionado){
                        obj.add(new BasicDBObject("equipoLocal.nombreAbreviado", equipoSeleccionado.getAbre()));
                    }
                    allQuery.put("$and", obj);

                    //allQuery.put("playOff", false); // SI QUEREMOS PLAYOFF

                    fields.put("_id", 0);

                    fields.put("equipoVisitante.jugadores.id", 1);
                    fields.put("playOff", 1);
                    fields.put("equipoVisitante.jugadores."+cuartoElegido, 1);
                    fields.put("equipoVisitante.jugadores.listaTiros", 1);
                    fields.put("dia",1);
                    fields.put("mes",1);
                    fields.put("year",1);
                    fields.put("equipoVisitante.nombre",1);
                    fields.put("equipoLocal.nombre",1);

                    cursor = collection.find(allQuery,fields);

                    while(cursor.hasNext()) {
                        setLocalVisitante("VISITANTE");
                        DBObject get = cursor.next();
                        String dia = (String) get.get("dia");
                        String mes = (String) get.get("mes");
                        String year = (String) get.get("year");
                        String equipo = (String) ((BasicDBObject) get.get("equipoVisitante")).get("nombre");
                        String rival = (String) ((BasicDBObject) get.get("equipoLocal")).get("nombre");
                        
                        ArrayList<BasicDBObject> listaVisitantes =
                                        (ArrayList<BasicDBObject>) ((BasicDBObject) get.get("equipoVisitante")).get("jugadores");
                        for(int i = 0;i<listaVisitantes.size();i++){
                            if(listaVisitantes.get(i).get("id").equals(jugadorSeleccionado.getCodigo())){
                                Map ver = (Map)listaVisitantes.get(i).toMap().get(cuartoElegido);
                                if(ver!=null){
                                    if(ver.size()>1){
                                        partidosTotales++;
                                        partidosVisitante++;
                                        if((boolean)get.get("playOff")){
                                            partidosVisitantePlayoff++;
                                            partidosDeVisitantePlayoff = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaVisitantes.get(i).toMap(),partidosDeVisitantePlayoff,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaVisitantes.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                	insertarTiro(MapJavaMongo.devolverTirosContraEquipo((Map)listaTirosLocal.get(j).toMap(),dia,mes,year,rival,jugadorSeleccionado.getNombre(),equipo),visitantePlayoffTirosJugador.getListaTiros(),4);
                                                }
                                            }
                                        }else{
                                            partidosVisitanteRegular++;
                                            partidosDeVisitanteRegular = MapJavaMongo.devolverEstadisticaJugadorEquipo(listaVisitantes.get(i).toMap(),partidosDeVisitanteRegular,cuartoElegido);

                                            listaTirosLocal = (ArrayList<BasicDBObject>) listaVisitantes.get(i).get("listaTiros");
                                            if(listaTirosLocal!=null){
                                                for(int j=0;j<listaTirosLocal.size();j++){
                                                    insertarTiro(MapJavaMongo.devolverTirosContraEquipo((Map)listaTirosLocal.get(j).toMap(),dia,mes,year,rival,jugadorSeleccionado.getNombre(),equipo),visitanteRegularTirosJugador.getListaTiros(),3);
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    partidosDeVisitantePlayoff.calcularTirosDosPuntos();
                    partidosDeVisitantePlayoff.setUbicacion("");
                    partidosDeVisitantePlayoff.setCuando("PlayOff");
                    partidosDeVisitantePlayoff.setPartidosJugados(partidosVisitantePlayoff);
                    partidosDeVisitantePlayoff.setSituacionTiro("Estadisticas Totales del Jugador");
                    
                    //listaPartidosDeVisitantePlayoff.add(partidosDeVisitantePlayoff);
                    visitantePlayoffTirosJugador.getListadiferentesStats().add(partidosDeVisitantePlayoff);

                    partidosDeVisitanteRegular.calcularTirosDosPuntos();
                    partidosDeVisitanteRegular.setUbicacion("Visitante");
                    partidosDeVisitanteRegular.setCuando("Regular");
                    partidosDeVisitanteRegular.setPartidosJugados(partidosVisitanteRegular);
                    partidosDeVisitanteRegular.setSituacionTiro("Estadisticas Totales del Jugador");
                    
                    //listaPartidosDeVisitanteRegular.add(partidosDeVisitanteRegular);
                    visitanteRegularTirosJugador.getListadiferentesStats().add(partidosDeVisitanteRegular);

                    if(partidosVisitanteRegular!=0){
                        listaPartidosLocalVisitante.add(partidosDeVisitanteRegular);
                    }

                    if(partidosVisitantePlayoff!=0){
                        listaPartidosLocalVisitante.add(partidosDeVisitantePlayoff);
                    }
                        
                }
        }
        
        localRegular.calcularTirosCampo();
        localPlayoff.calcularTirosCampo();
        visitanteRegular.calcularTirosCampo();
        visitantePlayoff.calcularTirosCampo();
        
        localRegularTirosJugador.setTitulo("PARTIDOS COMO LOCAL EN TEMPORADA REGULAR");
        localPlayoffTirosJugador.setTitulo("PARTIDOS COMO LOCAL EN PLAYOFF");
        visitanteRegularTirosJugador.setTitulo("PARTIDOS COMO VISITANTE EN TEMPORADA REGULAR");
        visitantePlayoffTirosJugador.setTitulo("PARTIDOS COMO VISITANTE EN PLAYOFF");
        
        localRegularTirosJugador.getListadiferentesStats().add(new ControllerEstadisticaNormal(localRegular));
        localPlayoffTirosJugador.getListadiferentesStats().add(new ControllerEstadisticaNormal(localPlayoff));
        visitanteRegularTirosJugador.getListadiferentesStats().add(new ControllerEstadisticaNormal(visitanteRegular));
        visitantePlayoffTirosJugador.getListadiferentesStats().add(new ControllerEstadisticaNormal(visitantePlayoff));
        
        localRegularTirosJugador.rellenarGraficaDonutGenerico(localRegularTirosJugador.getDonutModel1(),localRegular.getDosPuntosMetidos(),localRegular.getDosPuntosFallados(),dosFuera,dosDentro);
        localRegularTirosJugador.rellenarGraficaDonutGenerico(localRegularTirosJugador.getDonutModel2(),localRegular.getTresPuntosMetidos(),localRegular.getTresPuntosFallados(),tresFuera,tresDentro);
        localRegularTirosJugador.rellenarGraficaDonutGenerico(localRegularTirosJugador.getDonutModel3(),localRegular.getTirosCampoMetidos(),localRegular.getTirosCampoFallados(),"TR Local Fuera","TR Local Dentro");
        
        localPlayoffTirosJugador.rellenarGraficaDonutGenerico(localPlayoffTirosJugador.getDonutModel1(),localPlayoff.getDosPuntosMetidos(),localPlayoff.getDosPuntosFallados(),dosFuera,dosDentro);
        localPlayoffTirosJugador.rellenarGraficaDonutGenerico(localPlayoffTirosJugador.getDonutModel2(),localPlayoff.getTresPuntosMetidos(),localPlayoff.getTresPuntosFallados(),tresFuera,tresDentro);
        localPlayoffTirosJugador.rellenarGraficaDonutGenerico(localPlayoffTirosJugador.getDonutModel3(),localPlayoff.getTirosCampoMetidos(),localPlayoff.getTirosCampoFallados(),"PO Local Fuera","PO Local Dentro");
        
        visitanteRegularTirosJugador.rellenarGraficaDonutGenerico(visitanteRegularTirosJugador.getDonutModel1(),visitanteRegular.getDosPuntosMetidos(),visitanteRegular.getDosPuntosFallados(),dosFuera,dosDentro);
        visitanteRegularTirosJugador.rellenarGraficaDonutGenerico(visitanteRegularTirosJugador.getDonutModel2(),visitanteRegular.getTresPuntosMetidos(),visitanteRegular.getTresPuntosFallados(),tresFuera,tresDentro);
        visitanteRegularTirosJugador.rellenarGraficaDonutGenerico(visitanteRegularTirosJugador.getDonutModel3(),visitanteRegular.getTirosCampoMetidos(),visitanteRegular.getTirosCampoFallados(),"TR Visitante Fuera","TR Visitante Dentro");
        
        visitantePlayoffTirosJugador.rellenarGraficaDonutGenerico(visitantePlayoffTirosJugador.getDonutModel1(),visitantePlayoff.getDosPuntosMetidos(),visitantePlayoff.getDosPuntosFallados(),dosFuera,dosDentro);
        visitantePlayoffTirosJugador.rellenarGraficaDonutGenerico(visitantePlayoffTirosJugador.getDonutModel2(),visitantePlayoff.getTresPuntosMetidos(),visitantePlayoff.getTresPuntosFallados(),tresFuera,tresDentro);
        visitantePlayoffTirosJugador.rellenarGraficaDonutGenerico(visitantePlayoffTirosJugador.getDonutModel3(),visitantePlayoff.getTirosCampoMetidos(),visitantePlayoff.getTirosCampoFallados(),"PO Visitante Fuera","PO Visitante Dentro");
        
        localRegularTirosJugador.setPorcentaje1(localRegular.getDosPuntosMetidos(),localRegular.getDosPuntosFallados());
        localRegularTirosJugador.setPorcentaje2(localRegular.getTresPuntosMetidos(),localRegular.getTresPuntosFallados());
        localRegularTirosJugador.setPorcentaje3(localRegular.getTirosCampoMetidos(),localRegular.getTirosCampoFallados());
        
        localPlayoffTirosJugador.setPorcentaje1(localPlayoff.getDosPuntosMetidos(),localPlayoff.getDosPuntosFallados());
        localPlayoffTirosJugador.setPorcentaje2(localPlayoff.getTresPuntosMetidos(),localPlayoff.getTresPuntosFallados());
        localPlayoffTirosJugador.setPorcentaje3(localPlayoff.getTirosCampoMetidos(),localPlayoff.getTirosCampoFallados());
        
        visitanteRegularTirosJugador.setPorcentaje1(visitanteRegular.getDosPuntosMetidos(),visitanteRegular.getDosPuntosFallados());
        visitanteRegularTirosJugador.setPorcentaje2(visitanteRegular.getTresPuntosMetidos(),visitanteRegular.getTresPuntosFallados());
        visitanteRegularTirosJugador.setPorcentaje3(visitanteRegular.getTirosCampoMetidos(),visitanteRegular.getTirosCampoFallados());
        
        visitantePlayoffTirosJugador.setPorcentaje1(visitantePlayoff.getDosPuntosMetidos(),visitantePlayoff.getDosPuntosFallados());
        visitantePlayoffTirosJugador.setPorcentaje2(visitantePlayoff.getTresPuntosMetidos(),visitantePlayoff.getTresPuntosFallados());
        visitantePlayoffTirosJugador.setPorcentaje3(visitantePlayoff.getTirosCampoMetidos(),visitantePlayoff.getTirosCampoFallados());
        
        tablaTirosJugador.add(localRegularTirosJugador);
        tablaTirosJugador.add(localPlayoffTirosJugador);
        tablaTirosJugador.add(visitanteRegularTirosJugador);
        tablaTirosJugador.add(visitantePlayoffTirosJugador);
        
    }
    
    private void insertarTiro(ControllerTiros cartaTiro, ArrayList<ControllerTiros> listaTiros,int situacionTemporada) {
        
        // Miramos si dentro o fuera los tiros
        if(!comprobarCanastaDentroFuera(cartaTiro)){return;}
            
        // Miramos la distancia
        if(!comprobarDistanciaCanasta(cartaTiro)){return;}
            
        // Miramos el tiempo para acabar el cuarto
        if(!comprobarTiempoRestante(cartaTiro)){return;}
                
        // Miramos el tipo de canasta
        if(!comprobarTipoCanasta(cartaTiro)){return;}
        
        // Miramos en que cuarto es el tiro
        if(!comprobarCanastaCuarto(cartaTiro)){return;}
        
        // Miramos la situación del partido cuando se efectuo el tiro. Perdiendo - Empate - Ganando
        if(!comprobarSituacionTiro(cartaTiro)){return;}
        
        // Miramos situación de tiro, si al encestar se pone por delante el equipo
        if(!comprobarPonerseDelante(cartaTiro)){return;}
        
        switch(situacionTemporada){
            case 1:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        localRegular.sumarDosMetido();
                    }else{
                        localRegular.sumarDosFallado();
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        localRegular.sumarTresMetido();
                    }else{
                        localRegular.sumarTresFallado();
                    }
                }
                break;
            case 2:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        localPlayoff.sumarDosMetido();
                    }else{
                        localPlayoff.sumarDosFallado();
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        localPlayoff.sumarTresMetido();
                    }else{
                        localPlayoff.sumarTresFallado();
                    }
                }
                break;
            case 3:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        visitanteRegular.sumarDosMetido();
                    }else{
                        visitanteRegular.sumarDosFallado();
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        visitanteRegular.sumarTresMetido();
                    }else{
                        visitanteRegular.sumarTresFallado();
                    }
                }
                break;
            case 4:
                if(cartaTiro.getTipo().equals("2")){
                    if(cartaTiro.isDentro()){
                        visitantePlayoff.sumarDosMetido();
                    }else{
                        visitantePlayoff.sumarDosFallado();
                    }
                }else{
                    if(cartaTiro.isDentro()){
                        visitantePlayoff.sumarTresMetido();
                    }else{
                        visitantePlayoff.sumarTresFallado();
                    }
                }
                break;
            default:
            	   break;
        }
        listaTiros.add(cartaTiro);
            
    }
    
    private boolean comprobarCanastaCuarto(ControllerTiros cartaTiro){
        if(cuartoElegido.equals("boxscore")){
            return true;
        }else if(cuartoElegido.equals("cuarto1") && cartaTiro.getCuarto().contains("1st quarter")){
            return true;
        }else if(cuartoElegido.equals("cuarto2") && cartaTiro.getCuarto().contains("2nd quarter")){
            return true;
        }else if(cuartoElegido.equals("cuarto3") && cartaTiro.getCuarto().contains("3rd quarter")){
            return true;
        }else if(cuartoElegido.equals("cuarto4") && cartaTiro.getCuarto().contains("4th quarter")){
            return true;
        }else if(cuartoElegido.equals("over1") && cartaTiro.getCuarto().contains("1st over")){
            return true;
        }else if(cuartoElegido.equals("over2") && cartaTiro.getCuarto().contains("2nd over")){
            return true;
        }else if(cuartoElegido.equals("over3") && cartaTiro.getCuarto().contains("3rd over")){
            return true;
        }else if(cuartoElegido.equals("over4") && cartaTiro.getCuarto().contains("4th over")){
            return true;
        }
        return false;
    }
    
    private boolean comprobarDistanciaCanasta(ControllerTiros cartaTiro){
        if(listaDistanciasElegidas.equals("Todas las distancias")){
            return true;
        }
        double[] distancia = jugadorUtil.devolverDistanciaMetrosPies(listaDistanciasElegidas);
        if(cartaTiro.getDistancia() >=distancia[0] && cartaTiro.getDistancia() <=distancia[1]) {
        	return true;
        }else {
        	return false;
        }
    }
    
    
    private boolean comprobarCanastaDentroFuera(ControllerTiros cartaTiro) {
        if(listaDentroElegidos.equals("Todas las Canastas")){
            return true;
        }else if(listaDentroElegidos.equals("Dentro") && cartaTiro.isDentro()){
            return true;
        }else if(listaDentroElegidos.equals("Fuera") && !cartaTiro.isDentro()){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarTipoCanasta(ControllerTiros cartaTiro){
        if(listaTipoCanastaElegida.equals("Todos los tipos")){
            return true;
        }else if(listaTipoCanastaElegida.equals("2 Puntos") && cartaTiro.getTipo().equals("2")){
            return true;
        }else if(listaTipoCanastaElegida.equals("3 Puntos") && cartaTiro.getTipo().equals("3")){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarTiempoRestante(ControllerTiros cartaTiro){
        if(listaTiempoRestanteElegido.equals("Todo el cuarto")){
            return true;
        }
        int tiempoCuarto = jugadorUtil.devolverTiempoCuarto(listaTiempoRestanteElegido);
        if(cartaTiro.getTiempoRestante()<=tiempoCuarto){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean comprobarSituacionTiro(ControllerTiros cartaTiro){
        if(listaSituacionPartidoElegido.equals("Todas las situaciones")){
            return true;
        }
        
        // EMPATE - PERDIENDO - GANANDO
        
        switch(getListaSituacionPartidoElegido()) {
        case "Empate":
        	if(cartaTiro.getSituacionAntes().equals("EMPATE")) {return true;}
        	break;
        case "Ganando":
        	if(cartaTiro.getSituacionAntes().equals("GANANDO")) {return true;}
        	break;
        case "Perdiendo":
        	if(cartaTiro.getSituacionAntes().equals("PERDIENDO")) {return true;}
        	break;
        }
        return false;
    }
    
    private boolean comprobarPonerseDelante(ControllerTiros cartaTiro){
        if(listaPonerseDelanteElegido.equals("Todas las canastas")){
            return true;
        }
        
        if(cartaTiro.getSituacionDespues().equals("GANANDO")) {return true;}

        return false;
    }
    
    @SuppressWarnings("unused")
	private void doFind(FacesContext context, String clientId) {
        FacesContext.getCurrentInstance().getViewRoot().invokeOnComponent(context, clientId, new ContextCallback() {
            @Override
            public void invokeContextCallback(FacesContext context,
                    UIComponent component) {
                found = component;
            }
        });
    }
    
    public String comprobarTiro(Boolean dentro){
        if(dentro){
            return getMake();
        }else{
            return getMiss();
        }
    }
    
    public String comprobarTiroClase(Boolean dentro){
        if(dentro){
            return "make";
        }else{
            return "miss";
        }
    }

    public void borrarDatosContraEquipo() {
        partidosDeLocalPlayoff = new ControllerEstadisticaNormal();
        partidosDeLocalRegular = new ControllerEstadisticaNormal();
        partidosDeVisitantePlayoff = new ControllerEstadisticaNormal();
        partidosDeVisitanteRegular = new ControllerEstadisticaNormal();

        listaPartidosLocalVisitante.clear();
        
        
        listaPartidosDeVisitantePlayoff.clear();
        listaPartidosDeLocalRegular.clear();
        listaPartidosDeLocalPlayoff.clear();
        listaPartidosDeVisitanteRegular.clear();
        tablaTirosJugador.clear();
        
        partidosTotales=0;
        partidosLocal=0;
        partidosVisitante=0;
        partidosLocalRegular=0;
        partidosVisitanteRegular=0;
        partidosLocalPlayoff=0;
        partidosVisitantePlayoff=0;
        resetearContadores();
    }
    
    
    public ListaEquipos[] getEquipos(){
        return ListaEquipos.values();
    }
    
    public List<SelectItem> getSelectMenuAtributo() {
        if(selectMenuAtributo==null){
            selectMenuAtributo = Utilidades.devolverMenuAtributos();
        }
        return selectMenuAtributo;
    }

    public List<SelectItem> getSelectMenuTiempoPartido() {
        if(selectMenuTiempoPartido==null){
            selectMenuTiempoPartido=Utilidades.devolverMenuTiempoPartido();
        }
        return selectMenuTiempoPartido;
    }

    public List<SelectItem> getSelectMenuUbicacion() {
        if(selectMenuUbicacion==null){
            selectMenuUbicacion=Utilidades.devolverMenuUbicacion();
        }
        return selectMenuUbicacion;
    }

    public List<SelectItem> getSelectMenuResultado() {
        if(selectMenuResultado==null){
            selectMenuResultado=Utilidades.devolverMenuResultado();
        }
        return selectMenuResultado;
    }

    public List<SelectItem> getSelectMenuCuando() {
        if(selectMenuCuando==null){
            selectMenuCuando = Utilidades.devolverMenuCuando();
        }
        return selectMenuCuando;
    }

    public List<SelectItem> getSelectMenuTemporadas() {
        if(selectMenuTemporadas == null){
            selectMenuTemporadas = Utilidades.devolverTemporadas();
        }
        return selectMenuTemporadas;
    }
    
    private void resetearContadores() {
        localRegular = new JugadorTirosContraEquipo();
        localPlayoff = new JugadorTirosContraEquipo();
        visitanteRegular = new JugadorTirosContraEquipo();
        visitantePlayoff = new JugadorTirosContraEquipo();
    }
    
    public String devolverStyle(boolean maxima){
        if(maxima){
            return "yellowRaw";
        }else{
            return "white";
        }
    }
}
