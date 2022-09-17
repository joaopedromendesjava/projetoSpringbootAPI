package br.com.springboot.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.projeto.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ // interface para manipulação em banco
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarPorNome(String name); // recebe um nome pelo parametro e joga dentro da query 
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update Usuario u set u.idade = ?1 where u.nome = joaoenathalia")
	public List<Usuario> updateIdade(int idade);

}
