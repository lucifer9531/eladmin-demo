package com.google.base;

import java.util.List;
import java.util.Set;

/**
 * @author iris
 */
public interface BaseConvert<D, E> {

    /**
     * DTO转Entity
     * @param dto /
     * @return /
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     * @param entity /
     * @return /
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    List <E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    List <D> toDto(List<E> entityList);

    /**
     * DTO集合转Entity集合
     * @param dtoList /
     * @return /
     */
    Set <E> toEntity(Set<D> dtoList);

    /**
     * Entity集合转DTO集合
     * @param entityList /
     * @return /
     */
    Set <D> toDto(Set<E> entityList);
}
