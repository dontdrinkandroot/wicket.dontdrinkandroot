package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface EntityLoader
{
    <T extends Entity<ID>, ID extends Serializable> T find(Class<T> clazz, ID id);

    <T extends Entity<ID>, ID extends Serializable> T load(Class<T> clazz, ID id);
}
