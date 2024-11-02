package Cookease.com.service;

import Cookease.com.repository.RecipeJpaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeJpaRepository recipeJpaRepository;

    @Autowired
    private final RestTemplate restTemplate;

    public Map<String, Object> getRecipeDetails(String recipeCode) {
        String url = "https://api.spoonacular.com/recipes/" + recipeCode + "/information";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", "2953877ea539463d990c1f44907e2cbf");
        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            // analyzedInstructions의 steps 추출
            List<Map<String, Object>> stepsList = new ArrayList<>();
            JsonNode instructionsNode = rootNode.path("analyzedInstructions");
            if (instructionsNode.isArray() && instructionsNode.size() > 0) {
                JsonNode stepsNode = instructionsNode.get(0).path("steps");
                for (JsonNode step : stepsNode) {
                    Map<String, Object> stepDetails = new HashMap<>();
                    stepDetails.put("number", step.path("number").asInt());
                    stepDetails.put("step", step.path("step").asText());
                    stepsList.add(stepDetails);
                }
            }
            result.put("steps", stepsList);

            // ingredients 추출
            List<Map<String, Object>> ingredientsList = new ArrayList<>();
            JsonNode ingredientsNode = rootNode.path("extendedIngredients");
            for (JsonNode ingredient : ingredientsNode) {
                Map<String, Object> ingredientDetails = new HashMap<>();
                ingredientDetails.put("name", ingredient.path("name").asText());
                ingredientDetails.put("amount", ingredient.path("amount").asDouble());
                ingredientDetails.put("unit", ingredient.path("unit").asText());
                ingredientsList.add(ingredientDetails);
            }
            result.put("ingredients", ingredientsList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 더미 데이터 파싱하는 함수
    public Map<String, Object> parseDummyData(String recipeCode) {
        String url = "https://api.spoonacular.com/recipes/" + recipeCode + "/information";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", "2953877ea539463d990c1f44907e2cbf");
        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);

        Map<String, Object> parsedData = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            // title과 recipe id 추출
            parsedData.put("title", rootNode.path("title").asText());
            parsedData.put("recipeId", rootNode.path("id").asInt());

            // extendedIngredients에서 id 추출
            List<Integer> ingredientIds = new ArrayList<>();
            JsonNode ingredientsNode = rootNode.path("extendedIngredients");
            for (JsonNode ingredient : ingredientsNode) {
                ingredientIds.add(ingredient.path("id").asInt());
            }
            parsedData.put("ingredientIds", ingredientIds);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedData;
    }

    // 재료 가지고 레시피 찾아주는 함수. -> 재료 넣고 썸네일 여러개 보여주는 함수.
    public List<Map<String, Object>> getRecipesByIngredients(List<String> ingredients, int number, int ranking, boolean ignorePantry) {
        String url = "https://api.spoonacular.com/recipes/findByIngredients";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", "2953877ea539463d990c1f44907e2cbf")
                .queryParam("ingredients", String.join(",", ingredients))
                .queryParam("number", number)
                .queryParam("ranking", ranking)
                .queryParam("ignorePantry", ignorePantry);

        ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
        List<Map<String, Object>> recipes = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            if (rootNode.isArray()) {
                for (JsonNode recipeNode : rootNode) {
                    Map<String, Object> recipeDetails = new HashMap<>();
                    recipeDetails.put("id", recipeNode.path("id").asInt());
                    recipeDetails.put("title", recipeNode.path("title").asText());
                    recipeDetails.put("image", recipeNode.path("image").asText());
                    recipes.add(recipeDetails);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }
//    이름. 북마크 여부, 사진, 레시피 번호


}