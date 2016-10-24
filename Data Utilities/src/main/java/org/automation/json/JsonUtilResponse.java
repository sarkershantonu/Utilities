package org.automation.json;

import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilResponse {
    /*public static SearchResponse getJsonResponse(InputStream contentAsStream){
        SearchResponse searchAgreementsResponse = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            searchAgreementsResponse = mapper.readValue(contentAsStream, SearchResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchAgreementsResponse;
    }
    
 public static <T> T get(InputStream contentAsStream, Class<T> t){
        T searchAgreementsResponse = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            searchAgreementsResponse = mapper.readValue(contentAsStream, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchAgreementsResponse;
    }*/
}
