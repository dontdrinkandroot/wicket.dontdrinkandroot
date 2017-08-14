package net.dontdrinkandroot.extensions.springdatajpa.model;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UuidEntity<ID extends Serializable> extends Entity<ID>
{
    String getUuid();
}
