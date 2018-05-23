app.factory('databaseService', ($http, $q) => {

    return {

        getAllScenes: () => {
            var deferred = $q.defer(),
            httpPromise = $http.get('http://localhost:8080/api/scenes');

            httpPromise
                .then((response) => {
                    deferred.resolve(response);
                },(error) => {
                    console.error(error);
                });

            return deferred.promise;
        },
      
        getFilmDetails: (title) => {
            var deferred = $q.defer(),
            httpPromise = $http.get('http://localhost:8080/api/films/' + title);

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
