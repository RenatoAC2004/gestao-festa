package br.gov.sp.etec.gestaofesta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.etec.gestaofesta.model.Login;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public ModelAndView abrirLogin() {		
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@PostMapping("/login")
	public String enviarLogin(Login login) {		
			
		if(login.getEmail().equals("admin@hotmail.com") && login.getSenha().equals("123")) {
			return "redirect:/convidados";
		}
		return "redirect:/";
	}
	
}
