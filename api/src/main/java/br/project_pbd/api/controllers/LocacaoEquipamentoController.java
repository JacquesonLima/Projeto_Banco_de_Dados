package br.project_pbd.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.project_pbd.api.domain.LocacaoEquipamento;
import br.project_pbd.api.repositories.LocacaoEquipamentoRepository;

@RestController
@RequestMapping("/locacoes-equipamentos")
public class LocacaoEquipamentoController {

  private final LocacaoEquipamentoRepository locacaoEquipamentoRepository;

  public LocacaoEquipamentoController(LocacaoEquipamentoRepository locacaoEquipamentoRepository) {
    this.locacaoEquipamentoRepository = locacaoEquipamentoRepository;
  }

  @GetMapping
  public List<LocacaoEquipamento> listarLocacoesEquipamentos() {
    return locacaoEquipamentoRepository.findAll();
  }
}