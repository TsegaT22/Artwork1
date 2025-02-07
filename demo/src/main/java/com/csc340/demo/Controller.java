package com.csc340.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class Controller {
    @GetMapping("/artworks")
    public Object getArtwork(@RequestParam(value = "id",defaultValue = "129884")String id){
        try{
            String URL = "https://api.artic.edu/api/v1/artworks/" + id; //Consumes a third-party API
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();

            /**
             * <pre>
             **"data": {
             *         "id": 129884,
             *         "api_model": "artworks",
             *         "api_link": "https://api.artic.edu/api/v1/artworks/129884",
             *         "is_boosted": true,
             *         "title": "Starry Night and the Astronauts",
             *         "alt_titles": null,
             *         "thumbnail": {
             *             "lqip": "data:image/gif;base64,R0lGODlhBAAFAPQAABw/Zhg/aBRBaBZBahRCaxxBahxEahNIchZJcR9LdB9OdiZIZSBEbShLbjxRZyBPeipRcSpReUpWaitXgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAAAAAAALAAAAAAEAAUAAAURoMJIDhJAywAcAlEkxhNNTQgAOw==",
             *             "width": 5376,
             *             "height": 6112,
             *             "alt_text": "Abstract painting composed of small vertical dabs of multiple shades of blue with a small area of similar strokes of red, orange, and yellow in the upper right."
             *         },
             *         "main_reference_number": "1994.36",
             *         "has_not_been_viewed_much": false,
             *         "boost_rank": 1,
             *         "date_start": 1972,
             *         "date_end": 1972,
             *         "date_display": "1972",
             * </pre>

             */
            String jsonListResponse = restTemplate.getForObject(URL,String.class);
            JsonNode root = objectMapper.readTree(jsonListResponse).get("data");
            //This will extract needed information and build the artwork.
            ArtMuseum artworks = new ArtMuseum(root.get("title").asText(), root.get("id").asText(),
                    root.get("api_model").asText(), root.get("date_display").asText());
            return artworks;
        } catch (JsonProcessingException ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /artworks";
        }
    }


}
