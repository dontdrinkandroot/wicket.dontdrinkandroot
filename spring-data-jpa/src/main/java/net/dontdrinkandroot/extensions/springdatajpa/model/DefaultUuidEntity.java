package net.dontdrinkandroot.extensions.springdatajpa.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@MappedSuperclass
public class DefaultUuidEntity extends DefaultEntity implements UuidEntity<Long>
{
    @Column(length = 36, nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Override
    public String getUuid()
    {
        return this.uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}
