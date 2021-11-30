package com.icaroelucas.restauranteorlajk.administracao.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.EdicaoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.NovaSenhaDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.NovoUsuarioDTO;
import com.icaroelucas.restauranteorlajk.administracao.usuario.dto.UsuarioEmEdicaoDAO;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Perfil;
import com.icaroelucas.restauranteorlajk.entities.usuario.model.Usuario;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.PerfilRepository;
import com.icaroelucas.restauranteorlajk.entities.usuario.repository.UsuarioRepository;

public class UsuarioService {

	UsuarioRepository usuarioRepository = null;
	PerfilRepository perfilRepository = null;

	public UsuarioService iniciar(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
		if (!foiIniciado()) {
			this.perfilRepository = perfilRepository;
			this.usuarioRepository = usuarioRepository;
		}

		return this;
	}

	private boolean foiIniciado() {
		return usuarioRepository != null && perfilRepository != null;
	}

	public Model popularModel(Model model) {
		model.addAttribute("usuarios", buscaUsuarios());
		return model;
	}

	public Model popularModel(Model model, long id) {
		model.addAttribute("usuarioeperfil", editaUsuario(id));
		return model;
	}

	public List<Usuario> buscaUsuarios() {
		return usuarioRepository.findAll();
	}

	public void adicionaUsuario(NovoUsuarioDTO usuarioDTO) {

		List<Perfil> perfis = new ArrayList<>();

		List<String> perfisString = usuarioDTO.getPerfil();

		for (String perfil : perfisString) {
			perfis.add(perfilRepository.findByNome(perfil));
		}
		usuarioRepository.save(usuarioDTO.toUsuario(perfis));
	}

	public UsuarioEmEdicaoDAO editaUsuario(long id) throws NoSuchElementException {
		Usuario usuario = usuarioRepository.findById(id).get();
		List<String> perfis = new ArrayList<>();
		for (Perfil perfil : usuario.getPerfis()) {
			perfis.add(perfil.getNome());
		}
		return new UsuarioEmEdicaoDAO(usuario, perfis);

	}

	public void editadoUsuario(EdicaoUsuarioDTO usuarioDTO) {

		List<Perfil> perfis = new ArrayList<>();
		List<String> perfisString = usuarioDTO.getPerfil();

		for (String perfil : perfisString) {
			perfis.add(perfilRepository.findByNome(perfil));
		}

		Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).get();
		usuario = usuarioDTO.toUsuario(usuario, perfis);
		usuarioRepository.save(usuario);
	}

	public void deletaUsuario(long id) {
		try {
			Usuario usuario = usuarioRepository.findById(id).get();
			usuarioRepository.delete(usuario);
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}

	public boolean primeiroAdmin() {

		Perfil perfil = perfilRepository.findByNome("ADMINISTRACAO");

		Boolean existe = usuarioRepository.existsByPerfis(perfil);
		return existe;
	}

	public Model criarPrimeiroAdmin(Model model) {

		String login = String.valueOf(new Random().nextInt(9999));
		String senha = String.valueOf(new Random().nextInt(9999));
		criarUsuarioAdmin(login, senha);

		model.addAttribute("login", login);
		model.addAttribute("senha", senha);
		return model;
	}

	public Model criarRecuperacao(Model model) {
		String login = String.valueOf(new Random().nextInt(9999));
		String senha = String.valueOf(new Random().nextInt(9999));
		criarUsuarioAdmin(login, senha);
		
		model.addAttribute("login", login);
		model.addAttribute("senha", senha);
		return model;
	}
	
	private void criarUsuarioAdmin(String login, String senha) {
		NovoUsuarioDTO novoUsuarioDTO = new NovoUsuarioDTO();

		ArrayList<String> perfis = new ArrayList<>();
		perfis.add("ADMINISTRACAO");

		novoUsuarioDTO.setNome("Recuperacao");
		novoUsuarioDTO.setUsuario(login);
		novoUsuarioDTO.setSenha(senha);
		novoUsuarioDTO.setPerfil(perfis);

		adicionaUsuario(novoUsuarioDTO);
	}

	public boolean senhasIguais(NovaSenhaDTO novaSenhaDTO) {
		return novaSenhaDTO.getSenha().equals(novaSenhaDTO.getConfirmacao());
	}

	public void trocaDeSenha(NovaSenhaDTO novaSenhaDTO) {
		Usuario usuario = usuarioRepository.findById(novaSenhaDTO.getId()).get();
		usuario = novaSenhaDTO.toUsuario(usuario);
		usuarioRepository.save(usuario);
		
	}

}
