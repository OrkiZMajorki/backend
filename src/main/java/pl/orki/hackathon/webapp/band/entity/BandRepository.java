package pl.orki.hackathon.webapp.band.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BandRepository extends JpaRepository<Band, Long>, JpaSpecificationExecutor<Band> {
}
