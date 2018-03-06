package net.dontdrinkandroot.extensions.springdatajpa.repository;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class AttributeSpecifications
{
    public static <T extends Entity, V> Specification<T> byAttribute(SingularAttribute<T, V> attribute, V value)
    {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }
}
