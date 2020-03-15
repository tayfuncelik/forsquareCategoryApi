package com.example.fs.controller;

import com.example.fs.dto.VenuesSearchResult;
import com.example.fs.entity.CompactVenue;
import com.example.fs.entity.GeoCode;
import com.example.fs.entity.VenueGroup;
import com.example.fs.exceptions.FoursquareApiException;
import com.example.fs.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoursquareApi {

    private static final String DEFAULT_VERSION = "20200315";

    private boolean skipNonExistingFields = true;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String oAuthToken;
    private IOHandler ioHandler;
    private String version = DEFAULT_VERSION;
    private boolean useCallback = true;
    private static final String apiUrl = "https://api.foursquare.com/v2/";

    public FoursquareApi(String clientId, String clientSecret, String redirectUrl) {
        this(clientId, clientSecret, redirectUrl, new DefaultIOHandler());
    }

    public FoursquareApi(String clientId, String clientSecret, String redirectUrl, IOHandler ioHandler) {
        this(clientId, clientSecret, redirectUrl, null, ioHandler);
    }

    public FoursquareApi(String clientId, String clientSecret, String redirectUrl, String oAuthToken, IOHandler ioHandler) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
        this.oAuthToken = oAuthToken;
        this.ioHandler = ioHandler;
    }

    public Result<VenuesSearchResult> venuesSearch(Map<String, String> params) throws FoursquareApiException {
        List<String> argsList = new ArrayList<String>();
        for (String s : params.keySet()) {
            argsList.add(s);
            argsList.add(params.get(s));
        }

        Object[] args = argsList.toArray();
        try {
            ApiRequestResponse response = doApiRequest(Method.GET, "venues/search", isAuthenticated(), args);
            return handleVenueSearchResult(response);
        } catch (JSONException e) {
            throw new FoursquareApiException(e);
        }
    }

    private ApiRequestResponse doApiRequest(Method method, String path, boolean auth, Object... params) throws JSONException, FoursquareApiException {
        String url = getApiRequestUrl(path, auth, params);
        Response response = ioHandler.fetchData(url, method);

        if (useCallback) {
            return handleCallbackApiResponse(response);
        } else {
            return handleApiResponse(response);
        }
    }

    private Result<VenuesSearchResult> handleVenueSearchResult(ApiRequestResponse response) throws FoursquareApiException, JSONException {
        VenuesSearchResult result = null;

        if (response.getMeta().getCode() == 200) {
            CompactVenue[] venues = null;
            VenueGroup[] groups = null;
            GeoCode geocode = null;
            if (response.getResponse().has("groups")) {
                groups = (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
            }

            if (response.getResponse().has("venues")) {
                venues = (CompactVenue[]) JSONFieldParser.parseEntities(CompactVenue.class, response.getResponse().getJSONArray("venues"), this.skipNonExistingFields);
            }

            if (response.getResponse().has("geocode")) {
                geocode = (GeoCode) JSONFieldParser.parseEntity(GeoCode.class, response.getResponse().getJSONObject("geocode"), this.skipNonExistingFields);
            }
            result = new VenuesSearchResult(venues, groups, geocode);
        }

        return new Result<VenuesSearchResult>(response.getMeta(), result);
    }

    private String getApiRequestUrl(String path, boolean auth, Object... params) throws FoursquareApiException {

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append(path);
        urlBuilder.append('?');

        if (params.length > 0) {
            int paramIndex = 0;
            try {
                while (paramIndex < params.length) {
                    Object value = params[paramIndex + 1];
                    if (value != null) {
                        urlBuilder.append(params[paramIndex]);
                        urlBuilder.append('=');
                        urlBuilder.append(URLEncoder.encode(value.toString(), "UTF-8"));
                        urlBuilder.append('&');
                    }

                    paramIndex += 2;
                }
            } catch (UnsupportedEncodingException e) {
                throw new FoursquareApiException(e);
            }
        }

        if (auth) {
            urlBuilder.append("oauth_token=");
            urlBuilder.append(getOAuthToken());
        } else {
            urlBuilder.append("client_id=");
            urlBuilder.append(clientId);
            urlBuilder.append("&client_secret=");
            urlBuilder.append(clientSecret);
        }

        urlBuilder.append("&v=" + version);

        if (useCallback) {
            urlBuilder.append("&callback=c");
        }

        return urlBuilder.toString();
    }

    public String getOAuthToken() {
        return oAuthToken;
    }

    public void setoAuthToken(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }

    private boolean isAuthenticated() {
        return oAuthToken != null && !"".equals(oAuthToken);
    }

    private ApiRequestResponse handleCallbackApiResponse(Response response) throws JSONException {
        if (response.getResponseCode() == 200) {
            String responseContent = response.getResponseContent();
            String callbackPrefix = "c(";
            String callbackPostfix = ");";
            JSONObject responseObject = new JSONObject(responseContent.substring(callbackPrefix.length(), responseContent.length() - callbackPostfix.length()));

            JSONObject metaObject = responseObject.getJSONObject("meta");
            int code = metaObject.getInt("code");
            String errorType = metaObject.optString("errorType");
            String errorDetail = metaObject.optString("errorDetail");

            JSONObject responseJson = responseObject.getJSONObject("response");
            JSONArray notificationsJson = responseObject.optJSONArray("notifications");

            return new ApiRequestResponse(new ResultMeta(code, errorType, errorDetail, response.getResponseHeaderRateLimit(), response.getResponseHeaderRateLimitRemaining()), responseJson, notificationsJson);
        } else {
            return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", response.getMessage(), response.getResponseHeaderRateLimit(), response.getResponseHeaderRateLimitRemaining()), null, null);
        }
    }

    private ApiRequestResponse handleApiResponse(Response response) throws JSONException {
        JSONObject responseJson = null;
        JSONArray notificationsJson = null;
        String errorDetail = null;

        if (response.getResponseCode() == 200) {
            JSONObject responseObject = new JSONObject(response.getResponseContent());
            responseJson = responseObject.getJSONObject("response");
            notificationsJson = responseObject.optJSONArray("notifications");
        } else {
            errorDetail = response.getMessage();
        }

        return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", errorDetail, response.getResponseHeaderRateLimit(), response.getResponseHeaderRateLimitRemaining()), responseJson, notificationsJson);
    }
}
