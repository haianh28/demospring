package demo.com.demospring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import demo.com.demospring.entity.Product;
import demo.com.demospring.entity.ProductCatogory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	private EntityManager entityManager;

// tao contructor
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		HttpMethod[] theUnSupportedAction = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };
		// Disable HTTP method for Products: PUT ,POST , DELETE
		config.getExposureConfiguration().forDomainType(Product.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedAction))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedAction));

		// Disable HTTP method for ProductCategory: PUT ,POST , DELETE
		config.getExposureConfiguration().forDomainType(ProductCatogory.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedAction))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnSupportedAction));

		// call an internal helper method
		exposeIds(config);
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		// get a list all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		// create an array of the entity type
		List<Class> entityClasses = new ArrayList<>();
		// get the entity type for the entites
		for (EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		// expose the entity ids for the array of entity/domain type
		Class[] domainTyes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTyes);
	}

}
