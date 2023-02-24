package org.nisum.challenge.mapper;

import lombok.NoArgsConstructor;
import org.nisum.challenge.core.entity.PhoneEntity;
import org.nisum.challenge.core.entity.UserEntity;
import org.nisum.challenge.core.model.PhoneModel;
import org.nisum.challenge.core.model.UserCreationModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
public class UserEntityMapper {

    public UserEntity modelToEntity(UserCreationModel userCreationModel, String encrypPass) {
        if ( userCreationModel == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( userCreationModel.getUsername() );
        userEntity.setEmail( userCreationModel.getEmail() );
        userEntity.setPassword( encrypPass );
        userEntity.setToken( userCreationModel.getToken() );
        userEntity.setPhones( phoneModelListToPhoneEntityList( userCreationModel.getPhones() ) );

        return userEntity;
    }

    protected List<PhoneEntity> phoneModelListToPhoneEntityList(List<PhoneModel> list) {
        if ( list == null ) {
            return Collections.emptyList();
        }

        List<PhoneEntity> list1 = new ArrayList<>( list.size() );
        for ( PhoneModel phoneModel : list ) {
            list1.add( phoneModelToPhoneEntity( phoneModel ) );
        }

        return list1;
    }

    protected PhoneEntity phoneModelToPhoneEntity(PhoneModel phoneModel) {
        if ( phoneModel == null ) {
            return null;
        }

        PhoneEntity phoneEntity = new PhoneEntity();

        phoneEntity.setNumber( phoneModel.getNumber() );
        phoneEntity.setCityCode( phoneModel.getCityCode() );
        phoneEntity.setCountryCode( phoneModel.getCountryCode() );

        return phoneEntity;
    }
}
