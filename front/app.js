let app = angular.module('myApp', [
    'ui.router',
    'ngMap',
]);

app.config(function ($stateProvider, $urlRouterProvider) {

    $stateProvider
        .state({
            name: 'home',
            url: '/home',
            templateUrl: '/views/home.html',
        });

    $urlRouterProvider.otherwise('/home');

});