package com.jujubaprojects.api02.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jujubaprojects.api02.Model.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
//   @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %:nome%")
    List<Usuario> findByNome( String nome);
}
