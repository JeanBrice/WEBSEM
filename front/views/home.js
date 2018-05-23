app.controller('homeCtrl', ($scope, NgMap, databaseService) => {

    $scope.getAllScenes = () => {
      /* databaseService.getAllScenes().then(response => {
          console.log(response);
          $scope.scenes = response;
        }, error => {
          console.error(error);
        }); */
        $scope.scenes = databaseService.getAllScenes();
        console.log($scope.scenes)
    };

    $scope.getAllScenes();

    NgMap.getMap().then(function (map) {
        console.log(map.getCenter());
        console.log('markers', map.markers);
        $scope.map = map;
    });

    $scope.getFilmDetails = (e, title) => {
        /* databaseService.getFilmDetails(title)
            .then((response) => {
                console.log(response);
                $scope.film = response;
            }, (error) => {
                console.error(error);
            }); */
        $scope.film = databaseService.getFilmDetails(title);
    };

});
