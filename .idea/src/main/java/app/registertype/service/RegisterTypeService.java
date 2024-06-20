package app.registertype.service;

import app.registertype.entity.ProductRegisterType;
import app.registertype.exceptions.RegisterTypeNotFoundException;

public interface RegisterTypeService {
    ProductRegisterType findByCode(String code) throws RegisterTypeNotFoundException;

}
