package yte.intern.spring.project.use_cases.register_to_the_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import yte.intern.spring.project.use_cases.common.entity.NormalUser;
import yte.intern.spring.project.use_cases.register_to_the_system.dto.RegisterSystemRequest;

@Mapper(componentModel = "spring")
public interface RegisterSystemMapper {
    
    RegisterSystemRequest mapToDto(NormalUser user);

    List<RegisterSystemRequest> mapToDto(List<NormalUser> userList);

    NormalUser mapToEntity(RegisterSystemRequest registerRequest);

    List<NormalUser> mapToEntity(List<RegisterSystemRequest> registerRequestList);
}