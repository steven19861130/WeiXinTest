<!DOCTYPE html>
<!--[if lt IE 7]>      <html lang="en" ng-app="myApp" class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html lang="en" ng-app="myApp" class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html lang="en" ng-app="myApp" class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en" ng-app="ntApp" class="no-js"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>My AngularJS App</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bower_components/html5-boilerplate/css/normalize.css">
  <link rel="stylesheet" href="bower_components/html5-boilerplate/css/main.css">
  <link rel="stylesheet" href="app.css">
  <link rel="stylesheet" href="css/bootstrap.css">
  <script src="bower_components/html5-boilerplate/js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
  <!-- <ul class="menu">
    <li><a href="#/view1">view1</a></li>
    <li><a href="#/view2">view2</a></li>
  </ul> -->

  <!--[if lt IE 7]>
      <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
  <![endif]-->
  <div class="container-fluid">
  <!--div ng-view></div-->
  <div ui-view></div>

  <!-- In production use:
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/x.x.x/angular.min.js"></script>
  -->
  <script src="bower_components/angular/angular.js"></script>
  <!--script src="bower_components/angular-route/angular-route.js"></script-->
  <script src="bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
  <script src="bower_components/angular-resource/angular-resource.js"></script>
  <!--script src="js/bootstrap.js"></script-->
  <script src="js/ui-bootstrap-custom-tpls-0.12.0.js"></script>>
  <script src="app.js"></script>
  <script src="views/myDesigns.js"></script>
  <script src="views/designDetail.js"></script>
  <script src="views/createDesign.js"></script>
  <script src="views/orderDesign.js"></script>
  <script src="views/designDetail.js"></script>
  <script src="js/service/designServices.js"></script>
  <script src="components/version/version.js"></script>
  <script src="components/version/version-directive.js"></script>
  <script src="components/version/interpolate-filter.js"></script>
</body>
</html>
