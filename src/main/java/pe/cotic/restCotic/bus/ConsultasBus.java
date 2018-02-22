package pe.cotic.restCotic.bus;

import pe.cotic.restCotic.bean.CambiarClave;
import pe.cotic.restCotic.bean.Credenciales;
import pe.cotic.restCotic.bean.Dispositivo;
import pe.cotic.restCotic.bean.InformacionUsuarioPortafolio;
import pe.cotic.restCotic.bean.Retorno;
import pe.cotic.restCotic.model.Registros;
import pe.cotic.restCotic.model.RespuestaCabecera;
import pe.cotic.restCotic.model.Version;
import pe.cotic.restCotic.objJson.ListarAlternativas;
import pe.cotic.restCotic.objJson.ListarNivel;
import pe.cotic.restCotic.objJson.ListarPortafolio;
import pe.cotic.restCotic.objJson.ListarPreguntas;
import pe.cotic.restCotic.objJson.ListarUsuario;

public interface ConsultasBus {
	
	public Version version();
	
	public ListarUsuario autenticarUsuario(Credenciales credenciales);
	
	public Retorno grabar(Registros registros);
	
	public ListarPortafolio listarPortafolio(Dispositivo dispositivo);
	
	public ListarPreguntas listarPreguntas(Dispositivo dispositivo);
	
	public ListarAlternativas listarAlternativas(Dispositivo dispositivo);
	
	public ListarNivel listarNivel();
	
	public Retorno grabarRespuestas(RespuestaCabecera cabecera);
	
	public InformacionUsuarioPortafolio informacionUsuarioPortafolio(InformacionUsuarioPortafolio informacionUsuarioPortafolio);
	
	public CambiarClave cambiarClave(CambiarClave cambiarClave);
	
}
