/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dtos.ProductDto;
import java.util.List;

/**
 *
 * @author Isai - HP
 */
public interface IProduct {
    public List<ProductDto> getProducts();
    public int validateExistProduct(ProductDto prod);
    public int addProduct(ProductDto prod);
    public int editProduct(ProductDto prod);
    public ProductDto findProduct(int id);
}
