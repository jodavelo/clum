package co.edu.usbcali.clum.controller;

import co.edu.usbcali.clum.domain.*;
import co.edu.usbcali.clum.dto.UsuarioDTO;
import co.edu.usbcali.clum.mapper.UsuarioMapper;
import co.edu.usbcali.clum.service.UsuarioService;
import co.edu.usbcali.clum.utility.Respuesta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping(value = "/saveUsuario")
    public void saveUsuario(@RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

            usuarioService.saveUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteUsuario/{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId")
    String usuarioId) throws Exception {
        try {
            Usuario usuario = usuarioService.getUsuario(usuarioId);

            usuarioService.deleteUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateUsuario/")
    public void updateUsuario(@RequestBody
    UsuarioDTO usuarioDTO) throws Exception {
        try {
            Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);

            usuarioService.updateUsuario(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataUsuario")
    public List<UsuarioDTO> getDataUsuario() throws Exception {
        try {
            return usuarioService.getDataUsuario();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getUsuario/{usuarioId}")
    public UsuarioDTO getUsuario(@PathVariable("usuarioId")
    String usuarioId) throws Exception {
        try {
            Usuario usuario = usuarioService.getUsuario(usuarioId);

            return usuarioMapper.usuarioToUsuarioDTO(usuario);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
    
    @PostMapping("/login")
    public ResponseEntity<Respuesta> loginUsuario(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
    	try {
			if(usuarioDTO == null) {
				return ResponseEntity.badRequest().body(new Respuesta("UsuarioDTO es nulo"));
			}
			usuarioService.verificarLoginUsuario(usuarioDTO.getUsuarioId(), usuarioDTO.getContrasenia());
			return ResponseEntity.ok().body(new Respuesta("Sesion iniciada exitosamente"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(new Respuesta(e.getMessage()));
		}
    }
}
