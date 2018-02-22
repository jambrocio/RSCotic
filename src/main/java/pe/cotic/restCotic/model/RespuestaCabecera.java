package pe.cotic.restCotic.model;

import java.util.List;

/**
 * Created by joch on 22/09/2017.
 */

public class RespuestaCabecera {

    int codigoRespuestaCabecera;
    int codigoPortafolio;
    int codigoUsuario;
    String fechaRespuestaCabecera;
    String codigoDispositivo;
    String versionAplicacion;
    
    List<AlterntivasSeleccionadas> listaAlternativas;

	public int getCodigoRespuestaCabecera() {
		return codigoRespuestaCabecera;
	}

	public void setCodigoRespuestaCabecera(int codigoRespuestaCabecera) {
		this.codigoRespuestaCabecera = codigoRespuestaCabecera;
	}

	public int getCodigoPortafolio() {
		return codigoPortafolio;
	}

	public void setCodigoPortafolio(int codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getFechaRespuestaCabecera() {
		return fechaRespuestaCabecera;
	}

	public void setFechaRespuestaCabecera(String fechaRespuestaCabecera) {
		this.fechaRespuestaCabecera = fechaRespuestaCabecera;
	}

	public String getCodigoDispositivo() {
		return codigoDispositivo;
	}

	public void setCodigoDispositivo(String codigoDispositivo) {
		this.codigoDispositivo = codigoDispositivo;
	}

	public String getVersionAplicacion() {
		return versionAplicacion;
	}

	public void setVersionAplicacion(String versionAplicacion) {
		this.versionAplicacion = versionAplicacion;
	}

	public List<AlterntivasSeleccionadas> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<AlterntivasSeleccionadas> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}
    
}
