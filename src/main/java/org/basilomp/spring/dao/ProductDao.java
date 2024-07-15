package org.basilomp.spring.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basilomp.spring.dto.ProductRequestDto;
import org.basilomp.spring.exception.ProductNotFoundException;
import org.basilomp.spring.model.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDao {

    private final DataSource dataSource;

    public void insertProduct(final ProductRequestDto requestDto) {
        log.info("Inserting product: {}", requestDto);
        final String insertSQL = "INSERT INTO products (account_number, balance, product_type, user_id) " +
                                 "VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, requestDto.getAccountNumber());
            preparedStatement.setBigDecimal(2, requestDto.getBalance());
            preparedStatement.setString(3, requestDto.getProductType());
            preparedStatement.setInt(4, requestDto.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        log.info("Attempting to retrieve all products from database");
        final String selectAllSQL = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setAccountNumber(resultSet.getInt("account_number"));
                product.setBalance(resultSet.getBigDecimal("balance"));
                product.setProductType(resultSet.getString("product_type"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return products;
    }

    public Product getProductById(final int id) {
        log.info("Getting product by id: {}", id);
        final String selectByIdSQL = "SELECT * FROM products WHERE id = ?";
        Product product = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectByIdSQL)) {
            preparedStatement.setInt(1, id);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    product = new Product();
                    product.setAccountNumber(resultSet.getInt("account_number"));
                    product.setBalance(resultSet.getBigDecimal("balance"));
                    product.setProductType(resultSet.getString("product_type"));
                    return product;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public List<Product> getProductByUserId(final Integer userId) {
        log.info("Getting product for user with id: {}", userId);
        final String selectByIdSQL = "SELECT * FROM products WHERE user_id = ?";
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectByIdSQL)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setAccountNumber(resultSet.getInt("account_number"));
                product.setBalance(resultSet.getBigDecimal("balance"));
                product.setProductType(resultSet.getString("product_type"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new ProductNotFoundException("Could not find products for the user with id " + userId);
        }
        return products;
    }
}
