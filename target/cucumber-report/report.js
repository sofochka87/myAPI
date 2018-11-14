$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/com/api/features/PostThenGet.feature");
formatter.feature({
  "name": "Employee REST Api requests",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Post an Employee and Verify in UI",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@ApiPost"
    }
  ]
});
formatter.step({
  "name": "Content type and Accept type is JSON",
  "keyword": "Given "
});
});