package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.model.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	public List<Veiculo> findByPlacaContainingIgnoreCase(@Param("placa") String placa);
	
	public List<Veiculo> findByCheckInOut(@Param("checkInOut") Boolean checkInOut);
	
	public List<Veiculo> findByMarcaContainingIgnoreCase(@Param("marca") String marca);
	
	public List<Veiculo> findByMarcaContainingIgnoreCaseAndModeloContainingIgnoreCase(@Param("marca") String marca, @Param("modelo") String modelo);	
	
}
