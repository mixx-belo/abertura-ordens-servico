package br.com.ordensservico.aberturaordensservico.repository;

import br.com.ordensservico.aberturaordensservico.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
    
}
