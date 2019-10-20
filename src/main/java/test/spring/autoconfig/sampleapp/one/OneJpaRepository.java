package test.spring.autoconfig.sampleapp.one;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OneJpaRepository extends JpaRepository<OneEntity, String> {
}
