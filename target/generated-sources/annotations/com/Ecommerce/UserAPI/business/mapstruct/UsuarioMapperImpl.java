package com.Ecommerce.UserAPI.business.mapstruct;

import com.Ecommerce.UserAPI.business.dto.Request.UsuarioRequestDTO;
import com.Ecommerce.UserAPI.business.dto.Response.UsuarioResponseDTO;
import com.Ecommerce.UserAPI.infrastructure.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T18:13:31-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Debian)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UserEntity paraUsuarioEntity(UsuarioRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setNome( dto.getNome() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPassword( dto.getPassword() );

        return userEntity;
    }

    @Override
    public UsuarioResponseDTO paraResponseDTO(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setVerificationCode( entity.getVerificationCode() );
        if ( entity.getId() != null ) {
            usuarioResponseDTO.setId( entity.getId() );
        }
        usuarioResponseDTO.setNome( entity.getNome() );
        usuarioResponseDTO.setEmail( entity.getEmail() );
        usuarioResponseDTO.setRole( entity.getRole() );

        return usuarioResponseDTO;
    }

    @Override
    public List<UsuarioResponseDTO> paraListaUsuarioResponseDTO(List<UserEntity> lista) {
        if ( lista == null ) {
            return null;
        }

        List<UsuarioResponseDTO> list = new ArrayList<UsuarioResponseDTO>( lista.size() );
        for ( UserEntity userEntity : lista ) {
            list.add( paraResponseDTO( userEntity ) );
        }

        return list;
    }
}
