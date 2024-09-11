package br.com.syonet.view;

import java.util.List;
import java.util.Scanner;

import br.com.syonet.model.Studant;
import br.com.syonet.service.StudantService;


public class StudantView {

  private int selectedOption;
  private boolean exit;
  private final Scanner scanner;
  private final StudantService service;

  public StudantView(StudantService service, Scanner scanner) {
    this.service = service;
    this.scanner = scanner;
  }

  public void init() {
    System.out.println("Olá, seja bem-vindo ao nosso cadastro de estudantes.");
  }

  public void showOptions() {
    System.out.println("Por favor, selecione uma operação abaixo:");
    System.out.println();
    System.out.println("\t(1) - Criar novo estudante");
    System.out.println("\t(2) - Listar estudantes");
    System.out.println("\t(3) - Atualizar estudante");
    System.out.println("\t(4) - Deletar estudante");
    System.out.println("\t(5) - Buscar estudante por nome");
    System.out.println("\t(6) - Buscar estudante por ID");
    System.out.println("\t(0) - Sair");
  }

  public Integer getSelectedOption() {
    return selectedOption;
  }

  public boolean isExit() {
    return this.exit;
  }

  public void readSelectedOption() {
    String nextLine = this.scanner.nextLine();
    int answer = Integer.parseInt(nextLine);
    this.exit = answer == 0;
    this.selectedOption = answer;
  }

  public void executeSelectedOperation() {
    switch (this.selectedOption) {
      case 1:
        this.initCreationProcess();
        break;
      case 2:
        this.initListProcess();
        break;
      case 3:
        this.initUpdateProcess();
        break;
      case 4:
        this.initDeleteProcess();
        break;
      case 5:
        this.initFindByNameProcess();
        break;
      case 6:
        this.initFindByIdProcess();
        break;
      default:
        System.out.println("Opção inválida. Tente novamente.");
        break;
    }
  }

  private void initCreationProcess() {
    System.out.println("Qual é o nome do estudante?");
    String name = this.scanner.nextLine();
    System.out.println("Qual é o email do estudante?");
    String email = this.scanner.nextLine();
    System.out.println("Qual é a idade do estudante?");
    int age = Integer.parseInt(this.scanner.nextLine());
    System.out.println("Criando novo estudante...");
    Studant studant = new Studant(name, age, email);
    long id = this.service.save(studant);
    System.out.println("O ID do novo estudante é " + id);
  }

  private void initListProcess() {
    List<Studant> students = this.service.listAll();
    if (students != null && !students.isEmpty()) {
      System.out.println("\nID\tNome\tIdade\tEmail");
      for (Studant student : students) {
        System.out.printf("%d\t%s\t%d\t%s\n", student.getId(), student.getName(), student.getAge(), student.getEmail());
      }
    } else {
      System.out.println("Não há estudantes cadastrados.");
    }
  }

  private void initUpdateProcess() {
    System.out.println("Digite o ID do estudante a ser atualizado:");
    long id = Long.parseLong(this.scanner.nextLine());
    Studant existingStudent = this.service.findById(id);
    if (existingStudent != null) {
      System.out.println("Novo nome (deixe em branco para manter '" + existingStudent.getName() + "'):");
      String name = this.scanner.nextLine();
      if (name.isEmpty()) {
        name = existingStudent.getName();
      }
      System.out.println("Novo email (deixe em branco para manter '" + existingStudent.getEmail() + "'):");
      String email = this.scanner.nextLine();
      if (email.isEmpty()) {
        email = existingStudent.getEmail();
      }
      System.out.println("Nova idade (deixe em branco para manter " + existingStudent.getAge() + "):");
      String ageStr = this.scanner.nextLine();
      int age = existingStudent.getAge();
      if (!ageStr.isEmpty()) {
        age = Integer.parseInt(ageStr);
      }
      Studant updatedStudent = new Studant(id, name, age, email);
      this.service.update(updatedStudent);
      System.out.println("Estudante atualizado com sucesso.");
    } else {
      System.out.println("Estudante não encontrado.");
    }
  }

  private void initDeleteProcess() {
    System.out.println("Digite o ID do estudante a ser deletado:");
    long id = Long.parseLong(this.scanner.nextLine());
    this.service.delete(id);
    System.out.println("Estudante deletado com sucesso.");
  }

  private void initFindByNameProcess() {
    System.out.println("Digite o nome do estudante para buscar:");
    String name = this.scanner.nextLine();
    List<Studant> students = this.service.findByName(name);
    if (students != null && !students.isEmpty()) {
      System.out.println("\nID\tNome\tIdade\tEmail");
      for (Studant student : students) {
        System.out.printf("%d\t%s\t%d\t%s\n", student.getId(), student.getName(), student.getAge(), student.getEmail());
      }
    } else {
      System.out.println("Nenhum estudante encontrado com o nome fornecido.");
    }
  }

  private void initFindByIdProcess() {
    System.out.println("Digite o ID do estudante para buscar:");
    long id = Long.parseLong(this.scanner.nextLine());
    Studant student = this.service.findById(id);
    if (student != null) {
      System.out.printf("ID: %d\nNome: %s\nIdade: %d\nEmail: %s\n", student.getId(), student.getName(), student.getAge(), student.getEmail());
    } else {
      System.out.println("Estudante não encontrado.");
    }
  }
}
