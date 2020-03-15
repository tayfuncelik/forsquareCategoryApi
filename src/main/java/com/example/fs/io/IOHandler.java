package com.example.fs.io;

/**
 * Abstract class representing IOHandler
 */
public abstract class IOHandler {

    public abstract Response fetchData(String url, Method method);

    public abstract Response fetchDataMultipartMime(String url, MultipartParameter... params);
}
