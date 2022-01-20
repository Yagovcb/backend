package br.com.bcbdigital.user_api.repository;

import br.com.bcbdigital.user_api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe Repository da entidade {@link User}
 * <p>
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpf(String cpf);

    List<User> queryByNomeLike(String name);

    Page<User> findAll(Pageable pageable);

    User findByCpfAndKey(String cpf, String key);

    boolean existsUserByCpfAndNome(String cpf, String name);

}
