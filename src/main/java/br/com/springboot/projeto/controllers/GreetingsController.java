package br.com.springboot.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.projeto.model.Usuario;
import br.com.springboot.projeto.repository.UsuarioRepository;


@RestController // mapea uma requisição mapeando a requisição(url)
public class GreetingsController {
	
	@Autowired //injeção de dependencia
	private UsuarioRepository usuarioRepository;
	
	
	
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    
    public String greetingText(@PathVariable String name) {
        return "Projeto Sprigboot API: " + name + "!";
    }
    
    @RequestMapping(value = "/olaMundo/{nome}", method = RequestMethod.GET) //mapeamento de requisição pega os valores intercepta url
    @ResponseStatus(HttpStatus.OK)
    public String metodoRetorno2(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario);
    	
    	return "Olá João Pedro " + nome;
    }
    
    @GetMapping(value = "listatodos") //Primeiro método de API 
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listUsuario(){ //retorna uma lista de usuarios
    
    	List<Usuario> usuarios = usuarioRepository.findAll(); //executa consulta no banco e busca todos
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retornar a lista em JSON
    			
    }
    
    @PostMapping(value = "salvar") //mapeamento de salvar //ENDPOINT
    @ResponseBody //retorna descrição da resposta
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario){ //recebe os dados para salvar, injeta na classe usuario
    	
    	if(usuario.getNome() == null ) {
    		
    		return new ResponseEntity<String>("É obrigatório informar Nome para salvar os dados", HttpStatus.OK);
    	}
    	else {
    		Usuario user = usuarioRepository.save(usuario); //retorna entidade e salva no banco 
    		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED); // retornando o que foi salvo e o status de execução
    	}
    }
    
    @DeleteMapping(value = "delete") //mapeamento de deletar
    @ResponseBody //descrição da resposta
    public ResponseEntity<String> delete(@RequestParam (name = "iduser") Long iduser){ // recebe o id do usuario que vai ser deletado
    	
    	usuarioRepository.deleteById(iduser); //deletando no banco pelo id //ENDPOINT
    	
    	return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK); // retorna uma string se for deletado
    }
    
    @GetMapping(value = "buscaruserid") //mapeamento buscar usuario por id
    @ResponseBody //descrição da resposta
    public ResponseEntity<Usuario> buscaruserid(@RequestParam (name = "iduser") Long iduser){ // recebe o id consulta e retorna
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get(); //buscando por id 
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); //retorna pra tela esse usuario
    
    }
    
    @PutMapping(value = "atualizar") //mapeamento de atualizar
    @ResponseBody //descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ // recebe todo o usuario e atualiza com os novos dados no objeto
    	
    	if(usuario.getId() == null ) { // validação para assegurar que receba um id
    		
    		return new ResponseEntity<String>("É obrigatório informar id para atualizar os dados", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario); //atualizando no banco os novos dados do usuario
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK); //retorna pra tela todo o usuario atualizado
    	
    }
    @GetMapping(value = "buscarPorNome") //mapeamento buscando por nome
    @ResponseBody //descrição da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam (name = "name") String name){ //recebe o ou os nomes por parametro e trata
    	
    	List<Usuario> usuarios = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); //busca por nome executando a query e retorna um ou varios
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retorna pra tela o resultado

    } 
    
}


