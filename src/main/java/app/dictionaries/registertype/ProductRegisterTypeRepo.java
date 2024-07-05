package app.dictionaries.registertype;

import app.dictionaries.accounttype.AccountType;
import app.dictionaries.productclass.ProductClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRegisterTypeRepo extends CrudRepository<ProductRegisterType, Integer> {
    ProductRegisterType findByCode(String code);
    List<ProductRegisterType> findAll();

    //List<ProductRegisterType> findByProductClassAndAccountType(String productClass, String accountType) throws RegisterTypeNotFoundException;
    //List<ProductRegisterType> findAllByProductClassAndAccountType(ProductClass productClass, AccountType accountType);

    /*@Query(value = "SELECT register_type " +
            "FROM tpp_ref_product_register_type register_type, tpp_ref_account_type account_type, tpp_ref_product_class product_class " +
            "WHERE register_type.product_class_code = product_class.value and register_type.account_type = account_type.value " +
                "AND product_class.value = :class_code AND account_type.value = :account_type",
            nativeQuery = true)*/
    /*@Query(value = "SELECT register_type.internal_id " +
            "FROM tpp_ref_product_register_type register_type, tpp_ref_product_class product_class " +
            "WHERE register_type.product_class_code = product_class.value " +
            "AND product_class.value = :class_code ",
            nativeQuery = true)
    List<ProductRegisterType> FindMyTypes(@Param("class_code") String productClassCode);*/

    List<ProductRegisterType> findByProductClassAndAccountType(ProductClass productClass, AccountType accountType);

}
