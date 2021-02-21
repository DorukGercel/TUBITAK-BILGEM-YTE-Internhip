package yte.intern.spring.project.use_cases.register_to_the_system.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.spring.project.use_cases.common.entity.NormalUser;
import yte.intern.spring.project.use_cases.register_to_the_system.dto.RegisterSystemRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-07T17:27:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class RegisterSystemMapperImpl implements RegisterSystemMapper {

    @Override
    public RegisterSystemRequest mapToDto(NormalUser user) {
        if ( user == null ) {
            return null;
        }

        RegisterSystemRequest registerSystemRequest = new RegisterSystemRequest();

        registerSystemRequest.setUsername( user.getUsername() );
        registerSystemRequest.setPassword( user.getPassword() );
        registerSystemRequest.setName( user.getName() );
        registerSystemRequest.setSurname( user.getSurname() );
        registerSystemRequest.setEmail( user.getEmail() );
        registerSystemRequest.setTcKimlikNo( user.getTcKimlikNo() );

        return registerSystemRequest;
    }

    @Override
    public List<RegisterSystemRequest> mapToDto(List<NormalUser> userList) {
        if ( userList == null ) {
            return null;
        }

        List<RegisterSystemRequest> list = new ArrayList<RegisterSystemRequest>( userList.size() );
        for ( NormalUser normalUser : userList ) {
            list.add( mapToDto( normalUser ) );
        }

        return list;
    }

    @Override
    public NormalUser mapToEntity(RegisterSystemRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        NormalUser normalUser = new NormalUser();

        normalUser.setUsername( registerRequest.getUsername() );
        normalUser.setPassword( registerRequest.getPassword() );
        normalUser.setName( registerRequest.getName() );
        normalUser.setSurname( registerRequest.getSurname() );
        normalUser.setEmail( registerRequest.getEmail() );
        normalUser.setTcKimlikNo( registerRequest.getTcKimlikNo() );

        return normalUser;
    }

    @Override
    public List<NormalUser> mapToEntity(List<RegisterSystemRequest> registerRequestList) {
        if ( registerRequestList == null ) {
            return null;
        }

        List<NormalUser> list = new ArrayList<NormalUser>( registerRequestList.size() );
        for ( RegisterSystemRequest registerSystemRequest : registerRequestList ) {
            list.add( mapToEntity( registerSystemRequest ) );
        }

        return list;
    }
}
