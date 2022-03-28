package br.com.stratws.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.stratws.entidades.CafeProjetos;
import br.com.stratws.entidades.EstatisticasProjeto;
import br.com.stratws.util.JPAUtil;

public class ProjetosDaoJDBC {
		
	public void update(EstatisticasProjeto estatisticasProjeto) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.merge(estatisticasProjeto);

		entityManager.getTransaction().commit();
		entityManager.close();

	}
	
	public void updateCafeProjetos(CafeProjetos cafeProjetos) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.merge(cafeProjetos);

		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@SuppressWarnings("unchecked")
	public List<EstatisticasProjeto> findAll() {
		
		List<EstatisticasProjeto> listaEstatisticasProjeto = new ArrayList<EstatisticasProjeto>();
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String sql = "SELECT * FROM projetos.estatisticasprojeto";
		
		Query query = entityManager.createNativeQuery(sql, "EstatisticasProjeto");

		listaEstatisticasProjeto = query.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return listaEstatisticasProjeto;
		
	}
		
	@SuppressWarnings("unchecked")
	public List<EstatisticasProjeto> pesquisaEnvioEmailPorStatus() {
		
		List<EstatisticasProjeto> listaEstatisticasProjeto = new ArrayList<EstatisticasProjeto>();
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String sql = "SELECT * " +
					 "  FROM projetos.estatisticasprojeto" +
					 " WHERE estatisticasprojeto.status not like 'Concluído'" +
					 "   AND estatisticasprojeto.status not like 'Em Análise'" +
					 "   AND estatisticasprojeto.status not like 'Em dia'" +
					 " ORDER BY estatisticasprojeto.descricao;";
		
		Query query = entityManager.createNativeQuery(sql,  "EstatisticasProjeto");
		
		listaEstatisticasProjeto = query.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
				
		return listaEstatisticasProjeto;
		
	}
	
	public EstatisticasProjeto findById(EstatisticasProjeto obj) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String sql = "SELECT * " +
					 "  FROM projetos.estatisticasprojeto" +
					 " WHERE estatisticasprojeto.id = " + obj.getId();
		
		Query query = entityManager.createNativeQuery(sql,  "EstatisticasProjeto");
		
		obj = (EstatisticasProjeto) query.getSingleResult();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return obj;
	}

}