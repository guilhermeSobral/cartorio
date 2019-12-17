package br.com.docket.cartorio.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.docket.cartorio.entities.Cartorio;
import br.com.docket.cartorio.entities.dto.CartorioDTO;
import br.com.docket.cartorio.services.CartorioService;

@RestController
public class CartorioController {
	
	@Autowired
	private CartorioService cartorioService;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("cartorios", cartorioService.findAllCartorios());
		return mv;
	}
	
	@GetMapping("/cartorio/novo")
	public ModelAndView form(Cartorio cartorio) {
		ModelAndView mv = new ModelAndView("novo-cartorio");
		mv.addObject("cartorio", cartorio);
		return mv;
	}
	
	@PostMapping("/cartorio/novo")
	public ModelAndView create(Cartorio cartorio) {
		cartorioService.createCartorio(cartorio);
		return index();
	}	
	
	@GetMapping("/atualizar/{id}")
	public ModelAndView updatePage(@PathVariable("id") Long idCartorio) {
		Cartorio cartorio = cartorioService.findCartorioById(idCartorio);
		ModelAndView mv = new ModelAndView("atualiza-cartorio");
		mv.addObject("cartorio", cartorio);
		return mv;
	}
	
	@PostMapping("/atualiza")
	public ModelAndView updateAction(Cartorio cartorio) {
		cartorioService.updateCartorio(cartorio);
		return index();
	}
	
	@GetMapping("/deletar/{id}")
	public ModelAndView deletePage(@PathVariable("id") Long idCartorio) {
		Cartorio cartorio = cartorioService.findCartorioById(idCartorio);
		ModelAndView mv = new ModelAndView("deleta-cartorio");
		mv.addObject("cartorio", cartorio);
		return mv;
	}
	
	@PostMapping("/deletar")
	public ModelAndView deleteAction(Cartorio cartorio) {
		cartorioService.deleteCartorio(cartorio.getId());
		return index();
	}
	 
	@GetMapping("/cartorios")
	public ResponseEntity<List<CartorioDTO>> readAllCartorios() {
		List<Cartorio> list = cartorioService.findAllCartorios();
		List<CartorioDTO> dto = list.stream().map(cartorio -> new CartorioDTO(cartorio.getId(), cartorio.getNome())).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	}

}
