package pe.cotic.restCotic.busImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import pe.cotic.restCotic.bean.CambiarClave;
import pe.cotic.restCotic.bean.Credenciales;
import pe.cotic.restCotic.bean.Dispositivo;
import pe.cotic.restCotic.bean.InformacionUsuarioPortafolio;
import pe.cotic.restCotic.bean.Retorno;
import pe.cotic.restCotic.bus.ConsultasBus;
import pe.cotic.restCotic.dao.ConsultasDao;
import pe.cotic.restCotic.model.Alternativa;
import pe.cotic.restCotic.model.Nivel;
import pe.cotic.restCotic.model.Portafolio;
import pe.cotic.restCotic.model.Pregunta;
import pe.cotic.restCotic.model.Registros;
import pe.cotic.restCotic.model.RespuestaCabecera;
import pe.cotic.restCotic.model.Usuario;
import pe.cotic.restCotic.model.Version;
import pe.cotic.restCotic.objJson.ListarAlternativas;
import pe.cotic.restCotic.objJson.ListarNivel;
import pe.cotic.restCotic.objJson.ListarPortafolio;
import pe.cotic.restCotic.objJson.ListarPreguntas;
import pe.cotic.restCotic.objJson.ListarUsuario;

@Service
public class ConsultasBusImpl implements ConsultasBus {
	
	private static final Logger log = Logger.getLogger(ConsultasBusImpl.class);
	
	@Resource
	private ConsultasDao consultasDao;
	
	@Override
	public Version version() {
		
		return consultasDao.version();
		
	}

	@Override
	public ListarUsuario autenticarUsuario(Credenciales credenciales) {
		
		List<Usuario> lista = consultasDao.autenticarUsuario(credenciales);
		ListarUsuario listaUsuario = new ListarUsuario();
		listaUsuario.setListarUsuario(lista);
		
		return listaUsuario;
	}

	@Override
	public Retorno grabar(Registros registros) {
		
		return consultasDao.grabar(registros);
	}

	@Override
	public ListarPortafolio listarPortafolio(Dispositivo dispositivo) {
		
		List<Portafolio> lista = consultasDao.listarPortafolio(dispositivo);
		ListarPortafolio listarPortafolio = new ListarPortafolio();
		listarPortafolio.setListarPortafolio(lista);
		
		return listarPortafolio;
	}

	@Override
	public ListarPreguntas listarPreguntas(Dispositivo dispositivo) {
		
		List<Pregunta> lista = consultasDao.listarPreguntas(dispositivo);
		ListarPreguntas listarPreguntas = new ListarPreguntas();
		listarPreguntas.setListarPreguntas(lista);
		
		return listarPreguntas;
		
	}

	@Override
	public ListarAlternativas listarAlternativas(Dispositivo dispositivo) {
		
		List<Alternativa> lista = consultasDao.listarAlternativas(dispositivo);
		ListarAlternativas listarAlternativas = new ListarAlternativas();
		listarAlternativas.setListarAlternativas(lista);
		
		return listarAlternativas;
		
	}

	@Override
	public ListarNivel listarNivel() {

		List<Nivel> lista = consultasDao.listarNivel();
		ListarNivel listarNivel = new ListarNivel();
		listarNivel.setListarNivel(lista);
		
		return listarNivel;

	}

	@Override
	public Retorno grabarRespuestas(RespuestaCabecera cabecera) {
		
		return consultasDao.grabarRespuestas(cabecera);
	}

	@Override
	public InformacionUsuarioPortafolio informacionUsuarioPortafolio(
			InformacionUsuarioPortafolio informacionUsuarioPortafolio) {
		
		return consultasDao.informacionUsuarioPortafolio(informacionUsuarioPortafolio);
	}

	@Override
	public CambiarClave cambiarClave(CambiarClave cambiarClave) {
		
		return consultasDao.cambiarClave(cambiarClave);
	}
	
}