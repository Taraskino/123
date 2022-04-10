package pl.edu.wszib.http2.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.wszib.http2.service.model.Plec;
import pl.edu.wszib.http2.service.model.Profile;

import java.util.List;

@Repository
public interface ProfileDao extends JpaRepository<Profile, Integer> {

    List<Profile> findAllByPlec(Plec plec);
}
