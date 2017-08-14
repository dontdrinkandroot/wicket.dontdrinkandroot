package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.UuidEntity;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UuidEntityService<T extends UuidEntity<ID>, ID extends Serializable> extends EntityService<T, ID>
{
    T findByUuid(String uuid);
}
