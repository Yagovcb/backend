package br.com.bcbdigital.shopping_api.repository.impl;

import br.com.bcbdigital.backend.dtos.dto.ShopReportDTO;
import br.com.bcbdigital.shopping_api.model.Shop;
import br.com.bcbdigital.shopping_api.repository.ReportRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *  Classe de implementação do Repository para montar dados de relatorio
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
        StringBuilder	sb	=	new	StringBuilder();
        sb.append("select s	");
        sb.append("from	shop s ");
        sb.append("where s.date	>= :dataInicio");

        if (Objects.nonNull(dataFim)) sb.append("and s.date <= :dataFim");

        if	(Objects.nonNull(valorMinimo)) sb.append("and s.total <= :valorMinimo");

        Query query	=	entityManager.createQuery(sb.toString());
        query.setParameter("dataInicio",	dataInicio);

        if (Objects.nonNull(dataFim)) query.setParameter("dataFim",	dataFim);

        if (Objects.nonNull(valorMinimo)) query.setParameter("valorMinimo",	valorMinimo);

        return	query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
        StringBuilder sb = new StringBuilder();

        sb.append("select count(sp.id), sum(sp.total), avg(sp.total)");
        sb.append("from	shopping.shop sp");
        sb.append("where sp.date >=	:dataInicio");
        sb.append("and sp.date <= :dataFim");

        Query	query	=	entityManager.createNativeQuery(sb.toString());
        query.setParameter("dataInicio",	dataInicio);
        query.setParameter("dataFim",	dataFim);

        Object[]	result	=	(Object[])	query.getSingleResult();

        ShopReportDTO	shopReportDTO	=	new	ShopReportDTO();
        shopReportDTO.setCount(((BigInteger)	result[0]).intValue());
        shopReportDTO.setTotal((Double)	result[1]);
        shopReportDTO.setMean((Double)	result[2]);

        return	shopReportDTO;
    }
}
