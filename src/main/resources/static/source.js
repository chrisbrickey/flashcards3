$(document).ready(function () {

var colorArray=["#086375", "#0C91AC", "#546a76","#88a0a8","#679436","#A5BE00", "#000000", "#4C5B5C"];
var cardState;
var cardStateExperiment;
var currentQuestion=0;
var currentQuestionExperiment=0;
var bank=new Array;
var bankExperiment=new Array;

loadExperiment();
// loadGame();

function loadExperiment(){
    $.ajax({
        type: 'GET',
        url: '/v1/deck',
        // data: {},
        cache: false,
        success: function(result) {
            console.log("I'm succeeding");
            console.log(result);
            launchFromCardController(result)
        }
    });

    // potentially more elegant way to write it
    // $.ajax({
    //     type: 'GET',
    //     url: '/v1/card',
    //     // data: {},
    //     cache: false,
    // }).done(launchFromCardController);
}

function loadGame(){
// TO USE CSV SOURCE:
  $.ajax({
    url: 'sample.csv',
    dataType: 'text',
  }).done(launchDirectFromCsv);

// TO USE JSON SOURCE:
// $.getJSON("input.json", function(data) {
//  buildBankFromJson(data);
//  showNewCard();
// })
}

function launchFromCardController(deckData) {
    var cards = deckData['cards']
    console.log("I'm inside launchFromCardController");
    console.log(cards);
    var cardCount = Object.keys(cards).length;
    console.log(cardCount);

    // var allRows = data.split(/\r?\n|\r/);
    //
    // // load all the data in the bank; start at index 1 to ignore the header row
    // for (var sourceRow=1; sourceRow<allRows.length; sourceRow++) {
    //
    //     // We are starting at index 1 in the csv data, but we want to start building the bank with index 0
    //     var bankRow = sourceRow - 1;
    //     bank[bankRow]=allRows[sourceRow].split(',');
    // }
    // $("#cardAreaExperiment").append('<div>' + cardData['category'] + '</div>');
    // $("#cardAreaExperiment").append('<div id="cardExperiment1" class="card">' + cardData['question'] + '</div>');
    // $("#cardAreaExperiment").append('<div id="cardExperiment2" class="card">' + cardData['answer'] + '</div>');

    console.log("I'm about to start the loop")
    for(i=0; i<cardCount; i++){
        console.log("im in the loop");
        console.log(i);
        var card = cards[i];
        console.log(card);
        bankExperiment[i]=[];
        bankExperiment[i][0]=card['question'];
        bankExperiment[i][1]=card['answer'];
    }

    // bankExperiment[0] = [];
    // bankExperiment[0][0] = cardData['question'];
    // bankExperiment[0][1] = cardData['answer'];
    console.log("I'm the bankExperiment");
    console.log(bankExperiment);
    showNewCardExperiment();
}

function showNewCardExperiment(){
    cardStateExperiment=0;
    var color1=colorArray[0];
    var color2=colorArray[1]
    $("#cardAreaExperiment").empty();
    // console.log(currentQuestionExperiment);
    $("#cardAreaExperiment").append('<div id="card1" class="card">' + bankExperiment[currentQuestionExperiment][0] + '</div>');
    $("#cardAreaExperiment").append('<div id="card2" class="card">' + bankExperiment[currentQuestionExperiment][1] + '</div>');
    $("#card1").css("background-color",color1);
    $("#card2").css("background-color",color2);
    $("#card2").css("top","200px");
    $("#cardAreaExperiment").on("click",function(){
        if(cardStateExperiment!=1){
            cardStateExperiment=1;
            //togglePosition();
            $("#card1").animate({top: "-=200"}, 150, function() {cardStateExperiment=0;togglePosition();});
            $("#card2").animate({top: "-=200"}, 150, function() {togglePosition2();});
        }//if
    });//click function
    currentQuestion++;
    $("#buttonAreaExperiment").empty();
    $("#buttonAreaExperiment").append('<div id="nextButton">next card exp</div>');
    $("#nextButtonExperiment").on("click",function(){
        if(currentQuestionExperiment<bank.length){showNewCardExperiment();}
        else{displayFinalMessageExperiment();}
    });//click function
}

function launchDirectFromCsv(data) {
  buildBankFromCsv(data);
  showNewCard();
}

function showNewCard(){
 cardState=0;
 var color1=colorArray[0];
 var color2=colorArray[1]
 $("#cardArea").empty();
 $("#cardArea").append('<div id="card1" class="card">' + bank[currentQuestion][0] + '</div>');
 $("#cardArea").append('<div id="card2" class="card">' + bank[currentQuestion][1] + '</div>');
 $("#card1").css("background-color",color1);
 $("#card2").css("background-color",color2);
 $("#card2").css("top","200px");
 $("#cardArea").on("click",function(){
  if(cardState!=1){
   cardState=1;
   //togglePosition();
   $("#card1").animate({top: "-=200"}, 150, function() {cardState=0;togglePosition();});
   $("#card2").animate({top: "-=200"}, 150, function() {togglePosition2();});
  }//if
 });//click function
 currentQuestion++;
 $("#buttonArea").empty();
 $("#buttonArea").append('<div id="nextButton">next card</div>');
 $("#nextButton").on("click",function(){
  if(currentQuestion<bank.length){showNewCard();}
  else{displayFinalMessage();}
 });//click function
}

function togglePosition(){
 if($("#card1").position().top==-200){$("#card1").css("top","200px");};
}

function togglePosition2(){
 if($("#card2").position().top==-200){$("#card2").css("top","200px");};
}

function displayFinalMessage(){
 $("#buttonArea").empty();
 $("#cardArea").empty();
 $("#cardArea").append('<div id="finalMessage">end of deck</div>');
}

function displayFinalMessageExperiment(){
    $("#buttonAreaExperiment").empty();
    $("#cardAreaExperiment").empty();
    $("#cardAreaExperiment").append('<div id="finalMessageExperiment">end of deck (experimental)</div>');
}

function buildBankFromJson(data) {
// TODO: adjust this structure to json actually generated by controllers
//  questionlist is the top-level key in the json - see sample.json
//  cardfront and cardback are the next level nested keys
 for(i=0;i<data.questionlist.length;i++){
   bank[i]=[];
   bank[i][0]=data.questionlist[i].cardfront;
   bank[i][1]=data.questionlist[i].cardback;
  }
}

function buildBankFromCsv(data) {
 var allRows = data.split(/\r?\n|\r/);

 // load all the data in the bank; start at index 1 to ignore the header row
 for (var sourceRow=1; sourceRow<allRows.length; sourceRow++) {

  // We are starting at index 1 in the csv data, but we want to start building the bank with index 0
   var bankRow = sourceRow - 1;
   bank[bankRow]=allRows[sourceRow].split(',');
 }
}

});