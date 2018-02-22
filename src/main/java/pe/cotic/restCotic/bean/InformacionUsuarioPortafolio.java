package pe.cotic.restCotic.bean;

import java.util.List;

public class InformacionUsuarioPortafolio {
	
	int codigoUsuario;
	int codigoPortafolio;
	int posicionUsuarioPortafolio;
	int practicasRespondidas;
	
	List<PorcentajeCorrectas> listaPorcentajeCorrectas;

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public int getCodigoPortafolio() {
		return codigoPortafolio;
	}

	public void setCodigoPortafolio(int codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}

	public int getPosicionUsuarioPortafolio() {
		return posicionUsuarioPortafolio;
	}

	public void setPosicionUsuarioPortafolio(int posicionUsuarioPortafolio) {
		this.posicionUsuarioPortafolio = posicionUsuarioPortafolio;
	}

	public int getPracticasRespondidas() {
		return practicasRespondidas;
	}

	public void setPracticasRespondidas(int practicasRespondidas) {
		this.practicasRespondidas = practicasRespondidas;
	}

	public List<PorcentajeCorrectas> getListaPorcentajeCorrectas() {
		return listaPorcentajeCorrectas;
	}

	public void setListaPorcentajeCorrectas(List<PorcentajeCorrectas> listaPorcentajeCorrectas) {
		this.listaPorcentajeCorrectas = listaPorcentajeCorrectas;
	}
	
}
