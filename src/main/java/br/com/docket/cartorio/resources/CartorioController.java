package br.com.docket.cartorio.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/novo")
	public ModelAndView create(Cartorio cartorio) {
		cartorioService.createCartorio(cartorio);
		return index();
	}
	
	@GetMapping("/cartorios")
	public ResponseEntity<List<CartorioDTO>> readAllCartorios() {
		List<Cartorio> list = cartorioService.findAllCartorios();
		List<CartorioDTO> dto = list.stream().map(cartorio -> new CartorioDTO(cartorio.getId(), cartorio.getNome())).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	}

}
