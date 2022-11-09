local greeting = "Hello ";

local person = function(name){
  "name": name,
  "welcome": greeting + name + "!"
};

local simpleMath = 45 + percentOfExposure;

local blue = function(name) name + "!";

{
  "person1": person("Alice"),
  "person2": person("Bob"),
  "person3": blue("Charlie"),
  "fromOutsideScope": simpleMath
}

