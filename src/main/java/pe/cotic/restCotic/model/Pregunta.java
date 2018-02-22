package pe.cotic.restCotic.model;

public class Pregunta {
	
	int codigoPregunta;
	String tituloPregunta;
	int codigoPortafolio;
	byte[] imagenPregunta;
	
	public int getCodigoPregunta() {
		return codigoPregunta;
	}
	public void setCodigoPregunta(int codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}
	public String getTituloPregunta() {
		return tituloPregunta;
	}
	public void setTituloPregunta(String tituloPregunta) {
		this.tituloPregunta = tituloPregunta;
	}
	public int getCodigoPortafolio() {
		return codigoPortafolio;
	}
	public void setCodigoPortafolio(int codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}
	public byte[] getImagenPregunta() {
		return imagenPregunta;
	}
	public void setImagenPregunta(byte[] imagenPregunta) {
		this.imagenPregunta = imagenPregunta;
	}
	
}
