package com.example.fs.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class DefaultIOHandler extends IOHandler {

    private static String BOUNDARY = "----------gc0p4Jq0M2Yt08jU534c0p";

    @Override
    public Response fetchData(String url, Method method) {
        int code = 200;

        try {
            URL aUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
            try {
                connection.setDoInput(true);
                if ("POST".equals(method.name())) {
                    connection.setDoOutput(true);
                }
                connection.setRequestMethod(method.name());
                connection.connect();

                String xRateLimit = connection.getHeaderField("X-RateLimit-Limit");
                String xRateLimitRemaining = connection.getHeaderField("X-RateLimit-Remaining");

                code = connection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = connection.getInputStream();
                    return new Response(readStream(inputStream), code, connection.getResponseMessage(), xRateLimit, xRateLimitRemaining);
                } else {
                    return new Response("", code, getMessageByCode(code), xRateLimit, xRateLimitRemaining);
                }

            } finally {
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            return new Response("", 400, "Malformed URL: " + url);
        } catch (IOException e) {
            return new Response("", 500, e.getMessage());
        }
    }

    @Override
    public Response fetchDataMultipartMime(String url, MultipartParameter... parameters) {
        int code = 200;

        try {
            URL aUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) aUrl.openConnection();
            try {
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
                connection.connect();

                OutputStream outputStream = connection.getOutputStream();

                StringBuffer startBoundaryBuilder = new StringBuffer("--")
                        .append(BOUNDARY)
                        .append("\r\n");

                outputStream.write(startBoundaryBuilder.toString().getBytes());

                for (MultipartParameter parameter : parameters) {
                    StringBuffer formDataBuilder = new StringBuffer()
                            .append("Content-Disposition: form-data; name=\"")
                            .append(parameter.getName())
                            .append("\"; filename=\"")
                            .append(parameter.getName())
                            .append("\"\r\n")
                            .append("Content-Type: ")
                            .append(parameter.getContentType())
                            .append("\r\n\r\n");
                    outputStream.write(formDataBuilder.toString().getBytes());
                    outputStream.write(parameter.getContent());
                }

                StringBuilder endBoundaryBuilder = new StringBuilder("\r\n--")
                        .append(BOUNDARY)
                        .append("--\r\n");
                outputStream.write(endBoundaryBuilder.toString().getBytes());

                outputStream.flush();
                outputStream.close();

                code = connection.getResponseCode();
                if (code == 200) {
                    //obtain the encoding returned by the server
                    String encoding = connection.getContentEncoding();

                    //create the appropriate stream wrapper based on the encoding type
                    InputStream resultingInputStream = null;
                    if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
                        resultingInputStream = new GZIPInputStream(connection.getInputStream());
                    } else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
                        resultingInputStream = new InflaterInputStream(connection.getInputStream(), new Inflater(true));
                    } else {
                        resultingInputStream = connection.getInputStream();
                    }

                    return new Response(readStream(resultingInputStream), code, connection.getResponseMessage());
                } else {
                    return new Response("", code, getMessageByCode(code));
                }

            } finally {
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            return new Response("", 400, "Malformed URL: " + url);
        } catch (IOException e) {
            return new Response("", 500, e.getMessage());
        }
    }

    private String readStream(InputStream inputStream) throws IOException {
        StringWriter responseWriter = new StringWriter();

        char[] buf = new char[1024];
        int l = 0;

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        while ((l = inputStreamReader.read(buf)) > 0) {
            responseWriter.write(buf, 0, l);
        }

        responseWriter.flush();
        responseWriter.close();
        return responseWriter.getBuffer().toString();
    }


    private String getMessageByCode(int code) {
        switch (code) {
            case 400:
                return "Bad Request";
            case 401:
                return "Unauthorized";
            case 403:
                return "Forbidden";
            case 404:
                return "Not Found";
            case 405:
                return "Method Not Allowed";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown";
        }
    }
}
