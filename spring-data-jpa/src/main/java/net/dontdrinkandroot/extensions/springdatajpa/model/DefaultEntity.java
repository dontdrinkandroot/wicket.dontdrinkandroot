package net.dontdrinkandroot.extensions.springdatajpa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@MappedSuperclass
public class DefaultEntity implements Entity<Long>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId()
    {
        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        DefaultEntity that = (DefaultEntity) o;

        return this.id != null ? this.id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode()
    {
        return this.id != null ? this.id.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return this.getClass() + "[id=" + this.id + "]";
    }
}
