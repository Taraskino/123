package pl.edu.wszib.http2.service;

import org.springframework.stereotype.Service;
import pl.edu.wszib.http2.service.common.CrudService;
import pl.edu.wszib.http2.service.model.Produkt;
import pl.edu.wszib.http2.service.model.ToDo;

@Service
public class ToDoService extends CrudService<ToDo, Integer> {
  public ToDoService() {
    super(Integer.class);
  }
}
