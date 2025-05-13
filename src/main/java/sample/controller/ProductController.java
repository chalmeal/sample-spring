package sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sample.exception.ServiceException;
import sample.service.ProductService;

/**
 * <pre>
 * 商品のControllerクラス
 * </pre>
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController extends SampleController {
    // DI
    private final ProductService service;

    /**
     * <pre>
     * 商品取得API
     * </pre>
     * 
     * @param productId 商品ID
     * @return 商品
     */
    @GetMapping("/{product_id}")
    public ResponseEntity<?> getProduct(
            @PathVariable(value = "product_id", required = false) String productId) {
        try {
            return response(service.getProduct(productId));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

}
