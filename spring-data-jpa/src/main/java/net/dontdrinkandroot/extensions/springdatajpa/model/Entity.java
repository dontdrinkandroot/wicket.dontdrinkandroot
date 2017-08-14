package net.dontdrinkandroot.extensions.springdatajpa.model;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface Entity<ID extends Serializable> extends Serializable
{
    ID getId();
}
