package com.example.fs.service;

import com.example.fs.controller.FoursquareApi;
import com.example.fs.dto.ReqDto;
import com.example.fs.dto.VenuesSearchResult;
import com.example.fs.entity.CompactVenue;
import com.example.fs.exceptions.FoursquareApiException;
import com.example.fs.io.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Component
public class CategoryService {

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${callbackUrl}")
    private String callbackUrl;

    /*
        public static void main(String[] args) {
             try {
                (new CategoryService()).searchVenues(null);
            } catch (FoursquareApiException e) {
                // TODO: Error handling
            }
        }
    */
    public Result<VenuesSearchResult> searchVenues(ReqDto reqDto) throws FoursquareApiException {
        // First we need a initialize FoursquareApi.
        FoursquareApi foursquareApi = new FoursquareApi(clientId, clientSecret, callbackUrl);

        // After client has been initialized we can make queries.
        Map<String, String> myMap = new HashMap<String, String>();
        /* myMap.put("near","şişli");
        myMap.put("ll","41.01,28.97");
        myMap.put("v","20200315");
        myMap.put("query","Bar");//category_name*/
        if (reqDto.getNear() != null) {
            myMap.put("near", reqDto.getNear());
        }
        if (reqDto.getLl() != null) {
            myMap.put("ll", reqDto.getLl());
        }
        if (reqDto.getV() != null) {
            myMap.put("v", reqDto.getV());
        }
        if (reqDto.getQuery() != null) {
            myMap.put("query", reqDto.getQuery());
        }


        Result<VenuesSearchResult> result = foursquareApi.venuesSearch(myMap);
        if (result.getMeta().getCode() == 200) {
            // if query was ok we can finally we do something with the data
            for (CompactVenue venue : result.getResult().getVenues()) {

                System.out.println(venue.getName());
            }
        } else {
            System.out.println("Error occured: ");
            System.out.println("  code: " + result.getMeta().getCode());
            System.out.println("  type: " + result.getMeta().getErrorType());
            System.out.println("  detail: " + result.getMeta().getErrorDetail());
        }

        return result;
    }


    public Result<VenuesSearchResult> searchVenues(Map<String, String> myMap) throws FoursquareApiException {
        // First we need a initialize FoursquareApi.
        FoursquareApi foursquareApi = new FoursquareApi(clientId, clientSecret, callbackUrl);

        Result<VenuesSearchResult> result = foursquareApi.venuesSearch(myMap);
        if (result.getMeta().getCode() == 200) {
            // if query was ok we can finally we do something with the data
            for (CompactVenue venue : result.getResult().getVenues()) {

                System.out.println(venue.getName());
            }
        } else {
            System.out.println("Error occured: ");
            System.out.println("  code: " + result.getMeta().getCode());
            System.out.println("  type: " + result.getMeta().getErrorType());
            System.out.println("  detail: " + result.getMeta().getErrorDetail());
        }

        return result;
    }

}
