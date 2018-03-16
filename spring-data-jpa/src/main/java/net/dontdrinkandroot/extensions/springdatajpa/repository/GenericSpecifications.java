package net.dontdrinkandroot.extensions.springdatajpa.repository;

import net.dontdrinkandroot.extensions.springdatajpa.model.Entity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;
import java.time.LocalDate;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class GenericSpecifications
{
    public static <T extends Entity, V> Specification<T> byAttribute(SingularAttribute<T, V> attribute, V value)
    {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    public static <T extends Entity> Specification<T> byAttributeAndLocalDateRange(
            SingularAttribute<T, LocalDate> localDateAttribute,
            LocalDate startDate,
            LocalDate endDate
    )
    {
        return (root, query, cb) -> {
            Path<LocalDate> localDatePath = root.get(localDateAttribute);
            return cb.and(
                    cb.greaterThanOrEqualTo(localDatePath, startDate),
                    cb.lessThanOrEqualTo(localDatePath, endDate)
            );
        };
    }
}
