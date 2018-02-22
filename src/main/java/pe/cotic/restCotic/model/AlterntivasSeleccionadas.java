package pe.cotic.restCotic.model;

public class AlterntivasSeleccionadas {
	
	int codigoPregunta;
    int codigoAlternativa;
    int flagAlternativaCorrecta;
    int posicion;
    
	public int getCodigoPregunta() {
		return codigoPregunta;
	}
	public void setCodigoPregunta(int codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}
	public int getCodigoAlternativa() {
		return codigoAlternativa;
	}
	public void setCodigoAlternativa(int codigoAlternativa) {
		this.codigoAlternativa = codigoAlternativa;
	}
	public int getFlagAlternativaCorrecta() {
		return flagAlternativaCorrecta;
	}
	public void setFlagAlternativaCorrecta(int flagAlternativaCorrecta) {
		this.flagAlternativaCorrecta = flagAlternativaCorrecta;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
    
}
