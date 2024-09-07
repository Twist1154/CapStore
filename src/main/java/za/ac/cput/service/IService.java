package za.ac.cput.service;

import java.util.List;

/**
 * IService.java
 *
 * A generic service interface that provides basic CRUD operations.
 *
 * @param <T>  The type of the entity.
 * @param <ID> The type of the entity ID.
 *
 * Author: Rethabile Ntsekhe
 * Date: 07-Sep-24
 */
public interface IService<T, ID> {

    /**
     * Creates a new entity.
     *
     * @param t The entity to create.
     * @return The created entity.
     */
    T create(T t);

    /**
     * Reads an entity by its ID.
     *
     * @param id The ID of the entity to read.
     * @return The entity if found, or null if not.
     */
    T read(ID id);

    /**
     * Updates an existing entity.
     *
     * @param t The updated entity.
     * @return The updated entity.
     */
    T update(T t);

    /**
     * Finds all entities.
     *
     * @return A list of all entities.
     */
    List<T> findAll();
}

