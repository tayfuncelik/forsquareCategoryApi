package com.example.fs.controller;

import com.example.fs.dto.ReqDto;
import com.example.fs.dto.VenuesSearchResult;
import com.example.fs.entity.CompactVenue;
import com.example.fs.io.Result;
import com.example.fs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/fetchCategoryByParams", method = RequestMethod.POST)
    private ResponseEntity<CompactVenue[]> fetchCategoryByParams(@RequestBody ReqDto reqDto) throws Exception {

        if (reqDto == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Result<VenuesSearchResult> res = categoryService.searchVenues(reqDto);
        return ResponseEntity.ok(res.getResult().getVenues());
    }

    /**
     * http://localhost:8080/foos?near=valletta&ll=2222&v=20200315&query=Bar
     */
    @GetMapping("/getByParams")
    private ResponseEntity<CompactVenue[]> getByParams(
            @RequestParam(required = false) String near,
            @RequestParam(required = false) String ll,
            @RequestParam(required = false) String v,
            @RequestParam(required = false) String query) throws Exception {

        ReqDto dto = new ReqDto();
        dto.setLl(ll);
        dto.setNear(near);
        dto.setQuery(query);
        dto.setV(v);

        Result<VenuesSearchResult> res = categoryService.searchVenues(dto);
        return ResponseEntity.ok(res.getResult().getVenues());
    }

    @PostMapping("/getByKeyValue")
    private ResponseEntity<Result<VenuesSearchResult>> getByParams(@RequestParam Map<String,String> allParams) throws Exception {

        Result<VenuesSearchResult> res = categoryService.searchVenues(allParams);
        return ResponseEntity.ok(res);
    }

/*
    private ResponseEntity<String> sendEmailToUser(Note note) {
        //This will send email about created note
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", note.getUserId().toString());

        User entityResp = restTemplate.getForObject("http://localhost:8081/users/getUser/{userId}", User.class, params);
        ResponseEntity<String> sendEmailResp = restTemplate.postForEntity("http://localhost:8082/email/sendEmail", entityResp, String.class);
        return sendEmailResp;
    }*/


}
