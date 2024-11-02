package Cookease.com.service;

import Cookease.com.domain.Ingredient;
import Cookease.com.domain.Recipe;
import Cookease.com.repository.RecipeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeJpaRepository recipeJpaRepository;

    @Autowired
    private final RestTemplate restTemplate;

//    public List<Recipe> getRecipeByIngredients(List<Ingredient> ingredients) {
//
//    }

    public Map<String, Object> getRecipeDetails(String recipeCode) {
        String url = "https://api.spoonacular.com/recipes/" + recipeCode + "/information";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", "2953877ea539463d990c1f44907e2cbf");
        ResponseEntity<Map> response = restTemplate.getForEntity(uriBuilder.toUriString(), Map.class);
        return response.getBody();
    }
}
