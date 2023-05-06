package com.attech.controller.api;

import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.website.WebsitesRequest;
import com.attech.model.entity.Websites;
import com.attech.service.WebsitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/sup")
public class WebsitesController {
    @Autowired
    private WebsitesService websitesService;
    @PostMapping("/web/add")
    public ResponseEntity<?> createdWeb(@RequestBody WebsitesRequest websitesRequest) throws ParseException {
        return ResponseEntity.ok(websitesService.createdWebsite(websitesRequest));
    }
    @DeleteMapping("/web/del/{id}")
    public ResponseEntity<ResponseObject> deleteWeb(@PathVariable Long id){
        ResponseObject responseObject = websitesService.deleteWeb(id);
        return ResponseEntity.ok(responseObject);
    }
}
