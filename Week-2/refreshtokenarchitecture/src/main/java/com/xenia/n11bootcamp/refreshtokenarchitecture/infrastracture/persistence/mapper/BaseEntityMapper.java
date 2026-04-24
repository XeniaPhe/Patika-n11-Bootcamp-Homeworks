package com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.mapper;

import java.util.List;
import java.util.Optional;

public abstract class BaseEntityMapper<DomainT, PersistenceT> {
    public abstract DomainT toDomain(PersistenceT persistenceEntity);
    public abstract PersistenceT fromDomain(DomainT domainEntity);

    public Optional<DomainT> toDomain(Optional<PersistenceT> persistenceEntity) {
        return persistenceEntity.map(this::toDomain);
    }

    public Optional<PersistenceT> fromDomain(Optional<DomainT> domainEntity) {
        return domainEntity.map(this::fromDomain);
    }

    public List<DomainT> toDomainEntities(List<PersistenceT> persistenceEntities) {
        return persistenceEntities.stream()
            .map(this::toDomain)
            .toList();
    }

    public List<PersistenceT> fromDomainEntities(List<DomainT> domainEntities) {
        return domainEntities.stream()
            .map(this::fromDomain)
            .toList();
    }
}
