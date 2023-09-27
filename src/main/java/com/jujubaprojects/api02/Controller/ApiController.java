package com.jujubaprojects.api02.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.jujubaprojects.api02.Model.Usuario;
import com.jujubaprojects.api02.Repository.UsuarioRepository;


@RestController
public class ApiController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @RequestMapping(value = "/mostrarnome/{nome}",method = RequestMethod.GET)
 // @GetMapping("/mostrarnome/{nome}")
    public String text(@PathVariable String nome){
        return "Curso Spring Boot API "+nome;

    }

  @RequestMapping(value = "/olamundo/{nome}",method = RequestMethod.GET)
 // @GetMapping("/mostrarnome/{nome}")
    public String retornaOlaMundo(@PathVariable String nome){

        Usuario usuario = new Usuario(); //Instânciando/criando Usuário
        usuario.setNome(nome);/*chama o nome do Usuario */

        usuarioRepository.save(usuario); /* Salva usuário no Banco de dados */
        return "Olá mundo "+nome;
    }
    
    @GetMapping(value ="/listardados")/*Nosso primeiro método de API */
    @ResponseBody /* Retorna os dados para o corpo da Resposta*/
    public ResponseEntity< List<Usuario>>  listaUsuaários(){

         List<Usuario> usuarios = usuarioRepository.findAll();/*Executa a consulta no banco de dados*/

        return new ResponseEntity< List<Usuario>> (usuarios, HttpStatus.OK); /*Retorna a lista JSON */

    }
    
    @PostMapping("/salvar")/*Mapeia na URL */
    @ResponseBody /* Retorna os dados para o corpo da Resposta*/
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario){/*Receber os dados para salvar */

    if(usuario.getId() == 0){
            return new ResponseEntity<String> ("digite um id válido" , HttpStatus.OK);

    }else{

      Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario> (user , HttpStatus.OK);
       }
    }

    @PutMapping("/atualizar")/*Mapeia na URL */
    @ResponseBody /* Retorna os dados para o corpo da Resposta*/
    public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario){

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);

    }


    @DeleteMapping("/deletar")
    @ResponseBody /* Retorna os dados para o corpo da Resposta*/
    public ResponseEntity<?> deletar(@RequestParam long id){

        usuarioRepository.deleteById(id);

        return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);

    }

    @GetMapping("/buscarId/{id}")
    @ResponseBody /* Retorna os dados para o corpo da Resposta*/
    public ResponseEntity<Usuario> buscarId(@PathVariable long id){

        Usuario usuario = usuarioRepository.findById(id).get();
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);

    }


    @GetMapping("/buscarNome/{nome}")
    @ResponseBody /* Retorna os dados para o corpo da Resposta*/
    public ResponseEntity< List<Usuario>> buscarPorNome(@PathVariable String nome){

        List<Usuario> usuario = usuarioRepository.findByNome(nome);

        return new ResponseEntity< List<Usuario>>(usuario, HttpStatus.OK);
    }
}
