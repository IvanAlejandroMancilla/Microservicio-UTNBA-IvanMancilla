package com.ivanmancilla.customerservice.mapper;

import java.util.List;

public interface GenericMapper<E, REQ, RES> {

    E toEntity(REQ requestDto);

    RES toResponseDTO(E entity);

    List<RES> toResponseDTOList(List<E> entityList);
}
