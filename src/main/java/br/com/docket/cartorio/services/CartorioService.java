package br.com.docket.cartorio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.docket.cartorio.entities.Cartorio;
import br.com.docket.cartorio.repositories.CartorioRepository;

@Service
public class CartorioService {
	
	@Autowired
	private CartorioRepository cartorioRepository;
	
	public Cartorio createCartorio(Cartorio cartorio) {
		return cartorioRepository.save(cartorio);
	}
	
	public Cartorio findCartorioById(Long idCartorio) {
		Optional<Cartorio> cartorio = cartorioRepository.findById(idCartorio);
		return cartorio.get();
	}
	
	public List<Cartorio> findAllCartorios() {
		return cartorioRepository.findAll();
	}
	
	public void updateCartorio(Cartorio cartorio) {
		cartorioRepository.save(cartorio);
	}	
	
	public void deleteCartorio(Long idCartorio) {
		cartorioRepository.deleteById(idCartorio);
	}

}
