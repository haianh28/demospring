package demo.com.demospring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import demo.com.demospring.entity.ProductCatogory;
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory",path = "product_category")
public interface ProductCategoryRepository extends JpaRepository<ProductCatogory, Long>{

}
