package carlesarnal.com.themoviedb.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by carles on 17/01/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "poster_path",
        "overview",
        "id",
        "title",
        "popularity",
        "vote_count",
        "vote_average"
})
public class Movie {

    @JsonProperty("poster_path")
    public String posterPath;
    @JsonProperty("overview")
    public String overview;
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("popularity")
    public float popularity;
    @JsonProperty("vote_count")
    public int voteCount;
    @JsonProperty("vote_average")
    public float voteAverage;

}
