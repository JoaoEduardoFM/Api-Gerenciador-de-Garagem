package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	public List<Veiculo> findByPlaca(@Param("placa") String placa);
	
	public List<Veiculo> findByCheckInOut(@Param("checkInOut") Boolean checkInOut);
	
	public List<Veiculo> findByMarca(@Param("marca") String marca);
	
	public List<Veiculo> findByMarcaAndModelo(String marca, String modelo);	
	
}
