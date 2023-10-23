package cat.itacademy.barcelonactiva.abdellaoui.fethi.s05.t01.n01.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cat.itacademy.barcelonactiva.abdellaoui.fethi.s05.t01.n01.model.domain.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{
	
	@Query("SELECT s FROM Sucursal s WHERE s.nomSucursal LIKE :x")
	public Page<Sucursal> search(@Param("x") String keyword, Pageable pageable);

}
