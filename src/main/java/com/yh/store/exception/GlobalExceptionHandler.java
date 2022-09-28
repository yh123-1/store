package com.yh.store.exception;

import com.yh.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局统一异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public JsonResult<Void> userNotExistException(UserNotFoundException e){
        return new JsonResult(e.CODE,e.getMessage());
    }
    @ExceptionHandler(InsertException.class)
    public JsonResult<Void> insertException(InsertException e){
        return new JsonResult(e.CODE,e.getMessage());
    }
    @ExceptionHandler(PasswordNotMatchException.class)
    public JsonResult<Void> passwordNotMatchException(PasswordNotMatchException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }
    @ExceptionHandler(UpdateException.class)
    public JsonResult<Void> updateException(UpdateException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }
    @ExceptionHandler(UserNameDuplicatedException.class)
    public JsonResult<Void> userNameDuplicatedException(UserNameDuplicatedException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }

    @ExceptionHandler(FileEmptyException.class)
    public JsonResult<Void> fileEmptyException(FileEmptyException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }

    @ExceptionHandler(FileSizeException.class)
    public JsonResult<Void> fileSizeException(FileSizeException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }

    @ExceptionHandler(FileTypeException.class)
    public JsonResult<Void> fileTypeException(FileTypeException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }

    @ExceptionHandler(FileUploadIOException.class)
    public JsonResult<Void> fileUploadIOException(FileUploadIOException e) {
        return new JsonResult(e.CODE, e.getMessage());
    }

    @ExceptionHandler(AddressCountLimitException.class)
    public JsonResult<Void> addressCountLimitException(AddressCountLimitException e){
        return new JsonResult<>(e.CODE, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public JsonResult<Void> accessDeniedException(AccessDeniedException e){
        return new JsonResult<>(e.CODE, e.getMessage());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public JsonResult<Void> addressNotFoundException(AddressNotFoundException e){
        return new JsonResult<>(e.CODE, e.getMessage());
    }

    @ExceptionHandler(DeleteException.class)
    public JsonResult<Void> deleteException(DeleteException e){
        return new JsonResult<>(e.CODE, e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public JsonResult<Void> productNotFoundException(ProductNotFoundException e){
        return new JsonResult<>(e.CODE, e.getMessage());
    }

    @ExceptionHandler(OrderNotExistException.class)
    public JsonResult<Void> orderNotExistException(OrderNotExistException e){
        return new JsonResult<>(e.CODE, e.getMessage());
    }

}
