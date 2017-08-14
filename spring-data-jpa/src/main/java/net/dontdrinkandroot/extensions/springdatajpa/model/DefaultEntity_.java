package net.dontdrinkandroot.extensions.springdatajpa.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DefaultEntity.class)
public abstract class DefaultEntity_
{
    public static volatile SingularAttribute<DefaultEntity, Long> id;
}
