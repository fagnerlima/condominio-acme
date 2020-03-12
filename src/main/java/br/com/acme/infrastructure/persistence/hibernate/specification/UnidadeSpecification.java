package br.com.acme.infrastructure.persistence.hibernate.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.acme.domain.model.unidade.Unidade;
import br.com.acme.domain.model.unidade.Unidade_;

public class UnidadeSpecification {

    public static Specification<Unidade> comMultas() {
        return Specification
                .where((root, query, builder) -> builder.isNotEmpty(root.get(Unidade_.MULTAS_UNIDADE)));
    }

    public static Specification<Unidade> semMultas() {
        return Specification
                .where((root, query, builder) -> builder.isEmpty(root.get(Unidade_.MULTAS_UNIDADE)));
    }

}
