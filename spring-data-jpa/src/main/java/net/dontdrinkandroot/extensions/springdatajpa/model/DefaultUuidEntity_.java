package net.dontdrinkandroot.extensions.springdatajpa.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DefaultUuidEntity.class)
public abstract class DefaultUuidEntity_ extends net.dontdrinkandroot.extensions.springdatajpa.model.DefaultEntity_
{
    public static volatile SingularAttribute<DefaultUuidEntity, String> uuid;
}
