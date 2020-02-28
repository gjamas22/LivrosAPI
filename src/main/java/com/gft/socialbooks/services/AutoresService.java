package com.gft.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.socialbooks.domain.Autor;
import com.gft.socialbooks.repository.AutoresRepository;
import com.gft.socialbooks.services.exceptions.AutorExistenteException;
import com.gft.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Optional<Autor> a = autoresRepository.findById(autor.getId());
			
			if(a != null) {
				throw new AutorExistenteException("O autor já existe");
			}
		}
		return autoresRepository.save(autor);
		
	}
	
	public Optional<Autor> buscar(Long id) {
		Optional<Autor> autor = autoresRepository.findById(id);
		
		if(autor.isEmpty()) {
			throw new AutorNaoEncontradoException("O autor não pode ser encontrado!");
		}
		return autor;
	}
	
}
