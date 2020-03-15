package com.example.fs.io;

/**
 * Class representing API query result
 */
public class Result<T> {

    private T result;
    private ResultMeta meta;

    public Result(ResultMeta meta, T result) {
        this.result = result;
        this.meta = meta;
    }

    public T getResult() {
        return result;
    }

    public ResultMeta getMeta() {
        return meta;
    }


    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", meta=" + meta +
                '}';
    }
}
