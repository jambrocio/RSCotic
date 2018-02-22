package pe.cotic.restCotic.dao;

import java.util.List;

import pe.cotic.restCotic.bean.CambiarClave;
import pe.cotic.restCotic.bean.Credenciales;
import pe.cotic.restCotic.bean.Dispositivo;
import pe.cotic.restCotic.bean.InformacionUsuarioPortafolio;
import pe.cotic.restCotic.bean.Retorno;
import pe.cotic.restCotic.model.Alternativa;
import pe.cotic.restCotic.model.Nivel;
import pe.cotic.restCotic.model.Portafolio;
import pe.cotic.restCotic.model.Pregunta;
import pe.cotic.restCotic.model.Registros;
import pe.cotic.restCotic.model.RespuestaCabecera;
import pe.cotic.restCotic.model.Usuario;
import pe.cotic.restCotic.model.Version;

public interface ConsultasDao {

	public Version version();
	
	public List<Usuario> autenticarUsuario(Credenciales credenciales);
	
	public Retorno grabar(Registros registros);
	
	public List<Portafolio> listarPortafolio(Dispositivo dispositivo);
	
	public List<Pregunta> listarPreguntas(Dispositivo dispositivo);
	
	public List<Alternativa> listarAlternativas(Dispositivo dispositivo);
	
	public List<Nivel> listarNivel();
	
	public Retorno grabarRespuestas(RespuestaCabecera cabecera);
	
	public InformacionUsuarioPortafolio informacionUsuarioPortafolio(InformacionUsuarioPortafolio informacionUsuarioPortafolio);
	
	public CambiarClave cambiarClave(CambiarClave cambiarClave);
	
}
