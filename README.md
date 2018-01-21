# TMDB

The initial view contains an infinite scroll list with the most popular tv shows using the following endpoint:  https://developers.themoviedb.org/3/movies/get-top-rated-movies
Each item of the list contains an image, the tv show title and the vote average fields.
The list is able to paginate
When a list item is clicked, it loads the tv show data in a detail view. This view contains: A big hero image, the title, the overview... 
Once in the detail view, the user will be able to navigate between similar tv shows                                   (https://developers.themoviedb.org/3/movies/get-similar-movies) by swapping horizontally. The first item will be the one that the user has clicked. Then it will load the related tv shows and the user will be able to navigate using swype to left or right.
