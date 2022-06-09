package by.iba.repository;

import by.iba.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractRepository<T extends AbstractEntity, K>
        extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {
}

