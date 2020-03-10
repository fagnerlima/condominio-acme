package br.com.acme.presentation.dto.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ResponseTO<T> implements Serializable {

    private static final long serialVersionUID = -7293115434952992083L;

    private T data;

    private List<String> errors = new ArrayList<>();

    private List<String> links = new ArrayList<>();

    public ResponseTO() {
    }

    public ResponseTO(T data) {
        this.data = data;
    }

    public ResponseTO(T data, List<String> links) {
        this.data = data;
        this.links = links;
    }

    public ResponseTO(List<String> errors) {
        super();
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

}
