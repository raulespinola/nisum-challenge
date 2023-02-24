package org.nisum.challenge.mapper;

import org.mapstruct.Mapper;
import org.nisum.challenge.core.entity.PhoneEntity;
import org.nisum.challenge.core.model.PhoneModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneModel entityToModel(PhoneEntity phoneEntity);
    List<PhoneModel> phoneEntityListToPhoneModelList(List<PhoneEntity> list);
}
