package org.example.productservice.controlleradvices;

import org.example.productservice.dtos.ArithmeticExceptionDto;
import org.example.productservice.dtos.ExceptionDto;
import org.example.productservice.exceptions.CategoryNotExistException;
import org.example.productservice.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException(){
        ArithmeticExceptionDto responseDto = new ArithmeticExceptionDto();
        responseDto.setMessage("Something Went Wrong!!!!");
        return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(ProductNotExistException productNotExistException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setName(productNotExistException.getMessage());
        exceptionDto.setDetails("Check the product id, it doesn't exist!!");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotExistException.class)
    public ResponseEntity<ExceptionDto> handleCategoryNotExistException(CategoryNotExistException categoryNotExistException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setName(categoryNotExistException.getMessage());
        exceptionDto.setDetails("Particular category doesn't exist, Please check it!!");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
