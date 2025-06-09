package com.dantas.springupskilling.repository;

import com.dantas.springupskilling.entity.Lawyer;
import com.dantas.springupskilling.projection.LawyerInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

    @Query(nativeQuery = true,
    value = """
        (select name, customers_number
        from lawyers
        where customers_number = (select max(customers_number)
                                  from lawyers))
    
        UNION ALL
    
        (select name, customers_number
        from lawyers
        where customers_number = (select min(customers_number)
                                  from lawyers))
        union all
    
        (select 'Average', round(avg(customers_number))
        from lawyers);
    """)
    List<LawyerInfoProjection> searchLaywers();
}
