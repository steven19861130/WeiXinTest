'use strict';

var myDesignList = angular.module('ntApp.myDesigns', ['ui.router', 'ntApp.createDesign', 'ntApp.orderDesign', 'ntApp.designDetail', 'designServices']);


var aGlobalDishList = [{
            _id: '111',
            imgSrc: 'http://placekitten.com/604/300',
            title: 'Dish1',
            desc: 'Dish1 Description',
            price: '$9.99',
            size: 'Large'
        },
        {
            _id: '222',
            imgSrc: 'http://placekitten.com/604/300',
            title: 'Dish2',
            desc: 'Dish2 Description',
            price: '$19.99',
            size: 'Large'
        },
        {
            _id: '333',
            imgSrc: 'http://placekitten.com/604/300',
            title: 'Dish3',
            desc: 'Dish3 Description',
            price: '$39.99',
            size: 'Large'
        },
        {
            _id: '444',
            imgSrc: 'http://placekitten.com/604/300',
            title: 'Dish4',
            desc: 'Dish4 Description',
            price: '$3.99',
            size: 'Small'
        },
        {
            _id: '555',
            imgSrc: 'http://placekitten.com/604/300',
            title: 'Dish5',
            desc: 'Dish1 Description',
            price: '$6.99',
            size: 'Small'
        }];


myDesignList.config(['$stateProvider', function($stateProvider) {
    $stateProvider.state('myDesigns', {
        url: '/myDesigns',
        templateUrl: 'views/myDesigns.html',
        controller: 'MyDesignsListCtrl'
    }).state('createDesign', {
        url: '/createDesign',
        templateUrl: 'views/createDesign.html',
        controller: 'CreateDesignCtrl'
    }).state('orderDesign', {
        url: '/orderDesign',
        templateUrl: 'views/orderDesign.html',
        controller: 'OrderDesignCtrl'
    }).state('designDetail', {
        url: '/designDetail/:designId',
        templateUrl: 'views/designDetail.html',
        controller: 'DesignDetailCtrl'
    });
}])

myDesignList.controller('MyDesignsListCtrl', ['$scope', '$state', 'Design', function($scope, $state, Design) {
    $scope.constant = {
        DESIGN_ITEM_OPT: ['delete', 'modify'],
    };

    /*var oResult = Design.DesignManager.query({action: 'getAllMyDesigns'}, function () {
        var newWidth = 600 + oResult.designList.length + 1;
        for (var i = 0; i < oResult.designList.length; i++) {
            oResult.designList[i].image = 'http://placekitten.com/' + newWidth + '/300';
        }
        $scope.aMyDesigns = oResult.designList;
    });*/
    $scope.aMyDesigns = aGlobalDishList;

    $scope.deleteDesign = function (sId) {
        /*var oDesign = {
            designId: sId
        };
        var oParam = {
            action: 'deleteDesign',
            data: oDesign
        };
        Design.delete(oParam);*/
        var idx = -1;
        for (var i = 0; i < $scope.aMyDesigns.length; i++) {
            if (sId === $scope.aMyDesigns[i]._id) {
                idx = i;
                break;
            }
        }
        if (idx !== -1) {
            $scope.aMyDesigns.splice(idx, 1);
        }
    };

    $scope.onDesignItemOptionClicked = function(sOption, oDesign) {
        if (sOption === 'delete') {
            $scope.deleteDesign(oDesign._id);
        } else if (sOption === 'modify') {

        } else {

        }
    };

    $scope.onDesignItemClicked = function(oDesign) {
        $state.go('designDetail', {designId: oDesign._id});
    };
}]);

myDesignList.controller('CreateDesignCtrl', ['$scope', '$state', 'Design', function($scope, $state, Design) {
    $scope.constant = {
        SIZE_ARRAY: ['Large', 'Small']
    };
    $scope.designInfo = {
        sTitle: '',
        sDesc: '',
        sPrice: '$9.99',
        sSize: 'Large'
    };

    // $scope.bPrivateDesign = false;
    // $scope.defultDesc = new Date().toUTCString();
    $scope.createDesign = function () {
        var oNewDesign = {
            _id: new Date().toUTCString(),
            imgSrc: 'http://placekitten.com/604/300',
            title: $scope.designInfo.sTitle,
            desc: $scope.designInfo.sDesc,
            price: $scope.designInfo.sPrice,
            size: $scope.designInfo.sSize,
        };
        aGlobalDishList.push(oNewDesign);

        //GO TO ORDER PAGE
        // $state.go('orderDesign');
    };

    $scope.deleteDesign = function (sId) {
        var oDesign = {
            designId: sId
        };
        var oParam = {
            action: 'deleteDesign',
            data: oDesign
        };
        Design.delete(oParam);
    };

    $scope.changeSize = function(sSize) {
        $scope.designInfo.sSize = sSize;
    };
}]);

myDesignList.controller('OrderDesignCtrl', ['$scope', 'Design', function($scope, Design) {
    
}]);