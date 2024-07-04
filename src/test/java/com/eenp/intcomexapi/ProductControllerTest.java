package com.eenp.intcomexapi;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.eenp.intcomexapi.controller.ProductController;
import com.eenp.intcomexapi.entity.Category;
import com.eenp.intcomexapi.entity.Product;
import com.eenp.intcomexapi.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductRepository productRepository;
	
	@InjectMocks
    private ProductController productController;
	
	@Test
	public void testGetProductById() throws Exception{
		Product product = new Product(
				"UnitTest Product Base",	// Product_Name
				2,							// Supplier_ID
				1,							// Category_ID
				35,							// Quantity_Per_Unit
				53.28,						// Unit_Price
				12,							// Units_In_Stock
				64,							// Units_On_Order
				1,							// Reorder_Level
				false						// Discontinued
			);

		product.setProductId(1); 			// Product_ID
		
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		
		mockMvc.perform(get("/api/products/1"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.productId").value(1))
	        .andExpect(jsonPath("$.productName").value("UnitTest Product Base"))
	        .andExpect(jsonPath("$.supplierId").value(2L))
	        .andExpect(jsonPath("$.categoryId").value(1L))
	        .andExpect(jsonPath("$.quantityPerUnit").value(35))
	        .andExpect(jsonPath("$.unitPrice").value(53.28))
	        .andExpect(jsonPath("$.unitsInStock").value(12))
	        .andExpect(jsonPath("$.unitsOnOrders").value(64))
	        .andExpect(jsonPath("$.reorderLevel").value(1))
	        .andExpect(jsonPath("$.discontinued").value(false))
	    ;
		
	}
	
	@Test
    public void testGetProducts() throws Exception {
        // Crear datos de prueba
        Product product = new Product();
        product.setProductId(2);
        product.setProductName("Cardify");
        product.setSupplierId(1);
        product.setCategoryId(2);
        Category category = new Category();
        category.setCategoryId(2);
        category.setCategoryName("CLOUD");
        category.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry...");
        category.setPicture("https://cdn.pixabay.com/photo/2024/01/26/08/07/ai-generated-8533603_1280.jpg");
        product.setCategory(category);
        product.setQuantityPerUnit(7);
        product.setUnitPrice(101.32);
        product.setUnitsInStock(16);
        product.setUnitsOnOrders(120);
        product.setReorderLevel(1);
        product.setDiscontinued(true);

        List<Product> products = List.of(product);
        Page<Product> productPage = new PageImpl<>(products, PageRequest.of(1, 1), 100000);

        // Mockear el repositorio
        when(productRepository.findAll((Pageable) any(Pageable.class))).thenReturn(productPage);

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(get("/products?pageNumber=1&pageSize=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(100000))
                .andExpect(jsonPath("$.totalPages").value(100000))
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.content[0].productId").value(2))
                .andExpect(jsonPath("$.content[0].productName").value("Cardify"))
                .andExpect(jsonPath("$.content[0].supplierId").value(1))
                .andExpect(jsonPath("$.content[0].categoryId").value(2))
                .andExpect(jsonPath("$.content[0].category.categoryId").value(2))
                .andExpect(jsonPath("$.content[0].category.categoryName").value("CLOUD"))
                .andExpect(jsonPath("$.content[0].category.description").value("Lorem Ipsum is simply dummy text of the printing and typesetting industry..."))
                .andExpect(jsonPath("$.content[0].category.picture").value("https://cdn.pixabay.com/photo/2024/01/26/08/07/ai-generated-8533603_1280.jpg"))
                .andExpect(jsonPath("$.content[0].quantityPerUnit").value(7))
                .andExpect(jsonPath("$.content[0].unitPrice").value(101.32))
                .andExpect(jsonPath("$.content[0].unitsInStock").value(16))
                .andExpect(jsonPath("$.content[0].unitsOnOrder").value(120))
                .andExpect(jsonPath("$.content[0].reorderLevel").value(1))
                .andExpect(jsonPath("$.content[0].discontinued").value(true))
                .andExpect(jsonPath("$.number").value(1))
                .andExpect(jsonPath("$.sort.empty").value(true))
                .andExpect(jsonPath("$.sort.sorted").value(false))
                .andExpect(jsonPath("$.sort.unsorted").value(true))
                .andExpect(jsonPath("$.first").value(false))
                .andExpect(jsonPath("$.last").value(false))
                .andExpect(jsonPath("$.numberOfElements").value(1))
                .andExpect(jsonPath("$.pageable.pageNumber").value(1))
                .andExpect(jsonPath("$.pageable.pageSize").value(1))
                .andExpect(jsonPath("$.pageable.sort.empty").value(true))
                .andExpect(jsonPath("$.pageable.sort.sorted").value(false))
                .andExpect(jsonPath("$.pageable.sort.unsorted").value(true))
                .andExpect(jsonPath("$.pageable.offset").value(1))
                .andExpect(jsonPath("$.pageable.paged").value(true))
                .andExpect(jsonPath("$.pageable.unpaged").value(false))
                .andExpect(jsonPath("$.empty").value(false));
    }
	/*
	@Test
	public void testMockMvcIsConfigured() throws Exception {
	    System.out.println("Testing MockMvc configuration...");

	    mockMvc.perform(get("/api/products"))
	           .andExpect(status().isOk());

	    System.out.println("MockMvc is configured correctly.");
	}
	
	@Test
    public void testGetProductsPagination() {
		try {
			
			Product product;
			List<Product> productList = new ArrayList();
			for(int i = 1; i<=3; i++) {
				product = new Product(
							"Product "+i,
							1,
							2,
							10,
							25.32,
							20,
							30,
							1,
							false
						);
				product.setProductId(i);
				System.out.println("EENP->product->"+product.toString());
				productList.add(product);
			}
	
	        // Configuración de paginación
	        int page = 0; // Página a recuperar (0-indexado)
	        int size = 2; // Tamaño de la página
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Product> productPage = new PageImpl<>(productList, pageable, productList.size());

	        //Page<Product> productPage = new PageImpl<>(productList, PageRequest.of(page, size), productList.size());
	
	        // Simulación del comportamiento del repositorio
	        when(productRepository.findAll(PageRequest.of(page, size))).thenReturn(productPage);
	
	        // Realización de la petición
	        MvcResult result = mockMvc.perform(get("/api/products")
	                .param("page", String.valueOf(page))
	                .param("size", String.valueOf(size)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.content").isArray())
	                .andExpect(jsonPath("$.content.length()").value(size))
	    	        .andExpect(jsonPath("$.content[0].productId").value(1))
	    	        .andExpect(jsonPath("$.content[0].productName").value("Product 1"))
	    	        .andExpect(jsonPath("$.content[0].supplierId").value(1))
	    	        .andExpect(jsonPath("$.content[0].categoryId").value(2))
	    	        .andExpect(jsonPath("$.content[0].quantityPerUnit").value(10))
	    	        .andExpect(jsonPath("$.content[0].unitPrice").value(25.32))
	    	        .andExpect(jsonPath("$.content[0].unitsInStock").value(20))
	    	        .andExpect(jsonPath("$.content[0].unitsOnOrders").value(30))
	    	        .andExpect(jsonPath("$.content[0].reorderLevel").value(1))
	    	        .andExpect(jsonPath("$.content[0].discontinued").value(false))
	    	        
	    	        .andExpect(jsonPath("$.content[1].productId").value(2))
	    	        .andExpect(jsonPath("$.content[1].productName").value("Product 2"))
	    	        .andExpect(jsonPath("$.content[1].supplierId").value(1))
	    	        .andExpect(jsonPath("$.content[1].categoryId").value(2))
	    	        .andExpect(jsonPath("$.content[1].quantityPerUnit").value(10))
	    	        .andExpect(jsonPath("$.content[1].unitPrice").value(25.32))
	    	        .andExpect(jsonPath("$.content[1].unitsInStock").value(20))
	    	        .andExpect(jsonPath("$.content[1].unitsOnOrders").value(30))
	    	        .andExpect(jsonPath("$.content[1].reorderLevel").value(1))
	    	        .andExpect(jsonPath("$.content[1].discontinued").value(false))
	    	        
	    	        .andExpect(jsonPath("$.content[2].productId").value(3))
	    	        .andExpect(jsonPath("$.content[2].productName").value("Product 3"))
	    	        .andExpect(jsonPath("$.content[2].supplierId").value(1))
	    	        .andExpect(jsonPath("$.content[2].categoryId").value(2))
	    	        .andExpect(jsonPath("$.content[2].quantityPerUnit").value(10))
	    	        .andExpect(jsonPath("$.content[2].unitPrice").value(25.32))
	    	        .andExpect(jsonPath("$.content[2].unitsInStock").value(20))
	    	        .andExpect(jsonPath("$.content[2].unitsOnOrders").value(30))
	    	        .andExpect(jsonPath("$.content[2].reorderLevel").value(1))
	    	        .andExpect(jsonPath("$.content[2].discontinued").value(false))
	
	                .andReturn();
	
	        // Imprimir el contenido de la respuesta si lo deseas
	        String responseBody = result.getResponse().getContentAsString();
	        System.out.println("Response Body:");
	        System.out.println(responseBody);
		}catch(Exception e) {
			System.out.println("ERROR->"+e.getMessage());
		}
    }
	*/
}
