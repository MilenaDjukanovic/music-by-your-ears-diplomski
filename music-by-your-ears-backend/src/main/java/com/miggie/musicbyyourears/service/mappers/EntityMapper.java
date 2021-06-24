package com.miggie.musicbyyourears.service.mappers;

import java.util.List;

/**
 * Interface that allows mapping between entities and DTO objects.
 *
 * @author mdjukanovic
 */
public interface EntityMapper <D, E>{

    /**
     * Maps entity to DTO
     * @param dto DTO
     * @return Entity
     */
    E toEntity(D dto);

    /**
     * Maps DTO to entity
     * @param entity Entity
     * @return DTO
     */
    D toDto(E entity);

    /**
     * Maps list of DTOs to list of Entities
     * @param dtoList DTO list
     * @return list of entities
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Maps list of Entities to DTOs
     * @param entityList Entity list
     * @return list of DTOs
     */
    List<D> toDto(List<E> entityList);
}
