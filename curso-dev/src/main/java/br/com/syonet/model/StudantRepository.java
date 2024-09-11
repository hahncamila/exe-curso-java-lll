package br.com.syonet.model;

import java.util.List;

public interface StudantRepository {
  Studant create(Studant studant);
  List<Studant> listAll();
  Studant update(Studant studant);
  void delete(long id);
  List<Studant> findByName(String name);
  Studant findById(long id);
}

