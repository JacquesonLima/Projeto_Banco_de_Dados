package br.project_pbd.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.project_pbd.api.domain.LocacaoEquipamento;

@Repository
public interface LocacaoEquipamentoRepository extends JpaRepository<LocacaoEquipamento, Long> {

}