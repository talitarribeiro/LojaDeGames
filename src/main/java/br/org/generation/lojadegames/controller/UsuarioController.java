package br.org.generation.lojadegames.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojadegames.model.Usuario;
import br.org.generation.lojadegames.model.UsuarioLogin;
import br.org.generation.lojadegames.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioService.listarUsuarios());

	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> loginUsuario(@RequestBody Optional <UsuarioLogin> usuarioLogin){
		
		return usuarioService.logarUsuario(usuarioLogin)
			.map(respostaLogin -> ResponseEntity.status(HttpStatus.OK).body(respostaLogin))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
		
		return usuarioService.cadastrarUsuario(usuario)
			.map(respostaCadastrar -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastrar))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
}