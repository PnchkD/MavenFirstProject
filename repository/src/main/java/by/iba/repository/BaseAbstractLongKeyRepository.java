package by.iba.repository;

import by.iba.entity.AbstractEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractLongKeyRepository<T extends AbstractEntity>
        extends BaseAbstractRepository<T, Long> {
}

