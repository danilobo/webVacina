package br.com.encoding.vacinao.repositories;

import br.com.encoding.vacinao.models.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
