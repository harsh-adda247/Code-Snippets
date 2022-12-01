package com.studyIQ.api.cms.liveclass.exceptions;

import com.studyIQ.api.cms.liveclass.ui.dto.ResponseModel;
import com.studyIQ.api.cms.liveclass.ui.model.response.UIBean;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RequestExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid request method type found for this request !", ex.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid media type found for this request !", ex.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid request, missing path variable " + ex.getParameter() + " !", ex.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid request, missing request parameter "
                + ex.getParameterName() + " of type " + ex.getParameterType() + " !", ex.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid type found for " + ex.getPropertyName() + ", whereas it should be of type " + ex.getRequiredType() + " !", ex.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid request : " + ex.getMessage(), ex.getCause());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UIBean<Object> responseBean = getErrorUIBeanInstance("Invalid media type, type not acceptable for this request !", ex.getCause());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Base Case
        if (ex.getClass() == MissingRequestHeaderException.class) {
            return handleMissingRequestHeaderException((MissingRequestHeaderException) ex);
        }
        UIBean<Object> responseBean = getErrorUIBeanInstance("Halting request due to an occurred error, " + ex.getMessage(), ex.getCause());
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        UIBean<Object> responseBean = new UIBean<>();
        responseBean.setMessage("Invalid request, mandatory header can't be missing !");
        responseBean.setSuccess(false);
        responseBean.setResponse("Unauthorised");
        responseBean.setData(ex.getMessage());
        return new ResponseEntity<>(responseBean, HttpStatus.UNAUTHORIZED);
    }


    /**
     * method to get ui bean instance for failing request purpose
     *
     * @param errorMessage - the error message to be shown to the user
     * @param data         - the extra details alongside with error message to be shown to the user
     * @return
     */
    private UIBean<Object> getErrorUIBeanInstance(String errorMessage, Object data) {
        UIBean<Object> uiBean = new UIBean<>();
        uiBean.setMessage(errorMessage);
        uiBean.setSuccess(false);
        uiBean.setData(data);
        return uiBean;
    }
}
