package com.Ecommerce.UserAPI.business.mapstruct;

import com.Ecommerce.UserAPI.business.dto.Request.UsuarioRequestDTO;
import com.Ecommerce.UserAPI.business.dto.Response.UsuarioResponseDTO;
import com.Ecommerce.UserAPI.infrastructure.entities.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    //@Mapping(source = "nome", target = "name") <- caso o nome do DTO seja diferente da entity (esclarece aqui)
   @Mapping(target = "id", ignore = true)
   UserEntity paraUsuarioEntity(UsuarioRequestDTO dto);


    UsuarioResponseDTO paraResponseDTO(UserEntity entity);

    List<UsuarioResponseDTO> paraListaUsuarioResponseDTO(List<UserEntity> lista);

}
