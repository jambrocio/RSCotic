package pe.cotic.restCotic.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import pe.cotic.restCotic.bean.CambiarClave;
import pe.cotic.restCotic.bean.Credenciales;
import pe.cotic.restCotic.bean.Dispositivo;
import pe.cotic.restCotic.bean.InformacionUsuarioPortafolio;
import pe.cotic.restCotic.bean.Retorno;
import pe.cotic.restCotic.bus.ConsultasBus;
import pe.cotic.restCotic.model.Registros;
import pe.cotic.restCotic.model.RespuestaCabecera;
import pe.cotic.restCotic.model.Version;
import pe.cotic.restCotic.objJson.ListarAlternativas;
import pe.cotic.restCotic.objJson.ListarNivel;
import pe.cotic.restCotic.objJson.ListarPortafolio;
import pe.cotic.restCotic.objJson.ListarPreguntas;
import pe.cotic.restCotic.objJson.ListarUsuario;

@Controller
public class ConsultasController{
	
	Gson gson = new Gson();
	private static final Logger log = Logger.getLogger(ConsultasController.class);
	
	@Autowired
	private ConsultasBus consultasBus;
	
	@RequestMapping(value = "/version", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Version versionEncuesta() {
		
		return consultasBus.version();
		
	}
	
	@RequestMapping(value = "/autenticarUsuario", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	ListarUsuario autenticarUsuario(@RequestBody Credenciales credenciales) {
		
		return consultasBus.autenticarUsuario(credenciales);
		
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Retorno grabar(@RequestBody Registros registros) {
		
		return consultasBus.grabar(registros);
		
	}
	
	@RequestMapping(value = "/listarPortafolio", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	ListarPortafolio listarPortafolio(@RequestBody Dispositivo dispositivo) {
		
		return consultasBus.listarPortafolio(dispositivo);
		
	}
	
	@RequestMapping(value = "/listarPreguntas", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	ListarPreguntas listarPreguntas(@RequestBody Dispositivo dispositivo) {
		
		return consultasBus.listarPreguntas(dispositivo);
		
	}
	
	@RequestMapping(value = "/listarAlternativas", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	ListarAlternativas listarAlternativas(@RequestBody Dispositivo dispositivo) {
		
		return consultasBus.listarAlternativas(dispositivo);
		
	}
	
	@RequestMapping(value = "/listarNivel", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody
	ListarNivel listarNivel() {
		
		return consultasBus.listarNivel();
		
	}
	
	@RequestMapping(value = "/grabarRespuestas", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Retorno grabarRespuestas(@RequestBody RespuestaCabecera cabecera) {
		
		return consultasBus.grabarRespuestas(cabecera);
		
	}
	
	@RequestMapping(value = "/informacionUsuarioPortafolio", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	InformacionUsuarioPortafolio informacionUsuarioPortafolio(@RequestBody InformacionUsuarioPortafolio informacionUsuarioPortafolio) {
		
		return consultasBus.informacionUsuarioPortafolio(informacionUsuarioPortafolio);
		
	}
	
	@RequestMapping(value = "/cambiarClave", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	CambiarClave cambiarClave(@RequestBody CambiarClave cambiarClave) {
		
		return consultasBus.cambiarClave(cambiarClave);
		
	}
}
