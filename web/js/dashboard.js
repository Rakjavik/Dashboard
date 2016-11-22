/**
 * Created by Tyrion on 10/9/2016.
 */
angular.module('dashboard',[]).
    controller('DashController', function ($scope, networkService) {
        // Initialize //
        $scope.devices = {};
        $scope.$on('devices',function(event,devices) {
            $scope.devices = devices;
        });
        networkService.getDevices();
        return;
    })
    .directive('deviceList', function () {
        return {
            templateUrl: 'templates/device.html'
        };
    })
        .service('networkService', function($http,$rootScope){
            this.getDevices = function() {
                $http.get("/network").success(function(results) {
                    console.log(results);
                    $rootScope.$broadcast('devices',results);
                    return;
                })
            }
        });

