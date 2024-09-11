package br.com.syonet.service;

import java.util.List;
import br.com.syonet.model.Studant;
import br.com.syonet.model.StudantRepository;

public class StudantService {
  private final StudantRepository repository;

  public StudantService(StudantRepository repository) {
    this.repository = repository;
  }

  public long save(Studant studant) {
    if (studant.getId() == 0) {
      Studant newStudant = this.repository.create(studant);
      return newStudant.getId();
    }
    throw new IllegalArgumentException("Id não pode ser atribuído");
  }

  public List<Studant> listAll() {
    return this.repository.listAll();
  }

  public Studant update(Studant studant) {
    if (studant.getId() > 0) {
      return this.repository.update(studant);
    }
    throw new IllegalArgumentException("Id deve ser maior que zero para atualização");
  }

  public void delete(long id) {
    this.repository.delete(id);
  }

  public List<Studant> findByName(String name) {
    return this.repository.findByName(name);
  }

  public Studant findById(long id) {
    return this.repository.findById(id);
  }
}
