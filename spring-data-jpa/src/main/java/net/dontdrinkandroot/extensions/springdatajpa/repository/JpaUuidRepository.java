package net.dontdrinkandroot.extensions.springdatajpa.repository;

import net.dontdrinkandroot.extensions.springdatajpa.model.UuidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface JpaUuidRepository<T extends UuidEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID>
{
    T findByUuid(String uuid);
}
