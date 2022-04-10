package pl.edu.wszib.http2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.http2.service.common.CrudService;
import pl.edu.wszib.http2.service.dao.ProfileDao;
import pl.edu.wszib.http2.service.exception.NotFoundException;
import pl.edu.wszib.http2.service.model.Plec;
import pl.edu.wszib.http2.service.model.Profile;

import java.util.List;

@Service
public class ProfileService {

  @Autowired
  public ProfileDao dao;

  public Profile get(Integer id) {
    return dao.findById(id).orElseThrow(() -> new NotFoundException());
  }
  public List<Profile> list(Plec plec) {
    if(plec == null){
      return dao.findAll();
    } else {
      return dao.findAllByPlec(plec);
    }

  }
  public void delete(Integer id) {
    dao.deleteById(id);
  }
  public Profile create(Profile newProfile) {
    newProfile.setId(null);
    return dao.save(newProfile);
  }
  public Profile update(Profile updateProfile) {
    Profile existing = get(updateProfile.getId());
    return dao.save(updateProfile);
  }
}


