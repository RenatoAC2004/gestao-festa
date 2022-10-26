package br.gov.sp.etec.gestaofesta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.etec.gestaofesta.model.Convidado;
import br.gov.sp.etec.gestaofesta.repository.ConvidadoRepository;

@Controller
public class ConvidadosController {
	
	@Autowired
	ConvidadoRepository repository;
	
	@GetMapping("/convidados")
	public ModelAndView listar() {		
		ModelAndView model = new ModelAndView("ListaConvidados");
		List<Convidado> convidados = repository.findAll();
		model.addObject("convidados",convidados);
		model.addObject("convidado",new Convidado());
		return model;
	}
	
	@GetMapping("/excluir/{id}")
	 public ModelAndView excluir(@PathVariable Long id) {
        repository.deleteById(id);        
        ModelAndView view = new ModelAndView("redirect:/convidados");
        return view;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {		
		Convidado convidado = repository.findById(id).get();
		ModelAndView view = new ModelAndView("ListaConvidados");
		view.addObject(convidado);
		
		List<Convidado> convidados = repository.findAll();
		view.addObject("convidados",convidados);
		return view;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Convidado convidado, BindingResult result) {
		ModelAndView model = new ModelAndView("redirect:/convidados");
		if(result.hasErrors()) {
			ModelAndView view = new ModelAndView("ListaConvidados");
			model.addObject(convidado);
			return view;
		}
		
		repository.save(convidado);		
		return model;
	}

}
