package org.select.dto;

public class SelectResult<T> {
    private boolean success;
    private T data;
    private String error;

    public SelectResult(){

    }
    public SelectResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SelectResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    public static SelectResult success(){
        SelectResult selectResult = new SelectResult();
        selectResult.setSuccess(true);
        return  selectResult;
    }
}
