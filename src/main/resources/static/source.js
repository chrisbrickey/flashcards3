$(document).ready(function () {

var colors=["#086375", "#0C91AC", "#546a76","#88a0a8","#679436","#A5BE00", "#000000", "#4C5B5C"];
var cardState;
var currentCard=0;
var store=new Array;

start();

function start(){
    $.ajax({
        type: 'GET',
        url: '/v1/deck',
        // data: {},
        cache: false,
        success: function(result) {
            playDeck(result)
        }
    });
}

function playDeck(deckData) {
  buildStore(deckData['cards']);
  showFlashCard();
}

function buildStore(cards) {
   for (var i=0; i<cards.length; i++) {
       store[i] = { question: cards[i]['question'], answer: cards[i]['answer']};
   }
}

function showFlashCard(){
 cardState=0;
 var color1=colors[0];
 var color2=colors[1]
 $("#cardArea").empty();
 $("#cardArea").append('<div id="cardTop" class="card">' + store[currentCard]['question'] + '</div>');
 $("#cardArea").append('<div id="cardBottom" class="card">' + store[currentCard]['answer'] + '</div>');
 $("#cardTop").css("background-color",color1);
 $("#cardBottom").css("background-color",color2);
 $("#cardBottom").css("top","200px");
 $("#cardArea").on("click",function(){
  if(cardState!=1){
   cardState=1;
   $("#cardTop").animate({top: "-=200"}, 150, function() {cardState=0;toggleTopToBottom();});
   $("#cardBottom").animate({top: "-=200"}, 150, function() {toggleBottomToTop();});
  }
 });
 currentCard++;
 $("#buttonArea").empty();
 $("#buttonArea").append('<div id="nextButton">next</div>');
 $("#nextButton").on("click",function(){
  if(currentCard<store.length){showFlashCard();}
  else{displayGameOver();}
 });
}

function toggleTopToBottom(){
 if($("#cardTop").position().top==-200){$("#cardTop").css("top","200px");};
}

function toggleBottomToTop(){
 if($("#cardBottom").position().top==-200){$("#cardBottom").css("top","200px");};
}

function displayGameOver(){
 $("#buttonArea").empty();
 $("#cardArea").empty();
 $("#cardArea").append('<div id="gameOver">game over</div>');
}
});