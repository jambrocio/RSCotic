package pe.cotic.restCotic.daoImpl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import pe.cotic.restCotic.bean.CambiarClave;
import pe.cotic.restCotic.bean.Credenciales;
import pe.cotic.restCotic.bean.Dispositivo;
import pe.cotic.restCotic.bean.InformacionUsuarioPortafolio;
import pe.cotic.restCotic.bean.PorcentajeCorrectas;
import pe.cotic.restCotic.bean.Retorno;
import pe.cotic.restCotic.dao.ConsultasDao;
import pe.cotic.restCotic.model.Alternativa;
import pe.cotic.restCotic.model.AlterntivasSeleccionadas;
import pe.cotic.restCotic.model.Nivel;
import pe.cotic.restCotic.model.Portafolio;
import pe.cotic.restCotic.model.Pregunta;
import pe.cotic.restCotic.model.Registros;
import pe.cotic.restCotic.model.RespuestaCabecera;
import pe.cotic.restCotic.model.Usuario;
import pe.cotic.restCotic.model.Version;
import pe.cotic.restCotic.util.Seguridad;
import pe.cotic.restCotic.util.Util;

@Repository
public class ConsultasDaoImpl implements ConsultasDao {
	
	private static final Logger log = Logger.getLogger(ConsultasDaoImpl.class);
	
	Gson gson = new Gson();

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	
	@Override
	public Version version() {
		
		Version version = new Version(); 
		
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall.withProcedureName("versionAplicacion").declareParameters(
				new SqlOutParameter("vo_versionAplicacionVigente", 	Types.VARCHAR),
				new SqlOutParameter("vo_versionUsuarios", 			Types.INTEGER),
				new SqlOutParameter("vo_versionPortafolio",			Types.INTEGER),
				new SqlOutParameter("vo_versionNivel", 				Types.INTEGER));
				
		Map<String,Object> result = jdbcCall.execute(); 
		
		version.setVersionUsuarios((Integer) result.get("vo_versionUsuarios"));
		version.setVersionAplicacionVigente((String) result.get("vo_versionAplicacionVigente"));
		version.setVersionPortafolio((Integer) result.get("vo_versionPortafolio"));
		version.setVersionNivel((Integer) result.get("vo_versionNivel"));
		
		return version;
		
	}

	@Override
	public List<Usuario> autenticarUsuario(Credenciales credenciales) {
		
		List<Usuario> listarUsuarios = new ArrayList<Usuario>();
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall.withProcedureName("autenticarUsuario").declareParameters(
				new SqlParameter("vi_usuario",		Types.VARCHAR),
				new SqlParameter("vi_clave",		Types.VARCHAR));
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("vi_usuario",		credenciales.getUsuario());
		parametros.addValue("vi_clave",			credenciales.getClave());
				
		Map<String,Object> result = jdbcCall.execute(parametros);
		
		List<Map<String, Object>> rs = (List<Map<String, Object>>) result.get("#result-set-1");
		
		if(rs == null){
			Usuario usuario = new Usuario();
			listarUsuarios.add(usuario);
		}else{
			for (Map<String, Object> map : rs) {
				
				Usuario usuario = new Usuario();
				
				usuario.setCodigoUsuario((Integer) map.get("codigoUsuario"));
				usuario.setApellidoPaterno((String) map.get("apellidoPaterno"));
				usuario.setApellidoMaterno((String) map.get("apellidoMaterno"));
				usuario.setNombres((String) map.get("nombres"));
				usuario.setSexo((String) map.get("sexo"));
				usuario.setFechaNacimiento((String) map.get("fechaNacimiento"));
				usuario.setCodigoInstitucion((Integer) map.get("codigoInstitucion"));
				usuario.setNombreInstitucion((String) map.get("nombreInstitucion"));
				
				listarUsuarios.add(usuario);
				
			}
		}
				
	    return listarUsuarios;
		
	}

	@Override
	public Retorno grabar(Registros registros) {
		
		Retorno retorno = new Retorno();
		try{
			
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("registrar").declareParameters(
					new SqlParameter("vi_codigoRegistro",		Types.INTEGER),
					new SqlParameter("vi_dni",					Types.VARCHAR),
					new SqlParameter("vi_apellidoPaterno",		Types.VARCHAR),
					new SqlParameter("vi_apellidoMaterno",		Types.VARCHAR),
					new SqlParameter("vi_nombres",				Types.VARCHAR),
					new SqlParameter("vi_sexo",					Types.VARCHAR),
					new SqlParameter("vi_fechaNacimiento",		Types.VARCHAR),
					new SqlParameter("vi_flagInicio",			Types.INTEGER),
					new SqlParameter("vi_flagFin",				Types.INTEGER),
					new SqlParameter("vi_placa",				Types.VARCHAR),
					new SqlParameter("vi_kilometraje",			Types.INTEGER),
					new SqlParameter("vi_galones",				Types.INTEGER),
					new SqlParameter("vi_grifo",				Types.VARCHAR),
					new SqlParameter("vi_monto",				Types.VARCHAR),
					new SqlParameter("vi_comentario",			Types.VARCHAR),
					new SqlParameter("vi_latitud",				Types.VARCHAR),
					new SqlParameter("vi_longitud",				Types.VARCHAR),
					new SqlParameter("vi_altitud",				Types.VARCHAR),
					new SqlParameter("vi_fechaRegistro",		Types.VARCHAR),
					new SqlParameter("vi_codigoTipoRegistro",	Types.INTEGER),
					new SqlParameter("vi_codigoUsuario",		Types.INTEGER),
					new SqlParameter("vi_foto1",				Types.BLOB),
					new SqlParameter("vi_foto2",				Types.BLOB),
					new SqlParameter("vi_foto3",				Types.BLOB),
					new SqlParameter("vi_foto4",				Types.BLOB),
					
					new SqlOutParameter("vo_retorno", 			Types.INTEGER),
					new SqlOutParameter("vo_mensaje", 			Types.VARCHAR));
			
			byte[] foto1 = Util.Base64ToBytes(registros.getFoto1());
			byte[] foto2 = Util.Base64ToBytes(registros.getFoto2());
			byte[] foto3 = Util.Base64ToBytes(registros.getFoto3());
			byte[] foto4 = Util.Base64ToBytes(registros.getFoto4());
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("vi_codigoRegistro",		registros.getCodigoRegistro());
			parametros.addValue("vi_dni",					registros.getDni());
			parametros.addValue("vi_apellidoPaterno",		registros.getApellidoPaterno());
			parametros.addValue("vi_apellidoMaterno",		registros.getApellidoMaterno());
			parametros.addValue("vi_nombres",				registros.getNombres());
			parametros.addValue("vi_sexo",					registros.getSexo());
			parametros.addValue("vi_fechaNacimiento",		registros.getFechaNacimiento());
			parametros.addValue("vi_flagInicio",			registros.getFlagInicio());
			parametros.addValue("vi_flagFin",				registros.getFlagFin());
			parametros.addValue("vi_placa",					registros.getPlaca());
			parametros.addValue("vi_kilometraje",			registros.getKilometraje());
			parametros.addValue("vi_galones",				registros.getGalones());
			parametros.addValue("vi_grifo",					registros.getGrifo());
			parametros.addValue("vi_monto",					registros.getMonto());
			parametros.addValue("vi_comentario",			registros.getComentario());
			parametros.addValue("vi_latitud",				registros.getLatitud());
			parametros.addValue("vi_longitud",				registros.getLongitud());
			parametros.addValue("vi_altitud",				registros.getAltitud());
			parametros.addValue("vi_fechaRegistro",			registros.getFechaRegistro());
			parametros.addValue("vi_codigoTipoRegistro",	registros.getCodigoTipoRegistro());
			parametros.addValue("vi_codigoUsuario",			registros.getCodigoUsuario());
			parametros.addValue("vi_foto1",					foto1);
			parametros.addValue("vi_foto2",					foto2);
			parametros.addValue("vi_foto3",					foto3);
			parametros.addValue("vi_foto4",					foto4);
			
			Map<String,Object> result = jdbcCall.execute(parametros);
			
			retorno.setCodigoRetorno((Integer) result.get("vo_retorno"));
			retorno.setMensajeRetorno((String) result.get("vo_mensaje"));
						
		}catch(Exception e){
			
			retorno.setCodigoRetorno(0);
			retorno.setMensajeRetorno("Error");
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	@Override
	public List<Portafolio> listarPortafolio(Dispositivo dispositivo) {
		
		List<Portafolio> listarPortafolio = new ArrayList<Portafolio>();
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall.withProcedureName("listarPortafolio").declareParameters(
				new SqlParameter("vi_usuario",	Types.VARCHAR));
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("vi_usuario",	dispositivo.getUsuario());
				
		Map<String,Object> result = jdbcCall.execute(parametros);
		
		List<Map<String, Object>> rs = (List<Map<String, Object>>) result.get("#result-set-1");
		for (Map<String, Object> map : rs) {
			
			Portafolio portafolio = new Portafolio();
			
			portafolio.setCodigoPortafolio((Integer) map.get("codigoPortafolio"));
			portafolio.setTituloPortafolio((String) map.get("tituloPortafolio"));
			portafolio.setDescripcionPortafolio((String) map.get("descripcionPortafolio"));
			portafolio.setImagenPortafolio((byte[]) map.get("imagenPortafolio"));//Automaticamente lo esta convirtiendo en base64
			portafolio.setFechaInicioPortafolio((String) map.get("fechaInicioPortafolio"));
			portafolio.setFechaFinPortafolio((String) map.get("fechaFinPortafolio"));
			portafolio.setFlagMostrarRespuestaCorrectaPortafolio((Integer) map.get("flagMostrarRespuestaCorrectaPortafolio"));
			portafolio.setTiempoPortafolio((Integer) map.get("tiempoPortafolio"));
			portafolio.setNivelPresentacion((Integer) map.get("nivelPresentacion"));
			portafolio.setCodigoPortafolioEnlace((Integer) map.get("codigoPortafolioEnlace"));
			portafolio.setCodigoNivel((Integer) map.get("codigoNivel"));
			
			listarPortafolio.add(portafolio);
			
		}
				
	    return listarPortafolio;
		
	}
	
	@Override
	public List<Pregunta> listarPreguntas(Dispositivo dispositivo) {
		
		List<Pregunta> listarPreguntas = new ArrayList<Pregunta>();
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall.withProcedureName("listarPreguntas").declareParameters(
				new SqlParameter("vi_usuario",	Types.VARCHAR));
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("vi_usuario",	dispositivo.getUsuario());
				
		Map<String,Object> result = jdbcCall.execute(parametros);
		
		List<Map<String, Object>> rs = (List<Map<String, Object>>) result.get("#result-set-1");
		for (Map<String, Object> map : rs) {
			
			Pregunta pregunta = new Pregunta();
			
			pregunta.setCodigoPregunta((Integer) map.get("codigoPregunta"));
			pregunta.setTituloPregunta((String) map.get("tituloPregunta"));
			pregunta.setCodigoPortafolio((Integer) map.get("codigoPortafolio"));
			pregunta.setImagenPregunta((byte[]) map.get("imagenPregunta"));
			
			listarPreguntas.add(pregunta);
			
		}
				
	    return listarPreguntas;
		
	}
	
	@Override
	public List<Alternativa> listarAlternativas(Dispositivo dispositivo) {
		
		List<Alternativa> listarAlternativas = new ArrayList<Alternativa>();
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall.withProcedureName("listarAlternativas").declareParameters(
				new SqlParameter("vi_usuario",	Types.VARCHAR));
		
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("vi_usuario",		dispositivo.getUsuario());
				
		Map<String,Object> result = jdbcCall.execute(parametros);
		
		List<Map<String, Object>> rs = (List<Map<String, Object>>) result.get("#result-set-1");
		for (Map<String, Object> map : rs) {
			
			Alternativa alternativa = new Alternativa();
			
			alternativa.setCodigoAlternativa((Integer) map.get("codigoAlternativa"));
			alternativa.setTituloAlternativa((String) map.get("tituloAlternativa"));
			alternativa.setCodigoPregunta((Integer) map.get("codigoPregunta"));
			alternativa.setFlagRespuestaCorrectaAlternativa((Integer) map.get("flagRespuestaCorrectaAlternativa"));
			
			listarAlternativas.add(alternativa);
			
		}
				
	    return listarAlternativas;
		
	}

	@Override
	public List<Nivel> listarNivel() {
		
		List<Nivel> listarNivel = new ArrayList<Nivel>();
		jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
		jdbcCall.withProcedureName("listarNivel").declareParameters();
		
		Map<String,Object> result = jdbcCall.execute();
		
		List<Map<String, Object>> rs = (List<Map<String, Object>>) result.get("#result-set-1");
		for (Map<String, Object> map : rs) {
			
			Nivel nivel = new Nivel();
			
			nivel.setCodigoNivel((Integer) map.get("codigoNivel"));
			nivel.setDescripcionNivel((String) map.get("descripcionNivel"));
			
			listarNivel.add(nivel);
			
		}
				
	    return listarNivel;
	}

	@Override
	public Retorno grabarRespuestas(RespuestaCabecera cabecera) {
		Retorno retorno = new Retorno();
		try{
			
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("grabarRespuestaCabecera").declareParameters(
					new SqlParameter("vi_codigoRespuestaCabecera",		Types.INTEGER),
					new SqlParameter("vi_codigoPortafolio",				Types.INTEGER),
					new SqlParameter("vi_codigoUsuario",				Types.INTEGER),
					new SqlParameter("vi_fechaRespuesta",				Types.VARCHAR),
					new SqlParameter("vi_codigoDispositivo",			Types.VARCHAR),
					new SqlParameter("vi_versionAplicacion",			Types.VARCHAR),
					
					new SqlOutParameter("vo_codigoRespuestaCabecera", 	Types.INTEGER),
					new SqlOutParameter("vo_retorno", 					Types.INTEGER));
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("vi_codigoRespuestaCabecera",	cabecera.getCodigoRespuestaCabecera());
			parametros.addValue("vi_codigoPortafolio",			cabecera.getCodigoPortafolio());
			parametros.addValue("vi_codigoUsuario",				cabecera.getCodigoUsuario());
			parametros.addValue("vi_fechaRespuesta",			cabecera.getFechaRespuestaCabecera());
			parametros.addValue("vi_codigoDispositivo",			cabecera.getCodigoDispositivo());
			parametros.addValue("vi_versionAplicacion",			cabecera.getVersionAplicacion() != null ? cabecera.getVersionAplicacion() : "");
			
			Map<String,Object> result = jdbcCall.execute(parametros);
			
			retorno.setCodigoRetorno((Integer) result.get("vo_retorno"));
			retorno.setMensajeRetorno(String.valueOf((Integer) result.get("vo_codigoRespuestaCabecera")));
			
			if(cabecera.getListaAlternativas() != null){
				for(AlterntivasSeleccionadas alt : cabecera.getListaAlternativas()){
					
					grabarRespuestaDetalle(retorno.getCodigoRetorno(), alt);
					
				}
			}
			
		}catch(Exception e){
			
			retorno.setCodigoRetorno(0);
			retorno.setMensajeRetorno("ERROR");
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public Retorno grabarRespuestaDetalle(int codigoRespuestaCabecera, AlterntivasSeleccionadas alternativa) {
		Retorno retorno = new Retorno();
		try{
			
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("grabarRespuestaDetalle").declareParameters(
					new SqlParameter("vi_codigoRespuestaCabecera",		Types.INTEGER),
					new SqlParameter("vi_codigoPregunta",				Types.INTEGER),
					new SqlParameter("vi_codigoAlternativa",			Types.INTEGER),
					new SqlParameter("vi_flagAlternativaCorrecta",		Types.INTEGER),
					
					new SqlOutParameter("vo_codigoRespuestaDetalle", 	Types.INTEGER));
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("vi_codigoRespuestaCabecera",		codigoRespuestaCabecera);
			parametros.addValue("vi_codigoPregunta",				alternativa.getCodigoPregunta());
			parametros.addValue("vi_codigoAlternativa",				alternativa.getCodigoAlternativa());
			parametros.addValue("vi_flagAlternativaCorrecta",		alternativa.getFlagAlternativaCorrecta());
			
			Map<String,Object> result = jdbcCall.execute(parametros);
			
			retorno.setCodigoRetorno((Integer) result.get("vo_codigoRespuestaDetalle"));
			retorno.setMensajeRetorno("OK");
						
		}catch(Exception e){
			
			retorno.setCodigoRetorno(0);
			retorno.setMensajeRetorno("ERROR");
			
			e.printStackTrace();
		}
		
		return retorno;
	}

	@Override
	public InformacionUsuarioPortafolio informacionUsuarioPortafolio(InformacionUsuarioPortafolio informacionUsuarioPortafolio) {
		
		InformacionUsuarioPortafolio info = new InformacionUsuarioPortafolio(); 
		List<PorcentajeCorrectas> listaPorcentajeCorrectas = new ArrayList<PorcentajeCorrectas>();
		try{
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("informacionUsuarioPortafolio").declareParameters(
					new SqlParameter("vi_codigoUsuario",				Types.INTEGER),
					new SqlParameter("vi_codigoPortafolio",				Types.INTEGER),
					
					new SqlOutParameter("vo_posicionUsuarioPortafolio", Types.INTEGER),
					new SqlOutParameter("vo_practicasRespondidas", 		Types.INTEGER));
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("vi_codigoUsuario",		informacionUsuarioPortafolio.getCodigoUsuario());
			parametros.addValue("vi_codigoPortafolio",	informacionUsuarioPortafolio.getCodigoPortafolio());
			
			Map<String,Object> result = jdbcCall.execute(parametros); 
			List<Map<String, Object>> rs = (List<Map<String, Object>>) result.get("#result-set-1");
			
			info.setPosicionUsuarioPortafolio((Integer) result.get("vo_posicionUsuarioPortafolio"));
			info.setPracticasRespondidas((Integer) result.get("vo_practicasRespondidas"));
			for (Map<String, Object> map : rs) {
				
				PorcentajeCorrectas porcentaje = new PorcentajeCorrectas();
				
				porcentaje.setCodigoPortafolio((Integer) map.get("codigoPortafolio"));
				porcentaje.setCodigoUsuario((Integer) map.get("codigoUsuario"));
				porcentaje.setPorcentajeCorrectas((String) map.get("porcentajeCorrectas"));
				porcentaje.setCodigoRespuestaCabecera((Integer) map.get("codigoRespuestaCabecera"));
				
				listaPorcentajeCorrectas.add(porcentaje);
			}
			
			info.setListaPorcentajeCorrectas(listaPorcentajeCorrectas);
			
		}catch(Exception e){
			
			e.printStackTrace();
		}

		return info;
		
	}

	@Override
	public CambiarClave cambiarClave(CambiarClave cambiarClave) {
		CambiarClave cambiar = new CambiarClave();
		try{
			
			jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			jdbcCall.withProcedureName("cambiarClave").declareParameters(
					new SqlParameter("vi_usuario",		Types.VARCHAR),
					new SqlParameter("vi_clave_actual",	Types.VARCHAR),
					new SqlParameter("vi_clave_nueva",	Types.VARCHAR),
					
					new SqlOutParameter("vo_retorno", 	Types.INTEGER));
			
			MapSqlParameterSource parametros = new MapSqlParameterSource();
			parametros.addValue("vi_usuario",		cambiarClave.getUsuario());
			parametros.addValue("vi_clave_actual",	cambiarClave.getValorCero());
			parametros.addValue("vi_clave_nueva",	cambiarClave.getValorUno());
			
			Map<String,Object> result = jdbcCall.execute(parametros);
			
			cambiar.setUsuario(cambiarClave.getUsuario());
			cambiar.setRetorno((Integer) result.get("vo_retorno"));
						
		}catch(Exception e){
			
			cambiar.setUsuario(cambiar.getUsuario());
			cambiar.setRetorno(0);
			
			e.printStackTrace();
		}
		
		return cambiar;
	}
}