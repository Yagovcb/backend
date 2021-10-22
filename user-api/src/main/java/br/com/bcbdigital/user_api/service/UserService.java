package br.com.bcbdigital.user_api.service;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.backend.dtos.exceptions.UserNotFoundException;
import br.com.bcbdigital.user_api.model.User;
import br.com.bcbdigital.user_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *  {@link Service} controller para gerenciar as  ações do controller {@link br.com.bcbdigital.user_api.controller.UserController}.
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();


    /**
     * Método responsavel por retornar todos os users
     * @param pageable paginação informada
     * @return um {@link Page<UserDTO>} com todos os registros da entidade {@link UserDTO} criados
     * */
    public List<UserDTO> getAll(Pageable pageable) {
        List<User> usuarios = repository.findAll(pageable).getContent();
        return usuarios.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Método responsavel por retornar um {@link UserDTO} especifico, dado seu id
     *
     * @param userId do {@link UserDTO} que está se buscando
     *
     * @return um {@link UserDTO} especifico
     * */
    public UserDTO findById(long userId) {
        Optional<User> usuario = repository.findById(userId);
        return usuario.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }

    /**
     * Método responsavel por salvar um {@link User} especifico, dado seu {@link UserDTO}
     *
     * @param userDTO entidade {@link UserDTO} que será persistida
     *
     * @return um {@link UserDTO} já persistido no banco
     * */
    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());
        User user = repository.save(modelMapper.map(userDTO, User.class));
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Método responsavel por deletar um {@link User} dado seu id
     *
     * @param userId do {@link UserDTO} que será deletado
     *
     * @return o {@link DetalheRespostaDTO} informando que o produto foi deletado
     * */
    public DetalheRespostaDTO delete(long userId) {
        Optional<User> user = repository.findById(userId);
        user.ifPresent(value -> repository.delete(value));
        return new DetalheRespostaDTO("Usuario deletado com sucesso", 200, LocalDate.now());
    }

    /**
     * Método responsavel por retornar um {@link UserDTO} especifico, dado seu cpf
     *
     * @param cpf do {@link UserDTO} que está se buscando
     *
     * @throws UserNotFoundException quando não achar nenhum funcionario
     *
     * @return um {@link UserDTO} especifico
     * */
    public UserDTO findByCpf(String cpf) {
        User user = repository.findByCpf(cpf);

        if (Objects.nonNull(user)) {
            return modelMapper.map(user, UserDTO.class);
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * Método responsavel por retornar um {@link UserDTO} especifico, dado seu cpf e a chave do usuario
     *
     * @param cpf do {@link UserDTO} que está se buscando
     * @param key do {@link UserDTO}
     *
     * @throws UserNotFoundException quando não achar nenhum funcionario
     *
     * @return um {@link UserDTO} especifico
     * */
    public UserDTO findByCpfAndKey(String cpf, String key) {
        User user = repository.findByCpfAndKey(cpf, key);
        if (Objects.nonNull(user)) {
            return modelMapper.map(user, UserDTO.class);
        }
        throw new UserNotFoundException();
    }

    /**
     * Método responsavel por Retornar uma {@link List<UserDTO>} com base no parametros que serão utilizados no filtro
     *
     * @param name do usuario para fazer a busca
     *
     * @return uma {@link List<UserDTO>} todos os produtos que se encaixem no filtro
     * */
    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = repository.queryByNomeLike(name);
        return usuarios.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}

