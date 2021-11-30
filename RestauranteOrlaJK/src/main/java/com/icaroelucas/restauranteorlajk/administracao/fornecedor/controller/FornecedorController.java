package com.icaroelucas.restauranteorlajk.administracao.fornecedor.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.icaroelucas.restauranteorlajk.administracao.fornecedor.dto.EdicaoFornecedorDTO;
import com.icaroelucas.restauranteorlajk.administracao.fornecedor.dto.NovoFornecedorDTO;
import com.icaroelucas.restauranteorlajk.administracao.fornecedor.service.FornecedorService;
import com.icaroelucas.restauranteorlajk.entities.fornecedor.repository.FornecedorRepository;

@Controller
@RequestMapping("/administracao/fornecedor")
public class FornecedorController {

	@Autowired
	FornecedorRepository fornecedorRepository;

	FornecedorService fornecedorService = new FornecedorService();

	@GetMapping("")
	public String fornecedor(Model model) {
		model = fornecedorService.iniciar(fornecedorRepository).popularModel(model);
		return "administracao/fornecedor/fornecedor";
	}

	@GetMapping("/novo")
	public String adicionaNovoFornecedor() {
		return "administracao/fornecedor/novo";
	}

	@PostMapping("/novo")
	public RedirectView adicionadoNovoAlimento(NovoFornecedorDTO fornecedor) {
		fornecedorService.novoFornecedor(fornecedor);
		return new RedirectView("");
	}

	@GetMapping("/edita")
	public Object editaMesa(Model model, @RequestParam long id) {
		try {
			model = fornecedorService.popularModel(model, id);
			return "administracao/fornecedor/edita";
		} catch (NoSuchElementException e) {
			System.out.println(e);
			return new RedirectView("");
		}
	}

	@PostMapping("/edita")
	public RedirectView editadaFornecedor(EdicaoFornecedorDTO fornecedor) {
		try {
			fornecedorService.editaFornecedor(fornecedor);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}

	@GetMapping("/exclui")
	public RedirectView excluiFornecedor(Model model, @RequestParam String id) {
		try {
			fornecedorService.excluiFornecedor(id);
		} catch (SQLIntegrityConstraintViolationException | DataIntegrityViolationException | EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		return new RedirectView("");
	}

}
