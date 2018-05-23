app.controller('homeCtrl', ($scope, NgMap, databaseService) => {

    $scope.getAllScenes = () => {
        databaseService.getAllScenes().then(response => {
            $scope.scenes = response.data;

        }, error => {
            console.error(error);
        });
    };
    $scope.getAllScenes();
   
    NgMap.getMap().then(function (map) {
        $scope.map = map;
    });

    $scope.getFilmDetails = (e, title) => {
        databaseService.getFilmDetails(title)
            .then((response) => {
                $scope.film = response.data.results.bindings[0];
            }, (error) => {
                console.error(error);
            });
    };

});
