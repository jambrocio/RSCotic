package pe.cotic.restCotic.model;

public class Alternativa {
	
	int codigoAlternativa;
	String tituloAlternativa;
	int flagRespuestaCorrectaAlternativa;
	int codigoPregunta;
	
	public int getCodigoAlternativa() {
		return codigoAlternativa;
	}
	public void setCodigoAlternativa(int codigoAlternativa) {
		this.codigoAlternativa = codigoAlternativa;
	}
	public String getTituloAlternativa() {
		return tituloAlternativa;
	}
	public void setTituloAlternativa(String tituloAlternativa) {
		this.tituloAlternativa = tituloAlternativa;
	}
	public int getFlagRespuestaCorrectaAlternativa() {
		return flagRespuestaCorrectaAlternativa;
	}
	public void setFlagRespuestaCorrectaAlternativa(int flagRespuestaCorrectaAlternativa) {
		this.flagRespuestaCorrectaAlternativa = flagRespuestaCorrectaAlternativa;
	}
	public int getCodigoPregunta() {
		return codigoPregunta;
	}
	public void setCodigoPregunta(int codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}
	
}
