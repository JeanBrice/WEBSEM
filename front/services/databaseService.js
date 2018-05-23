app.factory('databaseService', ($http, $q) => {

    var scenes = [{ title: '*batteries not included', coords: [40.7224452961828, -73.9786505699157] }, { title: '12 Angry Men', coords: [40.7137, -74.0079] }, { title: '13 Going on 30', coords: [40.7592204876521, -73.9846211671829] }, { title: '15 Minutes', coords: [40.7661, -73.9696] }, { title: '25th Hour', coords: [40.7117926273691, -74.0123283863067] }];

    var films = [{ title: '*batteries not included', year: '1987', link: 'http://imdb.com/title/tt0092494/', genre: 'Comedy, Family, Fantasy', imdbrating: '6.6', metascore: '0', runtime: '106 min', poster: 'https://ia.media-imdb.com/images/M/MV5BM2JlMDgxOTYtNmI2My00YzY1LWIyZTgtYzY0MjY3Y2RiZmY2L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTI4MjkwNjA@._V1_SX300.jpg' }, { title: '12 Angry Men', year: '1957', link: 'http://imdb.com/title/tt0050083/', genre: 'Crime, Drama', imdbrating: '8.9', metascore: '96', runtime: '96 min', poster: 'https://ia.media-imdb.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg' }, { title: '13 Going on 30', year: '2004', link: 'http://www.imdb.com/title/tt0337563/', genre: 'Comedy, Fantasy, Romance', imdbrating: '6.1', metascore: '57', runtime: '98 min', poster: 'https://images-na.ssl-images-amazon.com/images/M/MV5BMjA1MTI3ODEwNF5BMl5BanBnXkFtZTcwOTA4NDUyMQ@@._V1_SX300.jpg' }, { title: '15 Minutes', year: '2001', link: 'http://www.imdb.com/title/tt0179626/', genre: 'Action, Crime, Drama', imdbrating: '6.1', metascore: '34', runtime: '120 min', poster: 'https://ia.media-imdb.com/images/M/MV5BMTI3MzQ1MzIwNl5BMl5BanBnXkFtZTYwMTAxODc5._V1_SX300.jpg' }, { title: '25th Hour', year: '2002', link: 'http://www.imdb.com/title/tt0307901/', genre: 'Drama', imdbrating: '7.7', metascore: '67', runtime: '135 min', poster: 'https://ia.media-imdb.com/images/M/MV5BNmE0YjdlYTktMTU4Ni00Mjk2LWI3NWMtM2RjNmFiOTk4YjYxL2ltYWdlXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg' }];
  
    return {

        getAllScenes: () => {
            return scenes;
        },

        getFilmDetails: (title) => {
            for(var i = 0 ; i < films.length ; i++) {
                if(films[i].title === title) {
                    return films[i];
                }
            }
        },
      
        getFilmDetailsFUTURE: (title) => {
            var deferred = $q.defer(),
            httpPromise = $http.get('http://www.omdbapi.com/?apikey=86ecf20f&t=' + title);

            httpPromise
                .then((response) => {
                    deferred.resolve(response);
                },(error) => {
                    console.error(error);
                });

            return deferred.promise;
        }
        
    }
  
});
