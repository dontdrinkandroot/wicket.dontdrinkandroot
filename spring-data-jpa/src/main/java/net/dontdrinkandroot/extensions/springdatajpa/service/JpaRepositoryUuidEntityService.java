package net.dontdrinkandroot.extensions.springdatajpa.service;

import net.dontdrinkandroot.extensions.springdatajpa.model.UuidEntity;
import net.dontdrinkandroot.extensions.springdatajpa.repository.JpaUuidRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class JpaRepositoryUuidEntityService<T extends UuidEntity<ID>, ID extends Serializable, R extends JpaUuidRepository<T, ID> & JpaSpecificationExecutor<T>> extends JpaRepositoryEntityService<T, ID, R> implements UuidEntityService<T, ID>
{
    protected JpaRepositoryUuidEntityService()
    {
        /* Reflection Instantiation */
    }

    public JpaRepositoryUuidEntityService(R repository)
    {
        super(repository);
    }

    @Override
    public T findByUuid(String uuid)
    {
        return this.getRepository().findByUuid(uuid);
    }
}
