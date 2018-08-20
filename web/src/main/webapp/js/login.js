var app = angular.module('loginApp', []);
app.value("baseUrl", "http://" + controlHostAndPort);
app.controller(
    'loginController',
    function($scope, $http, baseUrl) {

        $scope.showContent=baseUrl;
    $scope.submitLogin = function(){
        var email = $scope.emailaddr;
        var password = $scope.Password;

        var loginUrl = baseUrl+"/daniel/login/sign_in";

        var searchParam = {
            "emailAddr":email,
            "password":password
        };
        $scope.showContent=loginUrl;
        $http.
        get(loginUrl,{params:searchParam})
            .then(
                function(result) {
                    $scope.showContent=result;
                }
            ,
                function(result){
                    $scope.showContent=result;
                });

    }
});