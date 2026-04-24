package com.xenia.n11bootcamp.refreshtokenarchitecture.application.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<EntityT, IdT> {
    EntityT save(EntityT entity);
    Optional<EntityT> findById(IdT id);
    List<EntityT> findAll();
    void deleteById(IdT id);
}
