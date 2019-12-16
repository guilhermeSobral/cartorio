package br.com.docket.cartorio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.docket.cartorio.entities.Cartorio;
import br.com.docket.cartorio.services.CartorioService;

@RestController
@RequestMapping("/cartorios")
public class CartorioController {
	
	@Autowired
	private CartorioService cartorioService;
	
	public ResponseEntity<List<Cartorio>> readAllCartorios() {
		List<Cartorio> list = cartorioService.findAllCartorios();
		return ResponseEntity.ok().body(list);
	}

}
